package edu.dsy.mp1;


//Grep Dispatcher

public class LogRequestDispatcher extends RequestDispatcher {

	LogRequestDispatcher(String fileName, String fileContent, String configFileName) {
		super(new LogParameters(fileName, fileContent), configFileName);
	}
}
