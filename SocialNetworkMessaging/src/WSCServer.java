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
                    System.out.println("signIn");

                } else if (ident.equals("addUser")) {
                    p = addUser(packet); //works
                    oos.writeObject(p);
                    System.out.println("addUser");

                } else if (ident.equals("changePassword")) {
                    changePassword(packet); //works but glitchy look at test class comment
                    System.out.println("changePassword");

                } else if (ident.equals("deleteUser")) {
                    deleteUser(packet); //doesn't work
                    System.out.println("deleteUser");

                } else if (ident.equals("changeHandle")) {
                    p = changeHandle(packet); //works but orginal user remains and isn't modified in chat
                    oos.writeObject(p);
                    System.out.println("changeHandle");

                } else if (ident.equals("changeChatName")) {
                    p = changeChatName(packet);
                    oos.writeObject(p);  //works but old chat name still prints
                    System.out.println("changeChatName");

                } else if (ident.equals("addFriend")) {
                    p = addFriend(packet);  //works
                    oos.writeObject(p);
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
                    System.out.println("addChat");

                } else if (ident.equals("update")) {
                    for (User user : users) { //can't tell if it works
                        if (user.getHandle().equals(packet.getHandle())) {
                            oos.writeObject(user);
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
                        for (Chat chat : friend.getChats()) {
                            for (User chatFriend : chat.getChatMembers()) {
                                if (chatFriend.getHandle().equals(packet.getHandle())) {
                                    chat.deleteMember(chatFriend);
                                }
                            }
                        }
                    }
                }
            }

            for (User user : users) {
                if (user.getHandle().equals(packet.getHandle())) {
                    users.remove(user);
                }
            }
        } catch (ConcurrentModificationException a) {
            deleteUser(packet);
        }
    }

    public Packet changeHandle(Packet packet) {
        for (User user : users) {
            if (user.getHandle().equals(packet.getNewHandle())) {
                Packet verify = new Packet(false, "Username already exists");
                return verify;
            }
        }

        for (User user : users) {
            if (user.getHandle().equals(packet.getHandle())) {
                user.changeHandle(packet.getNewHandle());
            }
        }

        Packet verify = new Packet(true, "Handle updated succesfully");
        return verify;

    }

    public Packet changeChatName(Packet packet) {
        for (User user : users) {
            for (Chat chat : user.getChats()) {
                if (chat.getChatName().equals(packet.getChatName())) {
                    chat.setChatName(packet.getNewChatName());
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
