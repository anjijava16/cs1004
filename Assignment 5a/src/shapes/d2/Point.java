
package shapes.d2;

public class Point implements shapes.Point {
  protected double x;
  protected double y;
  protected final boolean movable;

  public Point(double x, double y) {
    this(x, y, false);
  }

  public Point(Point p, boolean movable) {
    this(p.getX(), p.getY(), movable);
  }

  public Point(double x, double y, boolean movable) {
    this.x = x;
    this.y = y;
    this.movable = movable;
  }

  public void setX(double x) {
    if (movable)
      this.x = x;
  }

  public void setY(double y) {
    if (movable)
      this.y = y;
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public int getDimension() {
    return 2;
  }

  public String toString() {
    return "(" + x + ", " + y + ")";
  }
}