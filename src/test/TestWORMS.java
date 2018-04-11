package test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

import data.WormsAPI;
import data.biotree.TaxonNode;
import data.biotree.TaxonType;

public class TestWORMS {
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNameToID() throws Exception {
		assert(WormsAPI.nameToID("Neogobius melanostomus").equals(126916));
	}
	
	@Test
	public void testNameToIdLike() throws ParseException, IOException {
		//test a mispelling of the name is corrected
		assert(WormsAPI.likeNameToID("Neogobius melanosto").equals(126916));
	}
	
	@Test
	public void testNameToIdFuzzy() throws ParseException, IOException {
		//test a mispelling of the name is corrected by fuzzy name (slower)
		assert(WormsAPI.fuzzyNameToID("Neogobius melanosto").equals(126916));
	}
	
	@Test
	public void testIdToClassification() throws ParseException, IOException {
		TaxonNode nodes[] = WormsAPI.idToClassification(126916);
		assert(nodes.length == 7);
		
		//test all parents returned are correct
		assert(nodes[7-1].getName().equals("Neogobius melanostomus"));
		assert(nodes[7-1].getTaxonType().equals(TaxonType.Species));
		
		assert(nodes[7-2].getName().equals("Neogobius"));
		assert(nodes[7-2].getTaxonType().equals(TaxonType.Genus));
		
		assert(nodes[7-3].getName().equals("Gobiidae"));
		assert(nodes[7-3].getTaxonType().equals(TaxonType.Family));
		
		assert(nodes[7-4].getName().equals("Perciformes"));
		assert(nodes[7-4].getTaxonType().equals(TaxonType.Order));
		
		assert(nodes[7-5].getName().equals("Actinopterygii"));
		assert(nodes[7-5].getTaxonType().equals(TaxonType.Class));
		
		assert(nodes[7-6].getName().equals("Chordata"));
		assert(nodes[7-6].getTaxonType().equals(TaxonType.Phylum));

		assert(nodes[7-7].getName().equals("Animalia"));
		assert(nodes[7-7].getTaxonType().equals(TaxonType.Kingdom));
	}

}
