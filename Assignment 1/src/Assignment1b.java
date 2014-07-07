/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 1b
 */

/* Instructions
 * Fill out the main below to do the following:
 * Read in five lines of text from the user.
 * Output the lines in reverse order. (i.e. if
 * you read in lines
 *    line1
 *    line2
 *    line3
 *    line4
 *    line5
 * then output
 *    line5
 *    line4
 *    line3
 *    line2
 *    line1
 */

import java.util.Scanner;

public class Assignment1b {
	
	public static void main(String[] args) {
		//Variables: one Scanner and five Strings, for each of the five lines.
		Scanner scanner = new Scanner(System.in);
		String line0, line1, line2, line3, line4;
		//User prompt
		System.out.println("Please enter five lines of text.");
		//User input
		line0 = scanner.next();
		line1 = scanner.next();
		line2 = scanner.next();
		line3 = scanner.next();
		line4 = scanner.next();
		//Print out the reverse (with regard to lines) of the input
		System.out.println(line4);
		System.out.println(line3);
		System.out.println(line2);
		System.out.println(line1);
		System.out.println(line0);
		
	}
}
