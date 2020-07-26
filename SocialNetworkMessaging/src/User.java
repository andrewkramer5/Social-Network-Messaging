import java.util.ArrayList;

public class User {
	private String handle;
    private String password;
    private ArrayList<User> friends;
    
    public User(String handle, String password, ArrayList<User> friends) {
    	this.handle = handle;
    	this.password = password;
    	this.friends = friends;
    }

	public String getHandle() {
		return handle;
	}

	public String getPassword() {
		return password;
	}

	public ArrayList<User> getFriends() {
		return friends;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFriends(ArrayList<User> friends) {
		this.friends = friends;
	}
    
    
}
