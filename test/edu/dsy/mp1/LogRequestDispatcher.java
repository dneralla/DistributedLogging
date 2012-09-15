package edu.dsy.mp1;


//Grep Dispatcher

public class LogRequestDispatcher extends RequestDispatcher {

	LogRequestDispatcher(String fileName, String fileContent) {
		setInputParameters(new LogParameters(fileName, fileContent));
	}

	@Override
	public InputParameters getInputParameters() {
		return inputParams;
	}
}
