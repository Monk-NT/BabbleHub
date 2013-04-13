package hr.bitman.babelHub.redis;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import redis.clients.jedis.JedisPubSub;

public class RedisSubscriber extends JedisPubSub{

	private ChannelGroup channelGroup = new DefaultChannelGroup();
	private static RedisSubscriber instance = null;

	private RedisSubscriber(){
		
	}
	public static RedisSubscriber getInstance(){
		if (instance == null){
			instance = new RedisSubscriber();
		}
		return instance;
	}
	@Override
	public void onMessage(String channel, String msg) {
		channelGroup.write(new TextWebSocketFrame(msg));
		
	}

	@Override
	public void onPMessage(String arg0, String arg1, String arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPUnsubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSubscribe(String arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUnsubscribe(String arg0, int arg1) {
		
	}
	
	public void addChannel(Channel channel){
		this.channelGroup.add(channel);
	}

	
	
	
	
}
