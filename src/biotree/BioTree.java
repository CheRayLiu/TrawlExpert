package biotree;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import search.BST;

public class BioTree {
	private static BST<Integer, TaxonNode> nodes;
	
	/**
	 * Initialize species abstract object
	 */
	public static void init() {
		nodes = new BST<Integer, TaxonNode>();
	}

	/**
	 * Reads the BioTree from a file written by write().
	 * TODO: Implement
	 * 
	 * @param fn
	 *            Filename to read from
	 */
	public static void init(String fn) {

	}

	/**
	 * Writes the BioTree BST to a file.
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
	public static Integer processRecord(int taxonId) {
		//pass taxonId directly to function to add / increment it
		if (processTaxonId(taxonId)) return null;
		System.out.println(taxonId);
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
	public static Integer processRecord(String scientificName) throws IOException {
		//reverse lookup based on name, try adding the found taxonId.
		int taxonId = WormsAPI.nameToID(scientificName);
		if (processTaxonId(taxonId)) return null;
		return taxonId;
	}
	
	/**
	 * Process a new entry if it doesn't exist. If it does exist, increment the number
	 * of Records for this classification by one. 
	 * @param taxonId New / existing TaxonID to add / increment count thereof.
	 * @return true if the process failed, false if nothing went wrong
	 */
	private static boolean processTaxonId(int taxonId) {
		TaxonNode[] newNodes = null;			//possible eventual new nodes
		TaxonNode tx = nodes.get(taxonId);	//search tree to see if the node exists already
		if (tx != null)						//if it does exist, increment its count
			tx.incCount();
		else {								//otherwise, perform API call to get tree
			try {
				newNodes = WormsAPI.idToClassification(taxonId);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (newNodes == null) return true;
			newNodes[newNodes.length - 1].incCount();	//one of the new nodes exists
			
			for (int i = newNodes.length - 1; i >= 0; i--) {		//iterate over all node starting from lowest child
				tx = newNodes[i];
				TaxonNode current = nodes.get(tx.getTaxonId());
				TaxonNode parent = null;
				if (i > 0) {										//if this is not the highest up find its parent
					parent = nodes.get(newNodes[i - 1].getTaxonId());		//the parent is either already in existence
					if (parent == null) parent = newNodes[i - 1];		//or is is the old one that will be added later
				}
				if (current == null) { 							//if this node is not found, add it
					nodes.put(tx.getTaxonId(), tx);				//put it in the search structure
					tx.setParent(parent);						//set its parent to the last
					if (parent != null) parent.addChild(tx);		//if a parent exists, add it as a child to its parent
				} else
					//stop loop if this node already exists in the tree (all its parents must exist too!)
					break;
			}
		}
		return false;
	}

	/**
	 * Get the species at a given index (taxonId). This assumes that the
	 * node already exists or else it will return null. As such, it is best
	 * to use this function once all the data has been parsed and the BioTree
	 * has been built. 
	 * 
	 * @param i
	 *            The speciesid (index) of the species.
	 * @return The Species object.
	 */
	public static TaxonNode getTaxonRecord(int taxonId) {
		return nodes.get(taxonId);
	}
	
	public static void printTree() {
		printTree(nodes.get(2), 0);
	}
	
	/**
	 * Print a taxonNode's tree starting at the supplied root.
	 * @param tx
	 * @param level
	 */
	private static void printTree(TaxonNode tx, int level) {
		String padd = new String(new char[level * 4]).replace('\0', ' ');
		System.out.format(padd + "%s %d\n", tx.getName(), tx.getCount());
		for (TaxonNode tx2: tx.getChildren())
			printTree(tx2, level + 1);
	}
}
