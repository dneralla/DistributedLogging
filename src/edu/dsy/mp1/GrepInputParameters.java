package edu.dsy.mp1;
/*
 * Class for managing Grep parameters
 */
public class GrepInputParameters implements java.io.Serializable{

	private String pattern;
	private String file;
	private String optionalParams;

	public GrepInputParameters(String pattern,String file)
	{
		this.file=file;
		this.pattern=pattern;
	}

	public GrepInputParameters(String pattern,String file,String optionalParams)
	{
		this.file=file;
		this.pattern=pattern;
		this.optionalParams =optionalParams;
	}

	public String getOptionalParams() {
		return optionalParams;
	}
	public void setOptionalParams(String optionalParams) {
		this.optionalParams = optionalParams;
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