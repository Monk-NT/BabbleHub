package hr.bitman.babelHub.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Server config singleton class used to load resources
 * @author vsrdarevic
 *
 */
public class ServerConfig {

	private Properties props = new Properties();
	
	private static ServerConfig instance = null;
	

	
	private ServerConfig(){
		InputStream is = getClass().getResourceAsStream("/configuration/server.properties");
		try {
			props.load(is);
		} catch (IOException e) {
			System.out.println("Can not load resource");
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
