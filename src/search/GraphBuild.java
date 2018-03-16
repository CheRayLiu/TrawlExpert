package search;

public class GraphBuild {
	
	private final int V; // Number of nodes
	private int E; // Number of edges
	private Fish<Integer>[] adj; // Adjacency list for a node
	
	public static void main(String[] args) {
		
		int[] testArray = {5,5,0,3,1,5,2,3,3,5,4,7};
		GraphBuild gb = new GraphBuild(testArray);
		System.out.println(gb.E());
	}
	
	public GraphBuild(int V){
		this.V = V;
		this.E = 0;
		adj = (Fish<Integer>[]) new Fish[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Fish<Integer>(); 
	}
	
	public GraphBuild(int[] x){
		this(x[0]);
		E = x[1];
		int i = 2;
		while(i < x.length){
			int v = x[i];
			int w = x[i++];
			addEdge(v,w);
			i++;
			E--;
		}
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
