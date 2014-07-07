import java.util.ArrayList;

public class Game {

	public static void main(String[] args) {
		// Check to make sure there are the correct number of inputs.
		if (args.length != 2) {
			System.err.println("Usage: java Game #ants #sugarPieces");
			System.exit(1);
		}

		// Define the vectors allAnts and allSugar
		ArrayList<Ant> allAnts = new ArrayList<Ant>();
		ArrayList<Sugar> allSugar = new ArrayList<Sugar>();

		// Define variables A and S;
		int A = 0;
		int S = 0;

		// Check to make sure that the first input (number of ants) is a
		// positive integer.
		try {
			// If it's an integer, but not positive, throw a
			// NumberFormatException (i.e. jump to the catch part of this
			// clause)

			// If it's not interpretable as an integer, throws a
			// NumberFormatException and jumps to the catch part of this clause.
			if (Integer.parseInt(args[0]) <= 0)
				throw new NumberFormatException();
			else
				// Otherwise, set A, the number of ants, to the integer value of
				// the input
				A = Integer.parseInt(args[0]);
		} catch (NumberFormatException nfe) {
			System.err.println("Usage: java Game #ants #sugarPieces");
			System.err.println("#ants must be a positive integer.");
			System.exit(2);
		}

		// Check to make sure that the second input (number of sugar pieces) is
		// a positive integer.
		try {
			// If it's an integer, but not positive, throw a
			// NumberFormatException (i.e. jump to the catch part of this
			// clause)

			// If it's not interpretable as an integer, throws a
			// NumberFormatException and jumps to the catch part of this clause.
			if (Integer.parseInt(args[1]) <= 0)
				throw new NumberFormatException();
			else
				// Otherwise, set S, the number of sugar pieces, to the integer
				// value of the input
				S = Integer.parseInt(args[1]);
		} catch (NumberFormatException nfe) {
			System.err.println("Usage: java Game #ants #sugarPieces");
			System.err.println("#sugarPieces must be a positive integer.");
			System.exit(3);
		}

		// Initialize the sugar vector (Must be initialized first because every
		// ant must know where each sugar piece is).
		for (int i = 0; i < S; i++)
			allSugar.add(new Sugar(new Point(
					Math.random() * 100,
					Math.random() * 100), Math.random() * 4 + 1));
		// Initialize the ant vector.
		for (int i = 0; i < A; i++)
			allAnts.add(new Ant(i, new Point(
					Math.random() * 100,
					Math.random() * 100), allSugar));

		// Number of turns that have passed.
		int turns = 0;

		// The turn loop of the simulation. Terminates when there is no sugar
		// left. (Continues while the sugar vector is not empty)
		while (!allSugar.isEmpty()) {
			// Step 1: Move each ant towards the closest piece of sugar.
			for (Ant ant : allAnts)
				ant.moveTowards(ant.getClosestSugar().getLocation());

			// Step 2: Check to see which ants are at which pieces of sugar and
			// make them eat those pieces.
			for (Sugar sugar : allSugar) {
				ArrayList<Ant> antsAtSugar = new ArrayList<Ant>();

				// Gets all of the ants at this piece of sugar.
				for (Ant ant : allAnts)
					if (ant.getLocation().distanceTo(sugar.getLocation()) < 0.001)
						antsAtSugar.add(ant);

				// If there are some ants at this sugar piece, have them eat it,
				// sharing the calories equally
				if (!antsAtSugar.isEmpty())
					for (Ant ant : antsAtSugar)
						ant.eat(sugar, antsAtSugar.size());
			}

			// Step 3: Remove any eaten sugar from the board.
			for (int i = 0; i < allSugar.size(); i++)
				if (allSugar.get(i).getEaten()) {
					allSugar.remove(i);
				}

			// Increment the number of turns passed.
			turns++;
		}

		// Writes out which ant has the most calories and how many turns were
		// executed. (As the instructions say to.)
		Ant antWithMostCalories = null;
		for (Ant ant : allAnts) {
			// Set antWithMostCalories to the first element of allAnts.
			if (antWithMostCalories == null)
				antWithMostCalories = ant;
			// If there's an ant with more calories than antWithMostCalories,
			// set antWithMostCalories to that ant.
			if (antWithMostCalories.getCalories() < ant.getCalories())
				antWithMostCalories = ant;
		}
		System.out.println("The ant with the most calories was ant " +
				antWithMostCalories.getID() + ", at point " +
				antWithMostCalories.getLocation() + " with " +
				antWithMostCalories.getCalories() + " calories.");
		System.out.println("There were " + turns + " turns.");
	}

}
