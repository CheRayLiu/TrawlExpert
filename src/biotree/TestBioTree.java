package biotree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestBioTree {

	@Before
	public void setUp() throws Exception {
		BioTree.init();
		Species s0 = new Species("Actinopterygii","Perciformes","Moronidae","Morone","Morone chrysops");
		Species s1 = new Species("Actinopterygii","Perciformes","Percidae","Perca","Perca flavescens");
		BioTree.addSpecies(s0);
		BioTree.addSpecies(s1);
	}

	@Test
	public void testFindSpecies() {
		assert BioTree.findSpecies("Morone chrysops") == 0;
		assert BioTree.findSpecies("Perca flavescens") == 1;
	}
	
	@Test
	public void testGetSpecies() {
		assert BioTree.getTaxonRecord(0).getSpecies() == "Morone chrysops";
		assert BioTree.getTaxonRecord(1).getSpecies() == "Perca flavescens";
	}

}
