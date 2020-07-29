import java.io.Serializable;

public class Packet implements Serializable{
    private String identifier; // String indicating which method to invoke on the packet
    private String handle; // String indicating a user's handle/username
    private String newHandle; // String indicating a new handle of a user
    private String friendHandle; // String indicating friend's handle
    private String password; // String indicating user's password
    private String chatName; // String indicating name of a Chat object
    private String newChatName; // String indicating a new chat name
    private Message message; // Message object (new message or edited message)
    private Message oldMessage; // Message object (a message to be deleted or edited)
    private String[] handles; // String array of handles consisting of users in a chat
    private boolean verified; // Boolean which is true if an action was successfully completed
    private String description; // String that describes the results of an attempted server interaction
    
    
    
	//Constructor used for:
    //deleteChat	(option = chatName)
    //signIn		(option = password)
    //addUser		(option = password)
    //changePassword(option = password)
    //addFriend		(option = friendHandle)
    public Packet(String identifier, String handle, String option) {
    	this.identifier = identifier;
    	this.handle = handle;
    	
    	if (identifier.equals("deleteChat")) {
    		
    		this.chatName = option;
    		
    	} else if (identifier.equals("signIn") || identifier.equals("addUser")
    			|| identifier.equals("changePassword")) {
    		
    		this.password = option;
    		
    	} else if (identifier.equals("addFriend")) {
    		this.friendHandle = option;
    	}
    }
    
    //Constructor used for:
    //changeChatName
    public Packet(String identifier, String handle, String chatName, String newChatName) {
    	this.identifier = identifier;
    	this.handle = handle;
    	this.chatName = chatName;
    	this.newChatName = newChatName;
    }
    
    
    //Constructor used for
    //deleteUser		(option = handle)
    //changeHandle		(option = newHandle)
    //update			(option = handle)
    public Packet(String identifier, String option) {
    	this.identifier = identifier;
    	if (identifier.equals("deleteUser") || identifier.equals("update")) {
    		this.handle = option;    		
    	} else if (identifier.equals("changeHandle")) {
    		this.newHandle = option;
    	}
    }
    
    
    //Constructor used for
    //deleteMessage
    //addMessage
    public Packet(String identifier, String chatName, Message message) {
    	this.identifier = identifier;
    	this.chatName = chatName;
    	this.message = message;
    }
    
    //Constructor used for
    //editMessage
    public Packet(String identifier, Message message, Message oldMessage) {
    	this.identifier = identifier;
    	this.message = message;
    	this.oldMessage = oldMessage;
    }
    
    //Constructor used for
    //addChat
    //If the user does not want to assign a chat name, set the String
    //chatName to "" (a blank string)
    public Packet(String identifier, String[] handles, String chatName) {
    	this.identifier = identifier;
    	this.handles = handles;
    	this.chatName = chatName;
    }
    
    
    //Constructor used for
    //verifySignIn		(when unsuccessful)
    //verifyNewUser		(when unsuccessful)
    //verifyNewChat;
    //verifyChangeUsername
    //verifyChangeChatName
    //verifyAddFriend
    public Packet(boolean verified, String description) {
    	this.verified = verified;
    	this.description = description;
    }
    
    
    
    
    public String getIdentifier() {
		return identifier;
	}


	public String getHandle() {
		return handle;
	}


	public String getNewHandle() {
		return newHandle;
	}


	public String getPassword() {
		return password;
	}


	public String getChatName() {
		return chatName;
	}


	public String getNewChatName() {
		return newChatName;
	}


	public Message getMessage() {
		return message;
	}


	public Message getOldMessage() {
		return oldMessage;
	}


	public String[] getHandles() {
		return handles;
	}


	public boolean isVerified() {
		return verified;
	}


	public String getDescription() {
		return description;
	}
	
	public String getFriendHandle() {
		return friendHandle;
	}
    
}