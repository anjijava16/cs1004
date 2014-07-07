package chess.pieces;

import java.awt.Color;
import java.util.ArrayList;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Move;
import chess.Moves;
import chess.Position;

public class Pawn extends ChessPiece {

	private static final long serialVersionUID = 821934616629420498L;

	public Pawn(ChessBoard board, Color color) {
		super(board, color);
		character = color == Color.WHITE ? WHITE_PAWN : BLACK_PAWN;
		UTF8Character = color == Color.WHITE ? UTF8_WHITE_PAWN
				: UTF8_BLACK_PAWN;
		value = PAWN_VALUE;
	}

	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Position temp;

		if (color == Color.WHITE) {
			// Normal pawn moving
			temp = position.relative(1, 0);
			if (board.getPieceAt(temp) == null && temp.isInBounds()) {
				if (temp.getRank() == 8)
					moves.add(new Move(this, temp, Moves.MOVE, null));
				else
					moves.add(new Move(this, temp, Moves.MOVE));

				// Double-step moving (only if pawn can move one step).
				temp = position.relative(2, 0);
				if (board.getPieceAt(temp) == null && position.getRank() == 2)
					moves.add(new Move(this, temp, Moves.MOVE));
			}
		} else if (color == Color.BLACK) {
			// Normal pawn moving
			temp = position.relative(-1, 0);
			if (board.getPieceAt(temp) == null && temp.isInBounds()) {
				if (temp.getRank() == 1)
					moves.add(new Move(this, temp, Moves.MOVE, null));
				else
					moves.add(new Move(this, temp, Moves.MOVE));

				// Double-step moving (only if pawn can move one step).
				temp = position.relative(-2, 0);
				if (board.getPieceAt(temp) == null && position.getRank() == 7)
					moves.add(new Move(this, temp, Moves.MOVE));
			}
		}

		// Add the possible moves of capture.
		moves.addAll(getCaptures());

		// Remove all invalid moves
		for (int i = 0; i < moves.size(); i++)
			if (moves.get(i).causesCheck()) {
				moves.remove(i);
				i--;
			}

		return moves;
	}

	public ArrayList<Move> getCaptures() {
		ArrayList<Move> captures = new ArrayList<Move>();
		Position temp;

		if (color == Color.WHITE) {
			// To the right
			temp = position.relative(1, 1);
			if (board.getPieceAt(temp) != null
					&& !board.getPieceAt(temp).getColor().equals(color)
					&& temp.isInBounds())
				if (temp.getRank() == 8)
					captures.add(new Move(this, temp, Moves.CAPTURE, null));
				else
					captures.add(new Move(this, temp, Moves.CAPTURE));

			// To the left
			temp = position.relative(1, -1);
			if (board.getPieceAt(temp) != null
					&& !board.getPieceAt(temp).getColor().equals(color)
					&& temp.isInBounds())
				if (temp.getRank() == 8)
					captures.add(new Move(this, temp, Moves.CAPTURE, null));
				else
					captures.add(new Move(this, temp, Moves.CAPTURE));

			// En passante.
			ArrayList<Move> moves = board.getGame().getMoves();
			if (moves != null && moves.size() != 0) {
				Move lastMove = moves.get(moves.size() - 1);
				Position end = lastMove.getDestination();
				temp = lastMove.getPosition().relative(-1, 0);
				if (position.getRank() == 5
						&& lastMove.getPiece() instanceof Pawn
						&& lastMove.getPosition().getRank() == 7
						&& end.getRank() == 5 && board.getPieceAt(end) != null
						&& !board.getPieceAt(end).getColor().equals(color)
						&& temp.isInBounds())
					captures.add(new Move(this, temp, Moves.EN_PASSANTE));
			}
		} else if (color == Color.BLACK) {
			// To the right
			temp = position.relative(-1, 1);
			if (board.getPieceAt(temp) != null
					&& !board.getPieceAt(temp).getColor().equals(color)
					&& temp.isInBounds())
				if (temp.getRank() == 1)
					captures.add(new Move(this, temp, Moves.CAPTURE, null));
				else
					captures.add(new Move(this, temp, Moves.CAPTURE));

			// To the left
			temp = position.relative(-1, -1);
			if (board.getPieceAt(temp) != null
					&& !board.getPieceAt(temp).getColor().equals(color)
					&& temp.isInBounds())
				if (temp.getRank() == 1)
					captures.add(new Move(this, temp, Moves.CAPTURE, null));
				else
					captures.add(new Move(this, temp, Moves.CAPTURE));

			// En passante.
			ArrayList<Move> moves = board.getGame().getMoves();
			if (moves != null && moves.size() != 0) {
				Move lastMove = moves.get(moves.size() - 1);
				Position end = lastMove.getDestination();
				temp = lastMove.getPosition().relative(1, 0);
				if (position.getRank() == 4
						&& lastMove.getPiece() instanceof Pawn
						&& lastMove.getPosition().getRank() == 2
						&& end.getRank() == 4 && board.getPieceAt(end) != null
						&& !board.getPieceAt(end).getColor().equals(color)
						&& temp.isInBounds())
					captures.add(new Move(this, temp, Moves.EN_PASSANTE));
			}
		}

		return captures;
	}

	public ArrayList<Move> getAttacks() {
		ArrayList<Move> attacks = new ArrayList<Move>();
		Position temp;

		if (color == Color.WHITE) {
			// To the right
			temp = position.relative(1, 1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));

			// To the left
			temp = position.relative(1, -1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));
		} else if (color == Color.BLACK) {
			// To the right
			temp = position.relative(-1, 1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));

			// To the left
			temp = position.relative(-1, -1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));
		}

		return attacks;
	}
}
