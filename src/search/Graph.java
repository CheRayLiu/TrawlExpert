package search;

import graph.Bag;

public class Graph {
	
	private final int V; // Number of nodes
	private int E; // Number of edges
	private Bag<Integer>[] adj; // Adjacency list for a node
	
	public static void main(String[] args) {
		
		
	}
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
