import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Client Chat List GUI
 *
 * JPanel that holds the form for listing the user's current chats
 *
 * @author Andrew Kramer 
 *
 * @version July 28, 2020
 *
 */
public class ClientChatListGUI extends JPanel {
	
	public final int WINDOW_WIDTH;
	public final int WINDOW_HEIGHT;
	
	ClientApplication client;
	
	JPanel accountInfoPanel;
	JLabel textUsernameLabel;
	JLabel usernameLabel;
	JButton changeUsernameButton;
	JButton changePasswordButton;
	JButton addFriendButton;
	JButton removeFriendButton;
	JButton logoutButton;
	JButton deleteAccountButton;
	
	JPanel chatListPanel;
	JLabel titleLabel;
	
	JPanel friendListPanel;
	JLabel textFriendsLabel;
	
	public ClientChatListGUI(ClientApplication client) {
		this.client = client;
		this.WINDOW_WIDTH = client.WINDOW_WIDTH;
		this.WINDOW_HEIGHT = client.WINDOW_HEIGHT;
		
		//Account Info Panel
		accountInfoPanel = new JPanel();
		accountInfoPanel.setLayout(new GridLayout(8, 1, 10, 20));
		
		textUsernameLabel = new JLabel("Username:");
		textUsernameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		accountInfoPanel.add(textUsernameLabel);
		
		usernameLabel = new JLabel();
		usernameLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		accountInfoPanel.add(usernameLabel);
		
		changeUsernameButton = new JButton("Change Username");
		accountInfoPanel.add(changeUsernameButton);
		
		changePasswordButton = new JButton("Change Password");
		accountInfoPanel.add(changePasswordButton);
		
		addFriendButton = new JButton("Add Friend");
		accountInfoPanel.add(addFriendButton);
		
		removeFriendButton = new JButton("Remove Friend");
		accountInfoPanel.add(removeFriendButton);
		
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO log out
				client.setPanel(client.PANEL_CHOICES[0]);
				client.setLoggedInUser(new User("", ""));
			}
		});
		accountInfoPanel.add(logoutButton);
		
		deleteAccountButton = new JButton("Delete Account");
		accountInfoPanel.add(deleteAccountButton);
		
		accountInfoPanel.setBackground(Color.white);
		accountInfoPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//End Account Info Panel
		
		//Chat List Panel
		chatListPanel = new JPanel();
		chatListPanel.setLayout(new BorderLayout());
		
		titleLabel = new JLabel("WSC Messenger");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
		chatListPanel.add(titleLabel, BorderLayout.NORTH);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		//chatListPanel.setBackground(Color.green);
		//End Chat List Panel
		
		//Friend List Panel
		friendListPanel = new JPanel();
		friendListPanel.setLayout(new BorderLayout());
		
		textFriendsLabel = new JLabel("Friends List");
		textFriendsLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
		friendListPanel.add(textFriendsLabel, BorderLayout.NORTH);
		
		friendListPanel.setBackground(Color.white);
		friendListPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		//End Friend List Panel
		
		this.setLayout(new BorderLayout());
		this.add(accountInfoPanel, BorderLayout.WEST);
		this.add(chatListPanel, BorderLayout.CENTER);
		this.add(friendListPanel, BorderLayout.EAST);
		//this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		//this.setOpaque(true);
		this.setPreferredSize(new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT));
	}
	
	public void update() {
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	usernameLabel.setText(client.getLoggedInUser().getHandle());
        		//TODO display user's current friends
            }
        });
	}
	
	//For separate thread specifically updating chats
	public void updateChats() {
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	//TODO display user's current chats
            }
        });
	}
}
