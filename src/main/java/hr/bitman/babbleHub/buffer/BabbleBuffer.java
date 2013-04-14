package hr.bitman.babbleHub.buffer;

/**
 * Class used to buffer the chat
 * @author vsrdarevic
 *
 */
public class BabbleBuffer {

	private String[] buffer;
	
	private final int BUF_SIZE = 10;
	private int cur = 0;
	
	public BabbleBuffer(){
		buffer = new String[BUF_SIZE];
	}
	
	public BabbleBuffer(String chatLine){
		super();
		buffer[cur] = chatLine;
		cur++;
	}
	
	public void addLine(String chatLine){
		if (cur == BUF_SIZE){
			cur = 0;
			setToNull();
		}
		buffer[cur] = chatLine;
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
	
	private void setToNull(){
		for (int i = 0; i < BUF_SIZE; i++){
			buffer[i] = null;
		}
	}
}
