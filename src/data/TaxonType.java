package data;

/**
 * Enumeration of classifications of Taxontype.
 * @author Christopher W. Schankula
 *
 */


public enum TaxonType {
	Kingdom, Phylum, Class, Order,
	Family, Genus, Species, Subspecies;
	
	public String toString() {
		return name();
	}
}
