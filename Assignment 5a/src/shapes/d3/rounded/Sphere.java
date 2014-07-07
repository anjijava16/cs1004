
package shapes.d3.rounded;

import shapes.d3.ThreeD;

public class Sphere extends ThreeD {
  public final double radius;

  public Sphere(double radius) {
    this.radius = Math.abs(radius);
  }

  public double getVolume() {
    return 4.0/3.0 * radius * radius * radius * Math.PI;
  }

  public double getSurfaceArea() {
    return 4 * radius * radius * Math.PI;
  }
}