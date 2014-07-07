import java.lang.Math;

/**
 * Point is just a class with x and y coordinates that are doubles. It has a
 * distanceTo(Point other) convenience method and overrides the toString()
 * method in java.lang.Object.
 * 
 * @author Kai-Zhan Lee
 * 
 */
public class Point {

	private double x;

	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double distanceTo(Point other) {
		return Math.sqrt(
					Math.pow((x - other.getX()), 2) +
					Math.pow((y - other.getY()), 2)
				);
	}

	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
