package hr.bitman.babbleHub.server;

import hr.bitman.babbleHub.buffer.BabbleBuffer;
import hr.bitman.babbleHub.redis.RedisPublisher;
import hr.bitman.babbleHub.redis.RedisSubscriber;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.DefaultHttpResponse;
import org.jboss.netty.handler.codec.http.HttpHeaders;
import org.jboss.netty.handler.codec.http.HttpMethod;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.jboss.netty.handler.codec.http.HttpVersion;
import org.jboss.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import org.jboss.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import org.jboss.netty.util.CharsetUtil;

public class BabbleHubUpstreamHandler extends SimpleChannelUpstreamHandler {
	
	private final RedisPublisher publisher = new RedisPublisher();
	private RedisSubscriber subscriber = RedisSubscriber.getInstance();
	private WebSocketServerHandshaker handshaker;
	private static BabbleBuffer buffer = new BabbleBuffer();
	private final static Logger log = Logger.getLogger(BabbleHubUpstreamHandler.class);
	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
	
		Object msg = e.getMessage();
		if (msg instanceof HttpRequest){
			handleHttpRequests(ctx, (HttpRequest) msg);
		} else if (msg instanceof WebSocketFrame){
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}
	
	private void handleWebSocketFrame(ChannelHandlerContext ctx,
			WebSocketFrame frame) {
		
		if (frame instanceof CloseWebSocketFrame){
			handshaker.close(ctx.getChannel(), (CloseWebSocketFrame) frame);
			return;
		}
		

		if (!(frame instanceof TextWebSocketFrame)){
			throw new UnsupportedOperationException(frame.getClass().getName() + " not supported");
			
		}
		String request = ((TextWebSocketFrame) frame).getText();	
		String message = ctx.getChannel().getId() + ": " + request;
		
		buffer.addLine(message);
		
		publisher.publish(message);
		

		
	}

	private void handleHttpRequests(ChannelHandlerContext ctx, HttpRequest req) {
		
		if (req.getMethod() != HttpMethod.GET){
			log.info("Detected POST request - FORBIDDEN");
			sendHttpResponse(ctx, req, new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FORBIDDEN));
		}
		
		if ("/".equals(req.getUri())){
			log.info("New connection recieved - requesting index.html");
			HttpResponse res = new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
			ChannelBuffer content = ServeFile.getContent("index.html");
			res.setHeader(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
			HttpHeaders.setContentLength(res,content.readableBytes());
			res.setContent(content);
			sendHttpResponse(ctx, req, res);
			log.info("index.html sent");
			return;
		}
		if ("/favicon.ico".equals(req.getUri())){
			sendHttpResponse(ctx, req, new DefaultHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NOT_FOUND));
			return;
		}
		
		log.info("Handshake initiated");
		WebSocketServerHandshakerFactory handshakerFactory = new WebSocketServerHandshakerFactory("ws://" + req.getHeader(HttpHeaders.Names.HOST) +"/bableHub", null, false);
		handshaker = handshakerFactory.newHandshaker(req);
		if (handshaker == null){
			log.info("Handshake failed due to unsupported WebSocket version");
			handshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.getChannel());
		} else {
			
			handshaker.handshake(ctx.getChannel(), req).addListener(WebSocketServerHandshaker.HANDSHAKE_LISTENER);
			log.info("Handshake succeeded, adding channel to subscriber");
			log.debug("Sending buffered chat:" + buffer.toString());
			ctx.getChannel().write(new TextWebSocketFrame(buffer.toString()));
			subscriber.addChannel(ctx.getChannel());
			
		}
	}

	private void sendHttpResponse(ChannelHandlerContext ctx, HttpRequest req,
			HttpResponse res) {
		 
		if (res.getStatus().getCode() != 200){
			res.setContent(ChannelBuffers.copiedBuffer(res.getStatus().toString(),CharsetUtil.UTF_8));
			HttpHeaders.setContentLength(res, res.getContent().readableBytes());
		}
		ChannelFuture future = ctx.getChannel().write(res);
		if (HttpHeaders.isKeepAlive(req) || res.getStatus().getCode() != 200){
			future.addListener(ChannelFutureListener.CLOSE);
		}
		
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, ExceptionEvent arg1)
			throws Exception {
		super.exceptionCaught(arg0, arg1);
	}

}
