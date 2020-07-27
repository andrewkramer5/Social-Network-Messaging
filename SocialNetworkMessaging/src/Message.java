public class Message {
    private String handle;
    private String content;

    public Message(String handle, String content) {
        this.handle = handle;
        this.content = content;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
