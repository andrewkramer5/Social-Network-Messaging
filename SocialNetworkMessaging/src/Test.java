import java.io.*;
import java.net.*;

public class Test {
	String tempHandle;
	User u;
	Client c;
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Socket s = new Socket("localhost", 4200);
		
		Client c = new Client(s);
		
		Test t = new Test(c);
		
		t.addFirstUser();
		
		t.addBadUser();
		
		t.addSecondUser();
		
		t.addFirstFriend();
		
		t.addNonExistentFriend();
		
		t.addAlreadyFriend();
		
		t.addDirectMessageWithoutName();
		
		t.addDirectMessageWithName();
		
		t.addChatWithAlreadyExistingDefaultName();
		
		t.addChatWithAlreadyExistingCustomName();
		
		t.signIn();
		
		t.signInWithNonExistingUsername();
		
		t.signInWithIncorrectPassword();
		
		t.showData();
		
		//for some reason this method (as well as addMessage) do not instantly
		//update on the server. However, if you run the Test class again, it works
		//and other data (users etc...) are saved as well, but the other data saves
		//the first time around. It is confusing. Message me if you have any questions
		//but right now I don't get why its happening.
		t.changePassword();
		
		t.showData();
		
		t.addMessageFromFirstUser();
		
		t.addMessageFromSecondUser();
		
		t.addAnotherMessageFromFirstUser();
		
		t.addAnotherMessageFromSecondUser();
		
		t.showData();
	}
	
	public Test(Client c) {
		this.c = c;
	}
	
	public void addFirstUser() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addUser", "ehendrich", "broccoli11");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempt to create account with username ehendrich");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addBadUser() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addUser", "ehendrich", "brocco");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempt to recreate account with username ehendrich");
		
		if (verify.isVerified()) {			
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addSecondUser() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addUser", "raj_the_baller", "balling_every_day");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempt to create account with username raj_the_baller");
		
		if (verify.isVerified()) {			
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addFirstFriend() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addFriend", "ehendrich", "raj_the_baller");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("ehendrich attempted to add raj_the_baller as a friend");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addNonExistentFriend() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addFriend", "ehendrich", "raj_the_lame_guy");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("ehendrich attempted to add raj_the_lame_guy as a friend");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addAlreadyFriend() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addFriend", "ehendrich", "raj_the_baller");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("ehendrich attempted to add raj_the_baller as a friend");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addDirectMessageWithoutName() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"}, 
				"");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempt to create chat with default name between "
				+ "ehendrich and raj_the_baller");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addDirectMessageWithName() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"}, 
				"Coding buds");
		
		c.sendPacket(p);
		
		//Code breaks here
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempt to create chat called Coding buds between"
				+ " ehendrich and raj_the_baller");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	
	public void addChatWithAlreadyExistingDefaultName() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"}, 
				"");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempt to recreate chat with default name between"
				+ " ehendrich and raj_the_baller");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void addChatWithAlreadyExistingCustomName() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"}, 
				"Coding buds");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempt to recreate chat called Coding buds between"
				+ " ehendrich and raj_the_baller");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void signIn() throws IOException, ClassNotFoundException {
		Packet p = new Packet("signIn", "ehendrich", "broccoli11");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempted to sign in with correct information");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void signInWithNonExistingUsername() throws IOException, ClassNotFoundException {
		Packet p = new Packet("signIn", "bob", "broccoli11");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempted to sign in with nonexistent username");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void signInWithIncorrectPassword() throws IOException, ClassNotFoundException {
		Packet p = new Packet("signIn", "ehendrich", "wrong");
		
		c.sendPacket(p);
		
		Packet verify = c.receivePacket();
		
		System.out.println("Attempted to sign in with incorrect password");
		
		if (verify.isVerified()) {
			System.out.println(verify.getDescription());
		} else {
			System.out.println(verify.getDescription());
		}
		System.out.println();
	}
	
	public void changePassword() throws IOException, ClassNotFoundException {
		Packet p = new Packet("changePassword", "ehendrich", "newPassword");
		
		c.sendPacket(p);
		
		System.out.println("Attempt to change password to newPassword");
		
		System.out.println();
	}
	
	public void addMessageFromFirstUser() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addMessage", "Coding buds", new Message("ehendrich", "Hello Raj"));
		
		c.sendPacket(p);
		
		System.out.println("Attempt to send message from ehendrich");
		
		System.out.println();
	}
	
	public void addMessageFromSecondUser() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addMessage", "Coding buds", new Message("raj_the_baller", "Hello Evan"));
		
		c.sendPacket(p);
		
		System.out.println("Attempt to send message from raj_the_baller");
		
		System.out.println();
	}
	
	public void addAnotherMessageFromFirstUser() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addMessage", "ehendrich, raj_the_baller", new Message("ehendrich", "How are you?"));
		
		c.sendPacket(p);
		
		System.out.println("Attempt to send another message from ehendrich");
		
		System.out.println();
	}
	
	public void addAnotherMessageFromSecondUser() throws IOException, ClassNotFoundException {
		Packet p = new Packet("addMessage", "ehendrich, raj_the_baller", new Message("raj_the_baller", "I am quite well"));
		
		c.sendPacket(p);
		
		System.out.println("Attempt to send another message from raj_the_baller");
		
		System.out.println();
	}
	
	
	
	
	
	public void showData() throws IOException, ClassNotFoundException {
		Packet p = new Packet("update", "ehendrich");
		
		c.sendPacket(p);
		
		User u = c.reciveUser();
		
		System.out.println("Handle:");
		
		System.out.println(u.getHandle());
		
		System.out.println();
		
		System.out.println("Password:");
		
		System.out.println(u.getPassword());
		
		System.out.println();
		
		System.out.println("Friends:");
		int i = 1;
		for (User user : u.getFriends()) {
			System.out.println(i + ". " + user.getHandle());
			i++;
		}
		
		System.out.println();
		
		System.out.println("Chats:");
		int a = 1;
		for (Chat chat : u.getChats()) {
			System.out.println(a + ". " + chat.getChatName());
			if (chat.getChatContent() != null) {
				for (Message m : chat.getChatContent()) {
					System.out.println(m.getHandle());
					System.out.println(m.getContent());
				}
			} else {
				System.out.println("It was null");
			}
			a++;
		}
		
		System.out.println();
	}
}
