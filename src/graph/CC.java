/*
 * Based on implementation from Sedgewick & Wayne, Algorithms 4th Edition (2011).
 */

package graph;

import java.util.ArrayList;

/**
 * Find the connected components in an undirected graph.
 * @author TrawlStars, Inc.
 *
 */
public class CC {

	/**
	 * Whether a node has been visited by the DFS.
	 */
	private boolean[] marked;
	/**
	 * A mapping from node to its component id.
	 */
	private int[] id;
	/**
	 * The number of connected components in the graph.
	 */
	private int count;

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
