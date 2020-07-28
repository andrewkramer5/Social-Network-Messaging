import java.io.*;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;

public class WSCServer implements Runnable, DataStorage {
	
	Socket socket;
	private ArrayList<User> users;
	
	
	public WSCServer(Socket socket) {
		this.socket = socket;
	}
	
	public WSCServer() {
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
    	
    	ServerSocket serverSocket = new ServerSocket(4200);
    	
    	while (true) {
    		Socket socket = serverSocket.accept();
    		
    		WSCServer server = new WSCServer(socket);
    				
    		new Thread(server).start();
    	}
    }
	
	public void run() {
		try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
			while (true) {
				
				Packet packet = (Packet) ois.readObject();
				Packet p;
				
				switch (packet.getIdentifier()) {
				
				case "deleteChat" :
					deleteChat(packet);
					
				case "signIn" :
					p = signIn(packet);
					oos.writeObject(p);
					
				case "addUser" :
					p = addUser(packet);
					oos.writeObject(p);
					
				case "changePassword" :
					changePassword(packet);
					
				case "deleteUser" :
					deleteUser(packet);
					
				case "changeHandle" :
					p = changeHandle(packet);
					oos.writeObject(p);
					
				case "changeChatName" :
					p = changeChatName(packet);
					oos.writeObject(p);
					
				case "addFriend" :
					p = addFriend(packet);
					oos.writeObject(p);
					
				case "deleteMessage" :
					deleteMessage(packet);
					
				case "editMessage" : 
					editMessage(packet);
					
				case "addChat" :
					p = addChat(packet);
					oos.writeObject(p);
					
				case "update" :
					oos.writeObject(users);
				
				default : 
					
				}
				
				WriteToFile w = new WriteToFile();
				
				w.writeUsers(users, "data.txt");
				
			}
		} catch (IOException e) {

    	} catch (ClassNotFoundException a) {

    	}
	}
	
	public void deleteChat(Packet packet) {
		for (User user : users) {
			if (user.getHandle().equals(packet.getHandle())) {
				for (Chat chat : user.getChats()) {
					if (chat.getChatName().equals(packet.getChatName())) {
						for (User chatMember : chat.getChatMembers()) {
							for (Chat friendChat : chatMember.getChats()) {
								if (friendChat.getChatName().equals(packet.getChatName())) {
									friendChat.deleteMember(user);
								}
							}
						}
						chat.deleteMember(user);
					}
				}
			}
		}
	}
	
	public Packet signIn(Packet packet) {
		for (User user : users) {
			if (user.getHandle().equals(packet.getHandle()) &&
				user.getPassword().equals(packet.getPassword())) {
				Packet verify = new Packet(true, "Sign in Successful");
				return verify;
			} else if (user.getHandle().equals(packet.getHandle()) &&
					!user.getPassword().equals(packet.getPassword())) {
				Packet verify = new Packet(false, "Incorrect Password");
				return verify;
			}
		}
		Packet verify = new Packet(false, "Username does not exist");
		return verify;
	}
	
	public void changePassword(Packet packet) {
		for (User user : users) {
			if (user.getHandle().equals(packet.getHandle())) {
				user.changePassword(packet.getPassword());
			}
		}
	}
	
	public Packet addUser(Packet packet) {
		for (User user : users) {
			if (user.getHandle().equals(packet.getHandle())) {
				Packet verify = new Packet(false, "Username already exists");
				return verify;
			}
		}
		
		User newUser = new User(packet.getHandle(), packet.getPassword());
		
		users.add(newUser);
		
		Packet verify = new Packet( true, "Account created successfully");
		return verify;
	}
	
	public void deleteUser(Packet packet) {
		for (User user : users) {
			for (User friend : (user.getFriends())) {
				if (friend.getHandle().equals(packet.getHandle())) {
					user.removeFriend(friend);
					for (Chat chat : friend.getChats()) {
						for (User chatFriend : chat.getChatMembers()) {
							if (chatFriend.getHandle().equals(packet.getHandle())) {
								chat.deleteMember(chatFriend);
							}
						}
					}
				}
			}
		}
		
		for (User user : users) {
			if (user.getHandle().equals(packet.getHandle())) {
				users.remove(user);
			}
		}
	}
	
	public Packet changeHandle(Packet packet) {
		for (User user : users) {
			if (user.getHandle().equals(packet.getNewHandle())) {
				Packet verify = new Packet(false, "Username already exists");
				return verify;
			}
		}
		
		for (User user : users) {
			if (user.getHandle().equals(packet.getHandle())) {
				user.changeHandle(packet.getNewHandle());
			}
		}
		
		Packet verify = new Packet(true, "Handle updated succesfully");
		return verify;
		
	}
	
	public Packet changeChatName(Packet packet) {
		for (User user : users) {
			for (Chat chat : user.getChats()) {
				if (chat.getChatName().equals(packet.getChatName())) {
					Packet verify = new Packet(false, "Chat name already exists");
					return verify;
				}
			}
		}
		
		for (User user : users) {
			for (Chat chat : user.getChats()) {
				if (chat.getChatName().equals(packet.getChatName())) {
					chat.setChatName(packet.getNewChatName());
				}
			}
		}
		
		Packet verify = new Packet(true, "Chat name updated");
		return verify;
	}
	
	public Packet addFriend(Packet packet) {
		boolean friendExists = false;
		User adder = new User();
		User friend = new User();
		for (User user : users) {
			if (user.getHandle().equals(packet.getFriendHandle())) {
				friendExists = true;
				friend = user;
			} else if (user.getHandle().equals(packet.getHandle())) {
				adder = user;
			}
		}
		if (!friendExists) {
			Packet verify = new Packet(false, "User with that username does not exist");
			return verify;
		}
		
		adder.addFriend(friend);
		friend.addFriend(adder);
		
		Packet verify = new Packet(true, friend.getHandle() + "added");
		return verify;
	}
	
	public void addMesage(Packet packet) {
		for (User user : users) {
			for (Chat chat : user.getChats()) {
				if (chat.getChatName().equals(packet.getChatName())) {
					chat.addMessage(packet.getMessage());
				}
			}
		}
	}
	
	public void deleteMessage(Packet packet) {
		for (User user : users) {
			for (Chat chat : user.getChats()) {
				if (chat.getChatName().equals(packet.getChatName())) {
					chat.deleteMessage(packet.getMessage());
				}
			}
		}
	}
	
	public void editMessage(Packet packet) {
		for (User user : users) {
			for (Chat chat : user.getChats()) {
				if (chat.getChatName().equals(packet.getChatName())) {
					chat.editMessage(packet.getOldMessage(), packet.getMessage());
				}
			}
		}
	}
	
	public Packet addChat(Packet packet) {
		for (User user : users) {
			for (Chat chat : user.getChats()) {
				if (chat.getChatName().equals(packet.getChatName())) {
					Packet verify = new Packet(false, "Chat name already exists");
					return verify;
				}
			}
		}
		
		ArrayList<User> chatMembers = new ArrayList<User>();
		
		for (String s : packet.getHandles()) {
			for (User user : users) {
				if (s.equals(user.getHandle())) {
					chatMembers.add(user);
				}
			}
		}
		
		Chat newChat = new Chat(chatMembers, packet.getChatName());
		
		for (User user : users) {
			if (newChat.getChatMembers().contains(user)) {
				user.addChat(newChat);
			}
		}
		
		Packet verify = new Packet(true, "chat created");
		return verify;
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
	
	public User getUser(String handle) {
		for (User user : users) {
			if (user.getHandle().equals(handle)) {
				return user;
			}
		}
		return null;
	}
}
