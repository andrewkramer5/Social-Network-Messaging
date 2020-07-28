import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
	
	private JFrame frame;
	private JPanel contentPane;
	private JPanel clientLoginPanel;
	private JPanel clientChatListPanel;
	
	private Client client;
	
	public ClientApplication(Client client) {
		this.client = client;
	}
	
	public static void main(String[] args) throws IOException {
		Client client = new Client();
		ClientApplication app = new ClientApplication(client);
    	
    	SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	app.displayGUI();
            }
        });
    }
	
	private void displayGUI()
    {
        JFrame frame = new JFrame("WSC Messenger");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel();
        contentPane.setBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());  
        
        clientLoginPanel = new ClientLoginGUI(this);
        contentPane.add(clientLoginPanel, this.PANEL_CHOICES[0]);
        clientChatListPanel = new ClientChatListGUI(this);
        contentPane.add(clientChatListPanel, this.PANEL_CHOICES[1]);
        
        frame.getContentPane().add(contentPane, BorderLayout.CENTER);       
        frame.pack();   
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
	
	public void setPanel(String panelName) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CardLayout cardLayout = (CardLayout) contentPane.getLayout();
                cardLayout.show(contentPane, panelName);
			}
		});
	}
	
	public JPanel getContentPane() {
    	return this.contentPane;
    }
}
