package edu.dsy.mp1;

/**
 * 
 * Grep input Parameters Class Contains parameters regexPattern file and
 * optional params used by the Grep.
 * 
 */
public class GrepInputParameters extends InputParameters implements
		java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private String pattern;

	private String optionalParams;

	private String fileName;

	public GrepInputParameters() {
		super();
	}

	public GrepInputParameters(String pattern, String file) {
		this.fileName = file;
		this.pattern = pattern;
	}

	public GrepInputParameters(String pattern, String file,
			String optionalParams) {
		this.fileName = file;
		this.pattern = pattern;
		this.optionalParams = optionalParams;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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

}