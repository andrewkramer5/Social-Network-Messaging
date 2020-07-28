import java.io.*;
import java.util.*;
import java.lang.*;

public class WriteToFile {
    public WriteToFile() {

    }

    public void writeUsers(ArrayList<User> users, String filename) throws IOException {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File(filename)));
        for (User u : users) {
            o.writeObject(u);
            o.writeBytes("\n");
            o.flush();
        }
        o.close();
    }
    public void writeChat(ArrayList<Chat> chats, String filename) throws IOException {
        ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File(filename)));
        for (Chat c : chats) {
            o.writeObject(c);
            o.writeBytes("\n");
            o.flush();
        }
        o.close();
    }
    public ArrayList<User> readUsersFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(new File(filename)));
        ArrayList<User> users= new ArrayList<>();
        while(true) {
            User u = (User) o.readObject();
            if(u != null) {
                users.add(u);
            }
            else {
                break;
            }
        }
        return users;
    }

    public ArrayList<Chat> readChatsFromFile(String filename) throws IOException, ClassNotFoundException {
        ObjectInputStream o = new ObjectInputStream(new FileInputStream(new File(filename)));
        ArrayList<Chat> chats= new ArrayList<>();
        while(true) {
            Chat c = (Chat) o.readObject();
            if(c != null) {
                //users.add(c);
            }
            else {
                break;
            }
        }
        return chats;
    }
}
