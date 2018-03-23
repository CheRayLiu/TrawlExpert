package search;

import java.util.Iterator;

public class Fish<Integer> implements Iterable<Integer> {

	private Node first;
	
	private class Node{
		Integer item;
		Node next;
	}
	
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
		
		public Integer next(){
			
			Integer item = current.item;
			current = current.next;
			return item;
		}
	}

	

	

}
