package test;

import search.Graph;
import static org.junit.Assert.*;
import org.junit.*;

public class TestGraph {

	@Before
	public void setUp() throws Exception {
		System.out.println("Setting up....");
	}
	
	@Test
	public void testV(){
		Graph g = new Graph(8);
		assertEquals(g.V(), 8);
		assertNotEquals(g.V(), 7);
		assertNotEquals(g.V(), 9);
	}
	
	@Test
	public void testAddEdgeAndE(){
		Graph g = new Graph(8);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		assertNotEquals(g.E(), 3);
		assertEquals(g.E(), 4);
		g.addEdge(7, 2);
		assertNotEquals(g.E(), 4);
		assertEquals(g.E(), 5);
	}
	
	@Test
	public void adj(){
		Graph g = new Graph(8);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
	}
}
