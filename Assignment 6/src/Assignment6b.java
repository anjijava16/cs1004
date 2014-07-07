import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Each promptType(String prompt) (where type is a primitive type) method in
 * this class utilizes a while loop that contains all other code. It asks the
 * user for an input of the given primitive type by using the prompt parameter
 * (in a try block of code), and if the input is invalid (wrong type) throws an
 * exception and goes to the catch block of code. Once the catch block of code
 * (a printed message that tells the user to enter a value of the given type) is
 * executed, the while loop is restarted.
 * 
 * @author Kai-Zhan Lee
 * 
 */
public class Assignment6b {

	/**
	 * Prompts the user for a double, and does not throw an exception.
	 * 
	 * @param prompt
	 *            A given prompt.
	 * @return The user-entered double.
	 */
	public double promptDouble(String prompt) {
		while (true)
			try {
				System.out.print(prompt + " ");
				// Compressed original lines 1, 5, 6 to the following line in
				// order to make the method faster:
				return new Scanner(System.in).nextDouble();
			} catch (InputMismatchException ime) {
				System.out.println("Please enter a double.");
			}
	}

	/**
	 * Prompts the user for a float, and does not throw an exception.
	 * 
	 * @param prompt
	 *            A given prompt.
	 * @return The user-entered float.
	 */
	public float promptFloat(String prompt) {
		while (true)
			try {
				System.out.print(prompt + " ");
				// Compressed original lines 1, 5, 6 to the following line in
				// order to make the method faster:
				return new Scanner(System.in).nextFloat();
			} catch (InputMismatchException ime) {
				System.out.println("Please enter a float.");
			}
	}

	/**
	 * Prompts the user for a byte, and does not throw an exception.
	 * 
	 * @param prompt
	 *            A given prompt.
	 * @return The user-entered byte.
	 */
	public byte promptByte(String prompt) {
		while (true)
			try {
				System.out.print(prompt + " ");
				// Compressed original lines 1, 5, 6 to the following line in
				// order to make the method faster:
				return new Scanner(System.in).nextByte();
			} catch (InputMismatchException ime) {
				System.out.println("Please enter a byte.");
			}
	}

	/**
	 * Prompts the user for a short, and does not throw an exception.
	 * 
	 * @param prompt
	 *            A given prompt.
	 * @return The user-entered short.
	 */
	public short promptShort(String prompt) {
		while (true)
			try {
				System.out.print(prompt + " ");
				// Compressed original lines 1, 5, 6 to the following line in
				// order to make the method faster:
				return new Scanner(System.in).nextShort();
			} catch (InputMismatchException ime) {
				System.out.println("Please enter a short.");
			}
	}

	/**
	 * Prompts the user for a long, and does not throw an exception.
	 * 
	 * @param prompt
	 *            A given prompt.
	 * @return The user-entered long.
	 */
	public long promptLong(String prompt) {
		while (true)
			try {
				System.out.print(prompt + " ");
				// Compressed original lines 1, 5, 6 to the following line in
				// order to make the method faster:
				return new Scanner(System.in).nextLong();
			} catch (InputMismatchException ime) {
				System.out.println("Please enter a long.");
			}
	}
}
