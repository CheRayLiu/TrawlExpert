package test;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import graph.Point;
import search.kdt.KDT;
import sort.GeneralCompare;
import sort.GeneralRange;

public class TestKDTree {
	
	private static KDT<Point> kdt;
	
	@BeforeClass
	public static void setUp() {
		//set up comparison functions
		GeneralCompare<Point> compX = (p1, p2) -> Double.compare(p1.getX(), p2.getX());
		GeneralCompare<Point> compY = (p1, p2) -> Double.compare(p1.getY(), p2.getY());
		
		//(2,3), (4,7), (5,4), (7,2), (8,1), (9,6), (9,6)
		//(8,1), (7,2), (2,3), (5,4), (9,6), (9,6), (4,7)
		//create points
		Point p1 = new Point(2,3);
		Point p2 = new Point(5,4);
		Point p3 = new Point(9,6);
		Point p4 = new Point(4,7);
		Point p5 = new Point(8,1);
		Point p6 = new Point(7,2);
		
		ArrayList<GeneralCompare<Point>> axes = new ArrayList<GeneralCompare<Point>>();
		axes.add(compX);
		axes.add(compY);
		
		Point[] pts = {p1, p2, p3, p3, p4, p5, p6};
		
		kdt = new KDT<Point>(axes, pts);
	}
	
	@Test
	public void testSize() {
		//should be 7 nodes in tree
		assert(kdt.size() == 7);
	}
	
	@Test
	public void testHeight() {
		//height should be lg(7) == 2.8 ~= 3 for the tree to be balanced
		assert(kdt.height() == 3);
	}
	
	@Test
	public void testRangeSearch() {
		//create range functions for ranges x in [2,7] and y in [3,6]
		GeneralRange<Point> xRange = p -> p.getX() <2 ? -1 : (p.getX() > 7 ? 1 : 0); //between 2 and 7
		GeneralRange<Point> yRange = p -> p.getY() < 3 ? -1 : (p.getY() > 6 ? 1 : 0); //between 3 and 6 
		
		//put ranges into an ArrayList of ranges for searching
		ArrayList<GeneralRange<Point>> ranges = new ArrayList<GeneralRange<Point>>();
		ranges.add(xRange);ranges.add(yRange);
		
		ArrayList<Point> results = (ArrayList<Point>) kdt.rangeSearch(ranges);
		//test size of result
		assert(results.size() == 2);
		//test results are within correct range
		for(Point p: results) {
			assert(p.getX() >= 2);
			assert(p.getX() <= 7);
			assert(p.getY() >= 3);
			assert(p.getY() <= 6);
		}
	}

	@Test
	public void testSearchFullRange() {
		//create range functions that always return 0
		GeneralRange<Point> xRange = p -> 0; //any x
		GeneralRange<Point> yRange = p -> 0; //any y
		
		ArrayList<GeneralRange<Point>> ranges = new ArrayList<GeneralRange<Point>>();
		ranges.add(xRange);ranges.add(yRange);
		
		ArrayList<Point> results = (ArrayList<Point>) kdt.rangeSearch(ranges);
		assert(results.size() == 7); //all points are found
	}
	
	@Test
	public void testSearchSpecificValue() {
		//create range functions that always return 0
		GeneralRange<Point> xRange = p -> Double.compare(p.getX(), 2); //only x = 2
		GeneralRange<Point> yRange = p -> Double.compare(p.getY(), 3); //only y = 3
		
		ArrayList<GeneralRange<Point>> ranges = new ArrayList<GeneralRange<Point>>();
		ranges.add(xRange);ranges.add(yRange);
		
		ArrayList<Point> results = (ArrayList<Point>) kdt.rangeSearch(ranges);
		assert(results.size() == 1); //only one point is found
		//point found should be the right one (within error)
		assert(results.get(0).getX() - 2.0 <= 1e-3);
		assert(results.get(0).getX() - 3.0 <= 1e-3);
	}
	
	@Test
	public void testSearchSpecificValueMultiplePoints() {
		//create range functions that always return 0
		GeneralRange<Point> xRange = p -> Double.compare(p.getX(), 9); //only x = 2
		GeneralRange<Point> yRange = p -> Double.compare(p.getY(), 6); //only y = 3
		
		ArrayList<GeneralRange<Point>> ranges = new ArrayList<GeneralRange<Point>>();
		ranges.add(xRange);ranges.add(yRange);
		
		ArrayList<Point> results = (ArrayList<Point>) kdt.rangeSearch(ranges);
		assert(results.size() == 2); //two points are found
		//points found should be the right one (within error)
		assert(results.get(0).getX() - 9.0 <= 1e-3);
		assert(results.get(0).getY() - 6.0 <= 1e-3);
		assert(results.get(1).getX() - 9.0 <= 1e-3);
		assert(results.get(1).getY() - 6.0 <= 1e-3);
	}

}
