package biotree;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import search.BST;

public class BioTree {
	private static BST<Integer, TaxonNode> nodes;
	
	public static void main(String[] args) {
		init();
		processRecord(125125);
		processRecord(125125);
		processRecord(125123);
		processRecord(125122);
		processRecord(125392);
		processRecord(125391);
		System.out.println(nodes.size());
		System.out.println(nodes.get(123207));
		Iterable<Integer> keys = nodes.keys();
		for (Integer i: keys) {
			System.out.println(nodes.get(i));
			//System.out.println(String.format("%-26s %s", nodes.get(i).getName(), nodes.get(i).getTaxonType()));
		}
		printTree(nodes.get(2), 0);
	}
	
	/**
	 * Initialize species abstract object
	 */
	public static void init() {
		nodes = new BST<Integer, TaxonNode>();
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
		TaxonNode[] newNodes = null;
		try {
			newNodes = WormsAPI.idToClassification(taxonId);
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TaxonNode tx = nodes.get(newNodes[newNodes.length - 1].getTaxonId());
		if (tx != null) {
			tx.incCount();
		} else {
			newNodes[newNodes.length - 1].incCount();
		}
		for (int i = newNodes.length - 1; i >= 0; i--) {
			tx = newNodes[i];
			TaxonNode result = nodes.get(tx.getTaxonId());
			TaxonNode parent = null;
			if (i > 0) {
				parent = nodes.get(newNodes[i - 1].getTaxonId());
				if (parent == null) parent = newNodes[i - 1];
			}
			if (result == null) { //if node is not found, add it
				nodes.put(tx.getTaxonId(), tx);
				tx.setParent(parent);
				if (parent != null) parent.addChild(tx);
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
	
	public static void printTree(TaxonNode tx, int level) {
		String padd = new String(new char[level * 4]).replace('\0', ' ');
		System.out.format(padd + "%s %d\n", tx.getName(), tx.getCount());
		for (TaxonNode tx2: tx.getChildren())
			printTree(tx2, level + 1);
	}
}
