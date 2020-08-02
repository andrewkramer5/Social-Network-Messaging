import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Client Chat GUI
 *
 * JPanel that holds the form for listing the user's selected chat
 *
 * @author Andrew Kramer
 *
 * @version August 1, 2020
 *
 */
public class ClientChatGUI extends JPanel {
	
	public final int WINDOW_WIDTH;
    public final int WINDOW_HEIGHT;

    ClientApplication app;
    private int numMembers;
    private ArrayList<String> sentMessages;
    
    JPanel backButtonPanel;
    JButton backButton;
    JButton editButton;
    JButton deleteButton;
    
    JPanel chatPanel;
    JLabel titleLabel;
    JScrollPane chatScrollPane;
    JTextArea chatArea;
    JPanel sendMessagePanel;
    JTextField messageField;
    JButton sendMessageButton;
    
    JPanel memberListPanel;
    JLabel textMemberLabel;
    JScrollPane memberListScrollPane;
    JPanel memberListContainer;
	
	public ClientChatGUI(ClientApplication app) {
		this.numMembers = app.getSelectedChat().getChatMembers().size();
        this.app = app;
        this.WINDOW_WIDTH = app.WINDOW_WIDTH;
        this.WINDOW_HEIGHT = app.WINDOW_HEIGHT;
        
        //Back Button Panel
        backButtonPanel = new JPanel();
        backButtonPanel.setLayout(new BorderLayout());
        
        backButton = new JButton("< Back");
        backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				app.setPanel(app.PANEL_CHOICES[1]);
			}
        });
        backButtonPanel.add(backButton, BorderLayout.NORTH);
        
        JPanel editDeletePanel = new JPanel();
        editDeletePanel.setLayout(new GridLayout(2, 1, 5, 10));
        
        editButton = new JButton("Edit Messages");
        editButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sentMessages != null) {
					if (sentMessages.size() != 0) {
						String selected = (String) JOptionPane.showInputDialog(app.getClientChatGUI(), 
								"Please select which message you want to edit", 
								"WSC Messenger", JOptionPane.QUESTION_MESSAGE, null, 
								sentMessages.toArray(), sentMessages.toArray()[0]);
						String newMessage = JOptionPane.showInputDialog(app.getClientChatGUI(), 
								"Enter replacement message", "WSC Messenger", JOptionPane.QUESTION_MESSAGE);
						//TODO send edit message packet
						//TODO receive verify packet
						//TODO send update packet
						//TODO set loggedInUser
						//TODO updateChats();
					} else {
						JOptionPane.showMessageDialog(null, "No messages sent to edit!",
			                    "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No messages sent to edit!",
		                    "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
				}
			}
        });
        editDeletePanel.add(editButton);
        
        deleteButton = new JButton("Delete Messages");
        deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sentMessages != null) {
					if (sentMessages.size() != 0) {
						String selected = (String) JOptionPane.showInputDialog(app.getClientChatGUI(), 
								"Please select which message you want to delete", 
								"WSC Messenger", JOptionPane.QUESTION_MESSAGE, null, 
								sentMessages.toArray(), sentMessages.toArray()[0]);
						//TODO send edit message packet
						//TODO receive verify packet
						//TODO send update packet
						//TODO set loggedInUser
						//TODO updateChats();
					} else {
						JOptionPane.showMessageDialog(null, "No messages sent to edit!",
			                    "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(null, "No messages sent to edit!",
		                    "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
				}
			}
        });
        editDeletePanel.add(deleteButton);
        
        backButtonPanel.add(editDeletePanel, BorderLayout.SOUTH);
        //End Back Button Panel
        
        //Chat Panel
        chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        
        titleLabel = new JLabel(app.getSelectedChat().getChatName());
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        chatPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        
        sendMessagePanel = new JPanel();
        messageField = new JTextField(30);
        sendMessageButton = new JButton("Send");
        sendMessageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (!messageField.getText().equals("")) {
					String message = messageField.getText();
					//TODO send add message packet to server
					//TODO receive verify packet
					//TODO send update packet
					//TODO set loggedInUser
					//TODO updateChats();
					messageField.setText("");
				} else {
					JOptionPane.showMessageDialog(null, "Can't send empty message!",
                            "WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
				}
			}
        });
        sendMessagePanel.add(messageField);
        sendMessagePanel.add(sendMessageButton);
        chatPanel.add(sendMessagePanel, BorderLayout.SOUTH);

        //chatPanel.setBackground(Color.green);
        //End Chat Panel
        
        //Friend List Panel
        memberListPanel = new JPanel();
        memberListPanel.setLayout(new BorderLayout());

        textMemberLabel = new JLabel("Members List");
        textMemberLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        memberListPanel.add(textMemberLabel, BorderLayout.NORTH);

        memberListPanel.setBackground(Color.white);
        memberListPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        //End Friend List Panel
        
        this.setLayout(new BorderLayout());
        this.add(backButtonPanel, BorderLayout.WEST);
        this.add(chatPanel, BorderLayout.CENTER);
        this.add(memberListPanel, BorderLayout.EAST);
        this.setPreferredSize(new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT));
	}
	
	public void update() {
		//TODO loggedInUser needs to be updated with new value from server using update packet
		titleLabel.setText(app.getSelectedChat().getChatName());
        
		numMembers = app.getLoggedInUser().getFriends().size();
        memberListContainer = new JPanel();
        memberListContainer.setLayout(new GridLayout(numMembers, 1, 0, 5));
        memberListScrollPane = new JScrollPane(memberListContainer);
        memberListPanel.add(memberListScrollPane, BorderLayout.CENTER);

        for (User u : app.getSelectedChat().getChatMembers()) {
            //System.out.println("Adding chats");
            JLabel l = new JLabel(u.getHandle());
            l.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
            l.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
            memberListContainer.add(l);
        }
	}
	
	public void updateChats() {
		//TODO selected chat needs to be updated with a new value from receiving new loggedInUser value from update packet
		chatArea = new JTextArea();
		chatArea.setEditable(false);
        chatScrollPane = new JScrollPane(chatArea);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        sentMessages = new ArrayList<String>();
		
		for (Message message : app.getSelectedChat().getChatContent()) {
			//19 characters and then indent
			
			if (message.getHandle().equals(app.getLoggedInUser().getHandle())) {
				sentMessages.add(message.getContent());
				//If message was sent by logged in user
				String temp = message.getContent();
				StringBuilder sb = new StringBuilder();
				
				for (int i = 0; i < temp.length(); i++) {
					if ((i % 18) == 0 && temp.length() > 19) {
						sb.append(temp.substring(0, 19) + "\n");
						sb.append("\t\t\t\t");
						temp = temp.substring(19);
					}
				}
				sb.append(temp);
				
				chatArea.append("\t\t\t\t" + sb.toString() + "\n");
			} else {
				String temp = message.getHandle() + ": " + message.getContent();
				StringBuilder sb = new StringBuilder();
				
				for (int i = 0; i < temp.length(); i++) {
					if ((i % 18) == 0 && temp.length() > 19) {
						sb.append(temp.substring(0, 19) + "\n");
						sb.append("   ");
						temp = temp.substring(19);
					}
				}
				sb.append(temp);
				
				chatArea.append("  " + sb.toString() + "\n");
			}
		}
	}
}
