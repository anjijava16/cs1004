/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 3a
 */

/* Instructions
 * In this assignment you'll create some simple functions.
 *   1) function: divides
 *      Make a function that accepts two ints and then
 *      returns true if the first divides the second and
 *      returns false otherwise.
 *   2) function: abs
 *      Make a function that returns the absolute value of
 *      an int -- i.e. -x if x < 0, and x otherwise
 *   3) function: max
 *      Make a function that returns the max of 2 ints
 *   4) function: maxMagnitude
 *      Make a function that takes two ints and returns
 *      the int with the maximum absolute value
 *   5) function: factor
 *      Make a function that returns the first positive
 *      factor of an int greater than 1. For 0 it should
 *      return 0, for +/-1 it should return 1
 */

public class Assignment3a {
	// the names of these methods are kind of self explanatory, but you can take a
	// look at the readme if you don't think so.
	public static boolean divides(int dividend, int divisor){
		return ((dividend % divisor) == 0);
	}
	
	public static int abs(int num){
		return ((num > 0)? num : -1 * num);
	}
	
	public static int max(int num1, int num2){
		return ((num1 > num2)? num1 : num2);
	}
	
	public static int maxMagnitude(int num1, int num2){
		return ((abs(num1) > abs(num2))? num1 : num2);
	}
	
	public static int factor(int num){
		for(int i = 2; i < num; i++)
			if(num % i == 0)
				return i;
		return num;
	}
}
