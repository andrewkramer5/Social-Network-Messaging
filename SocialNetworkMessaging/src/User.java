import java.io.Serializable;
import java.util.*;


public class User implements Serializable {
    private String handle;
    private String password;
    private ArrayList<User> friends;
    private ArrayList<Chat> chats;
    private int numFriends;



    public User(String handle, String password) {
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

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Chat> getChats() {
        return chats;
    }

    public void setChats(ArrayList<Chat> chats) {
        this.chats = chats;
    }

    public void addFriend(User u) {
        if(!friends.contains(u)) {
            friends.add(u);
            numFriends++;
        }
    }

    public void removeFriend(User u) {
        if(friends.contains(u)) {
            friends.remove(u);
            numFriends--;
        }
    }

    public void addChat(Chat c)  {
        if(!chats.contains(c)) {
            chats.add(c);
        }
    }
    public void removeChat(Chat c) {
        if(chats.contains(c)) {
            chats.remove(c);
        }
    }

}
