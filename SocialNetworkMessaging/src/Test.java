import java.io.*;

import java.net.*;
/**
 * Test class was used to test the server. It also indirectly tests all other classes
 * that are non-GUI related, (Chat, Packet, User, Message, Client, and WriteToFile).
 * Tests follow a logical order, I.E. send message is not tested until two users are
 * created and have been added as friends.
 * 
 * <p>Purdue University -- CS18000 -- Summer 2020 -- Project 5 -- Messaging Service</p>
 * 
 * @author Raj Karra
 * @author Evan Hendrich
 * @version July 31, 2020
 */

public class Test {
    String tempHandle;
    User u;
    Client c;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket s = new Socket("localhost", 4200);

        Client c = new Client(s);

        Test t = new Test(c);

        t.addFirstUser();

        t.addBadUser();

        t.addSecondUser();

        t.addFirstFriend();
        
        t.showData("ehendrich");
        
        t.removeFriend();

        t.addNonExistentFriend();

        t.addAlreadyFriend();

        t.addDirectMessageWithoutName();

        t.addDirectMessageWithName();

        t.addChatWithAlreadyExistingDefaultName();

        t.addChatWithAlreadyExistingCustomName();

        t.signIn();

        t.signInWithNonExistingUsername();

        t.signInWithIncorrectPassword();

        t.showData("ehendrich");

        //for some reason this method (as well as addMessage) do not instantly
        //update on the server. However, if you run the Test class again, it works
        //and other data (users etc...) are saved as well, but the other data saves
        //the first time around. It is confusing. Message me if you have any questions
        //but right now I don't get why its happening.
        //t.changePassword();

        //t.showData("ehendrich");

        t.addMessageFromFirstUser();

        t.addMessageFromSecondUser();

        t.addAnotherMessageFromFirstUser();

        t.addAnotherMessageFromSecondUser();

        t.changeHandles();

        t.showData("rajesh");

        t.editChat();

        //t.showData("raj_the_baller");

        t.deleteMessage();

//        t.showData("rajesh");
//
        t.changeChatName();
////
//        t.showData("rajesh");
////
        t.deleteChat();
////
////        //t.showData("raj_the_baller");
////
        t.deleteUser();
//
//        t.showData("rajesh");
        
        c.sendPacket(new Packet("end", "now"));
        
        s.close();
        

    }

    public Test(Client c) {
        this.c = c;
    }

    public void addFirstUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addUser", "ehendrich", "broccoli11");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempt to create account with username ehendrich");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addBadUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addUser", "ehendrich", "brocco");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempt to recreate account with username ehendrich");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addSecondUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addUser", "raj_the_baller", "balling_every_day");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempt to create account with username raj_the_baller");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addFirstFriend() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addFriend", "ehendrich", "raj_the_baller");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("ehendrich attempted to add raj_the_baller as a friend");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addNonExistentFriend() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addFriend", "ehendrich", "raj_the_lame_guy");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("ehendrich attempted to add raj_the_lame_guy as a friend");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addAlreadyFriend() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addFriend", "ehendrich", "raj_the_baller");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("ehendrich attempted to add raj_the_baller as a friend");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addDirectMessageWithoutName() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"},
                "");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempt to create chat with default name between "
                + "ehendrich and raj_the_baller");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addDirectMessageWithName() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"},
                "Coding buds");

        c.sendPacket(p);

        //Code breaks here

        Packet verify = c.receivePacket();

        System.out.println("Attempt to create chat called Coding buds between"
                + " ehendrich and raj_the_baller");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }


    public void addChatWithAlreadyExistingDefaultName() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"},
                "");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempt to recreate chat with default name between"
                + " ehendrich and raj_the_baller");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void addChatWithAlreadyExistingCustomName() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addChat", new String[] {"ehendrich", "raj_the_baller"},
                "Coding buds");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempt to recreate chat called Coding buds between"
                + " ehendrich and raj_the_baller");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void signIn() throws IOException, ClassNotFoundException {
        Packet p = new Packet("signIn", "ehendrich", "broccoli11");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempted to sign in with correct information");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void signInWithNonExistingUsername() throws IOException, ClassNotFoundException {
        Packet p = new Packet("signIn", "bob", "broccoli11");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempted to sign in with nonexistent username");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void signInWithIncorrectPassword() throws IOException, ClassNotFoundException {
        Packet p = new Packet("signIn", "ehendrich", "wrong");

        c.sendPacket(p);

        Packet verify = c.receivePacket();

        System.out.println("Attempted to sign in with incorrect password");

        if (verify.isVerified()) {
            System.out.println(verify.getDescription());
        } else {
            System.out.println(verify.getDescription());
        }
        System.out.println();
    }

    public void changePassword() throws IOException, ClassNotFoundException {
        Packet p = new Packet("changePassword", "ehendrich", "newPassword");

        c.sendPacket(p);

        Packet verify = c.receivePacket();  
        
        System.out.println(verify.getDescription());      

        System.out.println("Attempt to change password to newPassword");

        System.out.println();
    }

    public void addMessageFromFirstUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "Coding buds", new Message("ehendrich", "Hello Raj"));

        c.sendPacket(p);

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());

        System.out.println("Attempt to send message from ehendrich");

        System.out.println();
    }

    public void addMessageFromSecondUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "Coding buds", new Message("raj_the_baller", "Hello Evan"));

        c.sendPacket(p);

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());

        System.out.println("Attempt to send message from raj_the_baller");

        System.out.println();
    }

    public void addAnotherMessageFromFirstUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "ehendrich, raj_the_baller", new Message("ehendrich", "How are you?"));

        c.sendPacket(p);

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());

        System.out.println("Attempt to send another message from ehendrich");

        System.out.println();
    }

    public void addAnotherMessageFromSecondUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "ehendrich, raj_the_baller", 
        		  new Message("raj_the_baller", "I am quite well"));

        c.sendPacket(p);

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());

        System.out.println("Attempt to send another message from raj_the_baller");

        System.out.println();
    }

    public void changeHandles() throws IOException , ClassNotFoundException {
        c.sendPacket(new Packet("changeHandle", "ehendrich", "rajesh"));
        Packet p = c.receivePacket();
        System.out.println("attempted to change handle");
        System.out.println(p.isVerified());
        System.out.println();
    }

    public void deleteChat() throws IOException , ClassNotFoundException {
        c.sendPacket(new Packet("deleteChat", "rajesh", "Mad Lads"));

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());
        
        System.out.println("chat deleted");
    }

    public void deleteUser() throws IOException , ClassNotFoundException {
        c.sendPacket(new Packet("deleteUser", "raj_the_baller"));

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());
    }

    public void editChat() throws IOException , ClassNotFoundException {
    	Packet p = new Packet("editMessage", "rajesh, raj_the_baller", new Message("rajesh", "How are you?"), 
    			  new Message("rajesh", "foo bar"));
    	
        c.sendPacket(p);

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());
        
        System.out.println("packet sent");

    }

    public void deleteMessage() throws IOException , ClassNotFoundException {
        c.sendPacket(new Packet("deleteMessage", "Coding buds", new Message("rajesh", "Hello Raj")));

        Packet verify = c.receivePacket();
        
        System.out.println(verify.getDescription());
    }

    public void changeChatName() throws IOException, ClassNotFoundException {
        c.sendPacket(new Packet("changeChatName", "ehendrich", "Coding buds", "Mad Lads"));
        Packet p = c.receivePacket();
        System.out.println("Trying to change chat name");
        System.out.println(p.isVerified());
        System.out.println(p.getDescription());
    }
    
    public void removeFriend() throws IOException, ClassNotFoundException {
    	Packet p = new Packet("removeFriend", "ehendrich", "raj_the_baller");
    	c.sendPacket(p);
    	Packet poo = c.receivePacket();
    	
    	System.out.println(poo.isVerified());
    }

    public void showData(String handle) throws IOException, ClassNotFoundException {
        Packet p = new Packet("update", handle);

        c.sendPacket(p);

        User uu = c.receiveUser();

        System.out.println("Handle:");

        System.out.println(uu.getHandle());

        System.out.println();

        System.out.println("Password:");

        System.out.println(uu.getPassword());

        System.out.println();

        System.out.println("Friends:");
        int i = 1;
        for (User user : uu.getFriends()) {
            System.out.println(i + ". " + user.getHandle());
            i++;
        }

        System.out.println();

        System.out.println("Chats:");
        int a = 1;
        //System.out.println(u.getChats().get(0).getChatContent().size());
//        System.out.println(u.getChats().get(1).getChatContent().size());

        for (Chat chat : u.getChats()) {
            System.out.println(a + ". " + chat.getChatName());
            if (chat.getChatContent() != null) {
                for (Message m : chat.getChatContent()) {
                    System.out.print(m.getHandle() + ":");
                    System.out.println(m.getContent());
                }
            } else {
                System.out.println("It was null");
            }
            a++;
        }

        System.out.println();

    }
}