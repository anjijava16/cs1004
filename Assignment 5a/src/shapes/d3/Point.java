
package shapes.d3;

public class Point extends shapes.d2.Point {
  private double z;

  public Point(double x, double y, double z) {
    this(x, y, z, false);
  }

  public Point(Point p, boolean movable) {
    this(p.getX(), p.getY(), p.getZ(), movable);
  }

  public Point(shapes.d2.Point p, double z, boolean movable) {
    this(p.getX(), p.getY(), z, movable);
  }

  public Point(double x, double y, double z, boolean movable) {
    super(x, y, movable);
    this.z = z;
  }

  public void setZ(double z) {
    if (movable)
      this.z = z;
  }

  public double getZ() {
    return z;
  }

  public String toString() {
    return "(" + x + ", " + y + ", " + z + ")";
  }
}
