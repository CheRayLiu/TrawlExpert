package biotree;

import java.util.ArrayList;

// https://stackoverflow.com/questions/2697182/how-to-use-an-array-list
	
public class TaxonNode {
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
		this.children = null;
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
}
