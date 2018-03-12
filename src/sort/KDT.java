package sort;

import sandbox.Point;
import java.util.ArrayList;

public class KDT<KeyVal extends Comparable<KeyVal>> {
	Node root;
	ArrayList<GeneralCompare<KeyVal>> axes;
	
	public static void main(String[] args) {
		GeneralCompare<Point> compX = (p1, p2) -> ((Point) p1).getX() - ((Point) p2).getX();
		GeneralCompare<Point> compY = (p1, p2) -> ((Point) p1).getY() - ((Point) p2).getY();
		
		//(2,3), (4,7), (5,4), (7,2), (8,1), (9,6)
		//(8,1), (7,2), (2,3), (5,4), (9,6), (4,7)
		Point p1 = new Point(2,3);
		Point p2 = new Point(5,4);
		Point p3 = new Point(9,6);
		Point p4 = new Point(4,7);
		Point p5 = new Point(8,1);
		Point p6 = new Point(7,2);
		
		ArrayList<GeneralCompare<Point>> axes = new ArrayList<GeneralCompare<Point>>();
		axes.add(compX);
		axes.add(compY);
		
		Point[] pts = {p1, p2, p3, p4, p5, p6};
		
		KDT<Point> kdt = new KDT<Point>(axes, pts);
		System.out.println(kdt.size());
		System.out.println(kdt.height());
		
		//GeneralRange<Point> xRange = p -> 4 <= p.getX() && p.getX() <= 6;
		//GeneralRange<Point> yRange = p -> 3 <= p.getY() && p.getY() <= 5;
		
		GeneralRange<Point> xRange = p -> p.getX() < 2 ? -1 : (p.getX() > 7 ? 1 : 0);
		GeneralRange<Point> yRange = p -> 0;//p.getY() < 3 ? -1 : (p.getY() > 6 ? 1 : 0);
		
		
		ArrayList<GeneralRange<Point>> ranges = new ArrayList<GeneralRange<Point>>();
		ranges.add(xRange);
		ranges.add(yRange);
		
		Iterable<Point> results = kdt.rangeSearch(ranges);
		
		System.out.println("Results");
		for (Point p : results) {
			System.out.println(p);
		}

		System.out.println(kdt.toString());
	}
	
	private class Node{
		private KeyVal keyval;
		private Node left, right;
		private int n;
		
		public Node(KeyVal keyval, int n) {
			this.keyval = keyval;
			this.n = n;
		}
	}
	
	public KDT(ArrayList<GeneralCompare<KeyVal>> axes, Comparable<KeyVal>[] keyvals) {
		this.axes = axes;
		root = buildTree(keyvals, 0, keyvals.length - 1, 0);
	}
	
	private Node buildTree(Comparable<KeyVal>[] keyvals, int lo, int hi, int depth) {
		if (lo > hi) return null;
		int axis = depth % getK();
		
		int mid = (lo + hi) / 2;
		MergeSort.sort(keyvals, lo, hi, axes.get(axis));
		KeyVal median = (KeyVal) keyvals[mid];
		
		//TODO: fix size
		Node newNode = new Node(median, 0);
		newNode.left = buildTree(keyvals, lo, mid - 1, depth + 1);
		newNode.right = buildTree(keyvals, mid + 1, hi, depth + 1);
		
		newNode.n = size(newNode.left) + size(newNode.right) + 1;
		return newNode;
	}
	
	public Iterable<KeyVal> rangeSearch(ArrayList<GeneralRange<KeyVal>> range){
		ArrayList<KeyVal> result = new ArrayList<KeyVal>();
		rangeSearch(root, range, result, 0);
		return result;
	}
	
	private void rangeSearch(Node x, ArrayList<GeneralRange<KeyVal>> range, ArrayList<KeyVal> result, int depth) {
		if (x == null) return;
		int axis = depth % getK();
		GeneralRange<KeyVal> rg = range.get(axis);
		
		System.out.println("Try: " + x.keyval);
		
		int bounds = rg.isInBounds((KeyVal) x.keyval);
		if (bounds == 0) {
			System.out.println(pointInside(x.keyval, range));
			if (pointInside(x.keyval, range)) {
				result.add(x.keyval);
			}
			rangeSearch(x.left, range, result, depth + 1);
			rangeSearch(x.right, range, result, depth + 1);
		} else if (bounds > 0) {
			rangeSearch(x.left, range, result, depth + 1);
		} else if (bounds < 0)
			rangeSearch(x.right, range, result, depth + 1);
		
		return;
	}
	
	private boolean pointInside(KeyVal pt, ArrayList<GeneralRange<KeyVal>> range) {
		for (int i = 0; i < axes.size(); i++)
			if (range.get(i).isInBounds(pt) != 0) return false;
		return true;
	}
	
	public int size() {
		return size(root);
	}
	
	public int height() {
		return height(root);
	}
	
	private int height(Node x) {
		if (x == null) return 0;
		return 1 + Math.max(height(x.left), height(x.right));
	}
	
	private int size(Node x) {
		if (x == null) return 0;
		else return x.n;
	}
	
	public int getK() {
		return axes.size();
	}
	
	public String toString() {
		return toString(root, "");
	}
	
	private String toString(Node x, String depth) {
		if (x == null) return depth + "null\n";
		String result = "";
		result += depth + x.keyval.toString() + "\n";
		result += toString(x.left, depth + "    ");
		result += toString(x.right, depth + "    ");
		return result;
	}
}
