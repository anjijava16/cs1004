/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 1a
 */

/* Instructions
 * Fill out the main below to do the following:
 * Prompt the user to enter a number
 * Get the number from he user
 * Prompt the user to enter another number
 * Get the number from the user
 * Output, on one line each, without any other text:
 *   1) the sum of the two numbers
 *   2) the first minus the second
 *   3) the first times the second
 *   4) the first modulo the second
 */

import java.util.Scanner;

public class Assignment1a {
	
	public static void main(String[] args) {
		//Create the scanner (to read in the two integers)
		Scanner scanner = new Scanner(System.in);
		//Prompt the user to enter a number
		System.out.println("Please enter a number.");
		//Get the number from the user
		int num1 = scanner.nextInt();
		//Prompt the user to enter another number
		System.out.println("Please enter another number.");
		//Get the number from the user
		int num2 = scanner.nextInt();
		//Output
		//1. the sum of the two numbers
		System.out.println(num1 + num2);
		//2. the first minus the second
		System.out.println(num1 - num2);
		//3. the first times the second
		System.out.println(num1 * num2);
		//4. the first modulo the second
		System.out.println(num1 % num2);
	}
}
