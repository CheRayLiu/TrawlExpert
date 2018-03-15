package search;

public class RedBlackTree<Key, Value> {
	private Node<Key, Value> root; // Root of the tree
	private GeneralCompare<Key> compare;
	private Field<Key, Value> field;
	
	public static void main(String[] args) {
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Field<Integer, Integer[]> fld;
		fld = (a1) -> (Integer) a1[0];
		
		
		Integer[][] x = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}, {8, 8}, {9, 9}};
		RedBlackTree<Integer, Integer[]> myTree = new RedBlackTree<Integer, Integer[]>(fld, b1);
		for(int i = 0; i < x.length; i++){
			myTree.put(x[i]);
			System.out.println(" root: " + myTree.root().key());
			if (myTree.root().left() != null)
				System.out.println(" left: " + myTree.root().left().key());
			if (myTree.root().right() != null)
				System.out.println("right: " + myTree.root().right().key());
		}
	}
	
	/**
	 * Constructor for a red black tree object
	 * @param fld - A function that retrieves the desired field from an array of comparable items
	 * @param record - An array of comparable items holding data about a fish
	 * @param gc - A function that compares two comparable items
	 * @return The object itself
	 */
	public RedBlackTree(Field<Key, Value> fld, GeneralCompare<Key> gc) {
		compare = gc;
		field = fld;
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
	 * @param val - A new record
	 */
	public void put(Value val){
		put(root, field.field(val), val, this.compare);
	}
	
	/**
	 * Adds a new node to an existing tree
	 * @param h - An existing node on the tree
	 * @param key - The field being compared to place a record in a sorted tree
	 * @param val - The record being added to a tree
	 * @param gc - A function that compares two comparable items
	 * @return
	 */
	private Node<Key, Value> put(Node<Key, Value> h, Comparable<Key> key, Value val, GeneralCompare<Key> gc){
		
		// Placing the first node in a tree
		if (root == null) {
			root = new Node(key, val, 1, false);
			return root;
		}
		
		if(h == null)
			return new Node(key, val, 1, true);
		int n = h.n();
		Node<Key, Value> newNode = new Node(key, val, n, true);
		
		// Place new element in the tree
		int cmp = gc.compare(key, h.key());
		if (cmp < 0 & (h.left() == null))
				h.left(newNode);
		else if (cmp < 0 )
			h.left(put(h.left(), field.field(val), val, gc));
		else if (cmp > 0 & (h.right() == null))
			h.right(newNode);
		else if (cmp > 0)
			h.right(put(h.right(), field.field(val), val, gc));
		else
			h = newNode;

		// Rearrange the tree to maintain balance
		if(n > 2){
			if(isRed(h.right()) && (isRed(h.left())))
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
	 * @param x - Node in a tree
	 * @return Boolean result of whether the node is red or not
	 */
	private boolean isRed(Node<Key, Value> x){
		if (x == null)
			return false; 
		return x.color();
	}

	/**
	 * Rotates a subtree in a counterclockwise direction
	 * @param h - Root of a tree segment to be rotated
	 * @return New root of the rotated segment
	 */
	public Node<Key, Value> rotateLeft(Node<Key, Value> h){
		System.out.println("Rotate left!");
		Node<Key, Value> x = h.right();
		h.right(x.left());
		x.left(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		
		//h.n(1 + h.left().n() + h.right().n());
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
	 * @param h - Root of a tree segment to be rotated
	 * @return New root of the rotated segment
	 */
	public Node<Key, Value> rotateRight(Node<Key, Value> h){
		Node<Key, Value> x = h.left();
		h.left(x.right());
		x.right(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		
		//h.n(1 + h.left().n() + h.right().n());
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
	 * @param h - Root of tree segment whose colors are to be switched
	 */
	private void flipColors(Node<Key, Value> h){
		if(h.left() != null && h.right() != null){
			h.left().color(false);
			h.right().color(false);
			h.color(true);
		}
	}
}