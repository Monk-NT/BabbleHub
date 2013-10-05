package hr.bitman.babbleHub.server;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

public class StatusChecker {
	private final static InternalLogger log = InternalLoggerFactory.getInstance(StatusChecker.class);
	private int status = 1;
	private static StatusChecker instance = null;
	
	private StatusChecker(){
		status = 1;
	}
	
	public static StatusChecker getInstance(){
		log.info("Creating StatusChecker instance");
		if (instance==null){
			instance = new StatusChecker();
		}
		return instance;
	}
	
	public  ChannelBuffer printStatus(){
		log.info("Recieved printStatus request, printing status");
		
		return ChannelBuffers.copiedBuffer(("{\"redisStatus\": " + status + "}").getBytes());
		
	}
	
	public void redisIsDown(){
		this.status = -1;
	}
}
