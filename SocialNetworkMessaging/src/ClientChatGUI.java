import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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
    private int numMessages;
    
    JPanel backButtonPanel;
    JButton backButton;
    
    JPanel chatPanel;
    JLabel titleLabel;
    JScrollPane chatScrollPane;
    JPanel chatScrollContainer;
    JPanel sendMessagePanel;
    JTextField messageField;
    JButton sendMessageButton;
    
    JPanel memberListPanel;
    JLabel textMemberLabel;
    JScrollPane memberListScrollPane;
    JPanel memberListContainer;
	
	public ClientChatGUI(ClientApplication app) {
		this.numMembers = app.getSelectedChat().getChatMembers().size();
		this.numMessages = app.getSelectedChat().getChatContent().size();
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
        //End Back Button Panel
        
        //Chat Panel
        chatPanel = new JPanel();
        chatPanel.setLayout(new BorderLayout());
        
        titleLabel = new JLabel(app.getSelectedChat().getChatName());
        titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 5));
        chatPanel.add(titleLabel, BorderLayout.NORTH);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        chatScrollContainer = new JPanel();
        chatScrollPane = new JScrollPane(chatScrollContainer);
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
        
        sendMessagePanel = new JPanel();
        messageField = new JTextField(30);
        sendMessageButton = new JButton("Send");
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
		//Set title to chat title
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
		//TODO selected chat needs to be updated with a new value from server before updateChats() is called
		numMessages = app.getSelectedChat().getChatContent().size() + 1;
		chatScrollContainer = new JPanel();
        chatScrollPane = new JScrollPane(chatScrollContainer);
        //chatScrollContainer.setLayout(new GridLayout(numMessages, 1, 0, 0));
        chatScrollContainer.setLayout(new BoxLayout(chatScrollContainer, BoxLayout.PAGE_AXIS));
        chatScrollContainer.add(Box.createHorizontalGlue());
        chatScrollContainer.setPreferredSize(new Dimension(50, numMessages * 20));
        chatPanel.add(chatScrollPane, BorderLayout.CENTER);
		
		for (Message message : app.getSelectedChat().getChatContent()) {
			JPanel messagePanel = new JPanel();
			JLabel nameLabel = new JLabel(message.getHandle() + ": ");
			nameLabel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
			JLabel messageLabel = new JLabel(message.getContent());
			JButton editButton = new JButton("...");
			
			if (message.getHandle().equals(app.getLoggedInUser().getHandle())) {
				//If message was sent by logged in user
				nameLabel = new JLabel(": " + message.getHandle());
				nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				editButton.setHorizontalAlignment(SwingConstants.RIGHT);
				//editButton.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
				messageLabel.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
				messagePanel.add(messageLabel);
				messagePanel.add(nameLabel);
				messagePanel.add(editButton);
			} else {
				nameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				messagePanel.add(nameLabel);
				messageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
				messageLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 80));
				messagePanel.add(messageLabel);
			}
			chatScrollContainer.add(messagePanel);
		}
		
		JPanel emptyPanel = new JPanel();
		emptyPanel.setSize(100, 200);
	}
}
