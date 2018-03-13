package search;

//import search.RedBlackTree.Node;

public class Node<Key, Value>{
	
	private final Comparable<Key> key;
	private Value val;
	private Node<Key, Value> left, right;
	private int n; //size of subtree beneath this node (inclusive)
	private boolean color;

	public Node(Comparable<Key> key, Value val, int n, boolean color){
		this.key = key;
		this.val = val;
		this.n = n;
		this.color = color;
	}
	
	public Comparable<Key> key(){
		return this.key;
	}
	
	public Value val(){
		return this.val;
	} 

	public void val(Value val){
		this.val = val;
	}
	
	public Node<Key, Value> left(){
		return this.left;
	}
	
	public void left(Node<Key, Value> left){
		this.left = left;
	}
	
	public Node<Key, Value> right(){
		return this.right;
	}
	
	public void right(Node<Key, Value> right){
		this.right = right;
	}
	
	public int n(){
		return this.n;
	}
	
	public void n(int n){
		this.n = n;
	}
	
	public boolean color(){
		return this.color;
	}
	
	public void color(boolean color){
		this.color = color;
	}
}
