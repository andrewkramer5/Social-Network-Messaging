import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class WSCServer implements Runnable, DataStorage {
	
	Socket socket;
	
	
	public WSCServer(Socket socket) {
		this.socket = socket;
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
    	
    	ServerSocket serverSocket = new ServerSocket(4200);
    	
    	while (true) {
    		Socket socket = serverSocket.accept();
    		
    		WSCServer server = new WSCServer(socket);
    				
    		new Thread(server).start();    		
    	}
    }
	
	public void run() {
		try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
    			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
			while (true) {
				Packet packet = (Packet) ois.readObject();
				
				switch (packet.getIdentifier()) {
				
				case "deleteChat" :
					data.deleteChat(packet);
					
				case "signIn" :
					data.signIn(packet);
					
				case "addUser" :
					data.addUser(packet);
					
				case "deleteUser" :
					data.deleteUser(packet);
					
				case "changeHandle" :
					data.changeHandle(packet);
					
				case "changeChatName" :
					data.changeChatName(packet);
					
				case "addFriend" :
					data.addFriend(packet);
					
				case "deleteMessage" :
					data.deleteMessage(packet);
					
				case "editMessage" : 
					data.editMessage(packet);
					
				case "addChat" :
					data.addChat(packet);
					
				case "update" :
					oos.writeObject(data.getConversations());
					oos.writeObject(data.getUser(packet.getHandle()));
				
				default : 
					return;
				}
				
			}
		} catch (IOException e) {

    	} catch (ClassNotFoundException a) {

    	}
	}
}
