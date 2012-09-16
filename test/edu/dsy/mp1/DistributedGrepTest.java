package edu.dsy.mp1;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.junit.Test;

public class DistributedGrepTest {
	@Test
	public void testGrepFunction() throws Exception {
		String outputFileName = "output.txt";
		String expectedOutcome = "!";
		GrepRequestDispatcher grepDispatcher = new GrepRequestDispatcher("[!@#$]*","*.log");
		LogRequestDispatcher logDispatcher = new LogRequestDispatcher("InFrequentPattern.log", "!", "src/edu/dsy/mp1/config.xml");
		grepDispatcher.setOutputFileName(outputFileName);
        logDispatcher.run();
        grepDispatcher.run();
        assertTrue(isFileContentMatching(outputFileName, expectedOutcome));
	}

	private boolean isFileContentMatching(String outputFileName, String result) {
		File output =  new File(outputFileName);
		InputStream in = null;
        char ch = 0;
        int count = 0;
		try {
			in = new FileInputStream(output);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
        Reader reader = new InputStreamReader(in);
        try {
			while((ch=(char) reader.read())!=-1 && count < result.length()) {
				if(ch != result.charAt(count))
					return false;
				count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        if(ch == -1 && count == result.length())
        	return true;
        return false;
	}
}
