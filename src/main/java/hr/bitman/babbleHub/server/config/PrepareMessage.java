package hr.bitman.babbleHub.server.config;

import hr.bitman.babbleHub.buffer.BabbleBuffer;

public class PrepareMessage {

	private static final String VALID_CHARS="-\\w+&@#/%=~()|";
	private static final String VALID_NON_TERMINAL="?!:,.;";
	private static final String FIND_URLS = "\\(*https?://["+ VALID_CHARS + VALID_NON_TERMINAL + "]*[" +VALID_CHARS + "]";
	
	public static String createMessage(String userId, String message){
		StringBuilder sb = new StringBuilder();
		sb.append(message.replaceAll(FIND_URLS,  "<a href='$0'>$0</a>"));
		
		
		return "{\"userId\": \"" + userId + "\", \"message\": \"" + sb.toString() + "\"}";
	}
	
	public static String createMessage(BabbleBuffer buffer){
		
		String bufferedText = buffer.toString();
		return "{\"messages\": [" + bufferedText.substring(0, bufferedText.length()) + "]}"; 
		
	}

}
