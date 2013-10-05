package hr.bitman.babbleHub;

import hr.bitman.babbleHub.config.ServerConfig;
import hr.bitman.babbleHub.redis.RedisSubscriber;
import hr.bitman.babbleHub.server.StatusChecker;
import hr.bitman.babbleHub.server.WebSocketPipelineFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.logging.Log4JLoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 
 * @author vsrdarevic
 *
 */
public class BabbleHub {

	private final int port;
	private final RedisSubscriber subscriber;
	
	
	
	public BabbleHub() {
		this.port = ServerConfig.getConfig().getPort();
		this.subscriber = RedisSubscriber.getInstance(); 
	}
	/**
	 * Runs the BabbleHub
	 */
	public void run(){

	    InternalLoggerFactory.setDefaultFactory(new Log4JLoggerFactory());


		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(
															Executors.newCachedThreadPool(), 
															Executors.newCachedThreadPool()));
		bootstrap.setPipelineFactory(new WebSocketPipelineFactory());
		
		bootstrap.bind(new InetSocketAddress(port));
		
		System.out.println("Server started at http://localhost:" + port);
		try{
			final Jedis subJed = new Jedis(ServerConfig.getConfig().getRedisLocation());
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try{
						subJed.subscribe(subscriber, "chat");
					} catch(JedisConnectionException e){
						StatusChecker.getInstance().redisIsDown();
					}
				}
			}).start();
		} catch(JedisConnectionException e){
			StatusChecker.getInstance().redisIsDown();
		}
		
	}
	
}
