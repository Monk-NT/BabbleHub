package hr.bitman.babbleHub.server;

import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.google.common.io.ByteStreams;


public class ServeFile {

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
			System.out.println("Could not load content from file");
		}
		return null;
	}
	
}
