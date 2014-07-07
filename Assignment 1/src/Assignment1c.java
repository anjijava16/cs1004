/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 1c
 */

/* Instructions
 * Fill out the main below to do the following:
 * Assign to variables the following information, using the best type appropriate
 *   1) your name
 *   2) my name (Matthew Maycock)
 *   3) what you want people to think your age is
 *   4) your home institution (Columbia, Duke, Cambridge, etc)
 *   5) the name of this institution (Columbia University)
 *   6) the name of this course
 *   7) the room this course is taught in
 * Output everything in meaningful english
 *   "Matthew Maycock is teaching .... at .... to ... ... ..."
 * You can only use one System.out.println, so use concatenation
 * You don't need to use a scanner at all for this problem.
 */

public class Assignment1c {
	
	public static void main(String[] args) {
		// Assigning to variables the correct information.
		// 1. my name
		String myName = "Kai-Zhan Lee";
		// 2. instructor's name (Matthew Maycock)
		String instructorName = "Matthew Maycock";
		// 3. what I want people to think my age is
		int myDesiredPerceivedAge = 12; //This isn't some weird form of self-denial or delusion; I am mentally stable and 12 years old.		
		// 4. my home institution (Columbia, Duke, Cambridge, etc.)
		String myHomeInstitution = "The Dalton School"; //This isn't a mistake either; I actually am a high-schooler.
		// 5. the name of this institution (Columbia University)
		String courseInstitution = "Columbia University";
		// 6. the name of this course
		String courseName = "CS 1004: Introduction to Computer Science and Programming Java";
		// 7. the room this course is taught in
		int courseRoom = 633;
		// Output everything in meaningful english
		// You can only use one System.out.println(), so use concatenation
		System.out.println(myName + ", who attends " +
						   myHomeInstitution + ", is taking a course at " +
						   courseInstitution + " called \"" +
						   courseName + "\", taught by " +
						   instructorName + " in room " +
						   courseRoom + ", and wants other people to think that he is " +
						   myDesiredPerceivedAge + " years old.");
	}
}