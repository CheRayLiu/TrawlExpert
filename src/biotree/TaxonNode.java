package biotree;

import java.util.ArrayList;

/**
 * This class implements TaxonNode ADT
 * The BioTree class uses these nodes to construct its tree.
 * An implicit taxonomic hierarchy is constructed via. the parent and children properties. 
 * 
 * Used the following source(s):
 * https://stackoverflow.com/questions/2697182/how-to-use-an-array-list
 */
	
public class TaxonNode {
	private final int taxonId;
	private final TaxonType taxonType;
	private final String name;
	
	private TaxonNode parent;
	private ArrayList<TaxonNode> children = new ArrayList<TaxonNode>();
	private int count;
	
	
	/**
	 * TaxonNode constructor
	 * 
	 * @param taxonId
	 * @param taxonType
	 * @param name
	 */
	public TaxonNode(int taxonId, TaxonType taxonType, String name) {
		this.taxonId = taxonId;
		this.taxonType = taxonType;
		this.name = name;
		this.parent = null;
		this.children = new ArrayList<TaxonNode>();
		this.count = 0;
	}
	
	/**
	 * Gets the TaxonNode's Id
	 * 
	 * @return taxonId
	 */
	public int getTaxonId() {
		return this.taxonId;
	}

	/**
	 * Gets the TaxonNode's Type
	 * 
	 * @return taxonType
	 */
	public TaxonType getTaxonType() {
		return this.taxonType;
	}

	/**
	 * Gets the TaxonNode's Name
	 * 
	 * @return Name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Gets the TaxonNode's Parent
	 * 
	 * @return parent
	 */
	public TaxonNode getParent() {
		return this.parent;
	}
	
	/**
	 * Gets the TaxonNode's Arraylist of Children
	 * 
	 * @return Arraylist of children
	 */
	public Iterable<TaxonNode> getChildren() {
		return this.children;
	}
	
	
	/**
	 * Gets the TaxonNode's Children Count
	 * 
	 * @return Children Count
	 */
	public int getCount() {
		return this.count;
	}
	
	
	/**
	 * Sets the TaxonNode's Parent
	 * 
	 * @param parent
	 */
	public void setParent(TaxonNode parent) {
		this.parent = parent;
	}

	/**
	 * Adds a new child to the children list 
	 * 
	 * @param newChild
	 */
	public void addChild(TaxonNode newChild) {
		this.children.add(newChild);
	}
	
	/**
	 * Increments the children count
	 */
	public void incCount() {
		this.count ++;
	}
	
	/**
	 * Converts the properties of the taxonNode to String format
	 * 
	 * @return String of TaxonNode properties
	 */
	public String toString() {
		String s = "";
		s += String.format("%-20s%s\n", "Scientific Name:", name);
		s += String.format("%-20s%s\n", "Taxon Type:", taxonType);
		s += String.format("%-20s%d\n", "Taxon ID:", taxonId);
		if (parent != null) s += String.format("%-20s%d\n", "Parent:", parent.getTaxonId());
		s += String.format("%-20s", "Children:");
		for (TaxonNode tx: children) {
			s += String.format("%d ", tx.getTaxonId());
		}
		s += "\n";
		s += String.format("%-20s%d\n", "Count:", count);
		return s;
	}
}
