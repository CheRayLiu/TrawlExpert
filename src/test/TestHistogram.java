package test;

import search.trawl.*;

import org.junit.BeforeClass;
import org.junit.Test;

import data.FileProcessor;
import data.biotree.BioTree;
import model.TrawlExpert;

/**
 * Test cases for Histogram.java
 * @author TrawlStars, Inc.
 *
 */
public class TestHistogram {
	public static TrawlExpert te;

	@BeforeClass
	public static void setUp() throws Exception {
		BioTree.init();
		FileProcessor.setPath("smalldata.csv");
		FileProcessor.initProcessing();
		
	}

	@Test
	public void testSum1() {
		BasicSearchResult result = BasicSearch.range(154210, 1990, 2000);	// Esox lucius
		assert(Histogram.sum(result.results()) == 4);
	}
	
	@Test
	public void testSum2() {
		BasicSearchResult result = BasicSearch.range(154210, 2000, 2005);	// Esox lucius
		assert(Histogram.sum(result.results()) == 0);
	}
	
	@Test
	public void testSum3() {
		BasicSearchResult result = BasicSearch.range(448306, 2000, 2008);	// Anura
		assert(Histogram.sum(result.results()) == 9);
	}
}
