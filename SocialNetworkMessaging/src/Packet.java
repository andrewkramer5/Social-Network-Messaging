public class Packet {
    private String identifier;
    private String message;

    public Packet(String identifier, String message) {
        this.identifier = identifier;
        this.message = message;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
