package simpleChat;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Exception {
        String s = "a";
        Socket server = null;
        //////////////Send to Server
        try {
            server = new Socket("192.168.1.25", 33775);
            Scanner socketIn = new Scanner(server.getInputStream());
            Formatter socketOut = new Formatter(server.getOutputStream());
            System.out.println("Please Enter Massage for Send Server: ");
            Scanner systemIn = new Scanner(System.in);
            String next;
            do {
                next = systemIn.next();
                socketOut.format(next + "%n");
                socketOut.flush();
                String received = socketIn.nextLine();
                if (received != null)
                    System.out.println("Received From Server: " + received);
            } while (true);
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        server.close();
    }
}