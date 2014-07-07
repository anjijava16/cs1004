/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 3d
 */

/* Instructions
 * Write functions that, given an array of ints:
 *   1) function: odds
 *      add up every other item in the array, starting with the first
 *      note that if there are no such items, the sum is 0
 *      return the sum
 *   2) function: evens
 *      add up every other item in the array, starting with the second
 *      anote that if there are no such items, the sum is 0
 *      return the sum
 *   3) function: product
 *      multiply all the numbers together
 *      return the result
 *   4) function: parity
 *      calculate whether the sum of the numbers is even or odd
 *      you ARE NOT ALLOWED to add the numbers together first, either
 *      you CANNOT use any of the other functions you've written for
 *      this or the other three problems in this assignment.
 *      note that if the array is of length 0, i.e. there are no numbers
 *      to add, then the sum is 0 which is even
 *      return the result
 */

public class Assignment3d {
	public static int[] ints;
	
	public static int odds(){
		int oddSum = 0;
		for(int i = 0; i < ints.length; i += 2)
			oddSum += ints[i];
		return oddSum;
	}
	
	public static int evens(){
		int evenSum = 0;
		for(int i = 1; i < ints.length; i += 2)
			evenSum += ints[i];
		return evenSum;
	}
	
	public static int product(){
		int product = 1;
		for(int i = 0; i < ints.length; i++)
			product *= ints[i];
		return product;
	}
	
	public static boolean parity(){
		boolean isOdd = false;
		for(int i = 0; i < ints.length; i++)
			if(ints[i] % 2 == 1) //if the integer is odd, toggle isOdd
				isOdd = !isOdd;
		return isOdd; //if odd, return true, and otherwise, return false (see piazza)
	}
}
