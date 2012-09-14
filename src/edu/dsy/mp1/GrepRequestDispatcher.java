package edu.dsy.mp1;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;
//Grep Dispatcher

public class GrepRequestDispatcher{
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	GrepInputParameters inputParams;
	GrepRequestDispatcher(String pattern,String file){
		inputParams =new GrepInputParameters(pattern,file);
	}
	public void run()
	{

		Properties prop =new Properties();
		try{
		prop.load(new FileInputStream("config.properties"));
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		for(int i=1;i<=prop.size()/2;i++)
		{



			try{
			//1. creating a socket to connect to the server

			requestSocket = new Socket(prop.getProperty("server_"+i),Integer.parseInt(prop.getProperty("port_"+i)));

			//2. get Input and Output streams
			out = new ObjectOutputStream(requestSocket.getOutputStream());
			out.flush();
			in = new ObjectInputStream(requestSocket.getInputStream());
			//3: Communicating with the server

				try{
					//message = (String)in.readObject();
					//System.out.println("server>" + message);
					sendMessage(inputParams);
					message = (String)in.readObject();
					System.out.println("server>" + message);
				}
				catch(ClassNotFoundException classNot){
					System.err.println("data received in unknown format");
				}

		}
		catch(UnknownHostException unknownHost){
			System.err.println("You are trying to connect to an unknown host!");
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
		finally{
			//4: Closing connection
			try{
				in.close();
				out.close();
				requestSocket.close();
			}
			catch(IOException ioException){
				ioException.printStackTrace();
			}
		}}
	}

	public void sendMessage(GrepInputParameters params)
	{
		try{
			out.writeObject(params);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		GrepRequestDispatcher dispatcher= new GrepRequestDispatcher(args[0],args[1]);
		dispatcher.run();
	}
}
