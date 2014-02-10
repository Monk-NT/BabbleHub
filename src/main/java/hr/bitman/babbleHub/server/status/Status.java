package hr.bitman.babbleHub.server.status;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

public class Status {
	private final static InternalLogger log = InternalLoggerFactory.getInstance(Status.class);
	private int status = 1;
	private static Status instance = null;
	
	private Status(){
		status = 1;
	}
	
	public static Status getInstance(){
		log.info("Creating StatusChecker instance");
		if (instance==null){
			instance = new Status();
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
