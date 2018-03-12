package search;

public class RedBlackTree<Key, Value, T> {
	private Node root; // Root of the tree
	private GeneralCompare compareType;
	private Comparable<T> fieldType;
	
	public static void main(String[] args) {
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Integer[] x = {1,2,3,4,5,6,7,8,9};
		RedBlackTree myTree = RedBlackTree(x[0], x[0], b1);
		for(int i = 0; i < x.length; i++){
			put(x[i], x, b1);
		}
		/*
		h = x.root();
		while (h.right())
			System.out.println(h.right());
			h = h.right();
		*/
	}
	
	// RedBlackTree Constructor
	public RedBlackTree<Key, Value, T> RedBlackTree(Comparable<T> fieldT, Comparable<T>[] record, GeneralCompare gc) {
		root.val(record);
		compareType = gc;
		fieldType = fieldT;
		
		root.color(false);
		root.key(Field.field(fieldType, record));
		root.left(null);
		root.right(null);
		return this;
	}
	
	
	// Get root of a tree
	public Node root() {
		return this.root();
	}
	
	public void put(Comparable<T>[] val){
		put(root, Field.field(fieldType, val), val, this.compareType);
	}
	
	private Node put(Node h, Comparable<T> key, Comparable<T>[] val, GeneralCompare<T> gc){
		
		if(h == null)
			return new Node(key, val, 1, true);
		int n = h.n();
		Node newNode = new Node(key, val, n, true);
		
		int cmp = gc.compare(key, h.key());
		if (cmp < 0 )
			h.left(put(h.left(), Field.field(fieldType, val), val, gc));
		else if (cmp > 0)
			h.right(put(h.right(), Field.field(fieldType, val), val, gc));
		else

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