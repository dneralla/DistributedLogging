package edu.dsy.mp1;
/**
 * 
 * Implementation of Thread Class used by GrepRequestDispatcher
 * to start connecting to GrepListener  
 *
 */
public class DispatchThread extends Thread {

	private int param;
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public DispatchThread(int param) {
		this.setParam(param);
	}

	public int getParam() {
		return param;
	}

	public void setParam(int param) {
		this.param = param;
	}

}
