package edu.dsy.mp1;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Document;

import org.w3c.dom.NodeList;


//Grep Listener

public class GrepListener extends ServerListener{
	ServerSocket sSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	GrepInputParameters input;

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;

   
	public GrepListener(String fileXML) {
		super(fileXML);
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
   
   public void doAction(GrepInputParameters input) throws IOException
   {
	    System.out.println(input.getPattern());
	    System.out.println(input.getFileName());
        Process p = Runtime.getRuntime().exec("grep"+" "+input.getPattern()+" "+input.getFileName()+ " "+input.getOptionalParams() );
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String buffer,result="";
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
    	String fileXML= "src/edu/dsy/mp1/config.xml";
 		GrepListener server = new GrepListener(fileXML);

 			server.run();

 	}
}
