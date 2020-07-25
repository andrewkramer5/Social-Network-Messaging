import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientLoginGUI extends JPanel implements Runnable {
	
	public final int WIDTH = 600;
	public final int HEIGHT = 400;
	
	JFrame frame;
	JLabel handleLabel;
	JLabel passwordLabel;
	JTextField handleField;
	JTextField passwordField;
	JButton loginButton;
	JButton makeAccountButton;
	
	@Override
	public void run() {
		frame = new JFrame("WSC Messenger");
		frame.add(this);
		frame.setSize(600, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel handlePanel = new JPanel();
		handleLabel = new JLabel("Username");
		handleField = new JTextField(20);
		handlePanel.add(handleLabel);
		handlePanel.add(handleField);
		//handlePanel.setBackground(Color.pink);
		
		JPanel passwordPanel = new JPanel();
		passwordLabel = new JLabel("Password");
		passwordField = new JTextField(20);
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		//passwordPanel.setBackground(Color.blue);
		
		loginButton = new JButton("Login");
		loginButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		makeAccountButton = new JButton("Make New Account");
		makeAccountButton.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));
		
		this.add(handlePanel);
		this.add(passwordPanel);
		this.add(loginButton);
		this.add(makeAccountButton);
		//this.setBackground(Color.green);
		
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(BorderFactory.createEmptyBorder(90, 0, 90, 0));
	}
}
