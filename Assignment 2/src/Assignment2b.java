/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 2b
 */

/* Instructions
 * Make an action:
 *   name it collatzSequence
 *   it should accept one parameter, an int
 *   if the parameter is negative, make it positive
 *   if the parameter is 0, do nothing
 *   output the values for the collatz sequence starting from that number:
 *      if the current number is odd, triple it and add one
 *      if the current number is even, halve it
 *      print the number out before you change it
 *      stop when you get to one
 * You should make a main such that:
 *   1) if there are no arguments given, you should warn the user that
 *      an argument is required
 *      example:
 *      java Assignment2b
 *      Argument required, please give an argument.
 *   2) if there is an argument given, assume it is an int and change it
 *      into an int (see code examples where this happened) and use the
 *      action from above with that value
 *
 * Example:
 *    javac Assignment2b.java
 *    java Assignment2b 20
 *    20
 *    10
 *    5
 *    16
 *    8
 *    4
 *    2
 *    1
 */

public class Assignment2b {
	
	public static void collatzSequence(int num){
		if(num < 0){
			num = -1 * num;
		} else if(num == 0){
			return;
		}
		System.out.println(num);
		while(num != 1){
			if(num % 2 == 1){
				num = 3 * num + 1;
			} else if (num % 2 == 0){ // just to be clear
				num = num / 2;
			}
			System.out.println(num);
		}
	}
	
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("No parameters entered. Please enter a parameter");
			System.exit(1);
		}
		collatzSequence(Integer.parseInt(args[0]));
		System.exit(0);
	}
	
}