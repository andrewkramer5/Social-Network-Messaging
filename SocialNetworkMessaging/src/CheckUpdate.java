import java.net.*;
//this is the inner class to get updated messages
public class CheckUpdate implements Runnable {

    @Override
    public void run() {
        try {
            Client c = new Client(new Socket("localhost",4200));
            while(true) {
                c.sendPacket(new Packet("update"));
                User u = c.reciveUser(); //in the actual class, make u a instance variable of the outer class
                //now we compare the user to the user field or something
            }
        }
        catch (Exception e) {
            System.exit(0); //change later
        }
    }
}
