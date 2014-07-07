
import java.util.Scanner;

public class Choice {
	/* Notice that this public static INT not public static VOID
	 * this is because actions can become functions i.e. something that
	 * computes a value like:
	 *     public static int square(int x) {
	 *        return x * x;
	 *     }
	 * so what we got here is something that given a set of options
	 * will let the user choose and return the index of the choice.
	 *
	 * Also we have some new syntax String...
	 * This means "collect all the parameters from this point forward and
	 * put them in an array"
	 *
	 * So now we can do things like:
	 *   choice = Choice.choose(
	 *     "Say Hello to the wizard",
	 *     "Tell the wizard to leave",
	 *     "Go to sleep, the wizard can do as it pleases",
	 *     "Seek psychiatric help -- you're seeing wizards"
	 *   );
	 */
	public static int choose(String... choices) {
		if (choices.length == 0) {
			/* System.out.println is for printing normal output
			 * System.err.println is for printing error output
			 */
			System.err.println("ERROR: Cannot choose among zero options.");	
			// System.exit makes the program quit.
			System.exit(0);
		}
		int choice;
		boolean chooseAgain = false;
		Scanner readin = new Scanner(System.in);
		
		do {
			// If it's not the first time through the loop...
			if (chooseAgain) {
				System.out.println("Sorry, but that was an invalid choice. " +
						"Please try again:");
			}
			// It will be next time (unless we make a nice choice).
			chooseAgain = true;
			
			// Print all the options...
			for (int i = 0; i < choices.length; ++i) {
				System.out.println("" + i + ":  " + choices[i]);
			}
			
			// Prompt the user for a selection and get it.
			System.out.println();
			System.out.print("Please make a selection:  ");
			choice = readin.nextInt();
			
			// Loop again if it was a bad choice.
		} while (choice < 0 || choice >= choices.length);
		
		// Give the choice back
		return choice;
	}
}