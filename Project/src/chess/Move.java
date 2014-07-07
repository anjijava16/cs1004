package chess;

import java.awt.Color;
import java.util.ArrayList;

import chess.pieces.King;
import chess.pieces.Pawn;
import chess.pieces.Rook;

public class Move implements Moves {

	/**
	 * The identifying integer of this move.
	 */
	private int ID;

	/**
	 * The piece to be moved.
	 */
	private ChessPiece piece;

	/**
	 * The piece of this move's original position.
	 */
	private Position position;

	/**
	 * The piece of this move's destination.
	 */
	private Position destination;

	/**
	 * The type of move.
	 */
	private String type;

	/**
	 * The modifier of this move
	 */
	private String modifier;

	/**
	 * The chess piece this move's piece captures, if any.
	 */
	private ChessPiece capturedPiece;

	/**
	 * The chess piece this move's chess piece is to be promoted to, if any.
	 */
	private ChessPiece promotedPiece;

	/**
	 * The rook being castled to, if any.
	 */
	private Rook castledRook;

	/**
	 * The heuristic score of this move.
	 */
	private int score;

	/**
	 * Creates a Move.
	 *
	 * @param piece
	 *            The given chess piece to be moved.
	 * @param destination
	 *            The piece's destination.
	 * @param type
	 *            The type of move.
	 */
	public Move(ChessPiece piece, Position destination, String type) {
		this.piece = piece;
		position = piece.getPosition();
		this.destination = destination;
		this.type = type;
		if (isEnPassante())
			capturedPiece =
					piece.board.getPieceAt(new Position(
							position.getRank(),
							destination.getFile()));
		else if (isCapture())
			capturedPiece = piece.board.getPieceAt(destination);
		modifier = NONE;

		ChessPiece temp;
		temp = piece.board.getPieceAt(position.relative(0, 3));
		if (type.equals(KING_CASTLE))
			if (temp instanceof Rook &&
					temp.getColor().equals(piece.getColor()))
				castledRook = (Rook) temp;
			else
				this.type = INVALID;

		temp = piece.board.getPieceAt(position.relative(0, -4));
		if (type.equals(QUEEN_CASTLE))
			if (temp instanceof Rook &&
					temp.getColor().equals(piece.getColor()))
				castledRook = (Rook) temp;
			else
				this.type = INVALID;
	}

	/**
	 * Creates a promotion Move.
	 *
	 * @param piece
	 *            The given Pawn to be moved.
	 * @param destination
	 *            The Pawn's destination.
	 * @param type
	 *            The type of move. (i.e. move or capture)
	 * @param promotedPiece
	 *            The piece to which the pawn will be promoted.
	 */
	public Move(
			Pawn piece,
			Position destination,
			String type,
			ChessPiece promotedPiece) {
		this.piece = piece;
		position = piece.getPosition();
		this.destination = destination;
		this.type = type;
		if (promotedPiece == null)
			this.promotedPiece = piece;
		else
			this.promotedPiece = promotedPiece;
	}

	/**
	 * Sets the modifier to the appropriate keyword.
	 */
	public void setModifier() {
		Color oppositeColor = piece.board.oppositeColor(piece.getColor());
		ChessBoard chessBoard = piece.board.unreportedMove(this);

		King opponentKing = chessBoard.getKing(oppositeColor);

		if (opponentKing == null)
			modifier = NONE;
		else if (opponentKing.isChecked())
			modifier = CHECK;
		else if (opponentKing.isDoubleChecked())
			modifier = DOUBLE_CHECK;
		else if (opponentKing.isCheckMated())
			modifier = CHECKMATE;
	}

	/**
	 * Accessor method for piece.
	 *
	 * @return The piece being moved.
	 */
	public ChessPiece getPiece() {
		return piece;
	}

	/**
	 * Accessor method for promotedPiece.
	 *
	 * @return The piece that is being promoted, if any.
	 */
	public ChessPiece getPromotedPiece() {
		return promotedPiece;
	}

	/**
	 * Accessor method for position.
	 *
	 * @return The current position of the piece of this move.
	 */
	public Position getPosition() {
		return position;
	}

	/**
	 * Accessor method for destination.
	 *
	 * @return The destination of this move.
	 */
	public Position getDestination() {
		return destination;
	}

	/**
	 * Accessor method for castledRook.
	 *
	 * @return The rook being castled in this move.
	 */
	public Rook getCastledRook() {
		return castledRook;
	}

	/**
	 * Sets the score of this move to its heuristic value.
	 */
	protected void setScore() {
		if (isEnPassante())
			score = ChessPieces.PAWN_VALUE;
		else if (isCapture())
			score = piece.board.getPieceAt(destination).getValue();
		else if (isKingCastle())
			score = ChessPieces.PAWN_VALUE * 2;
		else if (isQueenCastle())
			score = (int) (ChessPieces.PAWN_VALUE * 1.5);
		else if (isPromotion())
			score = promotedPiece.getValue() - ChessPieces.PAWN_VALUE;
		else
			score = 0;

		if (doesCheckMate())
			score = ChessPieces.KING_VALUE;
	}

	/**
	 * Returns this move's score.
	 *
	 * @return The move's score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Increments this move's score by the given score.
	 *
	 * @param score
	 *            A given score.
	 */
	public void addScore(int score) {
		this.score += score;
	}

	/**
	 * @return Whether this move is a capture move.
	 */
	public boolean isCapture() {
		return type.equals(CAPTURE) || type.equals(EN_PASSANTE);
	}

	/**
	 * @return Whether this move is an en passante maneuver.
	 */
	public boolean isEnPassante() {
		return type.equals(EN_PASSANTE);
	}

	/**
	 * @return Whether this move is a castle on the king's side.
	 */
	public boolean isKingCastle() {
		return type.equals(KING_CASTLE);
	}

	/**
	 * @return Whether this move is a castle on the queen's side.
	 */
	public boolean isQueenCastle() {
		return type.equals(QUEEN_CASTLE);
	}

	/**
	 * @return Whether this move is a promotion.
	 */
	public boolean isPromotion() {
		return promotedPiece != null;
	}

	/**
	 * @return Whether this move is invalid.
	 */
	public boolean isInvalid() {
		return type.equals(INVALID);
	}

	/**
	 * @return The type of this move, in string form.
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return Whether this move will check the opponent.
	 */
	public boolean doesCheck() {
		setModifier();
		return modifier.equals(CHECK) || modifier.equals(DOUBLE_CHECK);
	}

	/**
	 * @return Whether this move will double check the opponent.
	 */
	public boolean doesDoubleCheck() {
		setModifier();
		return modifier.equals(DOUBLE_CHECK);
	}

	/**
	 * @return Whether this move will checkmate the opponent.
	 */
	public boolean doesCheckMate() {
		setModifier();
		return modifier.equals(CHECKMATE);
	}

	/**
	 * @return Whether this move will cause the piece's king to be checked.
	 */
	public boolean causesCheck() {
		// Return whether the king on the board with the move executed was
		// checked or not.
		King king = piece.board.unreportedMove(this).getKing(piece.getColor());
		if (king == null)
			return false;
		return king.isChecked();
	}

	/**
	 * Undoes this move if this move is the last move executed in the chess
	 * game.
	 * 
	 * @return Whether this move is the last move executed.
	 */
	protected boolean undo() {
		ArrayList<Move> moves = piece.board.getGame().getMoves();
		if (!moves.get(moves.size() - 1).equals(this))
			return false;
		return true;
	}

	/**
	 * Accessor method for ID.
	 *
	 * @return This move's ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Mutator method for ID.
	 *
	 * @param ID
	 *            Another ID.
	 */
	protected void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Executes this move, if possible.
	 *
	 * @return Whether this move can be executed.
	 */
	public boolean execute() {
		if (isPromotion()) {
			ChessGame game = piece.board.getGame();
			if (promotedPiece instanceof Pawn)
				promotedPiece = game.getPromotedPieceFromUser();
			promotedPiece.setID(piece.getID());
		}

		// Execute if possible, then return true. Otherwise, return false.
		if (canExecute() && piece.execute(this))
			return true;

		return false;
	}

	/**
	 * @return Whether this move can be executed.
	 */
	public boolean canExecute() {
		return piece != null && position != null && destination != null &&
				position.isInBounds() && destination.isInBounds() &&
				!type.equals(THEORETICAL) && !causesCheck();
	}

	public String toString() {
		String message = "";
		if (isEnPassante()) {
			message += piece;
			message += position;
			message += type + " ";
			message += capturedPiece;
			message += destination;
		} else if (isPromotion()) {
			message += piece;
			message += position;
			message += type;
			message += destination;
			message += "=" + promotedPiece;
		} else if (isCapture()) {
			message += piece;
			message += position;
			message += type;
			message += capturedPiece;
			message += destination;
		} else if (isKingCastle()) {
			message = "0 - 0";
		} else if (isQueenCastle()) {
			message = "0 - 0 - 0";
		} else if (isInvalid()) {
			message += type;
		} else { // Just a regular move.
			message += piece;
			message += position;
			message += type;
			message += destination;
		}

		// Set the modifier to whatever it should be.
		// if (modifier == NONE)
		// setModifier();
		message += modifier;
		return message;
	}

	/**
	 * @return A game log type move string.
	 */
	public String toGameLogString() {
		String message = "";
		if (isEnPassante()) {
			message += piece.getUTF8Char().toUpperCase();
			message += position;
			message += type + " ";
			message += capturedPiece.getUTF8Char().toUpperCase();
			message += destination;
		} else if (isPromotion()) {
			message += piece.getUTF8Char();
			message += position;
			message += type;
			message += destination;
			message += "=" + promotedPiece;
		} else if (isCapture()) {
			message += piece.getUTF8Char().toUpperCase();
			message += position;
			message += type;
			message += capturedPiece.getUTF8Char().toUpperCase();
			message += destination;
		} else if (isKingCastle()) {
			message = "0 - 0";
		} else if (isQueenCastle()) {
			message = "0 - 0 - 0";
		} else if (isInvalid()) {
			message += type;
		} else { // Just a regular move.
			message += piece.getUTF8Char();
			message += position;
			message += type;
			message += destination;
		}

		// Set the modifier to whatever it should be.
		setModifier();

		// Add on the modifier
		message += modifier;

		return message;
	}

	public Move clone() {
		Move move = null;
		try {
			move = (Move) super.clone();
		} catch (CloneNotSupportedException cnse) {}
		return move;
	}

	/**
	 * Converts a given string into a move.
	 *
	 * @param line
	 *            A given string.
	 * @return The move corresponding to the given string.
	 */
	public static Move fromUTF8String(String line) {
		Move move = null;
		return move;
	}
}
