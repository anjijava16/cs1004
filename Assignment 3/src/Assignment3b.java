/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 3b
 */

/* Instructions
 * In this assignment you'll be making a simple counter using
 * static variables. The counter should initially be 0 and,
 * there should be the following functionality:
 *   1) function: increment
 *      increase the value of the counter by exactly one.
 *      it should not return any value
 *   2) function: getCount
 *      returns the value of the counter
 */

public class Assignment3b {
	public static int counter = 0;
	
	public static void increment(){
		counter++;
	}
	
	public static int getCount(){
		return counter;
	}
}

