/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 4b
 */

/* Instructions
 * The class below is named Counter1
 * For Assignment3b we made a static counter. Since it was static,
 * we could only ever have one counter. Now we'll do the same but
 * without using static members. Make a class such that the given
 * main works as expected.
 *
 * Note that your instance members should only be public or
 * private, and if there is no reason for something to be public
 * then it should definitely be private.
 */

public class Counter1 {

	private int counter = 0;

	public void increment(){
		counter++;
	}

	public int getCount(){
		return counter;
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Arguments needed; goodbye.");
			System.exit(1);
		}

		Counter1 counter = new Counter1();
		Counter1 half = new Counter1();

		int n = Integer.parseInt(args[0]);
		for (int i = 0; i < n; ++i) {
			System.out.println("counter = " + counter.getCount() + ", should be " + i);
			System.out.println("half = " + half.getCount() + ", should be " + (i/2));
			counter.increment();
			if (i % 2 == 1)
				half.increment();
		}
	}
}
