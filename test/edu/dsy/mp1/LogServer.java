package edu.dsy.mp1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class LogServer {
	ServerSocket sSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	LogParameters input;

	public LogServer() {
	}

	public void run() {

		try {
			// 1. creating a server socket
			sSocket = new ServerSocket(2061);
			// 2. Wait for connection
			connection = sSocket.accept();
			System.out.println("Connection received from "
					+ connection.getInetAddress().getHostName());
			// 3. get Input and Output streams
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());

			// 4. The two parts communicate via the input and output streams
			try {
				input = (LogParameters) in.readObject();
				doCreateLogs(input);
			} catch (ClassNotFoundException classnot) {
				System.err.println("Data received in unknown format");
			}

		} catch (IOException ioException) {
			ioException.printStackTrace();
		} finally {
			// 4: Closing connection
			try {
				in.close();
				out.close();
				sSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
		}
	}
	/**
	 * @param input
	 * @throws IOException
	 */
	public void doCreateLogs(LogParameters input) throws IOException {
		Process p = Runtime.getRuntime().exec(
				"touch" + " " + input.getFileName());
		PrintWriter out = new PrintWriter(input.getFileName());
		out.append(input.getFileContent());
	}
}
