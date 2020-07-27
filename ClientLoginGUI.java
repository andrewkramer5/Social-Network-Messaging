import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientLoginGUI extends JPanel {
	
	public final int WINDOW_WIDTH;
	public final int WINDOW_HEIGHT;
	
	JLabel handleLabel;
	JLabel passwordLabel;
	JTextField handleField;
	JTextField passwordField;
	JButton loginButton;
	JButton makeAccountButton;
	
	public ClientLoginGUI(int width, int height) {
		
		this.WINDOW_WIDTH = width;
		this.WINDOW_HEIGHT = height;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		handleLabel = new JLabel("Username");
		handleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		c.gridx = 1;
		c.gridy = 1;
		c.ipadx = 20;
		c.ipady = 20;
		this.add(handleLabel, c);
		
		handleField = new JTextField(20);
		//handleField.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		c.gridx = 2;
		c.gridwidth = 2;
		this.add(handleField, c);
		
		passwordLabel = new JLabel("Password");
		passwordLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		c.gridx = 1;
		c.gridy = 2;
		c.gridwidth = 1;
		this.add(passwordLabel, c);
		
		passwordField = new JTextField(20);
		c.gridx = 2;
		c.gridwidth = 2;
		this.add(passwordField, c);
		
		JLabel empty = new JLabel();
		c.gridy = 3;
		this.add(empty, c);
		
		loginButton = new JButton("Login");
		c.gridy = 4;
		c.gridwidth = 1;
		c.insets = new Insets(10, 10, 10, 10);
		this.add(loginButton, c);
		
		makeAccountButton = new JButton("Make New Account");
		c.gridy = 5;
		this.add(makeAccountButton, c);
		
		this.setBorder(BorderFactory.createEmptyBorder(90, 0, 90, 0));
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT));
	}
}
