package search;

//import search.RedBlackTree.Node;

public class Node<T>{
	
	private Comparable<T> key;
	private Comparable<T>[] val;
	private Node left, right;
	private int n; //size of subtree beneath this node (inclusive)
	private boolean color;

	public Node(Comparable<T> key, Comparable<T>[] val, int n, boolean color){
		this.key = key;
		this.val = val;
		this.n = n;
		this.color = color;
	}
	
	
	public Comparable<T> key(){
		return this.key;
	}
	
	public void key(Comparable<T> key){
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
