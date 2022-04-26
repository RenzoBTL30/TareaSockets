package chat;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	public static void main(String[] args) throws IOException {
		String message, inFromServer;

		Socket clientSocket = new Socket("localhost", 6789);
		System.out.println("Estableciendo conexion....");

		Scanner inputFromUser = new Scanner(System.in);
		PrintWriter outputToServer = new PrintWriter(clientSocket.getOutputStream(), true);
		Scanner inputFromServer = new Scanner(clientSocket.getInputStream());
		
		while(true){
			
            System.out.print("Cliente: ");
            message = inputFromUser.nextLine();
            
            outputToServer.println(message);
            
            if(message.equals("terminar"))
                break;
            
            inFromServer = inputFromServer.nextLine();
            System.out.println("Server: "+inFromServer);
            
        }
        clientSocket.close();
	}
}
