import java.io.*;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Client {
	
	public final int WINDOW_WIDTH = 800;
	public final int WINDOW_HEIGHT = 600;
	public String[] PANEL_CHOICES = new String[] {"Login Panel", "Chat List Panel", "Chat Panel"};
	
	private JFrame frame;
	private JPanel contentPane;
	
	private JPanel clientLoginPanel;
	
	public Client() {
		
	}

    public boolean verifySignIn(Packet p) {
        return p.isVerified();
    }

    public boolean verifyNewUser(Packet p) {
        return p.isVerified();
    }

    public boolean verifyNewChat(Packet p) {
        return p.isVerified();
    }

    public boolean verifyChangeUserName(Packet p) {
        return p.isVerified();
    }

    public boolean verifyChangeChatName(Packet p) {
        return p.isVerified();
    }
    
    public static void main(String[] args) throws IOException {
    	//Socket socket = new Socket("127.0.0.1", 5555); //handle this later
    	//BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    	//PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        //JOptionPane.showMessageDialog(null, "Welcome to WSC", "WSC", JOptionPane.INFORMATION_MESSAGE);
    	
    	SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
            	new Client().displayGUI();
            }
        });
    }
    
    public JPanel getContentPane() {
    	return this.contentPane;
    }
    
    private void displayGUI()
    {
        JFrame frame = new JFrame("Card Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(
            BorderFactory.createEmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new CardLayout());  
        
        clientLoginPanel = new ClientLoginGUI(this.WINDOW_WIDTH, this.WINDOW_HEIGHT);
        contentPane.add(clientLoginPanel, this.PANEL_CHOICES[0]);
        
        frame.getContentPane().add(contentPane, BorderLayout.CENTER);       
        frame.pack();   
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
}
