import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client {

    public void verifySignIn() {

    }

    public void verifyNewUser() {

    }

    public void verifyNewChat() {

    }

    public void verifyChangeUserName() {

    }

    public void verifyChangeChatName() {

    }
    public static void main(String[] args) throws IOException {
    	//Socket socket = new Socket("127.0.0.1", 5555); //handle this later
    	//BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	//PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        //JOptionPane.showMessageDialog(null, "Welcome to WSC", "WSC", JOptionPane.INFORMATION_MESSAGE);
        SwingUtilities.invokeLater(new ClientLoginGUI());
    }
}
