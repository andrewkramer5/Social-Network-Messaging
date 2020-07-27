import java.io.Serializable;
import java.util.*;


public class User implements Serializable {
    private static int id = 1; //unique identifier
    private String handle;
    private String password;
    private ArrayList<User> friends;
    private ArrayList<Chat> chats;
    private int numFriends;

    public User(String handle, String password) {
        id++;
        this.handle = handle;
        this.password = password;
        friends = new ArrayList<>();
        chats = new ArrayList<>();
        numFriends = 0;
    }

    public String getHandle() {
        return handle;
    }

    public String getPassword() {
        return password;
    }

    public void changeHandle(String handle) {
        this.handle = handle;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void addFriends(User u) {
        if(!friends.contains(u)) {
            friends.add(u);
            numFriends++;
        }
    }

    public void removeFriend(User u) {
        //add gui menu here
        if(friends.contains(u)) {
            friends.remove(u);
            numFriends--;
            //have gui print confirmation
        }
    }

    public void addChat(Chat c)  {
        if(!chats.contains(c)) {
            chats.add(c)
        }
    }
    public void removeChat(Chat c) {
        if(chats.contains(c)) {
            chats.remove(c);
        }
    }

}
