/* Name: Kai-Zhan Lee
 * UNI: kl2792
 * CS 1004, Summer 2014
 * Programming Assignment 3_5a
 */

/* Instructions
 * Here we will be doing some command line processing
 * (the command line are the things we type into the dos window
 * or terminal like javac Something.java or cd some/path, etc)
 * Your program should work as follows:
 *    1) if you are given the argument -a, then the argument
 *       afterwards should be saved in a static variable named
 *       avalue (it should remain a String)
 *    2) if you are given the argument -b, then you should
 *       remember that you saw it, but do not save any String
 *       value for it
 *    3) the value for -a cannot be -b
 *    4) if -a value appeals multiple times, then the last
 *       one is the one that counts
 *    5) there MUST be a value for -a
 *    6) it is an error for there to be any other arguments
 *    7) the default value for avalue is "" -- i.e. if the
 *       user does not specify -a something, then the static
 *       variable avalue should be ""
 *
 * example proper uses:
 *    java Assignment3_5a -a something
 *    java Assignment3_5a -b
 *    java Assignment3_5a -a huh -b
 *    java Assignment3_5a -b -a huh
 *    java Assignment3_5a -a 1 -a 2 -b -a 3
 *
 * example improper uses:
 *    java Assignment3_5a -a -b      <-- -b cannot be the value for -a
 *    java Assignment3_5a -b -a      <-- -a needs a value
 *    java Assignment3_5a -a         <-- -a needs a value
 *    java Assignment3_5a hey -b     <-- hey, extra argument
 *    java Assignment3_5a -a 1 2     <-- 2 is an extra argument
 *
 * what to output
 *    if avalue has its default value (""), the the first line
 *    of the output should be:
 *      avalue has its default value
 *    otherwise it should be:
 *      avalue is "_______"
 *    where _____ is the value the user supplied, and you do need to
 *    have quotes around it
 *
 *    if the user specified -b then the second line should be:
 *      b is flagged
 *    otherwise it should be:
 *      b is unflagged
 *
 * example output:
 *    java Assignment3_5a -b
 *    avalue has its default value
 *    b is flagged
 *
 *    java Assignment3_5a -b -a something
 *    avalue is "something"
 *    b is flagged
 *
 *    java Assignment3_5a -a "huh"
 *    avalue is "huh"
 *    b is unflagged
 *
 *    java Assignment3_5a
 *    avalue has its default value
 *    b is unflagged
 *
 *    java Assignment3_5a -a this -a that
 *    avalue is "that"
 *    b is unflagged
 *
 * what to do with errors
 *    if one of the error situations occurs, i.e. -a -b or extra
 *    arguments, etc, then you should output what has happened
 *    and exit the program with System.exit(1);
 *
 * example:
 *    java Assignment3_5a -a
 *    Error: -a requires a value. Exiting.
 */

public class Assignment3_5a {
	public static String avalue = "";
	
	public static boolean bflagged = false;

	public static void main(String[] args){
		for(int i = 0; i < args.length; i++){
			if(args[i].equals("-a")){ //Check for -a and its argument
				if(i != args.length - 1 && !args[i + 1].equals("-b")){ //set avalue to the given value and skip the next argument
					avalue = args[i + 1];
					i++;
				} else if(i == args.length - 1){ //Error: -a needs a value
					System.err.println("Error: -a needs a value. Exiting.");
					System.exit(1);
				} else if(args[i + 1].equals("-b")){ //Error: -b can't be the value for -a
					System.err.println("Error: -b cannot be the value for -a. Exiting.");
					System.exit(1);
				}
			} else if(args[i].equals("-b")){ //Remembers that -b was used.
				bflagged = true;
			} else { //Error: Extra argument
				System.err.println("Error: \"" + args[i] + "\" is an extra argument. Exiting.");
				System.exit(1);
			}
		}
		//output for -a
		if(avalue == ""){
			System.out.println("avalue has its default value");
		} else {
			System.out.println("avalue is \"" + avalue + "\"");
		}
		//output for -b
		if(bflagged){
			System.out.println("b is flagged");
		} else {
			System.out.println("b is unflagged");
		}
		System.exit(0);
	}
}
