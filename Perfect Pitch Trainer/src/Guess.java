import java.util.Scanner;

public class Guess {

	public static boolean isUserRight(String userGuess, int pitchNumber) {
		if ((userGuess.equals("A") || userGuess.equals("a") ||
				userGuess.equals("A natural") || userGuess.equals("a natural")) &&
				pitchNumber == 0)
			return true;
		else if ((userGuess.equals("A#") || userGuess.equals("Bb") ||
				userGuess.equals("A sharp") || userGuess.equals("B flat") ||
				userGuess.equals("a sharp") || userGuess.equals("b flat")) &&
				pitchNumber == 1)
			return true;
		else if ((userGuess.equals("B") || userGuess.equals("b") ||
				userGuess.equals("B natural") || userGuess.equals("b natural")) &&
				pitchNumber == 2)
			return true;
		else if ((userGuess.equals("C") || userGuess.equals("c") ||
				userGuess.equals("C natural") || userGuess.equals("c natural")) &&
				pitchNumber == 3)
			return true;
		else if ((userGuess.equals("C#") || userGuess.equals("Db") ||
				userGuess.equals("C sharp") || userGuess.equals("D flat") ||
				userGuess.equals("c sharp") || userGuess.equals("d flat")) &&
				pitchNumber == 4)
			return true;
		else if ((userGuess.equals("D") || userGuess.equals("d") ||
				userGuess.equals("D natural") || userGuess.equals("d natural")) &&
				pitchNumber == 5)
			return true;
		else if ((userGuess.equals("D#") || userGuess.equals("Eb") ||
				userGuess.equals("D sharp") || userGuess.equals("E flat") ||
				userGuess.equals("d sharp") || userGuess.equals("e flat")) &&
				pitchNumber == 6)
			return true;
		else if ((userGuess.equals("E") || userGuess.equals("e") ||
				userGuess.equals("E natural") || userGuess.equals("e natural")) &&
				pitchNumber == 7)
			return true;
		else if ((userGuess.equals("F") || userGuess.equals("f") ||
				userGuess.equals("F natural") || userGuess.equals("f natural")) &&
				pitchNumber == 8)
			return true;
		else if ((userGuess.equals("F#") || userGuess.equals("Gb") ||
				userGuess.equals("F sharp") || userGuess.equals("G flat") ||
				userGuess.equals("f sharp") || userGuess.equals("g flat")) &&
				pitchNumber == 9)
			return true;
		else if ((userGuess.equals("G") || userGuess.equals("g") ||
				userGuess.equals("G natural") || userGuess.equals("g natural")) &&
				pitchNumber == 10)
			return true;
		else if ((userGuess.equals("G#") || userGuess.equals("Ab") ||
				userGuess.equals("G sharp") || userGuess.equals("A flat") ||
				userGuess.equals("g sharp") || userGuess.equals("a flat")) &&
				pitchNumber == 11)
			return true;
		else
			return false;
	}

	public static boolean isUserWrong(String userGuess, int pitchNumber) {
		if (!userGuess.equals("Play pitch again") &&
				!userGuess.equals("I give up") && !userGuess.equals("Exit")) {
			if (!userGuess.equals("A") && !userGuess.equals("a") &&
					!userGuess.equals("A natural") &&
					!userGuess.equals("a natural") && pitchNumber == 0)
				return true;
			else if (!userGuess.equals("A#") && !userGuess.equals("Bb") &&
					!userGuess.equals("A sharp") &&
					!userGuess.equals("B flat") &&
					!userGuess.equals("a sharp") &&
					!userGuess.equals("b flat") && pitchNumber == 1)
				return true;
			else if (!userGuess.equals("B") && !userGuess.equals("b") &&
					!userGuess.equals("B natural") &&
					!userGuess.equals("b natural") && pitchNumber == 2)
				return true;
			else if (!userGuess.equals("C") && !userGuess.equals("c") &&
					!userGuess.equals("C natural") &&
					!userGuess.equals("c natural") && pitchNumber == 3)
				return true;
			else if (!userGuess.equals("C#") && !userGuess.equals("Db") &&
					!userGuess.equals("C sharp") &&
					!userGuess.equals("D flat") &&
					!userGuess.equals("c sharp") &&
					!userGuess.equals("d flat") && pitchNumber == 4)
				return true;
			else if (!userGuess.equals("D") && !userGuess.equals("d") &&
					!userGuess.equals("D natural") &&
					!userGuess.equals("d natural") && pitchNumber == 5)
				return true;
			else if (!userGuess.equals("D#") && !userGuess.equals("Eb") &&
					!userGuess.equals("D sharp") &&
					!userGuess.equals("E flat") &&
					!userGuess.equals("d sharp") &&
					!userGuess.equals("e flat") && pitchNumber == 6)
				return true;
			else if (!userGuess.equals("E") && !userGuess.equals("e") &&
					!userGuess.equals("E natural") &&
					!userGuess.equals("e natural") && pitchNumber == 7)
				return true;
			else if (!userGuess.equals("F") && !userGuess.equals("f") &&
					!userGuess.equals("F natural") &&
					!userGuess.equals("f natural") && pitchNumber == 8)
				return true;
			else if (!userGuess.equals("F#") && !userGuess.equals("Gb") &&
					!userGuess.equals("F sharp") &&
					!userGuess.equals("G flat") &&
					!userGuess.equals("f sharp") &&
					!userGuess.equals("g flat") && pitchNumber == 9)
				return true;
			else if (!userGuess.equals("G") && !userGuess.equals("g") &&
					!userGuess.equals("G natural") &&
					!userGuess.equals("g natural") && pitchNumber == 10)
				return true;
			else if (!userGuess.equals("G#") && !userGuess.equals("Ab") &&
					!userGuess.equals("G sharp") &&
					!userGuess.equals("A flat") &&
					!userGuess.equals("g sharp") &&
					!userGuess.equals("a flat") && pitchNumber == 11)
				return true;
			else
				return false;
		} else
			return false;
	}

	public static void askContinue() {
		while (true) {
			System.out
					.println("Would you like to continue? Please say either 'Yes' or 'No'");
			Scanner scanner = new Scanner(System.in);
			String userContinue = scanner.nextLine();
			if (userContinue.equals("Yes") | userContinue.equals("yes"))
				break;
			else if (userContinue.equals("No") | userContinue.equals("no")) {
				System.out.println("Okay. Goodbye!");
				System.exit(1);
			} else
				System.out.println("You didn't follow instructions.");
		}
	}

	public static void askTryAgain() {
		System.out
				.println("Would you like to try again? Please say either 'Yes' or 'No'");
		Scanner scanner = new Scanner(System.in);
		String userTryAgain = scanner.nextLine();
		if (userTryAgain.equals("Yes") | userTryAgain.equals("yes")) {
			System.out.println("");
			return;
		} else if (userTryAgain.equals("No") | userTryAgain.equals("no")) {
			System.out.println("Okay. Goodbye!");
			System.exit(1);
		} else {
			System.out.println("You didn't follow instructions.");
			askTryAgain();
		}
	}

	public static void tellAnswer(int pitchNumber) {
		if (pitchNumber == 0) {
			System.out.println("The pitch was an A.");
			askTryAgain();
		}
		if (pitchNumber == 1) {
			System.out.println("The pitch was a Bb/A#.");
			askTryAgain();
		}
		if (pitchNumber == 2) {
			System.out.println("The pitch was a B.");
			askTryAgain();
		}
		if (pitchNumber == 3) {
			System.out.println("The pitch was a C.");
			askTryAgain();
		}
		if (pitchNumber == 4) {
			System.out.println("The pitch was a C#.");
			askTryAgain();
		}
		if (pitchNumber == 5) {
			System.out.println("The pitch was a D.");
			askTryAgain();
		}
		if (pitchNumber == 6) {
			System.out.println("The pitch was an Eb/D#.");
			askTryAgain();
		}
		if (pitchNumber == 7) {
			System.out.println("The pitch was an E.");
			askTryAgain();
		}
		if (pitchNumber == 8) {
			System.out.println("The pitch was an F.");
			askTryAgain();
		}
		if (pitchNumber == 9) {
			System.out.println("The pitch was an F#/Gb.");
			askTryAgain();
		}
		if (pitchNumber == 10) {
			System.out.println("The pitch was a G.");
			askTryAgain();
		}
		if (pitchNumber == 11) {
			System.out.println("The pitch was an Ab/G#.");
			askTryAgain();
		}
	}
}
