package edu.dsy.mp1;

/**
 * 
 * Log Generating request Dispatcher.
 * 
 */

public class LogRequestDispatcher extends RequestDispatcher {

	LogRequestDispatcher(String fileName, String pattern, int frequency,
			String configFileName) {
		super(new LogParameters(fileName, pattern, frequency), configFileName);
	}

}
