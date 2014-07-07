/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 2c
 */

/* Instructions
 * make an action such that:
 *   it is named "action2c"
 *   it accepts an int parameter
 *   1) if the parameter is 0, output "No Zeroes"
 *   2) otherwise, if the parameter is negative, multiply it by -1 to
 *      make it positive; if it is positive, leave it alone.
 *      Now, do the following:
 *      a) count how many items would be in the collatz sequence for
 *         the given number (i.e. how many lines would Assignment2b
 *         print if we used that number)
 *      b) make an array of Strings the size of the count from part a
 *      c) now fill the array with the string versions of the collatz
 *         sequence for the given parameter; note to make an int into a
 *         string you can do ("" + someInt) -- you might not need the (
 *         and ), but better safe than sorry.
 *      d) Output the items in the array from the first to the last
 *
 * Make a main that,
 *   1) If there are no args (how do you check that?) it will write
 *      "Goodbye!" and do nothing else
 *   2) Otherwise if there are args, turn the first one into an int
 *      and use it as the parameter to action2c
 *
 * example:
 *   javac Assignment2c.java
 *   java Assignment2c 10
 *   10
 *   5
 *   16
 *   8
 *   4
 *   2
 *   1
 */

public class Assignment2c {
	public static void action2c(int num){
		if(num == 0){
			System.out.println("No Zeroes");
			return;
		} else if (num < 0){
			num = -1 * num;
		}
		int collatzNumber = num;
		int collatzLength = 0;
		collatzLength++;
		while(collatzNumber != 1){
			if(collatzNumber % 2 == 1){
				collatzNumber = 3 * collatzNumber + 1;
			} else if (collatzNumber % 2 == 0){ // just to be clear
				collatzNumber = collatzNumber / 2;
			}
			collatzLength++;
		}
		String[] collatzSequence = new String[collatzLength];
		collatzNumber = num;
		int index = 0;
		collatzSequence[0] = String.valueOf(collatzNumber);
		index++;
		while(collatzNumber != 1){
			if(collatzNumber % 2 == 1){
				collatzNumber = 3 * collatzNumber + 1;
				collatzSequence[index] = String.valueOf(collatzNumber);
			} else if (collatzNumber % 2 == 0){ // just to be clear
				collatzNumber = collatzNumber / 2;
				collatzSequence[index] = String.valueOf(collatzNumber);
			}
			collatzLength++;
			index++;
		}
		for(int i = 0; i < collatzSequence.length; i++){
			System.out.println(collatzSequence[i]);
		}
	}
	
	public static void main(String[] args){
		if(args.length == 0){
			System.out.println("Goodbye!");
			System.exit(1);
		}
		action2c(Integer.parseInt(args[0]));
	}
}
