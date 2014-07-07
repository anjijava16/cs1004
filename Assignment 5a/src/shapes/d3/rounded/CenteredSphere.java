
package shapes.d3.rounded;

import shapes.d3.Point;
import shapes.Centered;

public class CenteredSphere extends Sphere implements Centered {
  private Point center;

  public CenteredSphere(double radius, Point center) {
    super(radius);
    this.center = new Point(center, false);
  }

  public shapes.d2.Point getCenter() {
    return center;
  }
}
