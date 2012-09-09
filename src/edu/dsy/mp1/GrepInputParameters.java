package edu.dsy.mp1;

public class GrepInputParameters implements java.io.Serializable{
	
	private String pattern;
	private String file;
	public GrepInputParameters(String file,String pattern)
	{
		this.file=file;
		this.pattern=pattern;
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	

}
