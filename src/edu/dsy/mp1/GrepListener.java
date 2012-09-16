package edu.dsy.mp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;




public class GrepListener {
	ServerSocket sSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	GrepInputParameters input;

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;

	public GrepListener() {}
	/**
	 *
	 */
    public void run()
    {


		int port=0;
		File propertiesXML = new File("src/edu/dsy/mp1/config.xml");
		try {

			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(propertiesXML);
			doc.getDocumentElement().normalize();

			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
		    XPathExpression expr = xpath.compile("//servers/server[name='"+InetAddress.getLocalHost().getHostName()+"']/port/text()");

			Object result = expr.evaluate(doc, XPathConstants.NODE);
			Node n =(Node)result;
			port=Integer.parseInt(n.getNodeValue());
		    } catch (Exception e) {
			e.printStackTrace();
		}

	  while(true){
		try{
	    sSocket = new ServerSocket(port);
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
	}}

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
	    System.out.println(input.getPattern());
	    System.out.println(input.getFile());
        Process p = Runtime.getRuntime().exec("grep"+" "+input.getPattern()+" "+input.getFile()+ " "+input.getOptionalParams() );
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
 		GrepListener server = new GrepListener();

 			server.run();

 	}
}