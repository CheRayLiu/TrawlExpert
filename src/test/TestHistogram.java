package test;

import search.trawl.*;

import org.junit.Before;
import org.junit.Test;

import model.TrawlExpert;
import search.RedBlackTree;
import search.trawl.BasicSearchResult;

/**
 * Test cases for Histogram.java
 * @author TrawlStars, Inc.
 *
 */
public class TestHistogram {
	public static TrawlExpert te;

	@Before
	public void setUp() throws Exception {
		// Assumes serialized data is present
		te = new TrawlExpert();
	}

	@Test
	public void testSum1() {
		BasicSearchResult result = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
		assert(Histogram.sum(result.results()) == 4);
	}
	
	@Test
	public void testSum2() {
		BasicSearchResult result = te.rangeSearch(154210, 2000, 2005);	// Esox lucius
		assert(Histogram.sum(result.results()) == 0);
	}
	
	@Test
	public void testSum3() {
		BasicSearchResult result = te.rangeSearch(448306, 2000, 2008);	// Anura
		assert(Histogram.sum(result.results()) == 9);
	}
}
