import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client {

    public boolean verifySignIn(Packet p) {
        return p.isVerified();
    }

    public boolean verifyNewUser(Packet p) {
        return p.isVerified();
    }

    public boolean verifyNewChat(Packet p) {
        return p.isVerified();
    }

    public boolean verifyChangeUserName(Packet p) {
        return p.isVerified();
    }

    public boolean verifyChangeChatName(Packet p) {
        return p.isVerified();
    }
    public static void main(String[] args) throws IOException {
       // Socket socket = new Socket("127.0.0.1", 5555); //handle this later
       // BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      //  PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        JOptionPane.showMessageDialog(null, "Welcome to WSC",
                "WSC", JOptionPane.INFORMATION_MESSAGE);

    }
}
