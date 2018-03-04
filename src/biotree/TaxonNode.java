package biotree;

import java.util.ArrayList;

// https://stackoverflow.com/questions/2697182/how-to-use-an-array-list
	
public class TaxonNode {
	//JSON returns long
	private final int taxonId;
	private final TaxonType taxonType;
	private final String name;
	
	private TaxonNode parent;	// this can't be final unless parent is recursively returned and constructed in constructor.
	private ArrayList<TaxonNode> children = new ArrayList<TaxonNode>();
	private int count;
	
	public TaxonNode(int taxonId, TaxonType taxonType, String name) {
		this.taxonId = taxonId;
		this.taxonType = taxonType;
		this.name = name;
		this.parent = null;
		this.children = new ArrayList<TaxonNode>();
		this.count = 0;
	}
	
	public int getTaxonId() {
		return this.taxonId;
	}
	
	public TaxonType getTaxonType() {
		return this.taxonType;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setParent(TaxonNode parent) {
		this.parent = parent;
	}
	
	public void addChild(TaxonNode newChild) {
		this.children.add(newChild);
	}
	
	public TaxonNode getParent() {
		return this.parent;
	}
	
	//Stub Changed
	public Iterable<TaxonNode> getChildren() {
		return this.children;
	}
	
	public int getCount() {
		return this.count;
	}
	
	public void incCount() {
		this.count ++;
	}
	
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
