package edu.dsy.mp1;

/**
 * parameters for the LogFile generator.
 * 
 */
public class LogParameters extends InputParameters implements
		java.io.Serializable {
	private String pattern;
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	private int frequency;

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public LogParameters(String fileName, String pattern, int frequency) {
		this.fileName = fileName;
		this.pattern = pattern;
		this.frequency = frequency;
	}

}
