package search;

public class GraphBuild {
	
	private final int V;
	private int E;
	private Fish<Integer>[] adj;
	
	public GraphBuild(int V){
		this.V = V;
		this.E = 0;
		adj = (Fish<Integer>[]) new Fish[V];
		for(int v = 0; v < V; v++)
			adj[v] = new Fish<Integer>(); 
	}
	
	/*public GraphBuild(In in){
		
	}*/
}
