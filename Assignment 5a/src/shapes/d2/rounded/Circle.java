
package shapes.d2.rounded;

import shapes.d2.TwoD;

public class Circle extends TwoD {
  public final double radius;

  public Circle(double radius) {
    this.radius = Math.abs(radius);
  }

  public double getVolume() {
    return radius * radius * Math.PI;
  }

  public double getSurfaceArea() {
    return 2 * radius * Math.PI;
  }
}