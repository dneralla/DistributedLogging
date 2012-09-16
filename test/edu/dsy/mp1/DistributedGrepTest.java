package edu.dsy.mp1;

import org.junit.Test;

public class DistributedGrepTest {
	@Test
	public void testGrepFunction() throws Exception {
		GrepRequestDispatcher dispatcher= new GrepRequestDispatcher("[1-9]*","*.log");
		dispatcher.run();
	}
}
