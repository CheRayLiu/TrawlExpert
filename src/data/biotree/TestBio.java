package data.biotree;

import static org.junit.Assert.*;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.FileProcessor;
import search.trawl.BasicSearch;

/**
 * Testing was performed on a small dataset for which we know the correct numbers of data for each
 * taxon. Takes a while to process the dataset since most of the species in the set are unique and
 * thus require API calls.
 * @author TrawlStars, Inc.
 *
 */
public class TestBio {

	/**
	 * Loads the small dataset into memory using the FileProcessor.
	 * @throws Exception Cannot find dataset given.
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		//load small test dataset 
		BioTree.init();
		FileProcessor.setPath("smalldata.csv");
		FileProcessor.initProcessing();
	}

	@Test
	public void testAnuraExists() throws ParseException {
		assert(BioTree.getTaxonRecord("Anura") != null);
	}
	
	@Test
	public void testGetTaxonNode() {
		//test that Esox is in dataset
		assert(BioTree.getTaxonRecord("Esox lucius") != null);
	}
	
	@Test
	public void testGetTaxonNodeFails() {
		//test that Esox is in dataset
		assert(BioTree.getTaxonRecord("Mythical Creature") == null);
	}
	
	@Test
	public void testGetCount() {
		//test count is correct
		assert(BioTree.getTaxonRecord("Esox lucius").getCount() == 4);
	}
	
	@Test
	public void testGetName() {
		//test name correct
		assert(BioTree.getTaxonRecord("Esox lucius").getName().equals("Esox lucius"));
	}
	
	@Test
	public void testGetParent() {
		//test that the correct parent has been stored in the dataset
		assert(BioTree.getTaxonRecord("Esox lucius").getParent().getTaxonId() == 154208);
	}
	
	@Test
	public void testGetChildren() {
		//test that the correct parent has been stored in the dataset
		for (TaxonNode tx: BioTree.getTaxonRecord("Esox").getChildren())
			assert(tx.getName().equals("Esox lucius") || tx.getName().equals("Esox masquinongy"));
	}

}
