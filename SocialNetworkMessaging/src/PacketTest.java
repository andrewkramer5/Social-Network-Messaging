import java.io.*;
import java.net.*;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.rules.Timeout;
import java.util.ArrayList;

public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
        System.out.println("Excellent - Test ran successfully");
        } else {
        for (Failure failure : result.getFailures()) {
        System.out.println(failure.toString());
        }
        }
}

public class PacketTest {
    @Test(timeout = 1000)
    public void getIdentifier() {
        Packet packet = new Packet();
        assertEquals(null, packet.getIdentifier());
    }
    @Test(timeout = 1000)
    public void getHandle() {
        Packet packet = new Packet("abc", "123");
        assertEquals("abc", packet.getHandle());
    }
    @Test(timeout = 1000)
    public void getNewHandle() {
        Packet packet = new Packet("rajesh", "123");
        assertEquals("rajesh", packet.getNewHandle());
    }
    @Test(timeout = 1000)
    public void getPassword() {
        Packet packet = new Packet("rajesh", "123");
        assertEquals("123", packet.getPassword());
    }
    @Test(timeout = 1000)
    public void getChatName() {
        Packet packet = new Packet("raj_the_baller", "123");
        assertEquals("raj_the_baller", packet.getChatName());
    }
    @Test(timeout = 1000)
    public void getNewChatName() {
        Packet packet = new Packet("rajesh", "123");
        assertEquals("rajesh", packet.getChatName());
    }
    @Test(timeout = 1000)
    public void getMessage() {
        Packet packet = new Packet();
        assertEquals(null, packet.getMessage());
    }
    @Test(timeout = 1000)
    public void getOldMessage() {
        Packet packet = new Packet();
        assertEquals(null, packet.getOldMessage());
    }
    @Test(timeout = 1000)
    public void getHandles() {
        Packet packet = new Packet();
        assertEquals(null, packet.getHandles());
    }
    @Test(timeout = 1000)
    public void isVerified() {
        Packet packet = new Packet();
        assertEquals(null, packet.isVerified());
    }
    @Test(timeout = 1000)
    public void getDescription() {
        Packet packet = new Packet();
        assertNotNull(packet.getDescription());
    }
    @Test(timeout = 1000)
    public void getFriendHandle() {
        Packet packet = new Packet();
        assertNotNull(packet.getFriendHandle());
    }
}