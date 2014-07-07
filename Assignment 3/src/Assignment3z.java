/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 3z
 */

/* Instructions
 * Write functions that, given an array of ints:
 *   1) function: half1
 *       return the sum of the first half of the array -- if
 *       there are an odd number of items, include the
 *       middle item in the sum
 *   2) function: half2
 *       return the sum of the second half of the array
 *       -- if there are an odd number of items, include
 *       the middle item in the sum 
 *   3) function: squares
 *       return the sum of all the squares of the elements
 *   4) function: zeroes
 *       without multiplying the numbers together, returns
 *       how many zeroes their product would have at the
 *       end. So if we had say an array that multiplied to
 *       24300, then it would return 2.
 */

/*
 * Apparently, I got the instructions wrong last time; I created a static array
 * of ints rather than making the array a parameter for each method.
 */
public class Assignment3z {

	/**
	 * @param array
	 *            A given array of ints.
	 * @return The sum of the first half of the array, including the middle
	 *         number if there is one.
	 */
	public static int half1(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length / 2; i++)
			sum += array[i];
		if(array.length % 2 == 1)
			sum += array[array.length/2];
		return sum;
	}

	/**
	 * @param array
	 *            A given array of ints.
	 * @return The sum of the second half of the array, including the middle
	 *         number if there is one.
	 */
	public static int half2(int[] array) {
		int sum = 0;
		for (int i = array.length / 2; i < array.length; i++)
			sum += array[i];
		return sum;
	}

	/**
	 * @param array
	 *            A given array of ints.
	 * @return The sum of all the squares of the ints in the array.
	 */
	public static int squares(int[] array) {
		int sumOfSquares = 0;
		for (int i = 0; i < array.length; i++)
			sumOfSquares += array[i] * array[i];
		return sumOfSquares;
	}

	/**
	 * @param array
	 *            A given array of ints.
	 * @return Without multiplying the numbers, how many leading zeroes the
	 *         product of all of the ints in the array would have.
	 */
	public static int zeroes(int[] array) {
		int twos = 0;
		int fives = 0;
		// If a number has n leading zeroes, then it can be represented as
		// x * 10^n, where x and n are integers. It can also be represented as
		// x * 2^n * 5^n. Therefore, the number of leading zeroes will be
		// either the number of factors of 2 or factors of 5, whichever is less,
		// because one needs both 2 and 5 to multiply together to create 10.
		for (int i = 0; i < array.length; i++) {
			// Adds the number of fives in this element to the variable fives.
			while (array[i] % 5 == 0) {
				array[i] /= 5;
				fives++;
			}
			// Adds the number of twos in this element to the variable twos.
			while (array[i] % 2 == 0) {
				array[i] /= 2;
				twos++;
			}
		}
		return (twos > fives) ? fives : twos;
	}
}
