package chess.pieces;

import java.awt.Color;
import java.util.ArrayList;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Move;
import chess.Moves;
import chess.Position;

public class King extends ChessPiece {

	private static final long serialVersionUID = -7951276817081534065L;

	public King(ChessBoard board, Color color) {
		super(board, color);
		character = color == Color.WHITE ? WHITE_KING : BLACK_KING;
		UTF8Character =
				color == Color.WHITE ? UTF8_WHITE_KING : UTF8_BLACK_KING;
		value = KING_VALUE;
	}

	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Position temp;

		// All possible moves.
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				temp = position.relative(i, j);
				if (board.getPieceAt(temp) == null && temp.isInBounds())
					moves.add(new Move(this, temp, Moves.MOVE));
			}

		// Check to see if the king can castle. King can't have moved, and
		// chosen rook can't have moved.
		if (!beenMoved(this) && !isChecked())
			if (color.equals(Color.WHITE)) {
				// Castle on the king's side.
				temp = new Position(1, 7);
				if (!beenMoved(board.getPieceAt(temp.relative(0, 1))) &&
						board.getPieceAt(temp) == null &&
						board.getPieceAt(temp.relative(0, -1)) == null &&
						!isPositionAttacked(position))
					moves.add(new Move(this, temp, Moves.KING_CASTLE));

				// Castle on the queen's side.
				temp = new Position(1, 3);
				if (!beenMoved(board.getPieceAt(temp.relative(0, -2))) &&
						board.getPieceAt(temp.relative(0, -1)) == null &&
						board.getPieceAt(temp) == null &&
						board.getPieceAt(temp.relative(0, 1)) == null &&
						!isPositionAttacked(position))
					moves.add(new Move(this, temp, Moves.QUEEN_CASTLE));
			} else if (color.equals(Color.BLACK)) {
				// Castle on the king's side.
				temp = new Position(8, 7);
				if (!beenMoved(board.getPieceAt(temp.relative(0, 1))) &&
						board.getPieceAt(temp) == null &&
						board.getPieceAt(temp.relative(0, -1)) == null)
					moves.add(new Move(this, temp, Moves.KING_CASTLE));

				// Castle on the queen's side.
				temp = new Position(8, 3);
				if (!beenMoved(board.getPieceAt(temp.relative(0, -2))) &&
						board.getPieceAt(temp.relative(0, -1)) == null &&
						board.getPieceAt(temp) == null &&
						board.getPieceAt(temp.relative(0, 1)) == null)
					moves.add(new Move(this, temp, Moves.QUEEN_CASTLE));
			}

		// Remove all invalid moves (a ConcurrentModificationException is
		// always triggered when using the iterator form of a for statement.)
		for (int i = 0; i < moves.size(); i++)
			if (moves.get(i).causesCheck()) {
				moves.remove(i);
				i--;
			}

		// Add the possible moves of capture.
		moves.addAll(getCaptures());

		return moves;
	}

	public ArrayList<Move> getCaptures() {
		ArrayList<Move> captures = new ArrayList<Move>();
		Position temp;

		// All possible captures.
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				temp = position.relative(i, j);
				if (board.getPieceAt(temp) != null &&
						!board.getPieceAt(temp).getColor().equals(color) &&
						temp.isInBounds())
					captures.add(new Move(this, temp, Moves.CAPTURE));
			}

		return captures;
	}

	public ArrayList<Move> getAttacks() {
		ArrayList<Move> attacks = new ArrayList<Move>();
		Position temp;

		// All possible captures.
		for (int i = -1; i <= 1; i++)
			for (int j = -1; j <= 1; j++) {
				temp = position.relative(i, j);
				if (temp.isInBounds() && !temp.equals(position))
					attacks.add(new Move(this, temp, Moves.THEORETICAL));
			}

		return attacks;
	}

	/**
	 * @param position
	 *            A given position.
	 * @return Whether a given position is attacked.
	 */
	private boolean isPositionAttacked(Position position) {
		ArrayList<ChessPiece> opponentPieces =
				board.getPieces(color.equals(Color.WHITE)
						? Color.BLACK
							: Color.WHITE);
		ArrayList<Move> allMoves = new ArrayList<Move>();

		// Set the allMoves vector.
		for (ChessPiece piece : opponentPieces)
			allMoves.addAll(piece.getAttacks());

		// If any of the moves end at the position.
		for (Move move : allMoves)
			if (move.getDestination().equals(position))
				return true;

		return false;
	}

	/**
	 * @param piece
	 *            A given piece.
	 * @return Whether a given piece has been moved at some point in the game.
	 */
	private boolean beenMoved(ChessPiece piece) {
		// If the piece is null, returns true because
		if (piece == null)
			return true;

		// Check all of the game's previous moves.
		for (Move move : board.getGame().getMoves())
			if (piece.equals(move.getPiece()) ||
					piece.equals(move.getCastledRook()))
				return true;

		// If not, it hasn't been moved.
		return false;
	}

	/**
	 * @return Whether the king is in check on its board.
	 */
	public boolean isChecked() {
		return isPositionAttacked(position);
	}

	/**
	 * @return Whether the king is in double-check on its board.
	 */
	public boolean isDoubleChecked() {
		// Set the allOpponentMoves vector.
		ArrayList<Move> allOpponentMoves = new ArrayList<Move>();
		for (ChessPiece piece : board.getPieces(board.oppositeColor(color)))
			allOpponentMoves.addAll(piece.getAttacks());

		// If any moves end at this position, increment the number of checks.
		int checks = 0;
		for (Move move : allOpponentMoves)
			if (move.getDestination().equals(position))
				checks++;

		return checks == 2;
	}

	/**
	 * @return Whether the king has been check-mated on its board.
	 */
	public boolean isCheckMated() {
		ArrayList<Move> allMoves = new ArrayList<Move>();
		for (ChessPiece piece : board.getPieces(color))
			allMoves.addAll(piece.getMoves());

		// If the king is checked and no moves are possible.
		return isChecked() && allMoves.size() == 0;
	}

}
