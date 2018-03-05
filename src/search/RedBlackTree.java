package search;

public class RedBlackTree<Key, Value> {
	
	private Node root;
	
	private int size();
	
	public <T> void put(Key key, Comparable<T>[] val){
		root = put(root, key, val);
		root.color(false);
	}
	
	private <T> Node put(Node h, Key key, Comparable<T>[] val){
		Node root = new Node(key, val, n, color);
		
		if(h == null)
			return new Node(key, val, 1, true);
		
		int cmp = key.compareTo(h.key());
		if(cmp < 0)
			h.left(put(h.left(), key, val));
		else if (cmp > 0)
			h.right(put(h.right(), key, val)); 
		else
			h.val(val);
		
		if(isRed(h.right) && !isRed(h.left))
			h = rotateLeft(h);
		if(isRed(h.left) && isRed(h.left.left))
			h = rotateRight(h);
		if(isRed(h.left) && isRed(h.right))
			flipColors(h);
		
		h.n = size(h.left) + size(h.right) + 1;
		return h;
	}
}
