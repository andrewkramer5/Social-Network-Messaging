import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.Test;

public class UserTest {
    @Test(timeout = 1000)
    public void getHandle() {
        User user = new User("rajesh", "123");
        assertEquals("rajesh", user.getHandle());
    }
    @Test(timeout = 1000)
    public void getPassword() {
        User user = new User("ehendrich", "123");
        assertEquals("123", user.getPassword());
    }
    @Test(timeout = 1000)
    public void changeHandle() {
        User user = new User("rajesh", "123");
        user.changeHandle("Robert");
        assertEquals("Robert", user.getHandle());
    }
    @Test(timeout = 1000)
    public void changePassword() {
        User user = new User("ehendrich", "123");
        user.changePassword("321");
        assertEquals("321", user.getPassword());
    }
    @Test(timeout = 1000)
    public void getFriends() {
        User user = new User();
        assertEquals(null, user.getFriends());
    }
    @Test(timeout = 1000)
    public void getFriends2() {
        User user = new User("Evan", "1234");
        user.addFriend(new User("Bob", "123"));
        assertEquals("Bob", user.getFriends().get(0).getHandle());
    }
    @Test(timeout = 1000)
    public void setFriends() {
        User user = new User("Evan", "1234");
        User friend = new User("Bob", "123");
        var friends = new ArrayList<User>();
        friends.add(friend);
        user.setFriends(friends);
        assertEquals("Bob", user.getFriends().get(0).getHandle());
    }
    @Test(timeout = 1000)
    public void getChats() {
        User user = new User();
        assertEquals(null, user.getChats());
    }
    @Test(timeout = 1000)
    public void getChats2() {
        User user = new User("Evan", "123");
        User friend = new User("Bob", "123");
        var users = new ArrayList<User>();
        users.add(user);
        users.add(friend);
        Chat c = new Chat(users, "Evan and Bob");
        user.addChat(c);
        assertEquals("Evan and Bob", user.getChats().get(0).getChatName());
    }
    @Test(timeout = 1000)
    public void setChats() {
        User user = new User("Evan", "1234");
        User friend = new User("Bob", "123");
        var friends = new ArrayList<User>();
        friends.add(friend);
        friends.add(user);
        Chat c = new Chat(friends, "Hi There");
        var chats = new ArrayList<Chat>();
        chats.add(c);
        user.setChats(chats);
        assertEquals("Hi There", user.getChats().get(0).getChatName());
    }
    @Test(timeout = 1000)
    public void addFriend() {
        User user = new User("Evan", "123");
        User friend = new User("friend", "123");
        user.addFriend(friend);
        assertEquals("friend", user.getFriends().get(0).getHandle());
    }
    @Test(timeout = 1000)
    public void removeFriend() {
    	User user = new User("Evan", "123");
        User friend = new User("friend", "123");
        user.addFriend(friend);
        user.removeFriend(friend);
        assertEquals(0, user.getFriends().size());
    }
    @Test(timeout = 1000)
    public void addChat() {
        User user = new User("Evan", "123");
        User friend = new User("Bob", "123");
        var friends = new ArrayList<User>();
        friends.add(user);
        friends.add(friend);
        Chat c = new Chat(friends, "Hi There");
        user.addChat(c);
        assertEquals("Hi There", user.getChats().get(0).getChatName());
    }
    @Test(timeout = 1000)
    public void removeChat() {
    	User user = new User("Evan", "123");
        User friend = new User("Bob", "123");
        var friends = new ArrayList<User>();
        friends.add(user);
        friends.add(friend);
        Chat c = new Chat(friends, "Hi There");
        user.addChat(c);
        user.removeChat(c);
        assertEquals(0, user.getChats().size());
    }
}