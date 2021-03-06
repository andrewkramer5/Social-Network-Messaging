import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Client class is used as a wrapper class to send items between server and GUI.
 * All data transfer between server and client is done using a Client object.
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Raj Karra
 * @version July 31, 2020
 */

public class Client {

    ObjectOutputStream out;
    ObjectInputStream in;
    Socket s;

    public Client(Socket s) throws IOException {
        this.s = s;
        this.out = new ObjectOutputStream(s.getOutputStream());
        this.in = new ObjectInputStream(s.getInputStream());
    }

    public void sendPacket(Packet p) throws IOException {
        this.out.writeObject(p);
    }

    public Packet receivePacket() throws IOException, ClassNotFoundException {
        return (Packet) this.in.readObject();
    }

    public void sendUser(User u) throws IOException {
        this.out.writeObject(u);
    }

    public User receiveUser() throws IOException, ClassNotFoundException {
        return (User) this.in.readObject();
    }

    public void askUpdate(String handle) {
    	try {
    		this.out.writeObject(new Packet("update", handle));
    	} catch (IOException a) {
    		a.printStackTrace();
    	}
    }

}