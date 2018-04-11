package test;

import search.trawl.*;
import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.FileProcessor;
import data.Record;
import data.biotree.BioTree;
import data.biotree.TaxonNode;
import model.TrawlExpert;
import search.RedBlackTree;
import search.trawl.BasicSearch;
import search.trawl.BasicSearchResult;

/**
 * Testing was performed on a small dataset for which we know the correct numbers of data for each
 * taxon. Takes a while to process the dataset since most of the species in the set are unique and
 * thus require API calls.
 * @author TrawlStars, Inc.
 *
 */
public class TestHistogram {
	public static TrawlExpert te;
	public static RedBlackTree<Integer, Integer> histo;
	/**
	 * Loads the small dataset into memory using the FileProcessor.
	 * @throws Exception Cannot find dataset given.
	 */
	@BeforeClass
	public static void setUp() throws Exception {
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
