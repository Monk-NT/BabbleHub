package hr.bitman.babbleHub.server.messages;

public class Message {

	String userId;
	String message;
	
	public Message(){
	}
	public Message(String userId, String message){
		this.userId = userId;
		this.message = message;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
