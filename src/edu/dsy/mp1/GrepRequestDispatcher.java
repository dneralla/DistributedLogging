package edu.dsy.mp1;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

//Grep Dispatcher

public class GrepRequestDispatcher{
	Socket requestSocket;
	ObjectOutputStream out;
	ObjectInputStream in;
	String message;
	GrepInputParameters inputParams;

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;
	GrepRequestDispatcher(String pattern,String file){
		inputParams =new GrepInputParameters(pattern,file);
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = nlList.item(0);
		return nValue.getNodeValue();
	}

	public void run()
	{
		File propertiesXML = new File("config.xml");
		try{
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(propertiesXML);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("server");
		    //prop.load(new FileInputStream("config.properties"));
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		for(int i=0; i < nList.getLength(); i++) {
			try {
				Element serverConfig = (Element) nList.item(i);
				String hostName = getTagValue("name", serverConfig);
				String hostPort = getTagValue("port", serverConfig);
				// 1. creating a socket to connect to the server
				requestSocket = new Socket(hostName,
						Integer.parseInt(hostPort));

				// 2. get Input and Output streams
				out = new ObjectOutputStream(requestSocket.getOutputStream());
				out.flush();
				in = new ObjectInputStream(requestSocket.getInputStream());
				// 3: Communicating with the server
				try {
					// message = (String)in.readObject();
					// System.out.println("server>" + message);
					sendMessage(inputParams);
					message = (String) in.readObject();
					System.out.println("server>" + message);
				} catch (ClassNotFoundException classNot) {
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
