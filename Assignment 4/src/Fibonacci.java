/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 4a
 */

/* Instructions
 * The class below is named Fibonacci
 * Fill out the class so that it has the following fields:
 *   a, an int
 *   b, an int
 *   sum, an int
 * the constructor should accept two parameters,
 * one is the initial value of a (so inA if you want) and
 * one is the initial value of b (so inB if you want)
 * during initialization, the sum field should be set to a+b
 * Note the fields should be public.
 *
 * Give your class a main that does the following:
 *   1) if there are no arguments, it should say "No Arguments. Goodbye!" and exit
 *   2) otherwise, assume the first argument is a string representng a positive (>0) integer
 *   3) let n represent the number this argument represents
 *   4) make an array of n objects objects of type Fibonacci
 *   5) set the first item in the array to be an Fibonacci object with a = 0, b = 1
 *   6) set every _subsequent_ item (i.e. the second item up to the last) to be
 *        an Fibonacci object with a equal to the b value of the previous item
 *        and with b equal to the sum from the previous item
 *        (you must get these values from the fields of the previous item in the array)
 *   7) loop through the array and print the sum fields form the first item to the last
 */

public class Fibonacci {

	public int a;

	public int b;

	public int sum;

	public Fibonacci(int a, int b){
		this.a = a;
		this.b = b;
		this.sum = a + b;
	}

	public static void main(String[] args){
		if(args.length == 0){
			System.err.println("No arguments. Goodbye!");
			System.exit(1);
		}
		int n = Integer.parseInt(args[0]);
		Fibonacci[] fibonacci = new Fibonacci[n];
		fibonacci[0] = new Fibonacci(0, 1);
		for(int i = 1; i < fibonacci.length; i++)
			fibonacci[i] = new Fibonacci(fibonacci[i - 1].b, fibonacci[i - 1].sum);
		//Note that I am just following the instructions, and this program does not
		// print out the fibonacci series from 1 to n; instead, it prints out the
		// series from 2 to (n+1).
		for(int i = 0; i < fibonacci.length; i++)
			System.out.println(fibonacci[i].sum);
	}

}
