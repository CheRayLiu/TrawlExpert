package search;

import java.util.Iterator;

public class Bag<Integer> implements Iterable<Integer> {

	private Node first; //pointer to head of linked list
	
	private class Node{
		Integer item;
		Node next;
	}
	
	/**
	 * Adds new node to linked list
	 * @param item the item to be added to the linked list
	 */
	public void add(Integer item){
		Node oldfirst = first;
		first = new Node();
		first.item = item;
		first.next = oldfirst;
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new ListIterator();
	}
	
	private class ListIterator implements Iterator<Integer>{
		
		private Node current = first;
		
		public boolean hasNext(){
			return current != null;
		}
		public void remove(){
			current.next = current.next.next;
		}
		public Integer next(){
			
			Integer item = current.item;
			current = current.next;
			return item;
			
		}
	}

	

	

}
