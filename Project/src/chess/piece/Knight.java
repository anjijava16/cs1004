package chess.piece;

import java.awt.Color;
import java.util.ArrayList;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Move;
import chess.Moves;
import chess.Position;

public class Knight extends ChessPiece {

	private static final long serialVersionUID = 5035148002342200745L;

	public Knight(ChessBoard board, Color color) {
		super(board, color);
		character = color == Color.WHITE ? WHITE_KNIGHT : BLACK_KNIGHT;
		UTF8Character = color == Color.WHITE ? UTF8_WHITE_KNIGHT
				: UTF8_BLACK_KNIGHT;
		value = KNIGHT_VALUE;
	}

	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Position temp;

		for (int i = -2; i <= 2; i++) { // Positive-Up-Right
			if (i == 0)
				continue;
			for (int k = -1; k <= 1; k += 2)
				if (i == 2 || i == -2) { // Up-down
					temp = position.relative(i, k);
					if (board.getPieceAt(temp) == null && temp.isInBounds())
						moves.add(new Move(this, temp, Moves.MOVE));
				} else { // Right-left
					temp = position.relative(k, i * 2);
					if (board.getPieceAt(temp) == null && temp.isInBounds())
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

		for (int i = -2; i <= 2; i++) { // Positive-Up-Right
			if (i == 0)
				continue;
			for (int k = -1; k <= 1; k += 2)
				if (i == 2 || i == -2) { // Up-down
					temp = position.relative(i, k);
					if (board.getPieceAt(temp) != null
							&& !board.getPieceAt(temp).getColor().equals(color)
							&& temp.isInBounds())
						captures.add(new Move(this, temp, Moves.CAPTURE));
				} else { // Right-left
					temp = position.relative(k, i * 2);
					if (board.getPieceAt(temp) != null
							&& !board.getPieceAt(temp).getColor().equals(color)
							&& temp.isInBounds())
						captures.add(new Move(this, temp, Moves.CAPTURE));
				}
		}
		return captures;
	}

	public ArrayList<Move> getAttacks() {
		ArrayList<Move> attacks = new ArrayList<Move>();
		Position temp;

		for (int i = -2; i <= 2; i++) { // Positive - Up or Right
			if (i == 0)
				continue;
			for (int k = -1; k <= 1; k += 2)
				if (i % 2 == 0) { // Up-down
					temp = position.relative(i, k);
					if (temp.isInBounds())
						attacks.add(new Move(this, temp, Moves.THEORETICAL));
				} else { // Right-left
					temp = position.relative(k, i * 2);
					if (temp.isInBounds())
						attacks.add(new Move(this, temp, Moves.THEORETICAL));
				}
		}

		return attacks;
	}

}
