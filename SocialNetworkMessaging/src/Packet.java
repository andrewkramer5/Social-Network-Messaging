public class Packet {
    private String identifier; // String indicating which method to invoke on the packet
    private String handle; // String indicating a user's handle/username
    private String newHandle; // String indicating a new handle of a user
    private String password; // String indicating user's password
    private String chatName; // String indicating name of a Chat object
    private String newChatName; // String indicating a new chat name
    private Message message; // Message object (new message or edited message)
    private Message oldMessage; // Message object (a message to be deleted or edited)
    private String[] handles; // String array of handles consisting of users in a chat
    private boolean verified; // Boolean which is true if an action was successfully completed
    private String description; // String that describes the results of an attempted server interaction
    private User user; // User object sent with verification messages to update 


	//Constructor used for:
    //deleteChat	(option = chatName)
    //signIn		(option = password)
    //addUser		(option = password)
    public Packet(String identifier, String handle, String option) {
    	this.identifier = identifier;
    	this.handle = handle;
    	
    	if (identifier.equals("deleteChat")) {
    		
    		this.chatName = option;
    		
    	} else if (identifier.equals("signIn") || identifier.equals("addUser")) {
    		
    		this.password = option;
    	}
    }
    
    
    //Constructor used for
    //deleteUser		(option = handle)
    //changeHandle		(option = newHandle)
    //changeChatName	(option = newChatName)
    //addFriend			(option = handle (of friend being added))
    //update			(option = handle)
    public Packet(String identifier, String option) {
    	this.identifier = identifier;
    	if (identifier.equals("deleteUser") || identifier.equals("addFriend") ||
    			identifier.equals("update")) {
    		this.handle = option;    		
    	} else if (identifier.equals("changeHandle")) {
    		this.newHandle = option;
    	} else if (identifier.equals("changeChatName")) {
    		this.newChatName = option;
    	}
    }
    
    
    //Constructor used for
    //deleteMessage
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
    public Packet(String identifier, String[] handles, String chatName) {
    	this.identifier = identifier;
    	this.handles = handles;
    	if (chatName == null) {
    		this.chatName = null;
    	}
    }
    
    
    //Constructor used for
    //verifyNewChat;
    //verifyChangeUsername
    //verifyChangeChatName
    //verifyAddFriend
    public Packet(String identifier, boolean verified, String description) {
    	this.identifier = identifier;
    	this.verified = verified;
    	this.description = description;
    }
    
    
    
    //Constructor used for
    //verifySignIn
    //verifyNewUser
    public Packet(String identifier, boolean verified, String description, User user) {
    	this.identifier = identifier;
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
    
}
