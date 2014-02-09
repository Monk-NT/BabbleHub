package hr.bitman.babbleHub.server.config;

import com.google.gson.Gson;

import hr.bitman.babbleHub.buffer.BabbleBuffer;
import hr.bitman.babbleHub.server.messages.Message;

public class PrepareMessage {

	private static final String VALID_CHARS="-\\w+&@#/%=~()|";
	private static final String VALID_NON_TERMINAL="?!:,.;";
	private static final String FIND_URLS = "\\(*https?://["+ VALID_CHARS + VALID_NON_TERMINAL + "]*[" +VALID_CHARS + "]";
	
	public static String createMessage(String userId, String message){
		StringBuilder sb = new StringBuilder();
		sb.append(message.replaceAll(FIND_URLS,  "<a href='$0'>$0</a>"));
		
		Message m = new Message(userId, sb.toString());
		Gson gson = new Gson();
		
		return gson.toJson(m);
	}
	
	public static String createMessage(BabbleBuffer buffer){
		
		Gson gson = new Gson();
		
		return gson.toJson(buffer.getBuffer());
		
	}

}
