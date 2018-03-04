package biotree;

import java.io.IOException;

import search.BST;

public class BioTree {
	private static BST<Integer, TaxonNode> nodes;
	private static int n;
	
	/**
	 * Initialize species abstract object
	 */
	public static void init() {
		nodes = new BST<Integer, TaxonNode>();
		n = 0;
	}

	/**
	 * TODO: Implement
	 * 
	 * @param fn
	 *            Filename to read from
	 */
	public static void init(String fn) {

	}

	/**
	 * TODO: Implement
	 * 
	 * @param fn
	 *            Filename to write to
	 */
	public static void write(String fn) {

	}

	/**
	 * Process a record. Adds classification to tree if it doesn't exist.
	 * Returns the taxonId of the new / existing record.
	 * 
	 * @param taxonId The taxonId of the possible new entry
	 * @return taxonId of new species entry
	 */
	public static int processRecord(int taxonId) {
		processTaxonId(taxonId);
		return taxonId;
	}
	
	/**
	 * Process a record. Adds classification to tree if it doesn't exist.
	 * Returns the taxonId of the new / existing record.
	 * 
	 * @param scientificName The scientific name of the possible new entry
	 * @return taxonId of new / existing entry
	 * @throws IOException 
	 */
	public static int processRecord(String scientificName) throws IOException {
		int taxonId = WormsAPI.nameToID(scientificName);
		processTaxonId(taxonId);
		return taxonId;
	}
	
	/**
	 * Process a new entry if it doesn't exist.
	 * @param taxonId
	 */
	private static void processTaxonId(int taxonId) {
		TaxonNode[] newNodes = WormsAPI.idToClassification(taxonId);
		for (int i = newNodes.length - 1; i > 0; i--) {
			TaxonNode tx = newNodes[i];
			TaxonNode result = nodes.get(tx.getTaxonId());
			TaxonNode parent = nodes.get(newNodes[i - 1].getTaxonId());
			if (parent == null) parent = newNodes[i - 1];
			if (result == null) { //if node is not found, add it
				tx.setParent(parent);
				parent.addChild(tx);
			} else
				break; //stop loop if this node already exists in the tree
		}
	}

	/**
	 * Get the species at a given index (speciesid).
	 * 
	 * @param i
	 *            The speciesid (index) of the species.
	 * @return The Species object.
	 */
	public static TaxonNode getTaxonRecord(int taxonId) {
		return nodes.get(taxonId);
	}
}
