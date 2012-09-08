package edu.dsy.mp1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class GrepListener {
	ServerSocket sSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	GrepInputParameters input;
	
	public GrepListener() {}
	/**
	 * 
	 */
    public void run()
    {

	try{
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
		
			try{
				input = (GrepInputParameters)in.readObject();
				Process p = Runtime.getRuntime().exec("grep"+" "+input.getPattern()+""+input.getFile());
				sendMessage(p.getOutputStream().toString()); 
					
			}
			catch(ClassNotFoundException classnot){
				System.err.println("Data received in unknown format");
			}
		
	}
	catch(IOException ioException){
		ioException.printStackTrace();
	}
	finally{
		//4: Closing connection
		try{
			in.close();
			out.close();
			sSocket.close();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}}
	}
 /**
  *    
  * @param msg
  */
     public void sendMessage(String msg)
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
