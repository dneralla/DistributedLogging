package edu.dsy.mp1;

import java.io.*;
import java.net.*;

public class GrepRequestDispatcher {
	ServerSocket sSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	String input;
	
	public GrepRequestDispatcher() {}
	
    public void run()
    {

		//1. creating a server socket
	      sSocket = new ServerSocket(200);
		//2. Wait for connection
		  connection = sSocket.accept();
		System.out.println("Connection received from " + connection.getInetAddress().getHostName());
		//3. get Input and Output streams
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());
		
		//4. The two parts communicate via the input and output streams
		do{
			try{
				input = (String)in.readObject();
				System.out.println("client>" + message);
				if (input.equals("bye"))
					sendMessage("bye");
			}
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
		}while(!message.equals("bye"));
	}
	catch(IOException ioException){
		ioException.printStackTrace();
	}
	finally{
		//4: Closing connection
		try{
			in.close();
			out.close();
			providerSocket.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}
    

void sendMessage(String msg)
{
	try{
		out.writeObject(msg);
		out.flush();
		System.out.println("server>" + msg);
	}
	catch(IOException ioException){
		ioException.printStackTrace();
	}
}



}
