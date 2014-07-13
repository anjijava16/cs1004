import java.util.*;

public class Main {
	static Random randomGen = new Random();

	public static void main(String[] args) {
		System.out.println("What pitch is this? Type what pitch you think it is.");
		while(true) {
			int pitchNumber = randomGen.nextInt(12);
			//The commented code below will tell you what note is played, so you can test what happens when the user answers correctly.
			//System.out.println(PlaySound.files[pitchNumber]);
			while(true) {
				PlaySound.playPitch(pitchNumber);
				System.out.println("Type 'Play pitch again' to hear the pitch another time.");
				System.out.println("Type 'I give up' if you want the answer, or 'Exit' if you want to stop.");
				Scanner scanner = new Scanner(System.in);
				String userGuess = scanner.nextLine();
				if(Guess.isUserRight(userGuess, pitchNumber)) {
					System.out.println("Correct! Nice job!");
					Guess.askContinue();
					PlaySound.playSequence();
					break;
				}
				else if(Guess.isUserWrong(userGuess, pitchNumber)) {
					System.out.println("Sorry, that's incorrect!");
					continue;
				}
				else if(userGuess.equals("I give up")) {
					Guess.tellAnswer(pitchNumber);
					PlaySound.playSequence();
					break;
				}
				else if(userGuess.equals("Play pitch again")) {
				}
				else if(userGuess.equals("Exit")) {
					System.out.println("Okay. Goodbye!");
					System.exit(1);
				}
				else {
					System.out.println("You didn't follow the instructions.");
					System.out.println("What pitch is this? Type what pitch you think it is.");
				}
			}
		}
	}
}