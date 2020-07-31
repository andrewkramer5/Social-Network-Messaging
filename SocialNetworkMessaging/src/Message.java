import java.io.Serializable;

/*
 * Message class consists of two fields, a constructor, and getters and setters. It
 * represents a message, so it has the message content and the handle of the user who
 * send the message.
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @authors Evan Hendrich, Raj Karra
 * @version July 31, 2020
 */

public class Message implements Serializable {
    private String handle; // handle of user who sent the message
    private String content; // the message sent

    /* 
     * constructor
     * 
     * @param String handle, handle of user sending message
     * 
     * @param String content, the message sent
     */
    public Message(String handle, String content) {
        this.handle = handle;
        this.content = content;
    } // Message

    public String getHandle() {
        return handle;
    } // getHandle

    public void setHandle(String handle) {
        this.handle = handle;
    } // setHandle

    public String getContent() {
        return content;
    } // getContent

    public void setContent(String content) {
        this.content = content;
    } // setContent
}

