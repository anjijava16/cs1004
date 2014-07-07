import java.util.Vector;
import java.util.Scanner;


public class Assignment6a {

	public static void main(String[] args){
		//Variable declaration, somewhat self-explanatory.
		Scanner scanner = new Scanner(System.in);
		Vector<String> lines = new Vector<String>();
		String line = "";
		
		//Gets all of the user's line inputs until "done."
		while(!line.equals("done."))
			lines.add(line = scanner.nextLine());
		
		//Remove the "done." at the end.
		lines.remove(lines.size() - 1);
		
		//Print the in-between line.
		System.out.println();
		
		//Prints out the Strings in the vector.
		for(String string: lines)
			System.out.println(string);
	}

}
