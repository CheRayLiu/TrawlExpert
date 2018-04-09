package graph;

import java.util.ArrayList;

import search.Graph;

public class CC {

	private boolean[] marked;
	private int[] id;
	private int count;
	
	public static void main(String[] args) {
		Graph g = new Graph(7);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		g.addEdge(5, 6);
		CC component = new CC(g);
		System.out.println(component.connected(2, 0)); //true
		System.out.println(component.connected(0,1)); //true
		System.out.println(component.connected(1, 5)); //false
		//System.out.println(component.connected(1,6)); //throws exception (supposed to)
		System.out.println(component.id(0));
		System.out.println(component.id(5));
		System.out.println(component.id(6));
	}
	/**
	 * Constructor for Connected Component 
	 * @param G Graph object consisting of a completed graph
	 */
	public CC(Graph G){
		marked = new boolean[G.V()];
		id = new int[G.V()];
		for(int s = 0; s < G.V(); s++){
			if(!marked[s]){
				dfs(G,s);
				count++;
			}
		}
	}
	/**
	 * Method to recursively do depth-first search
	 * @param G Graph object consisting of a completed graph
	 * @param v Value of node to be searched
	 */
	private void dfs(Graph G, int v){
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)){
			if(!marked[w])
				dfs(G, w);
		}
	}
	/**
	 * Boolean method to checked if two components are connected
	 * @param v First value to be checked
	 * @param w Second value to be checked
	 * @return True if connected (same component id), else False
	 */
	public boolean connected(int v, int w){
		return id[v] == id[w];
	}
	
	/**
	 * accesses the value of component id
	 * @param v indexed value
	 * @return value at index v
	 */
	public int id(int v){
		return id[v];
	}
	
	/**
	 * @return number of connected clusters
	 */
	public int count(){
		return count;
	}
}
