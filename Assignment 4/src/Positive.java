/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 4d
 */

/* Instructions
 * The class below is named Positive
 * We're going to make a positive natural number object
 *    1) there should be exactly one public constructor that accepts one int
 *    2) if there are non-public constructors, that is up to you
 *    3) if the parameter is negative, write an error and exit the program
 *    4) if the parameter is zero, write an error and ext the program
 *    5) otherwise, let the given parameter be denoted by v (for value)
 *    6) make the following methods to be used on the object:
 *        divides -- takes an int and returns true if that v divides that int else false
 *        multipleOf -- takes an int and returns true if that int divides v else false
 *        gcd -- takes another Positive object, which was constructed with
 *                   v' (v prime) and returns the greatest common divisor of v
 *                   and v' -- this should NOT access the instance variables
 *                   of the passed in Positive object
 *        isPrime -- returns whether v is a prime number
 *        factor -- if v is 1, then return the Positive object parameterized by 1.
 *                   otherwise, among all factors of v, let f be the smallest that
 *                   is not 1. Return a Positive object parameterized by f.
 *        toString -- returns a String representation of v
 */

public class Positive {

	private int v;

	public Positive(int number){
		if(number > 0)
			v = number;
		else if(number == 0){
			System.err.println("Error: Number cannot be zero. Exiting...");
			System.exit(1);
		} else {
			System.err.println("Error: Number cannot be negative. Exiting...");
			System.exit(1);
		}
	}

	public boolean divides(int number){
		return (number % v == 0);
	}

	public boolean multipleOf(int number){
		return (v % number == 0);
	}

	public int gcd(Positive number){
		int gcd;
		for(gcd = v; gcd > 1; gcd--)
			if(this.multipleOf(gcd) && number.multipleOf(gcd))
				return gcd;
		//Saves space on the stack; doesn't have to access (gcd=1).
		return 1;
	}

	public boolean isPrime(){
		for(int i = 2; i < v/2 + 1; i++)
			//Only has to count up to v/2 since 2 is the smallest divisor of v for it to be composite.
			//See proof in readme.txt
			if(this.multipleOf(i))
				return false;
		return true;
	}

	public Positive factor(){
		if(v == 1)
			return new Positive(1);
		for(int i = 2; i < v/2 + 1; i++)
			//Only has to count up to v/2 since 2 is the smallest divisor of v for it to be composite.
			//See proof in readme.txt
			if(this.multipleOf(i))
				return new Positive(i);
		//In the case that v is its own smallest factor (i.e. is prime),
		return new Positive(v);
	}

	public String toString(){ //Just return the integer's value.
		return String.valueOf(v);
	}

}
