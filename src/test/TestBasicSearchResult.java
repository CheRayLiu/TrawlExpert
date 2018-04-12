package test;

import search.trawl.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import data.Record;
import model.TrawlExpert;
import search.RedBlackTree;
import search.trawl.BasicSearchResult;

/**
 * Test cases for BasicSearchResult.java
 * @author TrawlStars, Inc.
 *
 */
public class TestBasicSearchResult {
	public static TrawlExpert te;
	
	@Before
	public void setUp() throws Exception {
		// Assumes serialized data is present
		te = new TrawlExpert();
	}

	@Test
	public void testTaxonId() {
		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
		assert(bsr.taxonId() == 154210);
	}
	
	@Test
	public void testYearLo() {
		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
		assert(bsr.yearLo() == 1990);
	}
	
	@Test
	public void testYearHi() {
		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
		assert(bsr.yearHi() == 2000);
	}
	
	@Test
	public void testSize() {
		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
		assert(bsr.n() == 4);
	}
	
	@Test
	public void testSum() {
		BasicSearchResult bsr = te.rangeSearch(448306, 1990, 2008);	// Anura
		assert(bsr.sum() == 9);
	}
}
