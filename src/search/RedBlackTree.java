package search;

public class RedBlackTree<Key, Value> {
	
	public static void main(String[] args) {
		int[] x = {1,2,3,4,5,6,7,8};
		
	}
	
	private Node root;
	
	private <T> int size(Node h){
		return h.n();
	}
	
	public <T> void put(Comparable<T> key, Comparable<T>[] val, GeneralCompare gc){
		root = put(root, key, val, gc);
		root.color(false);
	}
	
	private boolean isRed(Node x){
		return x.color();
	}
	
	public Node rotateLeft(Node h){
		Node x = h.right();
		h.right(x.left());
		x.left(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		h.n(1 + size(h.left()) + size(h.right()));
		return x;
	}
	
	public Node rotateRight(Node h){
		Node x = h.left();
		h.left(x.right());
		x.right(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		h.n(1 + size(h.left()) + size(h.right()));
		return x;
		
	}
	
	private void flipColors(Node h){
		if(h.left() != null && h.right() != null){
			h.left().color(false);
			h.right().color(false);
			h.color(true);
		}
	}
	
	private <T> Node put(Node h, Comparable<T> key, Comparable<T>[] val, GeneralCompare<T> gc){
		
		int n = size(h);
		
		Node root = new Node(key, val, n, true);
		
		if(h == null)
			return new Node(key, val, 1, true);
		
		int cmp = gc.compare(key, h.key());
		if(cmp < 0)
			h.left(put(h.left(), key, val, gc));
		else if (cmp > 0)
			h.right(put(h.right(), key, val, gc)); 
		else
			h.val(val);
		
		if(isRed(h.right()) && !isRed(h.left()))
			h = rotateLeft(h);
		if(isRed(h.left()) && isRed(h.left().left()))
			h = rotateRight(h);
		if(isRed(h.left()) && isRed(h.right()))
			flipColors(h);
		
		h.n(size(h.left()) + size(h.right()) + 1);
		return h;
	}
}
