package hr.bitman.babbleHub.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;


/**
 * Server config singleton class used to load resources
 * @author vsrdarevic
 *
 */
public class ServerConfig {

	private Properties props = new Properties();
	
	private static ServerConfig instance = null;
	private final static InternalLogger log = InternalLoggerFactory.getInstance(ServerConfig.class);

	
	private ServerConfig(){
		InputStream is = getClass().getResourceAsStream("/configuration/server.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			log.error("Can not load resource", e);
		}
	}
	
	public static ServerConfig getConfig(){
		if (instance == null){
			instance = new ServerConfig();
		}
		return instance;
	}
	
	
	public  String getRedisLocation(){
		return props.getProperty("redis.location");
	}
	
	public  String getProperty(String key){
		return props.getProperty(key);
	}
}
