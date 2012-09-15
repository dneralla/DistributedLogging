package edu.dsy.mp1;

import java.io.IOException;
import java.io.PrintWriter;

public class LogServer extends ServerListener{
	public LogServer() {
		super();
	}

	public void doAction(LogParameters input) throws IOException {
		Runtime.getRuntime().exec(
				"touch" + " " + input.getFileName());
		PrintWriter out = new PrintWriter(input.getFileName());
		out.append(input.getFileContent());
		out.close();
	}
}
