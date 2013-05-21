package hr.bitman.babbleHub.server.config;

import hr.bitman.babbleHub.buffer.BabbleBuffer;

public class PrepareMessage {

	public static String createMessage(String userId, String message){
		return "{\"userId\": \"" + userId + "\", \"message\": \"" + message + "\"}";
	}
	
	public static String createMessage(BabbleBuffer buffer){
		
		String bufferedText = buffer.toString();
		return "{\"messages\": [" + bufferedText.substring(0, bufferedText.length()) + "]}"; 
		
	}

}
