import java.io.Serializable;

/*
 * Packet class is used to send data between server and client. The data contained
 * in each packet varies depending on what data the server will need to carry out
 * a specific task. All packets being sent to the server have an identifer which
 * tells the server what to do with the data. Only the fields that need to be
 * initialized, are changed when creating a packet object, hence there are multiple
 * constructors.
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @authors Evan Hendrich, Raj Karra
 * @version July 31, 2020
 */

public class Packet implements Serializable {
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
    
    
    
	/*Constructor used for
	 * deleteChat	(option = chatName), the name of a chat
	 * signIn		(option = password), a user's password
	 * addUser		(option = password), a user's password
	 * changePassword(option = password), a user's password
	 * addFriend		(option = friendHandle), handle of a new friend
	 * changeHandle  (option = newHandle), updated handle of user
	 * removeFriend	 (option = friendHandle), handle of friend to be removed
     * 
     * @param String identifier, the name of the method to be called in server
     * 
     * @param String handle, the handle of the user sending the packet
     * 
     * @param String option, varies by identifier (descriptions above)
     */
    public Packet(String identifier, String handle, String option) {
    	this.identifier = identifier;
    	this.handle = handle;
    	
    	if (identifier.equals("deleteChat")) {
    		
    		this.chatName = option;
    		
    	} else if (identifier.equals("signIn") || identifier.equals("addUser")
    			|| identifier.equals("changePassword")) {
    		
    		this.password = option;
    		
    	} else if (identifier.equals("addFriend") || identifier.equals("removeFriend")) {
    		this.friendHandle = option;
    	} else if (identifier.equals("changeHandle")) {
    		this.newHandle = option;
    	}
    } //Packet
    
    /*
     * Constructor used for
     * changeChatName
     * 
     * @param String identifier, the name of the method to be called in server
     * 
     * @param String handle, the handle of the user sending the packet
     * 
     * @param String chatName, the name of a chat
     * 
     * @param String newChatName, the updated name of the chat
     */
    public Packet(String identifier, String handle, String chatName, String newChatName) {
    	this.identifier = identifier;
    	this.handle = handle;
    	this.chatName = chatName;
    	this.newChatName = newChatName;
    } // Packet
    
    
    /*
     * Constructor used for
     * deleteUser
     * update
     * 
     * @param String identifier, the name of the method to be called in server
     * 
     * @param String handle, the handle of the user sending the packet
     */
    public Packet(String identifier, String handle) {
    	this.identifier = identifier;
    	this.handle = handle;
    } //Packet
    
    
    /*
     * Constructor used for
     * deleteMessage
     * addMessage
     * 
     * @param String identifier, the name of the method to be called in server
     * 
     * @param String chatName, the name of a chat
     * 
     * @param Message message, a message to be added to or deleted from a chat
     */    
    public Packet(String identifier, String chatName, Message message) {
    	this.identifier = identifier;
    	this.chatName = chatName;
    	this.message = message;
    } // Packet
    
    
    /*
     * Constructor used for
     * editMessage
     * 
     * @param String identifier, the name of the method to be called in server
     * 
     * @param String chatName, the name of a chat
     * 
     * @param Message message, the edited message
     * 
     * @param Message oldMessage, the messae to be edited
     */  
    public Packet(String identifier, String chatName, Message message, Message oldMessage) {
    	this.identifier = identifier;
    	this.chatName = chatName;
    	this.message = message;
    	this.oldMessage = oldMessage;
    } // Packet
    
    /*
     * Constructor used for
     * addChat
     * If the user does not want to assign a chat name, set the String
     * chatName to "" (a blank string)
     * 
     * @param String identifier, the name of the method to be called in server
     * 
     * @param String[] handles, array of users' handles in the chat
     * 
     * @param String chatName, the name of the chat, set to blank String if
     * user wishes to use default chatName
     */
    public Packet(String identifier, String[] handles, String chatName) {
    	this.identifier = identifier;
    	this.handles = handles;
    	this.chatName = chatName;
    } // Packet
    
    
    /*
     * Constructor used for
     * verifySignIn
     * verifyNewUser
     * verifyNewChat
     * verifyChangeUsernameverify
     * ChangeChatName
     * verifyAddFriend
     * 
     * @param boolean verified, tells if an action was successful or not
     * 
     * @param String description, provides description of what went wrong
     * or right
     */
    public Packet(boolean verified, String description) {
    	this.verified = verified;
    	this.description = description;
    } // Packet
    
    
    
    
    public String getIdentifier() {
		return identifier;
	} // getIdentifier


	public String getHandle() {
		return handle;
	} // getHandle


	public String getNewHandle() {
		return newHandle;
	} // getNewHandle


	public String getPassword() {
		return password;
	} // getPassword


	public String getChatName() {
		return chatName;
	} // getChatName


	public String getNewChatName() {
		return newChatName;
	} // getNewChatName


	public Message getMessage() {
		return message;
	} // getMessage


	public Message getOldMessage() {
		return oldMessage;
	} // getMessage


	public String[] getHandles() {
		return handles;
	} // getHandles


	public boolean isVerified() {
		return verified;
	} // isVerified


	public String getDescription() {
		return description;
	} // getDescription
	
	public String getFriendHandle() {
		return friendHandle;
	} // getFriendHandle
    
}