package chess;

/**
 * Position is a simple class characterized by a rank and a file.
 * 
 * @author Kai-Zhan Lee
 * 
 */
public class Position {

	/**
	 * The rank of this Position.
	 */
	private int rank;

	/**
	 * The file of this Position.
	 */
	private int file;

	/**
	 * Creates a new position with the given rank and file.
	 * 
	 * @param rank
	 *            The given rank.
	 * @param file
	 *            The given file.
	 */
	public Position(int rank, int file) {
		this.rank = rank;
		this.file = file;
	}

	/**
	 * Accessor method for rank.
	 * 
	 * @return This Position's rank.
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * Accessor method for file.
	 * 
	 * @return This Position's file.
	 */
	public int getFile() {
		return file;
	}

	/**
	 * @return Whether this Position is in the bounds of a chessboard.
	 */
	public boolean isInBounds() {
		return 0 < rank && rank <= 8 && 0 < file && file <= 8;
	}

	/**
	 * Gets the Position relative to this one, specified by drank and dfile.
	 * 
	 * @param drank
	 *            The desired change in rank.
	 * @param dfile
	 *            The desired change in file.
	 * @return The Position drank ranks and dfile files away from this one.
	 */
	public Position relative(int drank, int dfile) {
		return new Position(rank + drank, file + dfile);
	}

	public boolean equals(Object object) {
		return (object instanceof Position) &&
				rank == ((Position) object).getRank() &&
				file == ((Position) object).getFile();
	}

	public String toString() {
		return String.valueOf((char) ('a' + file - 1)) + rank;
	}
}
