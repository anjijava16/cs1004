/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 2y
 */
  
/* Instructions
 * make an action such that:
 *   it is named "action2y"
 *   it accepts an int parameter
 *   1) if the parameter is negative, multiply it by -1 to
 *      make it positive; if it is positive, leave it alone.
 *      Now, do the following:
 *      a) count how many prime twins are in the first
 *          n primes. a prime twin are two primes that
 *          are next to each other (11 & 13, 17 & 19)
 *      b) make an array of Strings to hold the prime
 *          twins from the first n primes
 *      c) place the string representation of the given
 *          prime twins into the array
 *      d) output the array, one item on each line
 *
 * Make a main that,
 *   1) If there are no args (how do you check that?) it will write
 *      "Goodbye!" and do nothing else
 *   2) Otherwise if there are args, turn the first one into an int
 *      and use it as the parameter to action2y
 *
 * example:
 *   javac Assignment2y.java
 *   java Assignment2y 10
 *   3
 *   5
 *   7
 *   11
 *   13
 *   17
 *   19
 */
  
public class Assignment2y {
	
	public static void main(String[] args) {
		if(args.length == 0){
			System.out.println("Goodbye!");
			System.exit(1);
		}
		int result = Integer.parseInt(args[0]);
		action2y(result);
	}
	
	public static void action2y(int num) {
		int[] primes = new int[num];
		int primesCounter = 0;
		int check = 2;
		boolean isPrime;
		
		//Set all the primes.
		while(primesCounter != num) {
			isPrime = true;
			// Check if it's prime.
			for(int i = check; i >= 2; i--)
				if(check != i && check % i == 0)
					isPrime = false;
			if(isPrime) primes[primesCounter++] = check;
			check++;
		}
		
		// Set the twinPrimes array.
		String[] twinPrimes = new String[num - 1];
		for(int i = 0; i < twinPrimes.length; i++)
			twinPrimes[i] = String.valueOf(primes[i]) + " & " + String.valueOf(primes[i + 1]);
		
		// Print out the twinPrimes array.
		for(int i = 0; i < twinPrimes.length; i++)
			System.out.println(twinPrimes[i]);
	}
	
} 