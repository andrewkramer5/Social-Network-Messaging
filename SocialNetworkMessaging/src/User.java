import java.io.Serializable;
import java.util.*;


public class User implements Serializable {
    private static int id = 1; //unique identifier
    private String handle;
    private String password;
    private ArrayList<User> friends;
    //private ArrayList<Chat> chats;
    private int numFriends;

    public User(String handle, String password) {
        id++;
        this.handle = handle;
        this.password = password;
        friends = new ArrayList<>();
        numFriends = 0;
    }

    public String getHandle() {
        return handle;
    }

    public String getPassword() {
        return password;
    }

    public void changeHandle(String handle) {
        //gui menu
        this.handle = handle;
        //confirmation
    }

    public void changePassword(String password) {
        //gui menu
        this.password = password;
        //confirmation
    }

    private void addFriends(User u) {
        //add gui menu here
        friends.add(u);
        numFriends++;
        //show gui confirmation
    }

    private void removeFriend(User u) {
        //add gui menu here
        if(friends.contains(u)) {
            friends.remove(u);
            numFriends--;
            //have gui print confirmation
        }
        else {
            //have gui go back to main menu
        }
    }
}
