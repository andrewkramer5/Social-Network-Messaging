import java.util.ArrayList;
import java.io.Serializable;

public class Chat implements Serializable{
	ArrayList<Message> chatContent;
	ArrayList<User> chatMembers;
	String chatName;
	
	public Chat(ArrayList<User> members, String chatName) {
		chatContent = new ArrayList<Message>();
		
		ArrayList<User> chatMembers = new ArrayList<User>();
		
		boolean isEmpty = chatName.equals("");
		
		String defaultChatName = "";
		
		for (User u : members) {
			
			chatMembers.add(u);
			
			if (isEmpty) {
				
				defaultChatName += u.getHandle() + ", ";
			}
			
		}
		
		this.chatMembers = chatMembers;
		
		if (isEmpty) {
			this.chatName = defaultChatName.substring(0, defaultChatName.length() - 2);
		} else {
			this.chatName = chatName;
		}
		
	}
	
	public void addMember(User user) {
		chatMembers.add(user);
	}
	
	public void deleteMember(User user) {
		for (User u : chatMembers) {
			if (u.getHandle().equals(user.getHandle())) {
				chatMembers.remove(u);
			}
		}
		
		if (chatName.contains(user.getHandle() + ", ")) {
			chatName.replace(user.getHandle() + ", ",  "");
		}
	}
	
	public void addMessage(Message message) {
		chatContent.add(message);
	}
	
	public void deleteMessage(Message message) {
		chatContent.remove(message);
	}
	
	public void editMessage(Message message, Message revised) {
		for (Message m : chatContent) {
			if (m.equals(message)) {
				m = revised;
			}
		}
	}
	
	public ArrayList<Message> getChatContent() {
		return chatContent;
	}
	
	public ArrayList<User> getChatMembers() {
		return chatMembers;
	}
	
	public String getChatName() {
		return chatName;
	}
	
	public void setChatName(String name) {
		this.chatName = name;
	}
}
