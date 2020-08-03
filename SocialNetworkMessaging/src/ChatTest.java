import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

/**
 * A set of test cases for class: Chat
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Evan Hendrich
 * @author Ho Jun Lee
 * @version July 31, 2020
 */

public class ChatTest {
	User a = new User("Evan", "123");
	User b = new User("Bob", "123");
	User c = new User("Bill", "123");
	ArrayList<User> users = new ArrayList<User>();
	
	@Test(timeout = 1000)
    public void addMember() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        d.addMember(c);
        assertEquals(3, d.getChatMembers().size());
    }
	@Test(timeout = 1000)
    public void deleteMember() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        d.deleteMember(b);
        assertEquals(1, d.getChatMembers().size());
        assertEquals("Evan", d.getChatMembers().get(0).getHandle());
    }
	@Test(timeout = 1000)
    public void addMessage() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        d.addMessage(new Message("Evan", "Hello"));
        assertEquals("Hello", d.getChatContent().get(0).getContent());
    }
	@Test(timeout = 1000)
    public void deleteMessage() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        Message m = new Message("Evan", "Hello");
        d.addMessage(m);
        d.deleteMessage(m);
        assertEquals(0, d.getChatContent().size());
    }
	@Test(timeout = 1000)
    public void editMessage() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        Message m = new Message("Evan", "Hello");
        d.addMessage(m);
        d.editMessage(new Message("Evan", "Goodbye"), m);
        assertEquals("Goodbye", d.getChatContent().get(0).getContent());
    }
	@Test(timeout = 1000)
    public void getChatContent() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        Message m = new Message("Evan", "Hello");
        d.addMessage(m);
        assertEquals(1,  d.getChatContent().size());
        assertEquals("Hello", d.getChatContent().get(0).getContent());
    }
	@Test(timeout = 1000)
	public void getChatMembers() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        Message m = new Message("Evan", "Hello");
        d.addMessage(m);
        assertEquals(2,  d.getChatMembers().size());
        assertEquals("Evan", d.getChatMembers().get(0).getHandle());
    }
	@Test(timeout = 1000)
	public void getChatName() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        assertEquals("chat",  d.getChatName());
    }
	@Test(timeout = 1000)
	public void changeChatName() {
        users.add(a);
        users.add(b);
        Chat d = new Chat(users, "chat");
        d.setChatName("new name");
        assertEquals("new name",  d.getChatName());
    }
}
