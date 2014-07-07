
import shapes.Shape;
import shapes.Centered;

import shapes.d2.rounded.Circle;
import shapes.d2.rounded.CenteredCircle;
import shapes.d2.cornered.Square;

import shapes.d3.rounded.Sphere;
import shapes.d3.rounded.CenteredSphere;
import shapes.d3.cornered.Cube;


public class Geometry {
  public static void main(String[] args) {
    shapes.d2.Point o2 = new shapes.d2.Point(0.0, 0.0);
    shapes.d3.Point o3 = new shapes.d3.Point(0.0, 0.0, 0.0);
    shapes.d3.Point u3 = new shapes.d3.Point(1.0, 1.0, 1.0);

    display("Circle", new Circle(1.0));
    display("CenteredCircle", new CenteredCircle(1.0, o2));
    display("Square", new Square(1.0, o3));
    display("Sphere", new Sphere(1.0));
    display("CenteredSphere", new CenteredSphere(1.0, o3));
    display("Cube", new Cube(1.0, o3, u3));
  }

  private static void display(String name, Shape s) {
    System.out.println(
      name + "\n" +
      "\tVolume: " + s.getVolume() + "\n" +
      "\tSurface Area: " + s.getSurfaceArea()
    );
    if (s instanceof Centered)
      System.out.println("\tCentered at: " + ( (Centered)s ).getCenter() + "");
    System.out.println();
  }
}

