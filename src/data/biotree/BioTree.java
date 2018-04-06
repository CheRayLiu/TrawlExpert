package data.biotree;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import data.WormsAPI;
import search.BST;
import search.Field;
import search.kdt.KDT;
import sort.GeneralCompare;

public class BioTree implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4291273291916906661L;
	//FIXME: replace with a single kd-tree
	private static KDT<TaxonNode> nodes;
	private static BST<Integer, TaxonNode> idNodes = new BST<Integer, TaxonNode>();
	private static BST<String, TaxonNode> strNodes = new BST<String, TaxonNode>();
	private static BST<String, Integer> incorrectNames = new BST<String, Integer>();
	
	public static void main(String[] args) throws IOException, ParseException {
		BioTree.init("data/biotree");
		System.out.println(idNodes.get(2));
		Iterable<Integer> children = getNonEmptyChildren(159512);
		for (Integer i: children)
			System.out.println(i);
	}
	
	/**
	 * Initialize species abstract object
	 */
	public static void init() {
		GeneralCompare<TaxonNode> compName = (tn0, tn1) -> tn0.getName().compareTo(tn1.getName());
		GeneralCompare<TaxonNode> compTxId = (tn0, tn1) -> tn0.getTaxonId() - tn1.getTaxonId();
		Field<String, TaxonNode> fld = tn -> tn.getName();
		
		//nodes = new KDT<>(axes, keyvals);
		
		idNodes = new BST<Integer, TaxonNode>();
		strNodes = new BST<String, TaxonNode>();
		incorrectNames = new BST<String, Integer>();
	}

	/**
	 * Reads the BioTree from a file written by write().
	 * TODO: Implement
	 * 
	 * @param fn
	 *            Filename to read from
	 * @return 
	 */
	public static void init(String fn) {
		BST<Integer, TaxonNode> idNodes = null;
		try {
	         FileInputStream fileIn = new FileInputStream(fn+"/idnodes.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         idNodes = (BST<Integer, TaxonNode>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
		BioTree.idNodes = idNodes;
		
		BST<String, TaxonNode> strNodes = null;
		try {
	         FileInputStream fileIn = new FileInputStream(fn+"/strNodes.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         strNodes = (BST<String, TaxonNode>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	      }
		BioTree.strNodes = strNodes;
		
		BST<String, Integer> incorrectNames = null;
		try {
	         FileInputStream fileIn = new FileInputStream(fn+"/incorNames.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         incorrectNames = (BST<String, Integer>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      } catch (ClassNotFoundException c) {
	         c.printStackTrace();
	      }
		BioTree.incorrectNames = incorrectNames;
	}

	/**
	 * Writes the BioTree BST to a file.
	 * TODO: Implement
	 * 
	 * @param dir
	 *            Filename to write to
	 */
	public static void write(String dir) {
		//https://examples.javacodegeeks.com/core-java/io/file/check-if-directory-exists/
		File d = new File(dir);
		if (!d.exists())
			d.mkdirs();
		try {
	         FileOutputStream fileOut =
	        		 new FileOutputStream(dir+"/idNodes.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(BioTree.idNodes);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/kdtree.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		try {
	         FileOutputStream fileOut =
	        		 new FileOutputStream(dir+"/strNodes.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(BioTree.strNodes);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/kdtree.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		try {
	         FileOutputStream fileOut =
	        		 new FileOutputStream(dir+"/incorNames.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(BioTree.incorrectNames);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in /tmp/kdtree.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
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
		return taxonId;
	}
	
	/**
	 * Process a record. Adds classification to tree if it doesn't exist.
	 * Returns the taxonId of the new / existing record.
	 * 
	 * @param scientificName The scientific name of the possible new entry
	 * @return taxonId of new / existing entry
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static Integer processRecord(String scientificName) throws IOException, ParseException {
		//reverse lookup based on name, try adding the found taxonId.
		Integer taxonId = nameToTaxonId(scientificName);
		System.out.println(scientificName + ": " + taxonId);
		if (taxonId == null) return null;
		if (taxonId == -1)   return null;
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
		TaxonNode tx = idNodes.get(taxonId);	//search tree to see if the node exists already
		System.out.println("tx" + tx);
		if (tx != null)	{					//if it does exist, increment its count
			tx.incCount();
		}
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
				TaxonNode current = idNodes.get(tx.getTaxonId());
				TaxonNode parent = null;
				if (i > 0) {										//if this is not the highest up find its parent
					parent = idNodes.get(newNodes[i - 1].getTaxonId());		//the parent is either already in existence
					if (parent == null) parent = newNodes[i - 1];		//or is is the old one that will be added later
				}
				if (current == null) { 							//if this node is not found, add it
					System.out.println("Put: " + tx.getTaxonId());
					idNodes.put(tx.getTaxonId(), tx);				//put it in the search structure
					strNodes.put(tx.getName(), tx);
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
		return idNodes.get(taxonId);
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
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static TaxonNode getTaxonRecord(String scientificName) throws IOException, ParseException {
		return idNodes.get(nameToTaxonId(scientificName));
	}
	
	/**
	 * Get the TaxonNode containing information about the given scientific name. 
	 * This assumes that thenode already exists locally or else it will return null. 
	 * As such, it is best to use this function once all the data has been parsed 
	 * and the BioTree has been built. 
	 * 
	 * @param scientificName
	 *            The scientific name of the taxon.
	 * @return The Species object.
	 * @throws ParseException 
	 * @throws IOException 
	 */
	public static Integer nameToTaxonId(String scientificName) throws IOException, ParseException {
		Integer taxonId;
		//look up based on string literal, return if found
		TaxonNode tx = strNodes.get(scientificName);
		if (tx != null) return tx.getTaxonId();
		else System.out.println(scientificName + " not in local db");
		//look up in local incorrect names database, return if it exists
		taxonId = incorrectNames.get(scientificName);
		if (taxonId != null) {
			tx = idNodes.get(taxonId);
			if (tx != null) return tx.getTaxonId();
		} else {		//otherwise use Worms to look it up
			System.out.println(scientificName + " not in incor db");
			taxonId = WormsAPI.nameToRecordID(scientificName);
			if (taxonId == null) //if nothing is found, mark this species as not existing.
				incorrectNames.put(scientificName, -1);
			else {
				System.out.println(scientificName + " found in Worms: " + taxonId);
				incorrectNames.put(scientificName, taxonId);
			}
		}
		return taxonId;
	}
	
	public static Iterable<Integer> getNonEmptyChildren(int taxonId){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		getAllChildren(idNodes.get(taxonId), result, false);
		
		return result;
	}
	
	public static Iterable<Integer> getAllChildren(int taxonId){
		ArrayList<Integer> result = new ArrayList<Integer>();
		
		getAllChildren(idNodes.get(taxonId), result, true);
		
		return result;
	}
	
	private static void getAllChildren(TaxonNode txNode, ArrayList<Integer> result, boolean emptyAllowed) {
		if (txNode == null) return;
		if ((txNode.getCount() > 0) || emptyAllowed) result.add(txNode.getTaxonId());
		for (TaxonNode tx: txNode.getChildren()) {
			getAllChildren(tx, result, emptyAllowed);
		}
	}
	
	public static void printTree() {
		printTree(idNodes.get(2), 0);
	}
	
	public static void printTree(int taxonId) {
		TaxonNode txNode = idNodes.get(taxonId);
		if (txNode == null) return;
		printTree(txNode, 0);
	}
	
	public static void printTree(String scientificName) {
		TaxonNode txNode = strNodes.get(scientificName);
		if (txNode == null)
			try {
				int taxonId = WormsAPI.nameToRecordID(scientificName);
				txNode = idNodes.get(taxonId);
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (txNode == null) return;
		printTree(txNode, 0);
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
