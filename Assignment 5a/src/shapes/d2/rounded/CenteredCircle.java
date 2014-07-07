
package shapes.d2.rounded;

import shapes.d2.Point;
import shapes.Centered;

public class CenteredCircle extends Circle implements Centered {
  private Point center;

  public CenteredCircle(double radius, Point center) {
    super(radius);
    this.center = new Point(center, false);
  }

  /* center is a shape.d2.Point object...
   * but shape.d2.Point implements shapes.Point
   * so this still `makes sense'
   */
  public shapes.d2.Point getCenter() {
    return center;
  }
}