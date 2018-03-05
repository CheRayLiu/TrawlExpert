package search;

//import search.RedBlackTree.Node;

public class Node<Key, Value, T>{
	
	private Key key;
	private Comparable<T>[] val;
	private Node left, right;
	private int n;
	private boolean color;

	public Node(Key key, Comparable<T>[] val, int n, boolean color){
		this.key = key;
		this.val = val;
		this.n = n;
		this.color = color;
	}
	
	public Key key(){
		return this.key;
	}
	
	public void key(Key key){
		this.key = key;
		
	}
	
	public Comparable<T>[] val(){
		return this.val;
	} 

	public void val(Comparable<T>[] val){
		this.val = val;
	}
	
	public Node left(){
		return this.left;
	}
	
	public void left(Node left){
		this.left = left;
	}
	
	public Node right(){
		return this.right;
	}
	
	public void right(Node right){
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
