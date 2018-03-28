package sandbox;

import java.io.Serializable;

public class Point implements Comparable<Point>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5361956730616676054L;
	private final double x;
	private final double y;
	
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
	
	public String toString() {
		return String.format("(%d,%d)", x, y);
	}

	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
