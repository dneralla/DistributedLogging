package edu.dsy.mp1;

public class LogParameters extends InputParameters {
	private String fileContent;

	public LogParameters(String fileName, String fileContent) {
		super(fileName);
		this.fileContent = fileContent;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
}
