
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.net.*;
/**
 * Client Login GUI
 *
 * JPanel that holds the form for logging in
 *
 * @author Andrew Kramer 
 *
 * @version August 1, 2020
 *
 */
public class ClientLoginGUI extends JPanel {

    public final int WINDOW_WIDTH;
    public final int WINDOW_HEIGHT;

    ClientApplication app;
    JLabel titleLabel;
    JLabel handleLabel;
    JLabel passwordLabel;
    JTextField handleField;
    JTextField passwordField;
    JButton loginButton;
    JButton makeAccountButton;

    public ClientLoginGUI(ClientApplication app) {

        this.WINDOW_WIDTH = app.WINDOW_WIDTH;
        this.WINDOW_HEIGHT = app.WINDOW_HEIGHT;

        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        titleLabel = new JLabel("WSC Messenger");
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 2;
        c.ipadx = 20;
        c.ipady = 100;
        this.add(titleLabel, c);

        handleLabel = new JLabel("Username");
        handleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 1;
        c.ipady = 20;
        this.add(handleLabel, c);

        handleField = new JTextField(15);
        c.gridx = 1;
        c.gridwidth = 2;
        this.add(handleField, c);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 1;
        this.add(passwordLabel, c);

        passwordField = new JTextField(15);
        c.gridx = 1;
        c.gridwidth = 2;
        this.add(passwordField, c);

        JLabel empty = new JLabel("  ");
        c.gridy = 3;
        this.add(empty, c);
        c.gridx = 3;
        this.add(empty, c);
        c.gridx = 4;
        this.add(empty, c);

        loginButton = new JButton("Login");
        c.gridx = 2;
        c.gridy = 4;
        c.gridwidth = 1;
        c.insets = new Insets(10, 10, 10, 10);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oneFieldEmpty()) {
                    JOptionPane.showMessageDialog(null, "One of the fields is empty!",
                            "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        app.getClient().sendPacket(new Packet("signIn", app.getLoggedInUser().getHandle(),
                        app.getLoggedInUser().getPassword()));
                        Packet p = app.getClient().receivePacket();
                        if(p.isVerified()) {
                        	//TODO send and receive update packet
                        	//TODO set loggedInUser
                        	app.setPanel(app.PANEL_CHOICES[1]);
                            //app.getClient().sendPacket(new Packet("update", app.getLoggedInUser().getHandle()));
                            //app.setLoggedInUser(app.getClient().receiveUser());
                        }
                        else {
                            JOptionPane.showMessageDialog(null, p.getDescription(),
                                    "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "ERROR: Packets couldn't be sent and received!",
                                "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.add(loginButton, c);

        makeAccountButton = new JButton("Make New Account");
        c.gridx = 2;
        c.gridy = 5;
        makeAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (oneFieldEmpty()) {
                    JOptionPane.showMessageDialog(null, "One of the fields is empty!",
                            "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        app.getClient().sendPacket(new Packet("addUser", app.getLoggedInUser().getHandle(),
                                app.getLoggedInUser().getPassword()));
                        Packet p = app.getClient().receivePacket();
                        if (p.isVerified()) {
                        	JOptionPane.showMessageDialog(null, p.getDescription() + ", you can now login",
                                    "WSC Messenger", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                        	JOptionPane.showMessageDialog(null, p.getDescription(),
                                    "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "ERROR: Packets couldn't be sent and received!",
                                "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.add(makeAccountButton, c);

        //this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        //this.setOpaque(true);
        this.setPreferredSize(new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT));
    }

    public void update() {

    }

    public void clearFields() {
        this.handleField.setText("");
        this.passwordField.setText("");
    }

    public boolean oneFieldEmpty() {
        return this.handleField.getText().equals("") || this.passwordField.getText().equals("");
    }
}