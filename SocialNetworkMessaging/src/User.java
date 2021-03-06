import java.io.Serializable;
import java.util.*;

/**
 * User class holds all information that one user of the WSC needs. It contains all
 * fields necessary then getters and setters for those fields. It also allows for the
 * addition and removal of friends as well as chats.
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Evan Hendrich
 * @author Raj Karra
 * @version July 31, 2020
 */

public class User implements Serializable {
    private String handle; // the user's handle or display name (like a username)
    private String password; // the user's password
    private ArrayList<User> friends; // ArrayList of the user's friends
    private ArrayList<Chat> chats; // ArrayList of the chats that the user is in
    private int numFriends; // the number of friends that the user has


    /*
     * Constructor
     * 
     * Creates new user with no friends or chats, but initialized handle and password
     * 
     * @param String handle, the user's handle
     * 
     * @param String password, the user's password
     */
    public User(String handle, String password) {
        this.handle = handle;
        this.password = password;
        friends = new ArrayList<>();
        chats = new ArrayList<>();
        numFriends = 0;
    } // User
    
    /*
     * empty constructor
     */
    public User() {
    	
    } // User

    public String getHandle() {
        return handle;
    } // getHandle

    public String getPassword() {
        return password;
    } // getPassword

    public void changeHandle(String newHandle) {
        this.handle = newHandle;
    } // changeHandle

    public void changePassword(String newPassword) {
        this.password = newPassword;
    } // changePassword

    public ArrayList<User> getFriends() {
        return friends;
    } // getFriends

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    } // setFriends

    public ArrayList<Chat> getChats() {
        return chats;
    } // getChats

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    } // setChats

    /* 
     * Add a friend to the user's friends list and increase
     * their friend count
     * 
     * @param User u, the user to be added to the friends list
     */
    public void addFriend(User u) {
        friends.add(u);
        
        numFriends++;
    } // addFriend
    
    /*
     * Remove a friend from the user's friend list
     */
    public boolean removeFriend(User u) {
        for (User friend : friends) {
        	if (friend.getHandle().equals(u.getHandle())) {
        		friends.remove(friend);
        		return true;
        	} // end if
        }  // end for
        return true;
    } // removeFriend
    
    /*
     * add a chat to the user's chat list
     * 
     * @param Chat c, the chat to be added
     */
    public void addChat(Chat c)  {
        chats.add(c);
    } // addChat
    
    /*
     * remove a chat from the user's chat list
     * 
     * @param Chat c, the chat to be removed
     */
    public void removeChat(Chat c) {
    	Chat removed = null;
        for (Chat chat : chats) {
        	if (chat.getChatName().equals(c.getChatName())) {
        		removed = chat;
        	} // end if
        } // end for
        if (removed != null)
        	chats.remove(removed);
    } // removeChat
} // User