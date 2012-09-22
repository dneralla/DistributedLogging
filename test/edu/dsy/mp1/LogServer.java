package edu.dsy.mp1;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Log Generating server.
 * 
 * @author srikanth
 * 
 */
public class LogServer extends ServerListener {
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;

	public LogServer() {
		super("test/edu/dsy/mp1/config.xml");
	}

	public LogServer(String fileXML) {
		super(fileXML);
	}

	/**
	 * 
	 * @param input
	 * @throws IOException
	 */
	@Override
	public void doAction(InputParameters input) throws IOException {
		final int LINE_LENGTH = 20;

		Random generator = new Random(System.currentTimeMillis());
		// Create Logger
		Logger logger = Logger.getLogger(LogServer.class.getName());
		logger.setLevel(Level.INFO);
		int count = 0;
		try {
			File file = new File(((LogParameters) input).getFileName());
			file.delete();
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("created the file");
		fileTxt = new FileHandler(((LogParameters) input).getFileName());

		// Create txt Formatter
		formatterTxt = new SimpleFormatter();
		fileTxt.setFormatter(formatterTxt);
		logger.addHandler(fileTxt);
		int numLines = 10000000, currentLines = 0;
		while (currentLines++ < numLines) {

			if (count < ((LogParameters) input).getFrequency()) {
				logger.info(((LogParameters) input).getPattern());
				count++;
			} else {
				StringBuffer sb = new StringBuffer();
				for (int i = 0; i < LINE_LENGTH; i++) {
					sb.append((char) ((int) (Math.random() * 26) + 97));
				}
				logger.info(sb.toString());
			}
		}
		sendMessage("Logs generated SuccessFully");
		fileTxt.close();
	}

	public static void main(String args[]) {
		System.out.println("Starting Log Server");
		LogServer logServer = new LogServer();
		logServer.run();
	}
}
