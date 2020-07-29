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
	
	public ClientApplication() {
		this.client = null;
		this.currentPanel = this.PANEL_CHOICES[0];
	}
	
	public static void main(String[] args) throws IOException {
		
		ClientApplication app = new ClientApplication();
		app.setLoggedInUser(new User("", ""));
    	
    	SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	app.displayGUI();
            }
        });
    	
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
                
                //TODO when switching panels clear the data from previous panel
                if (panelName.equals(PANEL_CHOICES[0])) {
                	clientLoginPanel.clearFields();
                } else if (panelName.equals(PANEL_CHOICES[1])) {
                	
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
}
