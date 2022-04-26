package chat;

import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	String input, output;

	private Socket connectionSocket;
	private ServerSocket serverSocket;

	public void arrancarServidor() {

		try {
			serverSocket = new ServerSocket(6789);
			esperarConexion();
			Scanner inFromClient = new Scanner(connectionSocket.getInputStream());
			PrintWriter outFromServer = new PrintWriter(connectionSocket.getOutputStream(), true);

			Scanner inFromServer = new Scanner(System.in);

			while (true) {
				
				input = inFromClient.nextLine();
				System.out.println("Cliente: " + input);

				
				if(input.equals("terminar")) {
	                break;
	            }
				
				System.out.print("Server: ");
				output = inFromServer.nextLine();

				outFromServer.println(output);

				
			}
			serverSocket.close();

		} catch (IOException exceptionIO) {
			exceptionIO.printStackTrace();
		}

	}

	private void esperarConexion() throws IOException {
		System.out.println("Esperando conexiones");
		connectionSocket = serverSocket.accept();
		System.out.println("---- Cliente con ip: " + connectionSocket.getInetAddress().getHostName() + " conectado ----");
	}

	public static void main(String[] args) {
		
		Server server = new Server();
		server.arrancarServidor();
	}
}
