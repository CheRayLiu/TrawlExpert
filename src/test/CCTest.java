package test;

import static org.junit.Assert.*;
import org.junit.*;

import graph.CC;
import graph.Graph;

/**
 * Tests for connected components.
 * @author TrawlStars, Inc.
 *
 */
public class CCTest {
	
	@Before
	public void setUp() throws Exception {
		System.out.println("Setting up....");
	}
	
	@Test
	public void testId(){//Test to check if connected components have the same component ID
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
	public void TestConnected(){//test to check if connected(x,y) connects correctly
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
	public void testCluster(){//test if, after all the addEdge calls, the nodes are clustered correctly
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
	
	@Test
	public void testConnected2() {
		Graph g = new Graph(7);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		g.addEdge(5, 6);
		CC component = new CC(g);
		
		assert(component.connected(2, 0)); //true
		assert(component.connected(0,1)); //true
		assert(!component.connected(1, 5)); //false
	}
	
	@Test
	public void testComponentId2() {
		Graph g = new Graph(7);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		g.addEdge(5, 6);
		CC component = new CC(g);
		
		assert(component.id(0) == 0);
		assert(component.id(5) == 1);
		assert(component.id(6) == 1);
	}

}
