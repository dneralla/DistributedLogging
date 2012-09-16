package edu.dsy.mp1;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.junit.Test;

public class DistributedGrepTest {
	@Test
	public void testGrepInFrequentPattern() throws Exception {
		String outputFileName = "output1.txt", pattern = "(#!@)";
		int expectedFrequency = 1;
		GrepRequestDispatcher grepDispatcher = new GrepRequestDispatcher("[!@#$]*","*.log", outputFileName);
		LogRequestDispatcher logDispatcher = new LogRequestDispatcher("InFrequentPattern.log", "!", 1,  "src/edu/dsy/mp1/config.xml");
        logDispatcher.run();
        grepDispatcher.run();
        assertEquals(getFrequency(outputFileName, pattern), expectedFrequency);
	}

	@Test
	public void testGrepFrequentPattern() throws Exception {
		String outputFileName = "output2.txt", pattern = "(#!@)";
		int expectedFrequency = 1000;
		GrepRequestDispatcher grepDispatcher = new GrepRequestDispatcher(
				"[!@#$]*", "*.log", outputFileName);
		LogRequestDispatcher logDispatcher = new LogRequestDispatcher(
				"FrequentPattern.log", "!", expectedFrequency,
				"src/edu/dsy/mp1/config.xml");
		logDispatcher.run();
		grepDispatcher.run();
		assertEquals(getFrequency(outputFileName, pattern), expectedFrequency);
	}

	private int getFrequency(String outputFileName, String pattern)
			throws IOException {
		// Open the file that is the first
		// command line parameter
		FileInputStream fstream = new FileInputStream(outputFileName);
		// Get the object of DataInputStream
		DataInputStream in = new DataInputStream(fstream);
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		int frequency = 0;
		String line;
		while ((line = br.readLine()) != null) {
			if (line.contains(pattern))
				frequency++;
		}

		return frequency;
	}
}
