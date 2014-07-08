package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JComponent;

/**
 * ChessPiece is the basic class for all chess pieces.
 *
 * @author Kai-Zhan Lee
 * @see chess.ChessBoard
 * @see chess.ChessPieces
 * @see chess.piece.King
 * @see chess.piece.Queen
 * @see chess.piece.Rook
 * @see chess.piece.Bishop
 * @see chess.piece.Knight
 * @see chess.piece.Pawn
 *
 */
abstract public class ChessPiece extends JComponent implements ChessPieces {

	private static final long serialVersionUID = 6777882630441185098L;

	/**
	 * The counter used for each ID.
	 */
	public static int counter = 0;

	/**
	 * The ID of this piece. Used for finding a piece's location on the board
	 * and getting piece equality.
	 */
	private int ID;

	/**
	 * The chess piece's color.
	 */
	protected Color color;

	/**
	 * The value of this piece.
	 */
	protected int value;

	/**
	 * The UTF-16 character for this chess piece.
	 */
	protected char character;

	/**
	 * The UTF-8 character for this chess piece.
	 */
	protected char UTF8Character;

	/**
	 * The actual ChessBoard that this piece is on.
	 */
	protected ChessBoard board;

	/**
	 * The location of the chess piece (rank and file are from 1 - 8).
	 */
	protected Position position;

	/**
	 * Constructs a chess piece of the given color on the given board.
	 *
	 * @param board
	 *            The chess board that this chess piece is on.
	 * @param color
	 *            The color of this chess piece.
	 */
	public ChessPiece(ChessBoard board, Color color) {
		this.color = color;
		this.board = board;
		ID = counter++;
	}

	public void paintComponent(Graphics g) {
		char[] array = { character };
		g.setColor(color);
		if (equals(board.getSelectedPiece())) {
			g.setFont(ChessBoard.FONT);
			g.drawChars(array, 0, 1, board.getGame().getMouseX() -
					ChessBoard.SQUARE_SIZE / 2, board.getGame().getMouseY() -
					ChessBoard.SQUARE_SIZE / 2);
		} else
			g.drawChars(
					array,
					0,
					1,
					ChessBoard.SQUARE_SIZE * (position.getFile() - 1),
					ChessBoard.SQUARE_SIZE *
							(2 * 4 - (position.getRank() - 1)) -
							ChessBoard.OFFSET_CORRECTION);
	}

	public void paint(Graphics g) {
		char[] array = { character };
		g.drawChars(array, 0, 1,

		ChessBoard.SQUARE_SIZE * (position.getFile() - 1),

		ChessBoard.SQUARE_SIZE * (2 * 4 - (position.getRank() - 1)) -
				ChessBoard.OFFSET_CORRECTION);
	}

	/**
	 * Accessor method for color.
	 *
	 * @return The color of the chess piece.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Accessor method for value.
	 *
	 * @return The chess piece's value in the game.
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Accessor method for character.
	 *
	 * @return The chess piece's UTF-16 character.
	 */
	public char getChar() {
		return character;
	}

	/**
	 * Accessor method for position.
	 *
	 * @return The chess piece's position.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Accessor method for ID.
	 *
	 * @return The chess piece's ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Initializes the chess piece's position.
	 */
	public void setPosition() {
		position = board.getPositionOf(this);
	}

	/**
	 * Executes the given move, if possible.
	 *
	 * @param move
	 *            A given move to run.
	 * @return Whether it is possible to execute the move.
	 */
	protected boolean execute(Move move) {
		// If the other position is in the bounds of the board and this piece
		// can move to that position, move to the other position and return
		// true. Otherwise return false.
		if (canExecute(move)) {
			position = move.getDestination();
			board.execute(move);
			// Set this piece's position on the board.
			setPosition();
			return true;
		}
		return false;
	}

	public boolean moveTo(Position position) {
		Move move = null;
		for (Move possibleMove : getMoves())
			if (possibleMove.getDestination().equals(position)) {
				move = possibleMove;
				break;
			}

		// Execute the move, if possible, and return true, and otherwise false.
		if (move != null && move.execute())
			return true;
		else
			return false;
	}

	/**
	 * @param other
	 *            A given position.
	 * @return Whether this piece can execute the given move..
	 */
	public boolean canExecute(Move move) {
		return move != null && move.getDestination().isInBounds() &&
				move.getPiece().equals(this) && move.canExecute();
	}

	/**
	 * @return All currently possible moves.
	 */
	public abstract ArrayList<Move> getMoves();

	/**
	 * @return All currently possible captures.
	 */
	public abstract ArrayList<Move> getCaptures();

	/**
	 * @return All moves to squares being attacked by this piece.
	 */
	public abstract ArrayList<Move> getAttacks();

	/**
	 * Mutator method for board. Only used by ChessBoard's unreportedMove(Move
	 * move) method to update the game board.
	 *
	 * @param board
	 *            A given ChessBoard.
	 */
	protected void setBoard(ChessBoard board) {
		this.board = board;
	}

	/**
	 * @return The UTF8 character that represents this piece.
	 */
	public String getUTF8Char() {
		return String.valueOf(UTF8Character);
	}

	public String toString() {
		return String.valueOf(character);
	}

	/**
	 * A mutator method for ID. Only used when converting a chess board from
	 * file.
	 *
	 * @param ID
	 *            A given ID.
	 */
	protected void setID(int ID) {
		this.ID = ID;
	}
}
