package search;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
	private Node<Key, Value> root; // Root of the tree
	private GeneralCompare<Key> compare;
	private Field<Key, Value> field;
	
	// Main method only used for testing
	/*
	public static void main(String[] args) {
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Field<Integer, Integer[]> fld;
		fld = (a1) -> (Integer) a1[0];
		
		
		Integer[][] x = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}, {8, 8}, {9, 9}};
		RedBlackTree<Integer, Integer[]> myTree = new RedBlackTree<Integer, Integer[]>(fld, b1);

		// Add first 5 nodes, expected get(6) result is null
		for(int i = 0; i < 4; i++){
			myTree.put(x[i]);
		}
		assert(myTree.get((Comparable<Integer>) 6) == null);

		// Add remaining nodes, expected get(6) result is {6, 6} 
		for(int i = 5; i < x.length; i++){
			//System.out.println(x[i][0]);
			myTree.put(x[i]);
		}
		
		System.out.println("Root: " + myTree.root().key());
		System.out.println("myTree.get(6).key(): " + (Integer) myTree.get((Comparable<Integer>) 6).key());
		/*
		Node h = myTree.root(); 
		System.out.println(h.key());
		while (h.right() != null) {
			System.out.println(h.right().key());
			h = h.right();
		}
		
	}*/
	
	
	
	
	/**
	 * Constructor for a red black tree object
	 * @param fld A function that retrieves the desired field from an array of comparable items
	 * @param gc A function that compares two comparable items
	 */
	public RedBlackTree(Field<Key, Value> fld, GeneralCompare<Key> gc) {
		compare = gc;
		field = fld;
	}
	
	/**
	 * Wrapper method for retrieving the node that matches a desired key
	 * @param key Key pointing to the desired node
	 * @return A node containing who's key matches the input
	 */
	public Node<Key, Value> get(Key key) {
		return get(this.root, key);
	}
	
	/**
	 * Finds the node in a tree whose key matches a desired key (if the matching node exists)
	 * @param node Root of the subtree being searched
	 * @param key Desired key to be searched for in a tree
	 * @return The node containing the key, returns null if the key is not found
	 */
	private Node<Key, Value> get(Node<Key, Value> node, Key key) {
		if (node.key().equals(key)) {
			return node; 
		}
		// If key is greater than current node, look right
		if (this.compare.compare(node.key(), (Key) key) < 0)
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
	 * Getter method for the root of a tree
	 * @return The root of a tree object
	 */
	public Node<Key, Value> root() {
		return root;
	}
	
	/**
	 * Wrapper method for adding a new node
	 * @param val A new record
	 */
	public void put(Value val){
		Node<Key, Value> newNode = new Node<Key, Value>(field.field(val), val, 1, true);
		root = put(root, newNode);
	}
	
	/**
	 * Adds a new node to an existing tree
	 * @param h An existing node on the tree
	 * @param newNode A node to be added to the tree
	 * @return
	 */
	private Node<Key, Value> put(Node<Key, Value> h, Node<Key, Value> newNode){
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
		else if (cmp < 0 )
			h.left(put(h.left(), newNode));
		else if (cmp > 0 && (h.right() == null))
			h.right(newNode);
		else if (cmp > 0)
			h.right(put(h.right(), newNode));
		else
			h = newNode;

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
	private boolean isRed(Node<Key, Value> x){
		if (x == null)
			return false; 
		return x.color();
	}

	/**
	 * Rotates a subtree in a counterclockwise direction
	 * @param h Root of a tree segment to be rotated
	 * @return New root of the rotated segment
	 */
	public Node<Key, Value> rotateLeft(Node<Key, Value> h){
		Node<Key, Value> x = h.right();
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
	public Node<Key, Value> rotateRight(Node<Key, Value> h){
		Node<Key, Value> x = h.left();
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
	private void flipColors(Node<Key, Value> h){
		if(h.left() != null && h.right() != null){
			h.left().color(false);
			h.right().color(false);
			h.color(true);
		}
	}
}