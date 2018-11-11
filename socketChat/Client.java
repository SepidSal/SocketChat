package socketChat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	static String in_con = "";// input from console
	static String line = "";// input stream

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);
		Socket Client = new Socket("127.0.0.1", 8090);
		DataInputStream is = new DataInputStream(Client.getInputStream());
		DataOutputStream os = new DataOutputStream(Client.getOutputStream());
		
		class writeToSocketThread extends Thread { 
			// writing in Socket 
			public void run() {
				while (!in_con.equals("CLOSE") & !line.equals("CLOSE")) {
					// continue the loop until "close" entered or received 

					in_con = input.nextLine();// get input from console
					try {
						if (line.equals("CLOSE") || in_con.equals("CLOSE")) {
							os.writeBytes(in_con + "\n");
							continue;
						}
						os.writeBytes(in_con + "\n");
					} catch (IOException e) {
						System.out.println("error cw");
					}
				}
			}
		}

		class readFromSocketThread extends Thread {
			// reading from socket and writing it on the console
			@SuppressWarnings("deprecation")
			public void run() {
				while (!line.equals("CLOSE") & !in_con.equals("CLOSE")) {
					// continue the loop until "close" entered or received 

					try {
						line = is.readLine();
						
						// reading the input stream from socket
						if (line.equals("CLOSE") || in_con.equals("CLOSE")) {
							continue;
						}
						System.out.println(line);
					} catch (IOException e) {
						System.out.println("error cr");
					}
				}
			}
		}
		
		readFromSocketThread tread = new readFromSocketThread();
		
		writeToSocketThread twrite = new writeToSocketThread();
		
		tread.start();// starting the read thread
		twrite.start();// starting the write thread
	}
}

