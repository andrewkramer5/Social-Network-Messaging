import java.io.*;
import java.net.*;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.rules.Timeout;

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
        t.changePassword();

        t.showData("ehendrich");

        t.addMessageFromFirstUser();

        t.addMessageFromSecondUser();

        t.addAnotherMessageFromFirstUser();

        t.addAnotherMessageFromSecondUser();

        t.changeHandles();

        t.showData("rajesh");

        t.editChat();

        t.showData("raj_the_baller");

        t.deleteMessage();

        t.showData("rajesh");

        t.changeChatName();

        t.showData("rajesh");

        t.deleteChat();

        t.showData("raj_the_baller");

        t.deleteUser();

        t.showData("rajesh");

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
        Packet p = new Packet("addChat", new String[]{"ehendrich", "raj_the_baller"},
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
        Packet p = new Packet("addChat", new String[]{"ehendrich", "raj_the_baller"},
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
        Packet p = new Packet("addChat", new String[]{"ehendrich", "raj_the_baller"},
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
        Packet p = new Packet("addChat", new String[]{"ehendrich", "raj_the_baller"},
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

        System.out.println("Attempt to change password to newPassword");

        System.out.println();
    }

    public void addMessageFromFirstUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "Coding buds", new Message("ehendrich", "Hello Raj"));

        c.sendPacket(p);

        System.out.println("Attempt to send message from ehendrich");

        System.out.println();
    }

    public void addMessageFromSecondUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "Coding buds", new Message("raj_the_baller", "Hello Evan"));

        c.sendPacket(p);

        System.out.println("Attempt to send message from raj_the_baller");

        System.out.println();
    }

    public void addAnotherMessageFromFirstUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "ehendrich, raj_the_baller", new Message("ehendrich", "How are you?"));

        c.sendPacket(p);

        System.out.println("Attempt to send another message from ehendrich");

        System.out.println();
    }

    public void addAnotherMessageFromSecondUser() throws IOException, ClassNotFoundException {
        Packet p = new Packet("addMessage", "ehendrich, raj_the_baller", new Message("raj_the_baller", "I am quite well"));

        c.sendPacket(p);

        System.out.println("Attempt to send another message from raj_the_baller");

        System.out.println();
    }

    public void changeHandles() throws IOException, ClassNotFoundException {
        c.sendPacket(new Packet("changeHandle", "ehendrich", "rajesh"));
        Packet p = c.receivePacket();
        System.out.println("attempted to change handle");
        System.out.println(p.isVerified());
        System.out.println();
    }

    public void deleteChat() throws IOException {
        c.sendPacket(new Packet("deleteChat", "Mad Lads"));
        System.out.println("chat deleted");
    }

    public void deleteUser() throws IOException {
        c.sendPacket(new Packet("deleteUser", "raj_the_baller"));
    }

    public void editChat() throws IOException {
        c.sendPacket(new Packet("editMessage", new Message("ehendrich", "How are you?"),
                new Message("ehendrich", "foo bar")));

    }

    public void deleteMessage() throws IOException {
        c.sendPacket(new Packet("deleteMessage", "Coding buds", new Message("ehendrich", "How are you?")));
    }

    public void changeChatName() throws IOException, ClassNotFoundException {
        c.sendPacket(new Packet("changeChatName", "ehendrich", "Coding buds", "Mad Lads"));
        Packet p = c.receivePacket();
        System.out.println("Trying to change chat name");
        System.out.println(p.isVerified());
        System.out.println(p.getDescription());
    }

    public void showData(String handle) throws IOException, ClassNotFoundException {
        Packet p = new Packet("update", handle);

        c.sendPacket(p);

        User u = c.receiveUser();

        System.out.println("Handle:");

        System.out.println(u.getHandle());

        System.out.println();

        System.out.println("Password:");

        System.out.println(u.getPassword());

        System.out.println();

        System.out.println("Friends:");
        int i = 1;
        for (User user : u.getFriends()) {
            System.out.println(i + ". " + user.getHandle());
            i++;
        }

        System.out.println();

        System.out.println("Chats:");
        int a = 1;
        System.out.println(u.getChats().get(0).getChatContent().size());
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

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(TestCase.class);
        if (result.wasSuccessful()) {
            System.out.println("Excellent - Test ran successfully");
        } else {
            for (Failure failure : result.getFailures()) {
                System.out.println(failure.toString());
            }
        }
    }

    public class PacketTest {
        @Test(timeout = 1000)
        public void getIdentifier() {
            Packet packet = new Packet();
            assertNotnull(getIdentifier());
        }

        @Test(timeout = 1000)
        public void getHandle() {
            Packet packet = new Packet();
            assertEquals("abc", packet.getHandle());
        }

        @Test(timeout = 1000)
        public void getNewHandle() {
            Packet packet = new Packet();
            assertEquals("rajesh", packet.getNewHandle());
        }

        @Test(timeout = 1000)
        public void getPassword() {
            Packet packet = new Packet();
            assertEquals("ehendrich", packet.getPassword());
        }

        @Test(timeout = 1000)
        public void getChatName() {
            Packet packet = new Packet();
            assertEquals("raj_the_baller", packet.getChatName());
        }

        @Test(timeout = 1000)
        public void getNewChatName() {
            Packet packet = new Packet();
            assertEquals("rajesh", packet.getChatName());
        }

        @Test(timeout = 1000)
        public void getMessage() {
            Packet packet = new Packet();
            assertEquals("Hello Evan", packet.getMessage());
        }

        @Test(timeout = 1000)
        public void getOldMessage() {
            Packet packet = new Packet();
            assertEquals("Hello Raj", packet.getOldMessage());
        }

        @Test(timeout = 1000)
        public void getHandles() {
            Packet packet = new Packet();
            assertEquals("ehendrich", packet.getHandles());
        }

        @Test(timeout = 1000)
        public void isVerified() {
            Packet packet = new Packet();
            assertEquals("ehendrich", packet.isVerified());
            assertEquals("rajesh", packet.isVerified());
        }

        @Test(timeout = 1000)
        public void getDescription() {
            Packet packet = new Packet();
            assertNotNull(packet.getDescription());
        }

        @Test(timeout = 1000)
        public void getFriendHandle() {
            Packet packet = new Packet();
            assertNotNull(packet.getFriendHandle());
        }
    }

    public class UserTest {
        @Test(timeout = 1000)
        public void getHandle() {
            User user = new User();
            assertEquals("rajesh", user.getNewHandle());
        }

        @Test(timeout = 1000)
        public void getPassword() {
            User user = new User();
            assertEquals("ehendrich", user.getPassword());
        }

        @Test(timeout = 1000)
        public void getFriends() {
            User user = new User();
            assertEquals("raj_the_baller", user.getFriends());
        }

        @Test(timeout = 1000)
        public void getChats() {
            User user = new User();
            assertEquals("Chats:", user.getChats());
        }
    }

    public class Message {
        @Test(timeout = 1000)
        public void getHandle() {
            Message message = new Message();
            assertEquals("rajesh", message.getNewHandle());
        }

        @Test(timeout = 1000)
        public void getContent() {
            User user = new User();
            assertNotNull(message.getContent());
        }
    }

    public class Chat {
        @Test(timeout = 1000)
        public void getChatContent() {
            Chat chat = new Chat();
            assertEquals(a + ". " + chat.getChatName(), chat.getChatContent());
        }

        @Test(timeout = 1000)
        public void getChatMembers() {
            Chat chat = new Chat();
            assertNotNull(chat.getChatMembers());
        }

        @Test(timeout = 1000)
        public void getChatName() {
            Chat chat = new Chat();
            assertEquals("rajesh", chat.getChatName());
        }

    }

//    public class Server {
//    }

    public class Client {
        @Test(timeout = 1000)
        public void receivePacket() throws IOException, ClassNotFoundException {
            Client client = new Client();
            String a = "addUser", "ehendrich", "broccoli11";
            assertEquals(a, client.receivePacket());
        }

        public void receiveUser() throws IOException, ClassNotFoundException {
            Client client = new Client();
            assertEquals("update", client.receiveUser());
        }
    }
}
