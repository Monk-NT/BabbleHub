package hr.bitman.babelHub.redis;

import hr.bitman.babelHub.config.ServerConfig;
import redis.clients.jedis.Jedis;

/**
 * Redis publisher
 * @author Monk
 *
 */
public class RedisPublisher {

	private final Jedis publisher;
	
	
	private final String channel = "chat";
	
	public RedisPublisher(){
		publisher = new Jedis(ServerConfig.getConfig().getRedisLocation());
	}
	
	public void publish(String msg){
		publisher.publish(channel, msg);
	}
}
