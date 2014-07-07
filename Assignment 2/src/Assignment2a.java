/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 2a
 */

/* Instructions
 * Make actions:
 *   1) one called everyThird
 *      it should accept one parameter of type String[]
 *      it should print every third item of the parameter
 *   2) one called everyFifth
 *      it should accept one parameter of type String[]
 *      it should print every fifth item of the parameter
 *   3) one called everyThirdOrFifth
 *      it should print every third and fifth item
 *      so the 3rd, 5th, 6th, 9th, 10th, 12th, 15th, ...
 * Make a main that uses the three actions in the order presented,
 * but writes a message declaring when it is going to use them:
 *
 * how it should work:
 *   javac Assignment2a.java
 *   java Assignment2a 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
 *   usingEveryThird
 *   3
 *   6
 *   9
 *   12
 *   15
 *   usingEveryFifth
 *   5
 *   10
 *   15
 *   usingEveryThirdOrFifth
 *   3
 *   5
 *   6
 *   9
 *   10
 *   12
 *   15
 */

public class Assignment2a {
	
	/**
	 * Prints every third item of the parameter.
	 * 
	 * @param args Any array of strings
	 */
	public static void everyThird(String[] args){
		for(int i = 0; i < args.length; i++){
			if(i % 3 == 2){
				System.out.println(args[i]);
			}
		}
	}
	
	/**
	 * Prints every fifth item of the parameter.
	 * 
	 * @param args Any array of strings
	 */
	public static void everyFifth(String[] args){
		for(int i = 0; i < args.length; i++){
			if(i % 5 == 4){
				System.out.println(args[i]);
			}
		}
	}
	
	/**
	 * Prints every third or fifth item of the parameter.
	 * 
	 * @param args Any array of strings
	 */
	public static void everyThirdOrFifth(String[] args){
		for(int i = 0; i < args.length; i++){
			if(i % 5 == 4 || i % 3 == 2){
				System.out.println(args[i]);
			}
		}
	}
	
	public static void main(String[] args){
		System.out.println("usingEveryThird");
		everyThird(args);
		System.out.println("usingEveryFifth");
		everyFifth(args);
		System.out.println("usingEveryThirdOrFifth");
		everyThirdOrFifth(args);
	}
}