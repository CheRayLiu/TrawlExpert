package sandbox;

<<<<<<< HEAD
public class Point implements Comparable<Point> {
=======
import java.io.Serializable;

public class Point implements Comparable<Point>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5361956730616676054L;
>>>>>>> refs/remotes/origin/master
	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
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
