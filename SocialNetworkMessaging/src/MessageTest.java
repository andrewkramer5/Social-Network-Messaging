import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

/**
 * A set of test cases for class: Message
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Evan Hendrich
 * @author Ho Jun Lee
 * @version July 31, 2020
 */

public class MessageTest {
	@Test(timeout = 1000)
    public void getHandle() {
        Message m = new Message("handle", "a");
        assertEquals("handle", m.getHandle());
    }
	@Test(timeout = 1000)
    public void getContent() {
		Message m = new Message("a", "content");
        assertEquals("content", m.getContent());
    }
	@Test(timeout = 1000)
    public void setHandle() {
		Message m = new Message("handle", "a");
		m.setHandle("new handle");
        assertEquals("new handle", m.getHandle());
    }
	@Test(timeout = 1000)
    public void setContent() {
		Message m = new Message("a", "content");
		m.setContent("new content");
        assertEquals("new content", m.getContent());
    }
}
