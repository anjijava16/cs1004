/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 4c
 */

/* Instructions
 * The class below is named Counter2
 * In Counter1 we made a counter class that incremented by 1,
 * now we'll make one that increments by various numbers.
 * Fill out the class such that the below main works as expected.
 *
 * Note that your instance members should only be public or
 * private, and if there is no reason for something to be public
 * then it should definitely be private.
 */

public class Counter2 {

	private int counter;
	
	private int increment;
	
	public Counter2(int increment){
		counter = 0;
		this.increment = increment;
	}
	
	public void increment(){
		counter += increment;
	}
	
	public int getCount(){
		return counter;
	}
	
	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Arguments needed; goodbye.");
			System.exit(1);
		}

		Counter2 positive = new Counter2(1);
		Counter2 negative = new Counter2(-1);
		Counter2 evens = new Counter2(2);

		int n = Integer.parseInt(args[0]);
		for (int i = 0; i < n; ++i) {
			System.out.println("positive = " + positive.getCount() + ", should be " + i);
			System.out.println("negative = " + negative.getCount() + ", should be " + (-i));
			System.out.println("evens = " + evens.getCount() + ", should be " + (i*2));
			positive.increment();
			negative.increment();
			evens.increment();
		}
	}
}
