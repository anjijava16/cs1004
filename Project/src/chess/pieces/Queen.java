package chess.pieces;

import java.awt.Color;
import java.util.ArrayList;

import chess.ChessBoard;
import chess.ChessPiece;
import chess.Move;
import chess.Moves;
import chess.Position;

public class Queen extends ChessPiece {

	private static final long serialVersionUID = -9141135640712404622L;

	public Queen(ChessBoard board, Color color) {
		super(board, color);
		character = color == Color.WHITE ? WHITE_QUEEN : BLACK_QUEEN;
		UTF8Character = color == Color.WHITE ? UTF8_WHITE_QUEEN
				: UTF8_BLACK_QUEEN;
		value = QUEEN_VALUE;
	}

	public ArrayList<Move> getMoves() {
		ArrayList<Move> moves = new ArrayList<Move>();
		Position temp;

		// Going up
		temp = position;
		while (true) {
			temp = temp.relative(1, 0);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

		// Going down
		temp = position;
		while (true) {
			temp = temp.relative(-1, 0);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

		// Going right
		temp = position;
		while (true) {
			temp = temp.relative(0, 1);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

		// Going left
		temp = position;
		while (true) {
			temp = temp.relative(0, -1);
			if (board.getPieceAt(temp) == null && temp.isInBounds())
				moves.add(new Move(this, temp, Moves.MOVE));
			else
				break;
		}

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

		// Going up
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(1, 0);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

		// Going down
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(-1, 0);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

		// Going right
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(0, 1);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

		// Going left
		temp = position;
		while (temp.isInBounds()) {
			temp = temp.relative(0, -1);
			if (board.getPieceAt(temp) == null)
				continue;
			if (!board.getPieceAt(temp).getColor().equals(color))
				captures.add(new Move(this, temp, Moves.CAPTURE));
			break;
		}

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

		// Going up
		temp = position;
		while (true) {
			temp = temp.relative(1, 0);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

		// Going down
		temp = position;
		while (true) {
			temp = temp.relative(-1, 0);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

		// Going right
		temp = position;
		while (true) {
			temp = temp.relative(0, 1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

		// Going left
		temp = position;
		while (true) {
			temp = temp.relative(0, -1);
			if (temp.isInBounds())
				attacks.add(new Move(this, temp, Moves.THEORETICAL));
			else
				break;
			if (board.getPieceAt(temp) != null)
				break;
		}

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
