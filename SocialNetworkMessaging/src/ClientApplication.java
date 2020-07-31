import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Client Application
 *
 * The program the client runs
 *
 * @author Andrew Kramer 
 *
 * @version July 27, 2020
 *
 */
public class ClientApplication {
	
	public final int WINDOW_WIDTH = 700;
	public final int WINDOW_HEIGHT = 500;
	public String[] PANEL_CHOICES = new String[] {"Login Panel", "Chat List Panel", "Chat Panel"};
	public String currentPanel;
	
	private JFrame frame;
	private JPanel contentPane;
	private ClientLoginGUI clientLoginPanel;
	private ClientChatListGUI clientChatListPanel;
	
	private Client client;
	private User loggedInUser;
	private Chat selectedChat;
	
	public ClientApplication() {
		this.client = null;
		this.currentPanel = this.PANEL_CHOICES[0];
	}
	
	public static void main(String[] args) throws IOException {
		
		ClientApplication app = new ClientApplication();
		
		//testing
		app.setLoggedInUser(new User("", ""));
		app.loggedInUser.addFriend(new User("Raj", "1234"));
		app.loggedInUser.addFriend(new User("Evan", "5678"));
		app.loggedInUser.addChat(new Chat(app.loggedInUser.getFriends(), "CS Project Chat"));
		app.loggedInUser.addChat(new Chat(app.loggedInUser.getFriends(), "Group Chat #1"));
		app.loggedInUser.addChat(new Chat(app.loggedInUser.getFriends(), "Group Chat #2"));
		app.loggedInUser.addChat(new Chat(app.loggedInUser.getFriends(), "Group Chat #3"));
		app.loggedInUser.addChat(new Chat(app.loggedInUser.getFriends(), "Group Chat #4"));
		app.loggedInUser.addChat(new Chat(app.loggedInUser.getFriends(), "Group Chat #5"));
		app.loggedInUser.getChats().get(0).addMessage(new Message("Raj", "Hello guys"));
		//end testing
    	
    	SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	app.displayGUI();
            }
        });
    	
    	app.setPanel(app.PANEL_CHOICES[1]);
    	
    	try {
    		Client client = new Client(new Socket("127.0.0.1", 4200));
    		app.setClient(client);
    	} catch (ConnectException e) {
    		JOptionPane.showMessageDialog(null, "Unable to connect to server!", 
    				"WSC Messenger Error", JOptionPane.ERROR_MESSAGE);
    	}
    	
    	//TODO communicate with server
    	//TODO call update on gui
    }
	
	private void displayGUI()
    {
        JFrame frame = new JFrame("WSC Messenger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());  
        
        clientLoginPanel = new ClientLoginGUI(this);
        contentPane.add(clientLoginPanel, this.PANEL_CHOICES[0]);
        clientChatListPanel = new ClientChatListGUI(this);
        contentPane.add(clientChatListPanel, this.PANEL_CHOICES[1]);
        
        CardLayout cardLayout = (CardLayout) contentPane.getLayout();
        cardLayout.show(contentPane, this.currentPanel);
        
        frame.getContentPane().add(contentPane, BorderLayout.CENTER);       
        frame.pack();   
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void setPanel(String panelName) {
		this.currentPanel = panelName;
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, panelName);
                
                //TODO when switching panels clear the data and update
                if (panelName.equals(PANEL_CHOICES[0])) {
                	clientLoginPanel.clearFields();
                	clientLoginPanel.update();
                } else if (panelName.equals(PANEL_CHOICES[1])) {
                	clientChatListPanel.update();
                	clientChatListPanel.updateChats();
                } else if (panelName.equals(PANEL_CHOICES[2])) {
                	
                }
			}
		});
	}
	
	public JPanel getContentPane() {
    	return this.contentPane;
    }
	
	public void setLoggedInUser(User user) {
		this.loggedInUser = user;
	}
	
	public User getLoggedInUser() {
		return this.loggedInUser;
	}
	
	public ClientLoginGUI getClientLoginGUI() {
		return this.clientLoginPanel;
	}
	
	public ClientChatListGUI getClientChatListGUI() {
		return this.clientChatListPanel;
	}
	
	public JFrame getFrame() {
		return this.frame;
	}
	
	public Chat getSelectedChat() {
		return this.selectedChat;
	}
	
	public void setSelectedChat(Chat selectedChat) {
		this.selectedChat = selectedChat;
	}
}
