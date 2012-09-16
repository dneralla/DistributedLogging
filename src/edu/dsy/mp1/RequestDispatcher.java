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

//Generic Request Dispatcher

public abstract class RequestDispatcher{
	
	
	InputParameters inputParams;

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;
	String configFileName;
	
	StringBuffer output;
	public RequestDispatcher(InputParameters inputParams, String configFileName) {
		this.inputParams = inputParams;
		this.configFileName = configFileName;
		this.output =new StringBuffer();
	}
	public String getConfigFileName() {
		return configFileName;
	}

	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}

	private static String getTagValue(String sTag, Element eElement) {
		NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
        Node nValue = nlList.item(0);
		return nValue.getNodeValue();
	}

	public void run() {
		File propertiesXML = new File(configFileName);
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(propertiesXML);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("server");
			// prop.load(new FileInputStream("config.properties"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		for ( int i = 0; i < nList.getLength(); i++) {
			
			
			new DispatchThread(i) {
				public void run()
				{
					Socket requestSocket=null;
					ObjectOutputStream out=null;
					ObjectInputStream in = null;
				
			try {
				Element serverConfig = (Element) nList.item(this.getParam());
				String hostName = getTagValue("name", serverConfig);
			    String hostPort = getTagValue("port", serverConfig);

				// 1. creating a socket to connect to the server
				requestSocket = new Socket(hostName, Integer.parseInt(hostPort));

				// 2. get Input and Output streams
				out = new ObjectOutputStream(requestSocket.getOutputStream());
				out.flush();
				in = new ObjectInputStream(requestSocket.getInputStream());
				// 3: Communicating with the server
				try {
					// message = (String)in.readObject();
					// System.out.println("server>" + message);
				    sendMessage(inputParams,out);
					
					System.out.println((String) in.readObject());
					output.append((String) in.readObject());
				} catch (ClassNotFoundException classNot) {
					System.err.println("data received in unknown format");
				}
			} catch (UnknownHostException unknownHost) {
				System.err
						.println("You are trying to connect to an unknown host!");
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} finally {
				// 4: Closing connection
				try {
					in.close();
					out.close();
					requestSocket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
				}
			}.start();
		}
	}

	public void sendMessage(InputParameters params, ObjectOutputStream out)
	{
		try{
			out.writeObject(params);
			out.flush();
		}
		catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	public void setInputParameters(InputParameters inputParams) {
		this.inputParams = inputParams;
	}

	public InputParameters getInputParameters() {
		return inputParams;
	}
}
