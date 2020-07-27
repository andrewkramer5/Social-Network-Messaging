import java.util.ArrayList;

public class Chat {
	ArrayList<Message> chatContent;
	ArrayList<String> chatMembers;
	
	public Chat(String[] names) {
		ArrayList<String> chatMembers = new ArrayList<String>();
		
		for (String s : names) {
			
			chatMembers.add(s);
			
		}
		
		this.chatMembers = chatMembers;
	}
	
	public void addMember(String name) {
		chatMembers.add(name);
	}
	
	public void deleteName(String name) {
		chatMembers.remove(name);
	}
	
	public void addMessage(Message message) {
		chatContent.add(message);
	}
	
	public void deleteMessage(Message message) {
		chatContent.remove(message);
	}
	
	public void editMessage(Message message, String revised) {
		for (Message m : chatContent) {
			if (m.equals(message)) {
				m.setContent(revised);
			}
		}
	}
	
	public ArrayList<Message> getChatContent() {
		return chatContent;
	}
	
	public ArrayList<String> getChatMembers() {
		return chatMembers;
	}
}
