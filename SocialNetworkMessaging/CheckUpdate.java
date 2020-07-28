import java.net.*;
//this is the inner class to get updated messages
public class CheckUpdate implements Runnable {
    @Override
    public void run() {
        try {
            Client c = new Client(new Socket("localhost",4200));
            while(true) {
                c.sendPacket(new Packet("update"));
                Packet p = c.recivePacket();
                if(!p.isVerified()) {
                    //handle
                }
                Packet m = c.recivePacket(); //this is the message
            }
        }
        catch (Exception e) {
            //handle later
        }
    }
}
