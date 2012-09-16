package edu.dsy.mp1;

public class LogParameters extends InputParameters {
	private String pattern;

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
		super(fileName);
		this.pattern = pattern;
		this.frequency = frequency;
	}

}
