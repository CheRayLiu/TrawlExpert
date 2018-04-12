package test;

import search.trawl.*;

import java.util.ArrayList;
import org.junit.BeforeClass;
import org.junit.Test;
import data.FileProcessor;
import data.biotree.BioTree;
import search.trawl.BasicSearchResult;
import graph.Cluster;
import graph.RecordCluster;

/**
 * Test cases for Cluster.java
 * @author TrawlStars, Inc.
 *
 */
public class TestCluster {
	public static BasicSearchResult bsr;
	public static ArrayList<RecordCluster> clus;
	
	@BeforeClass
	public static void setUp() throws Exception {
		BioTree.init();
		FileProcessor.setPath("smalldata.csv");
		FileProcessor.initProcessing();
	}
	
	// Check if a cluster is returned for each object
	@Test
	public void testCluster1() {
		bsr = BasicSearch.range(154210, 1990, 2008);	// Esox lucius
		clus = Cluster.cluster(1.0, bsr);
		assert(clus.size() == bsr.n());
	}
	
	// Check if a cluster contains the correct amount of points
	@Test
	public void testCluster2() {
		bsr = BasicSearch.range(154210, 1990, 2008);	// Esox lucius
		clus = Cluster.cluster(.01, bsr);
		System.out.println(clus.get(0).N());
		assert(clus.get(0).N() == 1);
	}
	
	// Check if a cluster contains the correct amount of points
	@Test
	public void testCluster3() {
		bsr = BasicSearch.range(448306, 1990, 2008);	// Anura
		clus = Cluster.cluster(30.0, bsr);
		System.out.println(clus.get(4).N());
		assert(clus.get(0).N() == 3);
	}
	
	// Check if a cluster contains the correct amount of points
	@Test
	public void testCluster4() {
		bsr = BasicSearch.range(154210, 1990, 2008);	// Esox lucius
		clus = Cluster.cluster(300.0, bsr);
		System.out.println(clus.get(0).N());
		assert(clus.get(0).N() == 4);
	}
	
}
