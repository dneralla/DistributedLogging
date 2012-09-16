package edu.dsy.mp1;


import java.io.IOException;

import java.io.ObjectOutputStream;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import org.w3c.dom.NodeList;



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
