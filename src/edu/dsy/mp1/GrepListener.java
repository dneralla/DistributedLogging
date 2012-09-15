package edu.dsy.mp1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

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
		
	    sSocket = new ServerSocket(2060);
		connection = sSocket.accept();
		System.out.println("Connection received from " + connection.getInetAddress().getHostName());
		out = new ObjectOutputStream(connection.getOutputStream());
		out.flush();
		in = new ObjectInputStream(connection.getInputStream());
		try{   
			  input = (GrepInputParameters)in.readObject();
			  doGrep(input); 
			}
	    catch(ClassNotFoundException e){
		    e.printStackTrace();
		}
	    finally{
	   
			in.close();
			out.close();
			sSocket.close();
		
		}
	}catch(IOException ioException){
			ioException.printStackTrace();
		}
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
	    }
	  catch(IOException ioException){
		ioException.printStackTrace();
	    }
      }
/**
 * 
 * @param input
 * @throws IOException
 */
   public void doGrep(GrepInputParameters input) throws IOException
   {
        Process p = Runtime.getRuntime().exec("grep"+" "+input.getFile()+" "+input.getPattern());
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String buffer,result=null;
		while ((buffer = stdInput.readLine()) != null) {
		result = result + buffer;
		}
		sendMessage(result);
	        
   }
   
  /**
   *    
   * @param args
   */
  public static void main(String args[])
 	{
    	System.out.println("hello");
 		GrepListener server = new GrepListener();
 		while(true){
 			server.run();
 		}
 	}




}
