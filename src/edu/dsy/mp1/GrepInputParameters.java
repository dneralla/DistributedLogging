package edu.dsy.mp1;
/*
 * Class for managing Grep parameters
 */
public class GrepInputParameters  extends InputParameters implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pattern;
	
	private String optionalParams;

	public GrepInputParameters(String pattern,String file)
	{
		super(file);
		this.pattern=pattern;
	}

	public GrepInputParameters(String pattern,String file,String optionalParams)
	{
		super(file);
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
	
}