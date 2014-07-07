import java.util.ArrayList;

public class Ant {

	/**
	 * The ant's ID, used to distinguish it from other ants.
	 */
	private int ID;

	/**
	 * The number of calorie this ant has consumed.
	 */
	private double calorieCounter;

	/**
	 * This ant's current location (the center of its body)
	 */
	private Point center;

	/**
	 * Every ant has to know where all of the sugar pieces are. That information
	 * is stored in this vector.
	 */
	private ArrayList<Sugar> allSugar;

	public Ant(int ID, Point center, ArrayList<Sugar> allSugar) {
		this.ID = ID;
		calorieCounter = 0;
		this.center = center;
		this.allSugar = allSugar;

	}

	/**
	 * Accessor method for calorieCounter.
	 *
	 * @return The current number of calories eaten, which is stored in
	 *         calorieCounter.
	 */
	public double getCalories() {
		return calorieCounter;
	}

	/**
	 * Accessor method for center.
	 *
	 * @return The current location.
	 */
	public Point getLocation() {
		return center;
	}

	/**
	 * Accessor method for ID.
	 *
	 * @return The ant's ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Adds the given sugar's calorie content to the ant's calorie counter.
	 *
	 * @param sugar
	 *            A given piece of sugar.
	 * @param ants
	 *            The number of ants that are sharing this piece of sugar.
	 */
	public void eat(Sugar sugar, int ants) {
		calorieCounter += sugar.getCalorieContent() / ants;
		sugar.eat();
	}

	/**
	 * Moves this ant's location one unit towards the destination point.
	 *
	 * @param destination
	 *            The given destination point.
	 */
	public void moveTowards(Point destination) {
		if (center.distanceTo(destination) < 1) {
			center = destination;
			return;
		}
		// To move one unit, should set location to:

		// x: center.getX() + (destination.getX() -
		// center.getX())/center.distanceTo(destination)

		// y: center.getY() + (destination.getY() -
		// center.getY())/center.distanceTo(destination)
		center =
				new Point(center.getX() + (destination.getX() - center.getX()) /
						center.distanceTo(destination),

				center.getY() + (destination.getY() - center.getY()) /
						center.distanceTo(destination));
	}

	/**
	 * Gets the sugar piece closest to the ant (and therefore the one the ant
	 * will move towards).
	 *
	 * @return The sugar piece closest to the ant.
	 */
	public Sugar getClosestSugar() {
		Sugar returnSugar = null;
		for (Sugar sugar : allSugar) {
			if (returnSugar == null)
				returnSugar = sugar;
			if (center.distanceTo(sugar.getLocation()) < center
					.distanceTo(returnSugar.getLocation()))
				returnSugar = sugar;
		}
		return returnSugar;
	}

}
