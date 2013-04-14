package hr.bitman.babbleHub.server;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.google.common.io.ByteStreams;

/**
 * Reading a file from disk and serving it.
 * @author vsrdarevic
 *
 */
public class ServeFile {
	private final static Logger log = Logger.getLogger(ServeFile.class);
	public static final String PATH = "web/";
	/**
	 * 
	 * Method for getting content from file
	 * 
	 * @param filename
	 * @return ChannelBuffer
	 */
	public static ChannelBuffer getContent(String filename) {
		 try {
			byte[] array = ByteStreams.toByteArray(ChannelBuffer.class.getClassLoader().getResourceAsStream(PATH + filename));
			return ChannelBuffers.copiedBuffer(array);
		} catch (IOException e) {
			log.error("Could not load content from file");
		}
		return null;
	}
	
}
