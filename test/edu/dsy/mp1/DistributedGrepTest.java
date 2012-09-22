package edu.dsy.mp1;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.junit.Test;
import java.io.File;

/**
 * 
 * Junit Base class that does the testing
 * 
 */
public class DistributedGrepTest {

	DocumentBuilderFactory dbFactory;
	DocumentBuilder dBuilder;
	Document doc;
	NodeList nList;

	@Test
	public void testGrepInFrequentPattern() throws Exception {
		String outputFile = "output1.txt", pattern = "indy";

		String configFileName = "src/edu/dsy/mp1/config.xml";
		int serverCount = 0;

		File propertiesXML = new File(configFileName);
		try {
			dbFactory = DocumentBuilderFactory.newInstance();
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(propertiesXML);
			doc.getDocumentElement().normalize();
			nList = doc.getElementsByTagName("server");
			serverCount = nList.getLength();
		} catch (Exception e) {
			e.printStackTrace();
		}

		int expectedFrequency = 1;
		LogRequestDispatcher logDispatcher = new LogRequestDispatcher(
				"test/edu/dsy/mp1/InFrequentPattern.log", pattern,
				expectedFrequency, "test/edu/dsy/mp1/config.xml");
		logDispatcher.run();
		GrepRequestDispatcher grepDispatcher = new GrepRequestDispatcher(
				pattern, "test/edu/dsy/mp1/InFrequentPattern.log",
				configFileName);

		grepDispatcher.setOutputFile(outputFile);

		Thread.sleep(3000);
		grepDispatcher.run();
		assertEquals(getFrequency(outputFile, pattern), expectedFrequency
				* serverCount);
	}

	@Test
	public void testGrepFrequentPattern() throws Exception {
		String outputFile = "output2.txt", pattern = "indy";
		String configFileName = "src/edu/dsy/mp1/config.xml";
		int expectedFrequency = 1000;
		LogRequestDispatcher logDispatcher = new LogRequestDispatcher(
				"test/edu/dsy/mp1/FrequentPattern.log", pattern,
				expectedFrequency, "test/edu/dsy/mp1/config.xml");
		logDispatcher.run();
		GrepRequestDispatcher grepDispatcher = new GrepRequestDispatcher(
				pattern, "test/edu/dsy/mp1/FrequentPattern.log", configFileName);
		grepDispatcher.setOutputFile(outputFile);

		Thread.sleep(3000);
		grepDispatcher.run();
		assertTrue(getFrequency(outputFile, pattern) > 450);
	}

	private int getFrequency(String outputFile, String pattern)
			throws IOException {
		// Open the file that is the first
		// command line parameter
		FileInputStream fstream = new FileInputStream(outputFile);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		int frequency = 0;
		String line;
		while ((line = br.readLine()) != null) {

			frequency += countOccurences(line, pattern);
		}

		return frequency;
	}

	private int countOccurences(String source, String pattern) {
		Matcher m = Pattern.compile(pattern).matcher(source);
		int matches = 0;
		while (m.find())
			matches++;

		return matches;
	}

}
