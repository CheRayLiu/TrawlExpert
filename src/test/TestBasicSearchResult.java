package test;

import search.trawl.*;

import org.junit.BeforeClass;
import org.junit.Test;

import data.FileProcessor;
import data.biotree.BioTree;
import search.trawl.BasicSearchResult;

/**
 * Test cases for BasicSearchResult.java
 * @author TrawlStars, Inc.
 *
 */
public class TestBasicSearchResult {	
	@BeforeClass
	public static void setUp() throws Exception {
		BioTree.init();
		FileProcessor.setPath("smalldata.csv");
		FileProcessor.initProcessing();
	}

	@Test
	public void testTaxonId() {
		BasicSearchResult bsr = BasicSearch.range(154210, 1990, 2000);	// Esox lucius
		assert(bsr.taxonId() == 154210);
	}
	
	@Test
	public void testYearLo() {
		BasicSearchResult bsr = BasicSearch.range(154210, 1990, 2000);	// Esox lucius
		assert(bsr.yearLo() == 1990);
	}
	
	@Test
	public void testYearHi() {
		BasicSearchResult bsr = BasicSearch.range(154210, 1990, 2000);	// Esox lucius
		assert(bsr.yearHi() == 2000);
	}
	
	@Test
	public void testSize() {
		BasicSearchResult bsr = BasicSearch.range(154210, 1990, 2000);	// Esox lucius
		assert(bsr.n() == 4);
	}
	
	@Test
	public void testSum() {
		BasicSearchResult bsr = BasicSearch.range(448306, 1990, 2008);	// Anura
		assert(bsr.sum() == 9);
	}
}


//public class TestBasicSearchResult {
//	public static TrawlExpert te;
//	
//	@Before
//	public void setUp() throws Exception {
//		// Assumes serialized data is present
//		te = new TrawlExpert();
//		FileProcessor.setPath("smalldata.csv");
//		FileProcessor.initProcessing();
//	}
//
//	@Test
//	public void testTaxonId() {
//		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
//		assert(bsr.taxonId() == 154210);
//	}
//	
//	@Test
//	public void testYearLo() {
//		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
//		assert(bsr.yearLo() == 1990);
//	}
//	
//	@Test
//	public void testYearHi() {
//		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
//		assert(bsr.yearHi() == 2000);
//	}
//	
//	@Test
//	public void testSize() {
//		BasicSearchResult bsr = te.rangeSearch(154210, 1990, 2000);	// Esox lucius
//		assert(bsr.n() == 4);
//	}
//	
//	@Test
//	public void testSum() {
//		BasicSearchResult bsr = te.rangeSearch(448306, 1990, 2008);	// Anura
//		assert(bsr.sum() == 9);
//	}
//}
