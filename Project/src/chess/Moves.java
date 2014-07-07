package chess;

public interface Moves {

	// Move types
	public static final String MOVE = " - ";
	public static final String CAPTURE = " x ";
	public static final String THEORETICAL = "theoretical";
	// Special moves
	public static final String KING_CASTLE = "0 - 0";
	public static final String QUEEN_CASTLE = "0 - 0 - 0";
	public static final String EN_PASSANTE = " x";

	// Invalid move
	public static final String INVALID = "invalid";

	// Move modifiers
	public static final String NONE = " ";
	public static final String CHECK = " +";
	public static final String DOUBLE_CHECK = " ++";
	public static final String CHECKMATE = " #";
}
