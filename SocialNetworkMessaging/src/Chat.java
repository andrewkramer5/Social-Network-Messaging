import java.util.ArrayList;
import java.io.Serializable;

/**
 * Chat class is used for creating and manipulating chat objects. Chat objects
 * consist of the messages in the chat, the members in the chat, and a chat name.
 * Chats can be created with or without a chat name, but if no chat name is provided
 * then it is automatically created from the names of the chat members.
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Evan Hendrich
 * @author Raj Karra
 * @version July 31, 2020
 */

public class Chat implements Serializable {
	ArrayList<Message> chatContent; // ArrayList of message objects that make up a chat
	ArrayList<User> chatMembers; // ArrayList of the users in the chat
	String chatName; // name of the chat
	
	
	/* 
	 * constructor
	 * 
	 * creates a new chat that contains all users specified in the parameter, a chat name
	 * that is either specified or created from the users' handles separated by commas, and
	 * an empty ArrayList of Message objects
	 * 
	 * @param ArrayList<User> members, users who are in the chat
	 * 
	 * @param String chatName, the name of the chat (set to "" if default name to be used)
	 */
	public Chat(ArrayList<User> members, String chatName) {
		chatContent = new ArrayList<Message>();
		
		ArrayList<User> tempChatMembers = new ArrayList<User>();
		
		boolean isEmpty = chatName.equals("");
		
		String defaultChatName = "";
		
		for (User u : members) {
			
			tempChatMembers.add(u);
			
			if (isEmpty) {
				
				defaultChatName += u.getHandle() + ", ";
			} // end if
			
		} // end for
		
		this.chatMembers = tempChatMembers;
		
		if (isEmpty) {
			this.chatName = defaultChatName.substring(0, defaultChatName.length() - 2);
		} else {
			this.chatName = chatName;
		} // end if/else
		
	} // Chat
	
	/*
	 * blank constructor
	 */
	public Chat() {
		
	}
	
	/*
	 * Add a member to the chat
	 * 
	 * @param User user, the user to be added
	 */
	public void addMember(User user) {
		chatMembers.add(user);
	} // addMember
	
	/*
	 * delete a member from the chat
	 * 
	 * @param User user, the user to be deleted
	 */
	public void deleteMember(User user) {
		User removed = null;
		for (User u : chatMembers) {
			
			if (u.getHandle().equals(user.getHandle())) {
				
				removed = u;
			} // end if
		} // end for
		
		chatMembers.remove(removed);
		
		if (chatName.contains(user.getHandle() + ", ")) {
			chatName.replace(user.getHandle() + ", ",  "");
		} // end if
	}
	
	/*
	 * add a message to a chat
	 * 
	 * @param Message message, the message to be added
	 */
	public void addMessage(Message message) {
		chatContent.add(message);
	} // addMessage
	
	/*
	 * delete a message from a chat
	 * 
	 * @param Message message, the message to be deleted
	 */
	public void deleteMessage(Message message) {
		Message removed = null;
		for (Message m : chatContent) {
			
			if (m.getHandle().equals(message.getHandle()) &&
					  m.getContent().equals(message.getContent())) {
				
				removed = m;
			} // end if
		} // end for
		chatContent.remove(removed);
	} // deleteMessage
	
	/* 
	 * edit a message in the chat
	 * 
	 * @param Message newMessage, the edited message
	 * 
	 * @param oldMessage, the message to be edited	
	 */
	public void editMessage(Message newMessage, Message oldMessage) {
		for (Message m : chatContent) {
			
			if (m.getHandle().equals(oldMessage.getHandle()) && 
					  m.getContent().equals(oldMessage.getContent())) {
				
				m.setContent(newMessage.getContent());
			} // end if
		} //end for
	} // editMessage
	
	public ArrayList<Message> getChatContent() {
		return chatContent;
	} // getChatContent
	
	public ArrayList<User> getChatMembers() {
		return chatMembers;
	} // getChatMembers
	
	public String getChatName() {
		return chatName;
	} // getChatName
	
	public void setChatName(String name) {
		this.chatName = name;
	} // setChatName
}
