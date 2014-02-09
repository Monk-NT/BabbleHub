package hr.bitman.babbleHub.buffer;

import hr.bitman.babbleHub.server.messages.Message;

/**
 * Class used to buffer the chat
 * @author vsrdarevic
 *
 */
public class BabbleBuffer {

	private Message[] buffer;
	
	private final int BUF_SIZE = 10;
	private int cur = 0;
	
	public BabbleBuffer(){
		buffer = new Message[BUF_SIZE];
	}
	
	public BabbleBuffer(String userId, String message){
		super();
		buffer[cur] = new Message(userId, message);
		cur++;
	}
	
	public void addLine(String userId, String message){
		if (cur == BUF_SIZE){
			cur = 0;
			setToNull();
		}
		buffer[cur] = new Message(userId, message);
		cur++;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < cur; i++){
			sb.append(buffer[i] + "\n");
		}
		return sb.toString();
	}
	
	public Message[] getBuffer(){
		return this.buffer;
	}
	
	private void setToNull(){
		for (int i = 0; i < BUF_SIZE; i++){
			buffer[i] = null;
		}
	}
}
