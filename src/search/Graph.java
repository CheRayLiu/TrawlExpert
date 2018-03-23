package search;

public class Graph {
	
	private final int V; // Number of nodes
	private int E; // Number of edges
	private Fish<Integer>[] adj; // Adjacency list for a node
	
	public static void main(String[] args) {
		
		int[] testArray = {6,6,0,3,1,5,2,3,3,5,4,7,5,6};
		Graph gb = new Graph(testArray);
		System.out.println(gb.E());
		System.out.println(gb.V());
		
	}
	
	public Graph(int V){
		this.V = V;
		this.E = 0;
		adj = (Fish<Integer>[]) new Fish[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Fish<Integer>(); 
	}
	
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