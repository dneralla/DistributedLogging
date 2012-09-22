package edu.dsy.mp1;

import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import org.w3c.dom.NodeList;

/**
 * 
 * Client class which relays grep request to Servers and collects results and
 * displays on the console
 * 
 */
public class GrepRequestDispatcher extends RequestDispatcher {

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;
	String outputFile;

	public GrepRequestDispatcher(String regex, String filePattern,
			String configFile) {
		super(new GrepInputParameters(regex, filePattern), configFile);

	}

	public GrepRequestDispatcher(String regex, String filePattern,
			String optionalParams, String configFile) {
		super(new GrepInputParameters(regex, filePattern, optionalParams),
				configFile);
	}

	/**
	 * 
	 * @param outputFile
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	/**
	 * 
	 * @return
	 */
	public String getOutputFile() {
		return outputFile;
	}

	/**
	 * 
	 * @param params
	 * @param out
	 */
	public void sendMessage(GrepInputParameters params, ObjectOutputStream out) {
		try {
			out.writeObject(params);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/**
     * 
     */
	public void run() {
		super.run();
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter(
					this.outputFile));
			String outText = this.output.toString();
			System.out.println(outText);
			out.write(outText);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		String configFileName = "src/edu/dsy/mp1/config.xml";

		GrepRequestDispatcher dispatcher = new GrepRequestDispatcher(args[0],
				args[1], configFileName);
		dispatcher.setOutputFile("output1.txt");
		dispatcher.run();
	}
}
