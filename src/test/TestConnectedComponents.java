package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graph.CC;
import graph.Graph;

/**
 * Tests for connected components.
 * @author TrawlStars, Inc.
 *
 */
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


}
