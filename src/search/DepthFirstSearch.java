package search;

public class DepthFirstSearch {

		private boolean[] marked;
		private int count;
		
		public DepthFirstSearch(GraphBuild G, int s){
			marked = new boolean[G.V()];
			dfs(G, s);
			
		}
		
		public void dfs(GraphBuild G, int v){
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
