package edu.dsy.mp1;

public class InputParameters {
	protected String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputParameters(String fileName) {
		super();
		this.fileName = fileName;
	}
}
