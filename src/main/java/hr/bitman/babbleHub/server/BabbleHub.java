package hr.bitman.babbleHub.server;

import hr.bitman.babbleHub.config.ServerConfig;
import hr.bitman.babbleHub.redis.RedisSubscriber;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import redis.clients.jedis.Jedis;

/**
 * Main entry point into application
 * @author vsrdarevic
 *
 */
public class BabbleHub {

	private final int port = 8080;
	private final static RedisSubscriber subscriber = RedisSubscriber.getInstance();
	public void run(){
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
															Executors.newCachedThreadPool(), 
															Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new WebSocketPipelineFactory());
		
		bootstrap.bind(new InetSocketAddress(port));
		BasicConfigurator.configure();
		System.out.println("Server started at http://localhost:" + port);
		
	}
	
	public static void main(String[] args) {
		PropertyConfigurator.configure(BabbleHub.class.getClassLoader().getResourceAsStream("log4j.properties"));
		BabbleHub babel = new BabbleHub();
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
