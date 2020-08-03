import java.io.*;
import java.net.*;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.rules.Timeout;
import java.util.ArrayList;

/**
 * A set of test cases for class: Packet
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Evan Hendrich
 * @author Ho Jun Lee
 * @version July 31, 2020
 */

public class PacketTest {
    @Test(timeout = 1000)
    public void getIdentifier() {
        Packet packet = new Packet("hi", "a");
        assertEquals("hi", packet.getIdentifier());
    }
    @Test(timeout = 1000)
    public void getHandle() {
        Packet packet = new Packet("a", "handle", "a");
        assertEquals("handle", packet.getHandle());
    }
    @Test(timeout = 1000)
    public void getNewHandle() {
        Packet packet = new Packet("changeHandle", "a", "newHandle");
        assertEquals("newHandle", packet.getNewHandle());
    }
    @Test(timeout = 1000)
    public void getPassword() {
        Packet packet = new Packet("signIn", "a", "password");
        assertEquals("password", packet.getPassword());
    }
    @Test(timeout = 1000)
    public void getChatName() {
        Packet packet = new Packet("deleteChat", "a", "chat name");
        assertEquals("chat name", packet.getChatName());
    }
    @Test(timeout = 1000)
    public void getNewChatName() {
        Packet packet = new Packet("a", "a", "a", "new name");
        assertEquals("new name", packet.getNewChatName());
    }
    @Test(timeout = 1000)
    public void getMessage() {
        Packet packet = new Packet("a", "a", new Message("a", "message"));
        assertEquals("message", packet.getMessage().getContent());
    }
    @Test(timeout = 1000)
    public void getOldMessage() {
        Packet packet = new Packet("a", "a", new Message("a", "a"), new Message("a", "old message"));
        assertEquals("old message", packet.getOldMessage().getContent());
    }
    @Test(timeout = 1000)
    public void getHandles() {
        Packet packet = new Packet("a", new String[] {"Evan", "Bob"}, "a");
        assertEquals("Evan", packet.getHandles()[0]);
    }
    @Test(timeout = 1000)
    public void isVerified() {
        Packet packet = new Packet(true, "a");
        assertEquals(true, packet.isVerified());
    }
    @Test(timeout = 1000)
    public void getDescription() {
        Packet packet = new Packet(true, "description");
        assertEquals("description", packet.getDescription());
    }
    @Test(timeout = 1000)
    public void getFriendHandle() {
        Packet packet = new Packet("addFriend", "a", "friend handle");
        assertEquals("friend handle", packet.getFriendHandle());
    }
}