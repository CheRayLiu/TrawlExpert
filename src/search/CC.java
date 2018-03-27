package search;

public class CC {

	private boolean[] marked;
	private int[] id;
	private int count;
	
	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		CC component = new CC(g);
		System.out.println(component.connected(2, 0));
		System.out.println(component.connected(0,1));
		System.out.println(component.connected(1, 5));
		System.out.println(component.connected(1,6)); //throws exception (supposed to)
	}
	
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
	
	private void dfs(Graph G, int v){
		marked[v] = true;
		id[v] = count;
		for(int w : G.adj(v)){
			if(!marked[w])
				dfs(G, w);
		}
	}
	
	public boolean connected(int v, int w){
		return id[v] == id[w];
	}
	
	public int id(int v){
		return id[v];
	}
	
	public int count(){
		return count;
	}
}
