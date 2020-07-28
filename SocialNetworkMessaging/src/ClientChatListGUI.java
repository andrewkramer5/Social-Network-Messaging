import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

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
	
	JLabel titleLabel;
	JScrollPane chatListPane;
	JPanel accountInfoPane;
	
	public ClientChatListGUI(ClientApplication client) {
		
		this.WINDOW_WIDTH = client.WINDOW_WIDTH;
		this.WINDOW_HEIGHT = client.WINDOW_HEIGHT;
		
		this.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		titleLabel = new JLabel("WSC Messenger");
		titleLabel.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.ipadx = 20;
		c.ipady = 100;
		this.add(titleLabel, c);
		
		chatListPane = new JScrollPane();
		chatListPane.setLayout(new ScrollPaneLayout());
		c.gridx = 1;
		c.gridy = 1;
		this.add(chatListPane, c);
		
		accountInfoPane = new JPanel();
		c.gridx = 0;
		c.gridy = 1;
		this.add(accountInfoPane, c);
		
		this.setBorder(BorderFactory.createEmptyBorder(90, 0, 90, 0));
		this.setOpaque(true);
		this.setPreferredSize(new Dimension(this.WINDOW_WIDTH, this.WINDOW_HEIGHT));
	}
}
