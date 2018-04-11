package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graph.CC;
import graph.Graph;

public class TestConnectedComponents {
	Graph g;
	CC component;
	
	@Before
	public void setUp() throws Exception {
		g = new Graph(7);
		g.addEdge(4, 3);
		g.addEdge(2, 3);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		g.addEdge(5, 6);
		component = new CC(g);
	}

	@Test
	public void testConnected() {
		assert(component.connected(2, 0)); //true
		assert(component.connected(0,1)); //true
		assert(!component.connected(1, 5)); //false
	}
	
	@Test
	public void testComponentId() {
		assert(component.id(0) == 0);
		assert(component.id(5) == 1);
		assert(component.id(6) == 1);
	}

}
