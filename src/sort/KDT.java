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
		
		KDT<Point> kdt = new KDT(axes, pts);
		System.out.println(kdt.size());
		System.out.println(kdt.height());
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
		
		System.out.println(median);
		
		//TODO: fix size
		Node newNode = new Node(median, 0);
		newNode.left = buildTree(keyvals, lo, mid - 1, depth + 1);
		newNode.right = buildTree(keyvals, mid + 1, hi, depth + 1);
		
		newNode.n = size(newNode.left) + size(newNode.right) + 1;
		return newNode;
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
}
