package search;

public class DepthFirstSearch {

		private boolean[] marked;
		private int count;
		
		public static void main(String[] args) {
		
			Graph g = new Graph(5);
			g.addEdge(4, 3);
			g.addEdge(2, 3);
			g.addEdge(1, 2);
			g.addEdge(0, 2);
			DepthFirstSearch s = new DepthFirstSearch(g, g.V());
		}
		
		public DepthFirstSearch(Graph G, int s){
			marked = new boolean[G.V()];
			dfs(G, s);
			
		}
		
		public void dfs(Graph G, int v){
			marked[v] = true;
			count++;
			for(int w : G.adj(v))
				if(!marked[w])
					dfs(G, w);
		}
		
		public boolean marked(int w){
			return marked[w];
		}
		
		public int count(){
			return count;
		}
}
