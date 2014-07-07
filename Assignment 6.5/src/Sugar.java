public class Sugar {

	/**
	 * The location of this sugar piece.
	 */
	private Point location;

	/**
	 * The calorie content of this sugar piece.
	 */
	private double calorieContent;

	/**
	 * Whether this sugar piece has been eaten or not. When it is set to true,
	 * it will be removed in the next step of the turn loop.
	 */
	private boolean eaten = false;

	public Sugar(Point location, double calorieContent) {
		this.location = location;
		this.calorieContent = calorieContent;
	}

	/**
	 * Accessor method for location.
	 * 
	 * @return This sugar piece's location.
	 */
	public Point getLocation() {
		return location;
	}

	/**
	 * Accessor method for calorieContent.
	 * 
	 * @return This sugar piece's calorie content.
	 */
	public double getCalorieContent() {
		return calorieContent;
	}

	/**
	 * Accessor method for eaten
	 * 
	 * @return Whether this sugar piece has been eaten.
	 */
	public boolean getEaten() {
		return eaten;
	}

	/**
	 * Mutator method for eaten. Sets eaten to true, signaling that this piece
	 * of sugar should be removed from the allSugar vector in the next step of
	 * the turn loop
	 */
	public void eat() {
		eaten = true;
	}

}
