package edu.dsy.mp1;

public class LogParameters {
	public LogParameters(String fileName, String fileContent) {
		super();
		this.fileName = fileName;
		this.fileContent = fileContent;
	}

	private String fileName, fileContent;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}
}
