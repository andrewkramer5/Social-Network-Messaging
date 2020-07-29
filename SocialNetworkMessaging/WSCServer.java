import java.io.*;
import java.util.ConcurrentModificationException;
import java.util.ArrayList;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.InterruptedException;
public class WSCServer implements Runnable {

    Socket socket;
    private static ArrayList<User> users;


    public WSCServer(Socket socket) {
        this.socket = socket;
    }

    public WSCServer() {

    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket serverSocket = new ServerSocket(4200);

        users = new ArrayList<User>();

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
                String ident = packet.getIdentifier();
                Packet p;

                if (ident.equals("deleteChat")) {
                    deleteChat(packet); //doesn't work
                    System.out.println("deleteChat");

                } else if (ident.equals("signIn")) {
                    p = signIn(packet); //works
                    oos.writeObject(p);
                    oos.flush();
                    System.out.println("signIn");

                } else if (ident.equals("addUser")) {
                    p = addUser(packet); //works
                    oos.writeObject(p);
                    oos.flush();
                    System.out.println("addUser");

                } else if (ident.equals("changePassword")) {
                    changePassword(packet); //works serverside
                    System.out.println("changePassword");

                } else if (ident.equals("deleteUser")) {
                    deleteUser(packet); //works serverside
                    System.out.println("deleteUser");

                } else if (ident.equals("changeHandle")) {
                    p = changeHandle(packet); //works serverside
                    oos.writeObject(p);
                    oos.flush();
                    System.out.println("changeHandle");

                } else if (ident.equals("changeChatName")) {
                    p = changeChatName(packet);
                    oos.writeObject(p);  //works serverside
                    oos.flush();
                    System.out.println("changeChatName");

                } else if (ident.equals("addFriend")) {
                    p = addFriend(packet);  //works
                    oos.writeObject(p);
                    oos.flush();
                    System.out.println("addFriend");

                } else if (ident.equals("addMessage")) {
                    addMessage(packet);  //works
                    System.out.println("addMessage");


                } if (ident.equals("deleteMessage")) {
                    deleteMessage(packet); //doesn't work
                    System.out.println("deleteMessage");

                } else if (ident.equals("editMessage")) {
                    editMessage(packet); //doesn't work
                    System.out.println("editMessage");

                } else if (ident.equals("addChat")) {
                    p = addChat(packet);  //works
                    oos.writeObject(p);
                    oos.flush();
                    System.out.println("addChat");

                } else if (ident.equals("update")) {
                    for (User user : users) { //can't tell if it works
                        System.out.println(user.getHandle());
                        if (user.getHandle().equals(packet.getHandle())) {
                            System.out.println();
                            System.out.println("User Info:");
                            System.out.println("Handle: " + user.getHandle());
                            System.out.println("Password: " + user.getPassword());
                            System.out.println("Chat Names: ");
                            for (Chat c: user.getChats()) {
                                System.out.println(c.getChatName());
                            }
                            for (User u: user.getFriends()) {
                                if(user.getFriends().size() == 0) {
                                    System.out.println("This user has no friends");
                                    break;
                                }
                                System.out.println("User Info:");
                                System.out.println("Handle: " + user.getHandle());
                                System.out.println("Password" + user.getPassword());
                            }
                            oos.writeObject(user);
                            oos.flush();
                        }
                    }
                    System.out.println("update");
                }

//				WriteToFile w = new WriteToFile();
//
//				w.writeUsers(users, "data.txt");

            }
        } catch (IOException e) {

        } catch (ClassNotFoundException a) {

        }
    }

    public void deleteChat(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                for (Chat chat : user.getChats()) {
                    if (chat.getChatName().equals(packet.getChatName())) {
                        for (User chatMember : chat.getChatMembers()) {
                            for (Chat friendChat : chatMember.getChats()) {
                                if (friendChat.getChatName().equals(packet.getChatName())) {
                                    friendChat.deleteMember(user);
                                }
                            }
                        }
                        chat.deleteMember(user);
                    }
                }
            }
        }
        for (User u: users) {
            System.out.println(u.getHandle() + ":");
            for (Chat c : u.getChats()) {
                System.out.println(c.getChatName());
            }
        }
        System.out.println();
    }

    public Packet signIn(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle()) &&
                    user.getPassword().equals(packet.getPassword())) {
                Packet verify = new Packet(true, "Sign in Successful");
                return verify;
            } else if (user.getHandle().equals(packet.getHandle()) &&
                    !user.getPassword().equals(packet.getPassword())) {
                Packet verify = new Packet(false, "Incorrect Password");
                return verify;
            }
        }
        Packet verify = new Packet(false, "Username does not exist");
        return verify;
    }

    public void changePassword(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                user.changePassword(packet.getPassword());
                System.out.println(user.getHandle());
                System.out.println(user.getPassword());
            }
        }
    }

    public Packet addUser(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                Packet verify = new Packet(false, "Username already exists");
                return verify;
            }
        }

        User newUser = new User(packet.getHandle(), packet.getPassword());

        users.add(newUser);

        Packet verify = new Packet( true, "Account created successfully");
        return verify;
    }

    public void deleteUser(Packet packet) {
        try {
            for (User user : users) {
                for (User friend : (user.getFriends())) {
                    if (friend.getHandle().equals(packet.getHandle())) {
                        user.removeFriend(friend);
                    }
                }
                for (Chat chat : user.getChats()) {
                    for (User chatFriend : chat.getChatMembers()) {
                        if (chatFriend.getHandle().equals(packet.getHandle())) {
                            chat.deleteMember(chatFriend);
                        }
                    }
                }
            }

            for (User user : users) {
                if (user.getHandle().equals(packet.getHandle())) {
                    users.remove(user);
                }
            }
            for (User u: users) {
                System.out.println(u.getHandle());
                if(!u.getFriends().isEmpty()) {
                    for (User uss : u.getFriends()) {
                        System.out.println("Handle: " + uss.getHandle());
                    }
                }
                else {
                    System.out.println("the user has no friends");
                }
            }
            System.out.println();
            for (User us: users) {
                for (Chat c: us.getChats()) {
                    System.out.println(c.getChatName() + ": ");
                    for (User uss: c.getChatMembers()) {
                        System.out.println("CHAT User");
                        System.out.println(uss.getHandle());
                    }
                }
            }
            System.out.println();
        } catch (ConcurrentModificationException a) {
            deleteUser(packet);
        }
    }

    public Packet changeHandle(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getNewHandle())) {
                return new Packet(false, "Username already exists");
            }
        }

        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                user.changeHandle(packet.getNewHandle());
            }
        }
        for (User u: users) {
            if(!u.getHandle().equals(packet.getHandle())) {
                for (User us: u.getFriends()) {
                    if(us.getHandle().equals(packet.getHandle())) {
                        us.changeHandle(packet.getNewHandle());
                    }
                }
                for (Chat c: u.getChats()) {
                    for (User us: c.getChatMembers()) {
                        if(us.getHandle().equals(packet.getHandle())) {
                            us.changeHandle(packet.getNewHandle());
                        }
                    }
                }
            }
        }

        return new Packet(true, "Handle updated succesfully");
    }

    public Packet changeChatName(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.setChatName(packet.getNewChatName());
                    for (User us: users) {
                        System.out.println(us.getHandle() + ": ");
                        for (Chat c: us.getChats()) {
                            System.out.println(c.getChatName());
                        }
                    }
                    System.out.println();
                    return new Packet(true, "Chat name successfully changed");
                }
            }
        }
        return new Packet(false, "Unable to change chat name");
    }

    public Packet addFriend(Packet packet) {
        boolean friendExists = false;
        User adder = new User();
        User friend = new User();

        for (User user : users) {
            if (user.getHandle().equals(packet.getFriendHandle())) {
                friendExists = true;
                friend = user;
            } else if (user.getHandle().equals(packet.getHandle())) {
                adder = user;
            }
        }
        if (!friendExists) {
            Packet verify = new Packet(false, "User with that username does not exist");
            return verify;
        }

        if (adder.getFriends().contains(friend)) {
            Packet verify = new Packet(false, "You are already friends with " + friend.getHandle());
            return verify;
        }

        adder.addFriend(friend);
        friend.addFriend(adder);

        Packet verify = new Packet(true, friend.getHandle() + " added");
        return verify;
    }

    public void addMessage(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.addMessage(packet.getMessage());
                }
            }
        }
    }

    public void deleteMessage(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.deleteMessage(packet.getMessage());
                }
            }
        }
    }

    public void editMessage(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.editMessage(packet.getOldMessage(), packet.getMessage());
                }
            }
        }
    }

    public Packet addChat(Packet originalPacket) {
        Packet packet = originalPacket;

        while (true) {
            for (User user : users) {
                for (Chat chat : user.getChats()) {
                    if (chat.getChatName().equals(packet.getChatName())) {
                        Packet verify = new Packet(false, "Chat name already exists");
                        return verify;
                    }
                }
            }

            ArrayList<User> chatMembers = new ArrayList<User>();

            for (String s : packet.getHandles()) {
                for (User user : users) {
                    if (s.equals(user.getHandle())) {
                        chatMembers.add(user);
                    }
                }
            }

            Chat newChat = new Chat(chatMembers, packet.getChatName());

            if (packet.getChatName().equals("")) {
                packet = new Packet("addChat", packet.getHandles(), newChat.getChatName());

                continue;
            }

            for (User user : users) {
                if (newChat.getChatMembers().contains(user)) {
                    user.addChat(newChat);
                }
            }

            Packet verify = new Packet(true, "chat created");
            return verify;
        }
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getUser(String handle) {
        for (User user : users) {
            if (user.getHandle().equals(handle)) {
                return user;
            }
        }
        return null;
    }
}