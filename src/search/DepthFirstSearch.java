package search;

public class DepthFirstSearch {

		private boolean[] marked;
		private int count;
		
		public static void main(String[] args) {
			int[] testArray = {6,6,0,3,1,5,2,3,3,5,4,7,5,6};
			GraphBuild gb = new GraphBuild(testArray);
			//GraphBuild gb1 = new GraphBuild(gb.V());
			DepthFirstSearch s = new DepthFirstSearch(gb, gb.V());
		}
		
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
