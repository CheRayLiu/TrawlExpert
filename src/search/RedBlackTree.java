package search;

import java.io.Serializable;
import java.util.ArrayList;

import sort.GeneralCompare;

/**
 * 
 * @author HaleyGlavina
 *
 * @param <Key>
 * @param <Value>
 */
public class RedBlackTree<Key extends Comparable<Key>, Value> implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3108663423969613687L;
	private RBNode<Key, Value> root; // Root of the tree
	private GeneralCompare<Key> compare;
	private Field<Key, Value> field;
	
	/**
	 * Constructor for a red black tree object
	 * @param fld A function that retrieves the desired field from an array of comparable items
	 * @param gc A function that compares two comparable items
	 */
	public RedBlackTree(Field<Key, Value> fld, GeneralCompare<Key> gc) {
		compare = gc;
		field = fld;
		root = null;
	}
	
	/**
	 * Wrapper method for retrieving the node that matches a desired key
	 * @param key Key pointing to the desired node
	 * @return A node containing who's key matches the input
	 */
	public Value get(Key key) {
		RBNode<Key, Value> node = get(this.root, key);
		if (node == null) return null;
		else return get(this.root, key).val();
	}
	
	/**
	 * Finds the node in a tree whose key matches a desired key (if the matching node exists)
	 * @param node Root of the subtree being searched
	 * @param key Desired key to be searched for in a tree
	 * @return The node containing the key, returns null if the key is not found
	 */
	private RBNode<Key, Value> get(RBNode<Key, Value> node, Key key) {
		if (node == null) return null;
		if (node.key().equals(key))
			return node; 
		// If key is greater than current node, look right
		if (this.compare.compare(node.key(), key) < 0)
			if (node.right() != null)
				return get(node.right(), key);
			else 
				return null;
		// If key is smaller than current node, look left
		else if (this.compare.compare(node.key(), key) > 0)
			if (node.left() != null)
				return get(node.left(), key);
			else 
				return null;
		// If node == null, key does not exist in the tree
		return null;
	}

	/**
	 * Put a new node in the tree with a custom key. Allows the client to override the key
	 * in the node if they wish.
	 * @param key A custom key to assign to the node
	 * @param val A new record
	 */
	public void put(Key key, Value val){
		RBNode<Key, Value> newNode = new RBNode<Key, Value>(key, val, 1, true);
		root = put(root, newNode);
	}
	
	/**
	 * Put a new node in the tree. Uses the field getter supplied upon the creation
	 * of the tree to extract the key from the given value.
	 * @param val A new record
	 */
	public void put(Value val){
		RBNode<Key, Value> newNode = new RBNode<Key, Value>(field.field(val), val, 1, true);
		root = put(root, newNode);
	}
	
	/**
	 * Adds a new node to an existing tree
	 * @param h An existing node on the tree
	 * @param newNode A node to be added to the tree
	 * @return
	 */
	private RBNode<Key, Value> put(RBNode<Key, Value> h, RBNode<Key, Value> newNode){
		// Placing the first node in a tree
		if (root == null) {
			root = newNode;
			root.color(false);
			return root;
		}
		
		// Place new element in the tree
		int cmp = compare.compare(newNode.key(), h.key());
		if (cmp < 0 && (h.left() == null))
				h.left(newNode);
		else if (cmp < 0)
			h.left(put(h.left(), newNode));
		else if (cmp > 0 && (h.right() == null))
			h.right(newNode);
		else if (cmp > 0)
			h.right(put(h.right(), newNode));
		else
			h.val = newNode.val;

		// Rearrange the tree to maintain balance
		if(h.n() > 2){
			if(isRed(h.right()) && !isRed(h.left()))
				h = rotateLeft(h);
			if(isRed(h.left()) && isRed(h.left().left()))
				h = rotateRight(h);
			if(isRed(h.left()) && isRed(h.right()))
				flipColors(h);
		}

		// Increment how many nodes are in a subtree
		if ((h.left() != null) && (h.right() != null))
			h.n(h.left().n() + h.right().n() + 1);
		else if (h.left() != null)
			h.n(h.left().n() + 1);
		else if (h.right() != null)
			h.n(h.right().n() + 1);
		else 
			h.n(1);
		
		return h;
	}

	/**
	 * Check if the link to a node's parent is red
	 * @param x Node in a tree
	 * @return Boolean result of whether the node is red or not
	 */
	private boolean isRed(RBNode<Key, Value> x){
		if (x == null)
			return false; 
		return x.color();
	}

	/**
	 * Rotates a subtree in a counterclockwise direction
	 * @param h Root of a tree segment to be rotated
	 * @return New root of the rotated segment
	 */
	public RBNode<Key, Value> rotateLeft(RBNode<Key, Value> h){
		RBNode<Key, Value> x = h.right();
		h.right(x.left());
		x.left(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		
		// Increment how many nodes are in a subtree
		if (h.left() != null & h.right() != null)
			h.n(h.left().n() + h.right().n() + 1);
		else if (h.left() != null)
			h.n(h.left().n() + 1);
		else if (h.right() != null)
			h.n(h.right().n() + 1);
		else 
			h.n(1);
		
		return x;
	}

	/**
	 * Rotates a subtree in a clockwise direction
	 * @param h Root of a tree segment to be rotated
	 * @return New root of the rotated segment
	 */
	public RBNode<Key, Value> rotateRight(RBNode<Key, Value> h){
		RBNode<Key, Value> x = h.left();
		h.left(x.right());
		x.right(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		
		// Increment how many nodes are in a subtree
		if (h.left() != null & h.right() != null)
			h.n(h.left().n() + h.right().n() + 1);
		else if (h.left() != null)
			h.n(h.left().n() + 1);
		else if (h.right() != null)
			h.n(h.right().n() + 1);
		else 
			h.n(1);
		
		return x;

	}

	/**
	 * Changes two red connections from a single node to black
	 * @param h Root of tree segment whose colors are to be switched
	 */
	private void flipColors(RBNode<Key, Value> h){
		if(h.left() != null && h.right() != null){
			h.left().color(false);
			h.right().color(false);
			h.color(true);
		}
	}
	
	/**
	 * Getter method for the root of a tree, used for testing the module. Only available
	 * to methods in this package (for testing).
	 * @return The root of a tree object.
	 */
	public RBNode<Key, Value> root() {
		return root;
	}
	
	/**
	 * Returns all the keys in the red-black tree.
	 * @return An iterable of all keys in the red-black tree.
	 */
	public Iterable<Key> keys(){
		return keys(root, new ArrayList<Key>());
	}
	
	/**
	 * Private recursive method for finding all keys in the subtree of a given node.
	 * @param x The root node to get the subtree keys of.
	 * @param results The ArrayList to populate the keys with.
	 * @return An ArrayList of keys in this subtree.
	 */
	private ArrayList<Key> keys(RBNode<Key,Value> x, ArrayList<Key> results){
		if (x == null) return results;
		keys(x.left(), results);
		results.add(x.key());
		keys(x.right(), results);
		return results;		
	}
	
	/**
	 * Get the size of the tree.
	 * @return The total number number of nodes in the red-black tree. 
	 */
	public int size() {
		return root.n();
	}
}