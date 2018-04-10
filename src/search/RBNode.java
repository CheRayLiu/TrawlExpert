package search;

import java.io.Serializable;

// An abstract data type that represents a node in a Red Black Search Tree
public class RBNode<Key extends Comparable<Key>, Value> implements Serializable{
	
	private final Key key;
	private Value val;
	private RBNode<Key, Value> left, right;
	private int n; 
	private boolean color;
	private static final long serialVersionUID = -5243859485408524039L;

	/**
	 * Constructor
	 * @param key Used to assign order amongst the nodes/implement comparability
	 * @param val The value stored at a node
	 * @param n How many nodes are in the subtree beneath this node (inclusive)
	 * @param color True represents a red connection between the current node and its parent, black represents false
	 */
	public RBNode(Key key, Value val, int n, boolean color){
		this.key = key;
		this.val = val;
		this.n = n;
		this.color = color;
	}
	
	/**
	 * Getter for a node's key
	 * @return The node's key
	 */
	public Key key(){
		return this.key;
	}
	
	/**
	 * Getter for a node's value
	 * @return The node's value
	 */
	public Value val(){
		return this.val;
	} 

	/**
	 * Setter for a node's value
	 * @param val New value being assigned
	 */
	public void val(Value val){
		this.val = val;
	}
	
	/**
	 * Getter for a node's left child
	 * @return The node's left child
	 */
	public RBNode<Key, Value> left(){
		return this.left;
	}
	
	/**
	 * Setter for a node's left child
	 * @param left The node's new left child
	 */
	public void left(RBNode<Key, Value> left){
		this.left = left;
	}
	
	/**
	 * Getter for a node's right child
	 * @return The node's right child
	 */
	public RBNode<Key, Value> right(){
		return this.right;
	}
	
	/**
	 * Setter for a node's right child
	 * @param left The node's new right child
	 */
	public void right(RBNode<Key, Value> right){
		this.right = right;
	}
	
	/**
	 * Getter for the subtree size beneath the current node
	 * @return Subtree size
	 */
	public int n(){
		return this.n;
	}
	
	/**
	 * Setter for a subtree's size
	 * @param n New subtree size
	 */
	public void n(int n){
		this.n = n;
	}
	
	/**
	 * Getter method for a node's color
	 * @return The node's color
	 */
	public boolean color(){
		return this.color;
	}
	
	/**
	 * Setter for a node's color
	 * @param color New node color
	 */
	public void color(boolean color){
		this.color = color;
	}
}
