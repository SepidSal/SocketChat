package simpleChat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Formatter;
import java.util.Scanner;


public class Server {
	public static void main (String[] args) throws IOException {
		ServerSocket server= new ServerSocket(33775);
		Socket socket= server.accept();
		Scanner Input= new Scanner(socket.getInputStream());
		Formatter out= new Formatter(socket.getOutputStream());
		Scanner systemIn= new Scanner(System.in);
		String next;
		
		// continuous
		do{
			String received= Input.nextLine();
			System.out.println("received from client: "+received);
			next= systemIn.nextLine();
			out.format(next+"%n");
			out.flush();
		}while(!next.equals("exit"));
		socket.close();
		server.close();
		
	}
}