package search;

public class RedBlackTree<T> {
	private Node root; // Root of the tree
	private GeneralCompare compare;
	private Field field;
	
	public static void main(String[] args) {
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		fld = (a1) -> (Integer) a1[0];
		
		
		Integer[][] x = {{1, 1}, {2, 2}, {3, 3}, {4, 4}, {5, 5}, {6, 6}, {7, 7}, {8, 8}, {9, 9}};
		RedBlackTree myTree = RedBlackTree<Integer>(fld, x[0], b1);
		for(int i = 1; i < x.length; i++){
			myTree.put(x[i], x, b1);
		}
		/*
		h = x.root();
		while (h.right())
			System.out.println(h.right());
			h = h.right();
		*/
	}
	
	// RedBlackTree Constructor
	public RedBlackTree<T> RedBlackTree(Field fld, Comparable<T>[] record, GeneralCompare gc) {
		root.val(record);
		compare = gc;
		field = fld;
		
		root.color(false);
		root.key(fld(record));
		root.left(null);
		root.right(null);
		return this;
	}
	
	
	// Get root of a tree
	public Node root() {
		return this.root();
	}
	
	public void put(Comparable<T>[] val){
		put(root, field(val), val, this.compare);
	}
	
	private Node put(Node h, Comparable<T> key, Comparable<T>[] val, GeneralCompare<T> gc){
		
		if(h == null)
			return new Node(key, val, 1, true);
		int n = h.n();
		Node newNode = new Node(key, val, n, true);
		
		// Place new element in the tree
		int cmp = gc.compare(key, h.key());
		if (cmp < 0 )
			h.left(put(h.left(), field(val), val, gc));
		else if (cmp > 0)
			h.right(put(h.right(), field(val), val, gc));
		else

		// Rearrange the tree to maintain balance
		if(n > 2){
			if(isRed(h.right()) && !isRed(h.left()))
				h = rotateLeft(h);
			if(isRed(h.left()) && isRed(h.left().left()))
				h = rotateRight(h);
			if(isRed(h.left()) && isRed(h.right()))
				flipColors(h);
		}

		h.n(h.left().n() + h.right().n() + 1);
		return h;
	}


	// Check if the link to a node's parent is red
	private boolean isRed(Node x){
		if(x == null)
			return false;
		return true;
	}

	public Node rotateLeft(Node h){
		Node x = h.right();
		h.right(x.left());
		x.left(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		h.n(1 + h.left().n() + h.right().n());
		return x;
	}

	public static Node rotateRight(Node h){
		Node x = h.left();
		h.left(x.right());
		x.right(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		h.n(1 + h.left().n() + h.right().n());
		return x;

	}

	private void flipColors(Node h){
		if(h.left() != null && h.right() != null){
			h.left().color(false);
			h.right().color(false);
			h.color(true);
		}
	}
}