/*
 * Based on code from Sedgewick & Wayne, Algorithms 4th Edition (2011).
 */

package graph;

/**
 * An ADT for representing an undirected, unweighted graph.
 * @author TrawlStars, Inc.
 *
 */
public class Graph {
	
	/**
	 * Number of nodes in graph.
	 */
	private final int V;
	/**
	 * Number of edges in graph
	 */
	private int E;
	/**
	 *  Adjacency list for all nodes.
	 */
	private Bag<Integer>[] adj;
	
	/**
	 * Constructor used to build a graph of a specified size
	 * @param V The size of graph, or, the number of nodes in graph
	 */
	public Graph(int V){
		this.V = V;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Bag<Integer>(); 
	}
	/**
	 * Accesses the number of vertices
	 * @return The number of vertices
	 */
	public int V(){
		return V;
	}
	/**
	 * Accesses the number of edges
	 * @return The number of edges
	 */
	public int E(){
		return E;
	}
	/**
	 * Method to connect two vertices with an edge
	 * @param v First vertex to be connected
	 * @param w Second vertex to be connected
	 */
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	/**
	 * Method to access value in adjacency list
	 * @param V Vertex to be accessed
	 * @return Adjacency list at V
	 */
	public Iterable<Integer> adj(int V){
		return adj[V];
	}
}
