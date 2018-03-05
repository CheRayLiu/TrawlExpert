package search;

//import search.RedBlackTree.Node;

public class Node<Key, Value>{
	
	private Key key;
	private Value val;
	private Node left, right;
	private int n;
	private boolean color;

	Node(Key key, Value val, int n, boolean color){
		this.key = key;
		this.val = val;
		this.n = n;
		this.color = color;
	}
	
	public Key key(){
		return this.key;
	}
	
	public Value val(){
		return this.val;
	} 
	
	public Node left(){
		return this.left;
	}
	
	public Node right(){
		return this.right;
	}
	
	public int N(){
		return this.n;
	}
	
	public boolean color(){
		return this.color;
	}
}
