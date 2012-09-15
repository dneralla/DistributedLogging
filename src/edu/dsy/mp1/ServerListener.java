package edu.dsy.mp1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class ServerListener {
	ServerSocket sSocket;
	Socket connection = null;
	ObjectOutputStream out;
	ObjectInputStream in;
	InputParameters input;

	public ServerListener() {
	}

	/**
	 *
	 */
	public void run() {

		try {
			sSocket = new ServerSocket(2060);
			connection = sSocket.accept();
			System.out.println("Connection received from "
					+ connection.getInetAddress().getHostName());
			out = new ObjectOutputStream(connection.getOutputStream());
			out.flush();
			in = new ObjectInputStream(connection.getInputStream());
			try {
				input = (InputParameters) in.readObject();
				doAction(input);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				in.close();
				out.close();
				sSocket.close();
			}
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
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

	public void doAction(InputParameters input) throws IOException {
		return;
	}
}
