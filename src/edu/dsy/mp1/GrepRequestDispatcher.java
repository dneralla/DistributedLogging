package edu.dsy.mp1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
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

public class GrepRequestDispatcher extends RequestDispatcher{
	
	

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;

	public GrepRequestDispatcher(String regex, String filePattern,String configFile)
	{
	  super(new GrepInputParameters(regex, filePattern),configFile);
	    
	}

	public GrepRequestDispatcher(String regex, String filePattern,String optionalParams,String configFile)
	
	{
		super(new GrepInputParameters(regex, filePattern,optionalParams),configFile);
	}
	
	/*public GrepRequestDispatcher(String regex, String filePattern,boolean outputToFile,String outputFile,String configFile)
	{
		super(new GrepInputParameters(regex, filePattern),configFile);
		this.writeOutputToFile=outputToFile;
		this.outputFile=outputFile;
	}*/


	
	

	public void sendMessage(GrepInputParameters params,ObjectOutputStream out)
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
		String configFileName="src/edu/dsy/mp1/config.xml";
		GrepRequestDispatcher dispatcher= new GrepRequestDispatcher(args[0],args[1],configFileName);
		dispatcher.run();
	}
}
