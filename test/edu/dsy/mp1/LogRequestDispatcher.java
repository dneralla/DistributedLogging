package edu.dsy.mp1;


//Grep Dispatcher

public class LogRequestDispatcher extends RequestDispatcher {

	LogRequestDispatcher(String fileName, String fileContent, String configFileName) {
		setInputParameters(new LogParameters(fileName, fileContent));
		setConfigFileName(configFileName);
	}
}
