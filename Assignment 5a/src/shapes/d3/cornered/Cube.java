
package shapes.d3.cornered;

import shapes.d3.ThreeD;
import shapes.d3.Point;

public class Cube extends ThreeD {
  public final double side;
  public final Point cornerA;
  public final Point cornerB;

  public Cube(double side, Point cornerA, Point cornerB) {
    this.side = Math.abs(side);
    this.cornerA = new Point(cornerA, false);
    this.cornerB = new Point(cornerB, false);
  }

  public double getVolume() {
    return side * side * side;
  }

  public double getSurfaceArea() {
    return side * side * 6;
  }
}