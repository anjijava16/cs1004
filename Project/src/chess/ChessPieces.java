package chess;

/**
 * ChessPieces is an interface with all of the values of the chess pieces in the
 * game, as well as their UTF-16 characters.
 *
 * @author Kai-Zhan Lee
 * @see chess.ChessPiece
 * @see chess.piece.King
 * @see chess.piece.Queen
 * @see chess.piece.Rook
 * @see chess.piece.Bishop
 * @see chess.piece.Knight
 * @see chess.piece.Pawn
 *
 */
public interface ChessPieces {

	// The pieces' values.
	public static final int KING_VALUE = Integer.MAX_VALUE;
	public static final int QUEEN_VALUE = 9;
	public static final int ROOK_VALUE = 5;
	public static final int BISHOP_VALUE = 3;
	public static final int KNIGHT_VALUE = 3;
	public static final int PAWN_VALUE = 1;

	// The white pieces' characters, UTF-16.
	public static final char WHITE_KING = '\u2654';
	public static final char WHITE_QUEEN = '\u2655';
	public static final char WHITE_ROOK = '\u2656';
	public static final char WHITE_BISHOP = '\u2657';
	public static final char WHITE_KNIGHT = '\u2658';
	public static final char WHITE_PAWN = '\u2659';

	// The black pieces' characters, UTF-16.
	public static final char BLACK_KING = '\u265A';
	public static final char BLACK_QUEEN = '\u265B';
	public static final char BLACK_ROOK = '\u265C';
	public static final char BLACK_BISHOP = '\u265D';
	public static final char BLACK_KNIGHT = '\u265E';
	public static final char BLACK_PAWN = '\u265F';

	// The white pieces' characters, UTF-8.
	public static final char UTF8_WHITE_KING = 'K';
	public static final char UTF8_WHITE_QUEEN = 'Q';
	public static final char UTF8_WHITE_ROOK = 'R';
	public static final char UTF8_WHITE_BISHOP = 'B';
	public static final char UTF8_WHITE_KNIGHT = 'N';
	public static final char UTF8_WHITE_PAWN = 'P';

	// The black pieces' characters, UTF-8.
	public static final char UTF8_BLACK_KING = 'k';
	public static final char UTF8_BLACK_QUEEN = 'q';
	public static final char UTF8_BLACK_ROOK = 'r';
	public static final char UTF8_BLACK_BISHOP = 'b';
	public static final char UTF8_BLACK_KNIGHT = 'n';
	public static final char UTF8_BLACK_PAWN = 'p';
}
