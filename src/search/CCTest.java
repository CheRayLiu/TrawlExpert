package search;

import static org.junit.Assert.*;
import org.junit.*;

public class CCTest {
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Setting up....");
	}
	
	@Test
	public void testId(){
		int v = 11;
		Graph g = new Graph(v);
		g.addEdge(1,3);
		g.addEdge(9,8);
		g.addEdge(3, 8);
		g.addEdge(3, 9);
		g.addEdge(7, 4);
		g.addEdge(2, 4);
		g.addEdge(4, 6);
		g.addEdge(0, 5);
		CC cc = new CC(g);
		assertEquals(cc.id(4), cc.id(6));
		assertNotEquals(cc.id(3), cc.id(4));
	}
	
	@Test
	public void TestConnected(){
		int v = 11;
		Graph g = new Graph(v);
		g.addEdge(1,3);
		g.addEdge(9,8);
		g.addEdge(3, 8);
		g.addEdge(3, 9);
		g.addEdge(7, 4);
		g.addEdge(2, 4);
		g.addEdge(4, 6);
		g.addEdge(0, 5);
		CC cc = new CC(g);
		assertEquals(cc.connected(0, 1), false);
		assertEquals(cc.connected(8, 9), true);
		assertNotEquals(cc.connected(0, 10), true);
		assertEquals(cc.connected(2, 7), true);
	}
	
	@Test
	public void testCount(){
		int v = 11;
		Graph g = new Graph(v);
		g.addEdge(1,3);
		g.addEdge(9,8);
		g.addEdge(3, 8);
		g.addEdge(3, 9);
		g.addEdge(7, 4);
		g.addEdge(2, 4);
		g.addEdge(4, 6);
		g.addEdge(0, 5);
		CC cc = new CC(g);
		assertEquals(cc.count(), 4); //4 sets of clusters
		g.addEdge(0, 9);
		CC cc1 = new CC(g);
		assertEquals(cc1.count(), 3);//3 sets of clusters
	}
}
