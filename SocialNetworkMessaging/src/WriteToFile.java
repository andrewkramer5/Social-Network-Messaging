import java.io.*;
import java.util.*;
import java.lang.*;

/**
 * This class is used to save data to a file in case the server crashes.
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Evan Hendrich
 * @author Raj Karra
 * @version July 31, 2020
 */

public class WriteToFile {
	
	/*
	 * blank constructor
	 */
    public WriteToFile() {

    } // WriteToFile
    
    /*
     * outputs data to file specified in parameters
     * 
     * @pram ArrayList<User> users, data to be stored
     * 
     * @param String filename, filename in which to store data
     */
    public void writeUsers(ArrayList<User> users, String filename) {
    	try {
    		FileOutputStream fos = new FileOutputStream(filename, false);
			PrintWriter pw = new PrintWriter(fos);
			String out = "";
	        for (User u : users) {
	        	out = "";
	        	out = out + u.getHandle() + ":";
	        	out = out + u.getPassword() + ":";
	        	if (u.getFriends().size() < 1) {
	        		out += "noFriends;";
	        	}
	        	for (User friend : u.getFriends()) {
	        		out += friend.getHandle() + ",";
	        	}
	        	out = out.substring(0, out.length() - 1);
	        	out += ":";
	        	if (u.getChats().size() < 1) {
	        		out += "no;no;no;";
	        	}
	        	for (Chat c : u.getChats()) {
	        		out += c.getChatName() + ";";
	        		if (c.getChatMembers().size() < 1) {
	        			out += "nonExistent;";
	        		}
	        		for (User member : c.getChatMembers()) {
	        			out += member.getHandle() + ".";
	        		}
	        		out = out.substring(0, out.length() - 1);
	        		out += ";";
	        		if (c.getChatContent().size() < 1) {
	        			out += "noMessages;";
	        		}
	        		for (Message m : c.getChatContent()) {
	        			out += m.getHandle() + "/";
	        			out += m.getContent() + "|";
	        		}
	        		out = out.substring(0, out.length() - 1);
	        		out += ";";
	        	}
        		out += "\n";
        		System.out.println(out);
	        	pw.write(out);
	        	pw.flush();
	        	out = "";
	        	
//	            o.writeObject(u);
//	            o.writeBytes("\n");
//	            o.flush();
	        } // end for
//	        o.close();
	        pw.close();
    	} catch (IOException a) {
    		return;
    	} // end catch
    } // writeUsers
    
    public ArrayList<User> readUser() {
    	String filename = "data.txt";
    	var users = new ArrayList<User>();
    	var friendsList = new ArrayList<String>();
    	var conversations = new ArrayList<String>();
    	try {
    		FileReader fr = new FileReader(filename);
    		BufferedReader br = new BufferedReader(fr);
    		String input = br.readLine();
    		while (input != null) {
    			System.out.println(input);
    			String handle = input.substring(0, input.indexOf(':'));
    			input = input.substring(input.indexOf(':') + 1, input.length());
    			String password = input.substring(0, input.indexOf(':'));
    			input = input.substring(input.indexOf(':') + 1, input.length());
    			User u = new User(handle, password);
    			users.add(u);
    			String friends = input.substring(0, input.indexOf(':'));
    			friendsList.add(friends);
    			input = input.substring(input.indexOf(':') + 1, input.length());
    			String chats = input;
    			conversations.add(chats);
    			input = br.readLine();
    		} // end while
    		int i = 0;
    		for (String s : friendsList) {
    			for (String friend : s.split(",")) {
    				for (User u : users) {
    					if (u.getHandle().equals(friend)) {
    						users.get(i).addFriend(u);
    					}
    				}
    			}
    			i++;
    		}
    		int j = 0;
    		for (String s : conversations) {
    			var chatMembers = new ArrayList<User>();
    			if (!s.contains(";")) {
    				break;
    			}
    			String chatName = s.substring(0, s.indexOf(';'));
    			s = s.substring(s.indexOf(';') + 1, s.length());
    			String members = s.substring(0, s.indexOf(";"));
    			s = s.substring(s.indexOf(";") + 1, s.length());
    			String messages = s.substring(0, s.indexOf(";"));
    			while (members.contains(".")) {
    				String member = members.substring(0, members.indexOf('.'));
    				for (User u : users) {
    					if (u.getHandle().equals(member)) {
    						chatMembers.add(u);
    					}
    				}
    				if (members.contains(".")) {
    					members = members.substring(members.indexOf(".") + 1, members.length());
    				} else {
    					break;
    				}
    			}
    			Chat c = new Chat(chatMembers, chatName);
    			String content;
    			while (messages.contains("|")) {
    				String sender = messages.substring(0, messages.indexOf("/"));
    				messages = messages.substring(messages.indexOf("/"), messages.length());
    				if (messages.contains("|")) {
	    				content = messages.substring(0, messages.indexOf("|"));
	    				messages = messages.substring(messages.indexOf("|"), messages.length());
    				} else {
    					content = messages;
    				}
    				Message m = new Message(sender, content);
    				c.addMessage(m);
    			}
    			users.get(j).addChat(c);
    			j++;
    		}
    		br.close();
    		for (User u : users) {
    			System.out.println(u.getHandle());
    		}
    		return users;
    	} catch (FileNotFoundException a) {
    		return users;
    	} catch (IOException a) {
    		return users;
    	}
    	
    }
}