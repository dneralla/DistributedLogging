package edu.dsy.mp1;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * Server class which listens for grep requests and  provides results
 * to the client.
 *
 */

public class GrepListener extends ServerListener {

	public GrepListener(String fileXML) {
		super(fileXML);
	}

	/**
	 * 
	 * @param msg
	 */
	public void sendMessage(String msg) {
		try {
			out.writeObject(msg);
			out.flush();
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}

	/**
	 * Method that does the Grep and sends result to the requested client
	 * @param input
	 * @throws IOException
	 */
	@Override
	public void doAction(InputParameters input) throws IOException {
		Process p = Runtime.getRuntime().exec(
				"grep" + " " + ((GrepInputParameters) input).getPattern() + " "
						+ ((GrepInputParameters) input).getFileName() + " "
						+ ((GrepInputParameters) input).getOptionalParams());
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String buffer, result = "";
		while ((buffer = stdInput.readLine()) != null) {
			result = result + buffer;
		}
		sendMessage(result);

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		System.out.println("hello");
		String fileXML = "src/edu/dsy/mp1/config.xml";
		GrepListener server = new GrepListener(fileXML);

		server.run();

	}
}
