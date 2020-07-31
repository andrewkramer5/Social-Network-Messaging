import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * The server holds all data for all users. All data manipulation is done through this
 * server. The server can handle multiple clients connected at once. Then it waits for
 * data from the client, makes appropriate changes to the data on the server then returns
 * some data (varying to the task it has just carried out).
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @authors Evan Hendrich, Raj Karra
 * @version July 31, 2020
 */

public class WSCServer implements Runnable {

    Socket socket; // the socket between client and server
    private static ArrayList<User> users; // ArrayList of users

    /*
     * Constructor
     * 
     * @param, Socket socket, the socket between client and server
     */
    public WSCServer(Socket socket) {
        this.socket = socket;
    } // WSCServer
    
    /*
     * Blank constructor
     */
    public WSCServer() {

    } // WSCServer
    
    /*
     * The main method of the server waits for clients to connect then starts a new
     * thread with that socket. It also initializes the user array list.
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(4200);

        users = new ArrayList<User>();

        while (true) {
            Socket socket = serverSocket.accept();

            WSCServer server = new WSCServer(socket);

            new Thread(server).start();
        } // end while
    } // main

    /*
     * The run method sits waiting for the client to send it data in the form of a
     * Packet object. The server than takes that data, manipulates it, then updates 
     * the server data accordingly and returns data.
     */
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
            while (true) {
            		
                Packet packet = (Packet) ois.readObject();
                String ident = packet.getIdentifier();
                Packet p;

                if (ident.equals("deleteChat")) {
                    p = deleteChat(packet); //doesn't work
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("signIn")) {
                    p = signIn(packet); //works
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("addUser")) {
                    p = addUser(packet); //works
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("changePassword")) {
                    p = changePassword(packet); //works serverside
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("deleteUser")) {
                    p = deleteUser(packet); //works serverside
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("changeHandle")) {
                    p = changeHandle(packet); //works serverside
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("changeChatName")) {
                    p = changeChatName(packet);
                    oos.writeObject(p);  //works serverside
                    oos.flush();

                } else if (ident.equals("addFriend")) {
                    p = addFriend(packet);  //works
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("addMessage")) {
                    p = addMessage(packet);  //works
                    oos.writeObject(p);
                    oos.flush();


                } if (ident.equals("deleteMessage")) {
                    p = deleteMessage(packet); //works
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("editMessage")) {
                    p = editMessage(packet); //works
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("addChat")) {
                    p = addChat(packet);  //works
                    oos.writeObject(p);
                    oos.flush();

                } else if (ident.equals("removeFriend")) {
                	p = removeFriend(packet);
                	oos.writeObject(p);
                	oos.flush();
                	
                } else if (ident.equals("update")) {
                    for (User user : users) {
                        if (user.getHandle().equals(packet.getHandle())) {
                            oos.writeObject(user);
                            oos.flush();
                        } // end if
                    } // end for
                    
                } else if (ident.equals("end")) {
                	break;
                } // end else ifs
            } // end while
        } catch (IOException e) {
			WriteToFile w = new WriteToFile();

			w.writeUsers(users, "data.txt");
        } catch (ClassNotFoundException a) {
			WriteToFile w = new WriteToFile();

			w.writeUsers(users, "data.txt");;
        } // end catch statements
    } // run

    /*
     * delete chat removes a chat object from the specified user
     * and removes that user from the chat.
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet deleteChat(Packet packet) {
    	try {
	        for (User user : users) {
	            if (user.getHandle().equals(packet.getHandle())) {
	                for (Chat chat : user.getChats()) {
	                    if (chat.getChatName().equals(packet.getChatName())) {
	                        for (User chatMember : chat.getChatMembers()) {
	                            for (Chat friendChat : chatMember.getChats()) {
	                                if (friendChat.getChatName().equals(packet.getChatName())) {
	                                    friendChat.deleteMember(user);
	                                } // end if checking for chat in friends' chats
	                            } // end for iterating through chats of members of specified chat
	                        } // end for iterating through members of specified chat
	                    } // end if checking for chat name comparison
	                    chat.deleteMember(user);
	                } // end for iterating through chats of specified user 
	                for (Chat c : user.getChats()) {
	                	if (c.getChatName().equals(packet.getChatName())) {
	                		user.removeChat(c);
	                	} // end if
	                } // end for
	            } // end if checking for handle comparison
	        } // end for iterating through users
    	} catch (ConcurrentModificationException a) {
	    	
	    } // end catch
        return new Packet(true, "success");
    } // deleteChat

    /*
     * sign in verifies that a client is signing in with correct
     * handle and password
     * 
     * @param packet, the packet containing required data to perform task
     */
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
            } // end else if
        } // end for
        Packet verify = new Packet(false, "Username does not exist");
        return verify;
    } // signIn
    
    /*
     * change the password of a user
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet changePassword(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                user.changePassword(packet.getPassword());
                System.out.println(user.getHandle());
                System.out.println(user.getPassword());
            } // end if
        } // end for
        return new Packet(true, "success");
    } // changePassword
    
    /*
     * create a new user and add to the server
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet addUser(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                Packet verify = new Packet(false, "Username already exists");
                return verify;
            } // end if
        } // end for

        User newUser = new User(packet.getHandle(), packet.getPassword());

        users.add(newUser);

        Packet verify = new Packet( true, "Account created successfully");
        return verify;
    } // addUser
    
    /*
     * Remove a user from the server. It removes the user from friend's 
     * list of users the user was previously friends with, and removes
     * them from all chats, but saves the messages they sent
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet deleteUser(Packet packet) {
        try {
            for (User user : users) {
                for (User friend : (user.getFriends())) {
                    if (friend.getHandle().equals(packet.getHandle())) {
                        user.removeFriend(friend);
                    } // end if
                } // end for
                for (Chat chat : user.getChats()) {
                    for (User chatFriend : chat.getChatMembers()) {
                        if (chatFriend.getHandle().equals(packet.getHandle())) {
                            chat.deleteMember(chatFriend);
                        } // end if
                    } // end inner for
                    if (chat.getChatName().contains(packet.getHandle() + ", ")) {
                    	chat.setChatName(chat.getChatName().replace(packet.getHandle() + ", ", ""));
                    } // end if
                    if (chat.getChatName().contains(packet.getHandle())) {
                    	chat.setChatName(chat.getChatName().replace(", " + packet.getHandle(), ""));
                    } // end if
                } // end outer for
            }

            for (User user : users) {
                if (user.getHandle().equals(packet.getHandle())) {
                    users.remove(user);
                } // end if
            } // end for
            
            return new Packet(true, "success");
            
        } catch (ConcurrentModificationException a) {
            deleteUser(packet);
            return new Packet(true, "success");
        } // end catch
    } // deleteUser

    /*
     * Change the handle of a user in all places where it is used: 
     * message handles and default chat names
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet changeHandle(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getNewHandle())) {
                return new Packet(false, "Username already exists");
            } // end if
        } // end for
        
        for (User u: users) {
            if(!u.getHandle().equals(packet.getHandle())) {
                for (User us: u.getFriends()) {
                    if(us.getHandle().equals(packet.getHandle())) {
                        us.changeHandle(packet.getNewHandle());
                    } // end if
                } // end inner for
                for (Chat c: u.getChats()) {
                    for (User us: c.getChatMembers()) {
                        if(us.getHandle().equals(packet.getHandle())) {
                            us.changeHandle(packet.getNewHandle());
                        } // end if
                    } // end inner for
                    if (c.getChatName().contains(packet.getHandle() + ", ")) {
                    	c.setChatName(c.getChatName().replace(packet.getHandle() + ", ", 
                    			packet.getNewHandle() + ", "));
                    } // end if
                    if (c.getChatName().contains(packet.getHandle())) {
                    	c.setChatName(c.getChatName().replace(packet.getHandle(), 
                    			packet.getNewHandle() + ", "));
                    } // end if
                    for (Message m : c.getChatContent()) {
                    	if (m.getHandle().equals(packet.getHandle())) {
                    		m.setHandle(packet.getNewHandle());
                    	} // end if
                    } // end inner for
                } // end middle for
            } // end outer if
        } // end outer for

        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                user.changeHandle(packet.getNewHandle());
            } // end if
        } // end for

        return new Packet(true, "Handle updated succesfully");
    } // changeHandle

    /*
     * Change the chat name of a chat for user specified and all
     * other chat members
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet changeChatName(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.setChatName(packet.getNewChatName());
                } // end if
            } // end inner for
        } // end outer for
        return new Packet(true, "Chat name successfully changed");
    } // changeChatName

    /*
     * Add a friend to a user's friends list after checking that the
     * friend exists
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet addFriend(Packet packet) {
        boolean friendExists = false; // boolean to check if friend exists
        User adder = new User(); // the user adding the friend
        User friend = new User(); // the friend being added

        for (User user : users) {
            if (user.getHandle().equals(packet.getFriendHandle())) {
                friendExists = true;
                friend = user;
            } else if (user.getHandle().equals(packet.getHandle())) {
                adder = user;
            } // end else if
        } // end for
        if (!friendExists) {
            Packet verify = new Packet(false, "User with that username does not exist");
            return verify;
        } // end if

        if (adder.getFriends().contains(friend)) {
            Packet verify = new Packet(false, "You are already friends with " + friend.getHandle());
            return verify;
        } // end if

        adder.addFriend(friend);
        friend.addFriend(adder);

        Packet verify = new Packet(true, friend.getHandle() + " added");
        return verify;
    } // addFriend

    /*
     * Add a message to a chat.
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet addMessage(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                	if (chat.getChatContent().size() < 1) {
                		chat.addMessage(packet.getMessage());
                		continue;
                	} else if (!chat.getChatContent().get(chat.getChatContent().size() - 1).equals(
            				packet.getMessage())) {
                		chat.addMessage(packet.getMessage());
                	} // end else if
                } // end if checking for chat name comparison
            } // end inner for
        } // end outer for
        return new Packet(true, "success");
    } // addMessage

    /*
     * Delete a message from a chat.
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet deleteMessage(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.deleteMessage(packet.getMessage());
                } // end if
            } // end inner for
        } // end outer for
        return new Packet(true, "success");
    } // deleteMessage

    /*
     * Edit a message in a chat.
     * 
     * @param packet, the packet containing required data to perform task
     */
    public Packet editMessage(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.editMessage(packet.getOldMessage(), packet.getMessage());
                } // end if
            } // end inner for
        } // end outer for
        return new Packet(true, "message");
    } //editMessage
    
    /*
     * Adds a chat object to all users specified as chat members

     * @param originalPacket, the packet containing required data to perform task
     */
    public Packet addChat(Packet originalPacket) {
        Packet packet = originalPacket;

        while (true) {
            for (User user : users) {
                for (Chat chat : user.getChats()) {
                    if (chat.getChatName().equals(packet.getChatName())) {
                        Packet verify = new Packet(false, "Chat name already exists");
                        return verify;
                    } // end if
                } // end inner for
            } // end outer for

            ArrayList<User> chatMembers = new ArrayList<User>();

            for (String s : packet.getHandles()) {
                for (User user : users) {
                    if (s.equals(user.getHandle())) {
                        chatMembers.add(user);
                    } // end if
                } // end inner for
            } // end outer for

            Chat newChat = new Chat(chatMembers, packet.getChatName());
            
            //checks that the chat has not already been created with the default chat name
            if (packet.getChatName().equals("")) {
                packet = new Packet("addChat", packet.getHandles(), newChat.getChatName());

                continue;
            } // end if

            for (User user : users) {
                if (newChat.getChatMembers().contains(user)) {
                    user.addChat(newChat);
                } // end if
            } // end for

            Packet verify = new Packet(true, "chat created");
            return verify;
        } // end while
    } //addChat
    
    /*
     * Remove friend from both friends lists

     * @param packet, the packet containing required data to perform task
     */
    public Packet removeFriend(Packet packet) {
    	User remover = new User();
    	User removed = new User();
    	for (User user : users) {
    		if (user.getHandle().equals(packet.getHandle())) {
    			for (User u : user.getFriends()) {
    				if (u.getHandle().equals(packet.getFriendHandle())) {
    					removed = u;
    				} // end inner if
    			} // end inner for
    		} // end if
    	}
    	for (User user : users) {
    		if (user.getHandle().equals(packet.getFriendHandle())) {
    			for (User u : user.getFriends()) {
    				if (u.getHandle().equals(packet.getHandle())) {
    					remover = u;
    				} // end inner if 
    			} // end inner for
    		} // end else if
    	} // end for
    	for (User user : users) {
    		if (user.getHandle().equals(packet.getHandle())) {
    			user.removeFriend(removed);
    		} if (user.getHandle().equals(packet.getFriendHandle())) {
    			user.removeFriend(remover);
    		}
    	}
    	Packet p = new Packet(true, "success");
    	return p;
    } // removeFriend
    
    /*
     * returns all server data
     */
    public ArrayList<User> getUsers() {
        return users;
    } // getUsers
    
    /*
     * returns the User object specified by handle
     */
    public User getUser(String handle) {
        for (User user : users) {
            if (user.getHandle().equals(handle)) {
                return user;
            } // end if
        } // end for
        return null;
    } // getUser
    
    /*
     * convert data to String
     */
    public String toString() {
    	String s = "";
    	for (User user : users) {
    		s += user.getHandle() + "\n";
    		s += user.getPassword() + "\n\n";
    		s += "chats \n";
    		for (Chat chat : user.getChats()) {
    			s += chat.getChatName() + "\n\n";
    			s += "users in chat \n";
    			for (User u : chat.getChatMembers()) {
    				s += u.getHandle() + "\n";
    			}
    			s += "\n";
    			for (Message m : chat.getChatContent()) {
    				s += m.getHandle() + "\n";
    				s += m.getContent() + "\n\n";
    			} // end inner for
    		} // end middle for
    	} // end outer for
    	return s;
    } //toString
} // WSCServer