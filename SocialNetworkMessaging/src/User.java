import java.io.Serializable;
import java.util.*;


public class User implements Serializable {
    private String handle;
    private String password;
    private ArrayList<User> friends;
    //private ArrayList<Chat> chats;
    private int numFriends;

    public User(String handle, String password) {
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
}
