package test;

import search.trawl.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.FileProcessor;
import data.Record;
import data.biotree.BioTree;
import model.TrawlExpert;
import search.RedBlackTree;
import search.trawl.BasicSearchResult;
import graph.Cluster;
import graph.RecordCluster;

/**
 * Test cases for Cluster.java
 * @author TrawlStars, Inc.
 *
 */
public class TestCluster {
	public static TrawlExpert te;
	public static BasicSearchResult bsr;
	public static ArrayList<RecordCluster> clus;
	
	@BeforeClass
	public static void setUp() throws Exception {
		// Assumes serialized data is present
		//te = new TrawlExpert();
		BioTree.init();
		FileProcessor.setPath("smalldata.csv");
		FileProcessor.initProcessing();
		bsr = BasicSearch.range(154210, 1990, 2008);	// Esox lucius
	}
	
	// Check if a cluster is returned for each object
	@Test
	public void testCluster1() {
		clus = Cluster.cluster(1.0, bsr);
		assert(clus.size() == bsr.n());
	}
	
	// Check if a cluster contains the correct amount of points
	@Test
	public void testCluster2() {
		clus = Cluster.cluster(.01, bsr);
		System.out.println(clus.get(0).N());
		assert(clus.get(0).N() == 1);
	}
	
	// Check if a cluster contains the correct amount of points
	@Test
	public void testCluster3() {
		bsr = BasicSearch.range(448306, 1990, 2008);
		clus = Cluster.cluster(30.0, bsr);
		System.out.println(clus.get(0).N());
		assert(clus.get(0).N() == 3);
	}
	
	// Check if a cluster contains the correct amount of points
	@Test
	public void testCluster4() {
		bsr = BasicSearch.range(154210, 1990, 2008);
		System.out.println(bsr.n());
		clus = Cluster.cluster(300.0, bsr);
		System.out.println(clus.get(0).N());
		assert(clus.get(0).N() == 4);
	}
	
	
//	@Test
//	public void tesLatRange() {
//		Cluster.latRange(222.222);
//		System.out.println();
//		assert();
//	}
}
