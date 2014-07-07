Name: Kai-Zhan Lee
UNI: kl2792
Assignment 3.5 README

a)
main(): Runs through all of the arguments with a for loop, and then prints out
		the corresponding output (see below)
	1. If the argument is "-a", then the argument immediately after becomes the
	   value for the static String avalue and is skipped, unless it breaks one
	   of the following rules (upon which an error message is displayed):
	       1. The argument after "-a" is "-b".
	       2. There is no argument for "-a".
       	   3. Also, if there are multiple "-a"s, the last one is the one that
       	      is saved and outputted in avalue. However, if there is an error
       	      for one of these "-a"s, the program still displays an error
       	      message.
	2. If the argument is "-b", then the static boolean bflagged (by default
	   is false) is set to true.
	3. If there is any other argument, an error about an extra argument is
	   printed out.
	4. Output: (in this order)
	       1. If "-a" was not used, prints out: avalue has its default value
	       2. If "-a" was used properly, prints out: avalue is "<value>"
	       3. If "-b" was not used, prints out: b is unflagged.
	       4. If "-b" was used, prints out: b is flagged.