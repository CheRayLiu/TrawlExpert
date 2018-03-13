package search;

import java.io.*;

public class GraphBuild {
	
	private final int V;
	private int E;
	private Fish<Integer>[] adj;
	
	public static void main(String[] args) {
		GraphBuild gb = new GraphBuild(5);
		System.out.println(gb.V());
		System.out.println(gb.E());
	}
	
	public GraphBuild(int V){
		this.V = V;
		this.E = 0;
		adj = (Fish<Integer>[]) new Fish[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Fish<Integer>(); 
	}
	
	/*public GraphBuild(In in){
		this(in.readInt());
		int E = in.readInt();
		for(int i = 0; i < E; i++){
			int v = in.readInt();
			int w = in.readInt();
			addEdge(v, w);
		}
	}*/
	
	public int V(){
		return V;
	}
	
	public int E(){
		return E;
	}
	
	public void addEdge(int v, int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	
	public Iterable<Integer> adj(int V){
		return adj[V];
	}
}
