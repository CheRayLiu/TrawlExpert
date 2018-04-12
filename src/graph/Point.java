package graph;

import java.io.Serializable;

/**
 * An abstract data type representing a point on a plane.
 * @author macoutreachadmin
 *
 */
public class Point implements Comparable<Point>, Serializable {
	/**
	 * The serialization version identifier.
	 */
	private static final long serialVersionUID = 5361956730616676054L;
	/**
	 * The x-value of the point.
	 */
	private final double x;
	/**
	 * The y-value of the point.
	 */
	private final double y;
	
	/**
	 * Construct a new Point.
	 * @param x The x-value of the point.
	 * @param y the y-value of the point.
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return The x-value of the current point.
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * @return The x-value of the current point.
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Convert the current point to a string.
	 * @return A string representation of the point in the format (x,y).
	 */
	public String toString() {
		return String.format("(%f,%f)", x, y);
	}

	/**
	 * A compare function for the point. Simply to satisfy the Comparable requirement: always returns 0.
	 */
	@Override
	public int compareTo(Point o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
