package chess.pieces;

import java.awt.Color;
import java.util.ArrayList;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Move;
import chess.Moves;
import chess.Position;

public class Bishop extends ChessPiece {

	private static final long serialVersionUID = 4974592418733111512L;

	public Bishop(ChessBoard board, Color color) {
		super(board, color);
		character = color == Color.WHITE ? WHITE_BISHOP : BLACK_BISHOP;
		UTF8Character =
				color == Color.WHITE ? UTF8_WHITE_BISHOP : UTF8_BLACK_BISHOP;
		value = BISHOP_VALUE;
	}

	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Position temp;

		// Going up-right.
		temp = position;
		while (true) {
			temp = temp.relative(1, 1);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

		// Going up-left
		temp = position;
		while (true) {
			temp = temp.relative(1, -1);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

		// Going down-right
		temp = position;
		while (true) {
			temp = temp.relative(-1, 1);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

		// Going down-left
		temp = position;
		while (true) {
			temp = temp.relative(-1, -1);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

		// Remove all invalid moves
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

		// Going up-right
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(1, 1);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

		// Going up-left
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(1, -1);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

		// Going down-right
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(-1, 1);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

		// Going down-left
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(-1, -1);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

		return captures;
	}

	public ArrayList<Move> getAttacks() {
		ArrayList<Move> attacks = new ArrayList<Move>();
		Position temp;

		// Going up-right.
		temp = position;
		while (true) {
			temp = temp.relative(1, 1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.MOVE));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

		// Going up-left
		temp = position;
		while (true) {
			temp = temp.relative(1, -1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.MOVE));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

		// Going down-right
		temp = position;
		while (true) {
			temp = temp.relative(-1, 1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.MOVE));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

		// Going down-left
		temp = position;
		while (true) {
			temp = temp.relative(-1, -1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.MOVE));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

		return attacks;
	}

}
