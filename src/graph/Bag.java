//Based on Bag from Sedgewick & Wayne Algorithms 4th Edition (2011).

package graph;

import java.util.Iterator;

/**
 * A class for storing an unordered collection of items.
 * @author TrawlStars, Inc.
 *
 * @param <Value> The type of value to store in the Bag.
 */
public class Bag<Value> implements Iterable<Value> {

	/**
	 * The first item in the bag.
	 */
	private BagNode first; //pointer to head of linked list
	
	/**
	 * A node in the bag (like a linked-list node).
	 * @author TrawlStars, Inc.
	 *
	 */
	private class BagNode{
		/**
		 * The item in the node
		 */
		Value item;
		/**
		 * A pointer to the next node.
		 */
		BagNode next;
	}
	
	/**
	 * Adds new node to linked list
	 * @param item the item to be added to the linked list
	 */
	public void add(Value item){
		BagNode oldfirst = first;
		first = new BagNode();
		first.item = item;
		first.next = oldfirst;
	}
	
	/**
	 * Provides an iterator of items in the bag.
	 */
	@Override
	public Iterator<Value> iterator() {
		return new ListIterator();
	}
	
	/**
	 * Generates an iterator of items in the bag.
	 * @author TrawlStars, Inc.
	 *
	 */
	private class ListIterator implements Iterator<Value>{
		
		/**
		 * The first item in the bag.
		 */
		private BagNode current = first;
		
		/**
		 * Returns whether there are more items in the bag.
		 * @return true if there are more items available, false otherwise.
		 */
		public boolean hasNext(){
			return current != null;
		}
		/**
		 * Remove an item from the current iterator.
		 */
		public void remove(){
			current.next = current.next.next;
		}
		/**
		 * Get the next value in the bag.
		 */
		public Value next(){
			
			Value item = current.item;
			current = current.next;
			return item;
			
		}
	}

	

	

}
