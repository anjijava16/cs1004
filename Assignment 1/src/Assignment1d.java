/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 1d
 */

/* Instructions
 * Fill out the main below to do the following:
 * For each of the basic types:
 *   boolean
 *   byte
 *   short
 *   int
 *   long
 *   float
 *   double
 *   char
 * read in a value using a Scanner and store it in
 * the variable of the given type. Then output it
 * with System.out.println without any extra text.
 * (read in the order above and write them out in
 * the same order)
 */

import java.util.Scanner;

public class Assignment1d {
	public static void main(String[] args) {
		//Variables: one Scanner and one of each of the eight types
		Scanner scanner = new Scanner(System.in);
		boolean bool;
		byte b;
		short s;
		int i = (int) java.lang.Math.pow(2, 24) - 1;
		System.out.println(i);
		long l;
		float f;
		double d;
		char c;
		//Input and prompts (boolean, byte, short, int, long, float, double, char)
		System.out.println("Please enter a boolean.");
		bool = scanner.nextBoolean();
		System.out.println("Please enter a byte.");
		b = scanner.nextByte();
		System.out.println("Please enter a short.");
		s = scanner.nextShort();
		System.out.println("Please enter an int.");
		i = scanner.nextInt();
		System.out.println("Please enter a long.");
		l = scanner.nextLong();
		System.out.println("Please enter a float.");
		f = scanner.nextFloat();
		System.out.println("Please enter a double.");
		d = scanner.nextDouble();
		System.out.println("Please enter a char.");
		c = scanner.next().charAt(0);
		//Output: write the inputs out in the same order
		System.out.println(bool);
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
		System.out.println(f);
		System.out.println(d);
		System.out.println(c);
	}
}