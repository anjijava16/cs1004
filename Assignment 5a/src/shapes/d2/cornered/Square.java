
package shapes.d2.cornered;

import shapes.d2.TwoD;
import shapes.d2.Point;

public class Square extends TwoD {
  public final double side;
  public final Point bottomLeft;

  public Square(double side, Point bottomLeft) {
    this.side = Math.abs(side);
    this.bottomLeft = new Point(bottomLeft, false);
  }

  public double getVolume() {
    return side * side;
  }

  public double getSurfaceArea() {
    return 4 * side;
  }
}