package hr.bitman.babelHub.server;

import hr.bitman.babelHub.config.ServerConfig;
import hr.bitman.babelHub.redis.RedisSubscriber;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import redis.clients.jedis.Jedis;

/**
 * Main entry point into application
 * @author vsrdarevic
 *
 */
public class BabelHub {

	private final int port = 8080;
	private final static RedisSubscriber subscriber = RedisSubscriber.getInstance();
	public void run(){
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
															Executors.newCachedThreadPool(), 
															Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new WebSocketPipelineFactory());
		
		bootstrap.bind(new InetSocketAddress(port));
		System.out.println("Server started at http://localhost:" + port);
		
	}
	
	public static void main(String[] args) {
		BabelHub babel = new BabelHub();
		babel.run();
		final Jedis subJed = new Jedis(ServerConfig.getConfig().getRedisLocation());
	   
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				subJed.subscribe(subscriber, "chat");
				
			}
		}).start();
	}
}
