package search;

public class RedBlackTree<Key, Value> {
	
	public static void main(String[] args) {
		GeneralCompare<Integer> b1;
		b1 = (a1, a2) -> (Integer) a1 - (Integer) a2;
		Integer[] x = {1,2,3,4,5,6,7,8};
		for(int i = 0; i <= x.length; i++){
			put(x[i], x, b1);
		}
	}
	
	private static Node root;
	
	private static <T> int size(Node h){
		return h.n();
	}
	
	public static <T> void put(Comparable<T> key, Comparable<T>[] val, GeneralCompare gc){
		root = put(root, key, val, gc);
		root.color(false);
		System.out.println(root.key());
		System.out.println(root.val());
	}
	
	private static boolean isRed(Node x){
		return x.color();
	}
	
	public static Node rotateLeft(Node h){
		Node x = h.right();
		h.right(x.left());
		x.left(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		h.n(1 + size(h.left()) + size(h.right()));
		return x;
	}
	
	public static Node rotateRight(Node h){
		Node x = h.left();
		h.left(x.right());
		x.right(h);
		x.color(h.color());
		h.color(true);
		x.n(h.n());
		h.n(1 + size(h.left()) + size(h.right()));
		return x;
		
	}
	
	private static void flipColors(Node h){
		if(h.left() != null && h.right() != null){
			h.left().color(false);
			h.right().color(false);
			h.color(true);
		}
	}
	
	private static <T> Node put(Node h, Comparable<T> key, Comparable<T>[] val, GeneralCompare<T> gc){
		
		if(h == null)
			return new Node(key, val, 1, true);
		
		int n = size(h) + 1;
		
		Node root = new Node(key, val, n, true);
		
		int cmp = gc.compare(key, h.key());
		if(cmp < 0)
			h.left(put(h.left(), key, val, gc));
		else if (cmp > 0)
			h.right(put(h.right(), key, val, gc)); 
		else
			h.val(val);
		
		if(n > 2){
			if(isRed(h.right()) && !isRed(h.left()))
				h = rotateLeft(h);
			if(isRed(h.left()) && isRed(h.left().left()))
				h = rotateRight(h);
			if(isRed(h.left()) && isRed(h.right()))
				flipColors(h);
		}
		
		if(h.left() == null)
			h.n(size(h.right()) + 1);
		else if(h.right() == null)
			h.n(size(h.left()) + 1);
		else
			h.n(size(h.left()) + size(h.right()) + 1);
		
		return h;
	}
}
