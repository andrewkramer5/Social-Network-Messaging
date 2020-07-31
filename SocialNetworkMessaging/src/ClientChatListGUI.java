import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

/**
 * Client Chat List GUI
 *
 * JPanel that holds the form for listing the user's current chats
 *
 * @author Andrew Kramer 
 *
 * @version July 31, 2020
 *
 */
public class ClientChatListGUI extends JPanel {
	
	public final int WINDOW_WIDTH;
	public final int WINDOW_HEIGHT;
	
	ClientApplication client;
	private int numChats;
	private int numFriends;
	
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
	JScrollPane chatListScrollPane;
	JPanel chatListContainer;
	JButton addChatButton;
	
	JPanel friendListPanel;
	JLabel textFriendsLabel;
	JScrollPane friendListScrollPane;
	JPanel friendListContainer;
	
	public ClientChatListGUI(ClientApplication client) {
		this.numFriends = client.getLoggedInUser().getFriends().size();
		this.numChats = client.getLoggedInUser().getChats().size();
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
		changeUsernameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newHandle = JOptionPane.showInputDialog(client.getFrame(), "Please enter a new username", 
						"WSC Messenger", JOptionPane.OK_CANCEL_OPTION);
				if (newHandle != null) {
					if (!newHandle.equals("")) {
						//TODO send change handle packet to server with newHandle
						//TODO receive verify packet from server
						/*
						if (packet.isVerified()) {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger", JOptionPane.INFORMATION_MESSAGE);
				    		//TODO get user with update packet using newHandle
				    		//TODO set loggedInUser to received user
				    		update();
						} else {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
						}
						*/
					} else {
						JOptionPane.showMessageDialog(null, "Field can't be empty!", 
			    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		accountInfoPanel.add(changeUsernameButton);
		
		changePasswordButton = new JButton("Change Password");
		changePasswordButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String newPassword = JOptionPane.showInputDialog(client.getFrame(), "Please enter a new password", 
						"WSC Messenger", JOptionPane.OK_CANCEL_OPTION);
				if (newPassword != null) {
					if (!newPassword.equals("")) {
						//TODO send change password packet to server with newPassword
						//TODO receive verify packet from server
						/*
						if (packet.isVerified()) {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger", JOptionPane.INFORMATION_MESSAGE);
				    		//TODO get user with update packet
				    		//TODO set loggedInUser to received user
						} else {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
						}
						*/
					} else {
						JOptionPane.showMessageDialog(null, "Field can't be empty!", 
			    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		accountInfoPanel.add(changePasswordButton);
		
		addFriendButton = new JButton("Add Friend");
		addFriendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String friendHandle = JOptionPane.showInputDialog(client.getFrame(), "Please enter a username of a friend to add", 
						"WSC Messenger", JOptionPane.OK_CANCEL_OPTION);
				if (friendHandle != null) {
					if (!friendHandle.equals("")) {
						//TODO send add friend packet to server with friendHandle
						//TODO receive verify packet from server
						/*
						if (packet.isVerified()) {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger", JOptionPane.INFORMATION_MESSAGE);
				    		//TODO get user with update packet
				    		//TODO set loggedInUser to received user
				    		update();
						} else {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
						}
						*/
					} else {
						JOptionPane.showMessageDialog(null, "Field can't be empty!", 
			    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		accountInfoPanel.add(addFriendButton);
		
		removeFriendButton = new JButton("Remove Friend");
		removeFriendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String friendHandle = JOptionPane.showInputDialog(client.getFrame(), "Please enter a username of a friend to remove", 
						"WSC Messenger", JOptionPane.OK_CANCEL_OPTION);
				if (friendHandle != null) {
					if (!friendHandle.equals("")) {
						//TODO send remove friend packet to server with friendHandle
						//TODO receive verify packet from server
						/*
						if (packet.isVerified()) {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger", JOptionPane.INFORMATION_MESSAGE);
				    		//TODO get user with update packet
				    		//TODO set loggedInUser to received user
				    		update();
						} else {
							JOptionPane.showMessageDialog(null, packet.getDescription(), 
				    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
						}
						*/
					} else {
						JOptionPane.showMessageDialog(null, "Field can't be empty!", 
			    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		accountInfoPanel.add(removeFriendButton);
		
		logoutButton = new JButton("Logout");
		logoutButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				client.setPanel(client.PANEL_CHOICES[0]);
				client.setLoggedInUser(new User("", ""));
			}
		});
		accountInfoPanel.add(logoutButton);
		
		deleteAccountButton = new JButton("Delete Account");
		deleteAccountButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(client.getFrame(), "Are you sure you want to delete your account?", 
						"WSC Messenger", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {
					//TODO send delete account packet to server with loggedInUser.getHandle()
					
					client.setPanel(client.PANEL_CHOICES[0]);
					client.setLoggedInUser(new User("", ""));
				}
			}
		});
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
		
		addChatButton = new JButton("Create New Chat");
		addChatButton.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 20));
		addChatButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String chatName = JOptionPane.showInputDialog(client.getFrame(), "Please enter a name for the new chat", 
						"WSC Messenger", JOptionPane.OK_CANCEL_OPTION);
				if (chatName != null) {
					if (!chatName.equals("")) {
						ArrayList<String> handlesList = new ArrayList<String>();
						boolean loopAgain = true;
						boolean cancelled = false;
						
						do {
							String friendHandle = JOptionPane.showInputDialog(client.getFrame(), 
									"Please enter a name of a friend to add to the new chat", 
									"WSC Messenger", JOptionPane.OK_CANCEL_OPTION);
							if (friendHandle != null) {
								if (!friendHandle.equals("")) {
									handlesList.add(friendHandle);
									int selected = JOptionPane.showConfirmDialog(client.getFrame(), 
											"Would you like to add another friend?", 
											"WSC Messenger", JOptionPane.YES_NO_OPTION);
									if (selected == JOptionPane.NO_OPTION) {
										loopAgain = false;
									}
								} else {
									JOptionPane.showMessageDialog(null, "Field can't be empty!", 
						    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								cancelled = true;
								loopAgain = false;
							}
						} while (loopAgain);
						
						if (!cancelled) {
							Collections.sort(handlesList);
							String[] handles = (String[]) handlesList.toArray();
							
							//TODO send add chat packet to server with chatName and handles array
							//TODO receive verify packet from server
							/*
							if (packet.isVerified()) {
								JOptionPane.showMessageDialog(null, packet.getDescription(), 
					    				"WSC Messenger", JOptionPane.INFORMATION_MESSAGE);
					    		//TODO send get user packet to server
					    		//TODO set loggedInUser to received user
					    		update();
					    		updateChats();
							} else {
								JOptionPane.showMessageDialog(null, packet.getDescription(), 
					    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
							}
							*/
						}
					} else {
						JOptionPane.showMessageDialog(null, "Field can't be empty!", 
			    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		chatListPanel.add(addChatButton, BorderLayout.SOUTH);
		
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
		
		numFriends = client.getLoggedInUser().getFriends().size();
		friendListContainer = new JPanel();
		friendListContainer.setLayout(new GridLayout(numFriends, 1, 0, 5));
		friendListScrollPane = new JScrollPane(friendListContainer);
		friendListPanel.add(friendListScrollPane, BorderLayout.CENTER);
		
		for (User u : client.getLoggedInUser().getFriends()) {
    		//System.out.println("Adding chats");
			JLabel l = new JLabel(u.getHandle());
			l.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
			l.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
    		friendListContainer.add(l);
    	}
	}
	
	//For separate thread specifically updating chats
	public void updateChats() {
		/*
		SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	numChats = client.getLoggedInUser().getChats().size();
        		chatListContainer = new JPanel();
        		chatListContainer.setLayout(new GridLayout(numChats, 1, 0, 0));
        		chatListContainer.setPreferredSize(new Dimension(50, numChats * 100));
        		chatListScrollPane = new JScrollPane(chatListContainer);
        		chatListPanel.add(chatListScrollPane, BorderLayout.CENTER);
            	
            	for (Chat c : client.getLoggedInUser().getChats()) {
            		System.out.println("Adding chats");
            		chatListContainer.add(new ChatPanel(c));
            	}
            }
        });
        */
		
		numChats = client.getLoggedInUser().getChats().size();
		chatListContainer = new JPanel();
		chatListContainer.setLayout(new GridLayout(numChats, 1, 0, 0));
		chatListContainer.setPreferredSize(new Dimension(50, numChats * 100));
		chatListScrollPane = new JScrollPane(chatListContainer);
		chatListPanel.add(chatListScrollPane, BorderLayout.CENTER);
    	
    	for (Chat c : client.getLoggedInUser().getChats()) {
    		//System.out.println("Adding chats");
    		chatListContainer.add(new ChatPanel(c));
    	}
	}
	
	public void chatClicked(Chat chat) {
		//TODO switch to Client Chat GUI
		client.setSelectedChat(chat);
		
		System.out.println(chat.getChatName());
	}
	
	class ChatPanel extends JPanel {
		private JPanel northPanel;
		private JLabel chatName;
		private JButton deleteChatButton;
		
		private JPanel centerPanel;
		private JLabel lastUserLabel;
		private JLabel lastMessageLabel;
		
		private Chat chat;
		
		public ChatPanel(Chat chat) {
			this.chat = chat;
			northPanel = new JPanel();
			northPanel.setLayout(new FlowLayout());
			
			chatName = new JLabel(chat.getChatName());
			chatName.setHorizontalAlignment(SwingConstants.LEFT);
			chatName.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 40));
			deleteChatButton = new JButton("X");
			deleteChatButton.setHorizontalAlignment(SwingConstants.RIGHT);
			deleteChatButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			
			northPanel.add(chatName);
			northPanel.add(deleteChatButton);
			
			centerPanel = new JPanel();
			centerPanel.setLayout(new FlowLayout());
			
			int chatSize = chat.getChatContent().size();
			
			lastUserLabel = new JLabel("");
			lastMessageLabel = new JLabel("");
			
			if (chatSize > 0) {
				lastUserLabel = new JLabel(chat.getChatContent().get(chatSize - 1).getHandle() + ": ");
				lastMessageLabel = new JLabel(chat.getChatContent().get(chatSize - 1).getContent());
			}
			
			lastUserLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
			
			centerPanel.add(lastUserLabel);
			centerPanel.add(lastMessageLabel);
			
			this.setLayout(new GridLayout(2, 1, 0, 0));
			this.add(northPanel);
			this.add(centerPanel);
			this.setMaximumSize(new Dimension(200, 100));
			this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1, false));
			
			this.addMouseListener(new MouseListener() {

				@Override
				public void mouseClicked(MouseEvent e) {
					chatClicked(chat);
				}

				@Override
				public void mouseEntered(MouseEvent e) {}

				@Override
				public void mouseExited(MouseEvent e) {}

				@Override
				public void mousePressed(MouseEvent e) {}

				@Override
				public void mouseReleased(MouseEvent e) {}
			});
		}
	}
}
