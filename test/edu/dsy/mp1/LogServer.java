package edu.dsy.mp1;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogServer extends ServerListener{
	static private FileHandler fileTxt;
	static private SimpleFormatter formatterTxt;

	public LogServer() {
		super("test/edu/dsy/mp1/config.xml");
	}
	public LogServer(String fileXML) {
		super(fileXML);
	}

	public void doAction(LogParameters input) throws IOException {
		final int LINE_LENGTH = 20;

		Random generator = new Random(System.currentTimeMillis());
		// Create Logger
	    Logger logger = Logger.getLogger(LogServer.class.getName());
	    logger.setLevel(Level.INFO);
	    int count = 0;
		try {
			File file = new File(input.getFileName());
			file.delete();
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}

	    fileTxt = new FileHandler(input.getFileName());

	    // Create txt Formatter
	    formatterTxt = new SimpleFormatter();
	    fileTxt.setFormatter(formatterTxt);
	    logger.addHandler(fileTxt);
		while (true) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < LINE_LENGTH; i++) {
				sb.append((char) ((int) (Math.random() * 26) + 97));
			}
			System.out.println(sb.toString());
			logger.info(sb.toString());
			if(count < input.getFrequency() && generator.nextInt() % 2 == 0)
			{
				logger.info(input.getPattern());
				count++;
			}
		}
	}
}
