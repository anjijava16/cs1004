package chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;

import javax.swing.JComponent;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;

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

		// The opponent king
		King king = piece.board.unreportedMove(this).getKing(oppositeColor);

		if (king == null)
			modifier = NONE;
		else if (king.isCheckMated())
			modifier = CHECKMATE;
		else if (king.isDoubleChecked())
			modifier = DOUBLE_CHECK;
		else if (king.isChecked())
			modifier = CHECK;
		else
			modifier = NONE;
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
	 * @return The piece to be promoted, if any.
	 */
	public ChessPiece getPromotedPiece() {
		return promotedPiece;
	}

	/**
	 * Mutator method for promotedPiece.
	 *
	 * @param promotedPiece
	 *            The piece to be promoted.
	 */
	protected void setPromotedPiece(ChessPiece promotedPiece) {
		this.promotedPiece = promotedPiece;
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
	 * @return The rook being castled in this move, if any.
	 */
	public Rook getCastledRook() {
		return castledRook;
	}

	/**
	 * Accessor method for capturedPiece.
	 *
	 * @return The piece being captured in this move, if any.
	 */
	public ChessPiece getCapturedPiece() {
		return capturedPiece;
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
		return CHECK.equals(modifier) || DOUBLE_CHECK.equals(modifier);
	}

	/**
	 * @return Whether this move will double check the opponent.
	 */
	public boolean doesDoubleCheck() {
		return DOUBLE_CHECK.equals(modifier);
	}

	/**
	 * @return Whether this move will checkmate the opponent.
	 */
	public boolean doesCheckMate() {
		return CHECKMATE.equals(modifier);
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
		piece.board.undoMove(this);
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
		} else if (isKingCastle())
			message = "0 - 0";
		else if (isQueenCastle())
			message = "0 - 0 - 0";
		else if (isInvalid())
			message += type;
		else { // Just a regular move.
			message += piece;
			message += position;
			message += type;
			message += destination;
		}

		message += modifier;
		return message;
	}

	/**
	 * @return A game log type move string.
	 */
	public String toGameLogString() {
		String message = "";

		// The moved piece
		if (!piece.getUTF8Char().toUpperCase().equals("P"))
			message += piece.getUTF8Char().toUpperCase();

		// The piece's position
		message += position;

		// The type = move
		message += type;
		// The type of move
		if (isEnPassante()) {
			message += " ";
			message += destination;
		} else if (isPromotion()) {
			message += destination;
			message += "=" + promotedPiece.getUTF8Char();
		} else if (isCapture())
			message += destination;
		else if (isKingCastle())
			message = "0 - 0";
		else if (isQueenCastle())
			message = "0 - 0 - 0";
		else if (isInvalid())
			message += destination;
		else
			message += destination;

		// Add on the modifier
		message += modifier;

		return message;
	}

	/**
	 * @return A game log type move string.
	 */
	public String toSaveString() {
		String message = "";

		message += piece.getID() + ":";
		message += piece.getUTF8Char() + ":";
		message += position + ":";
		message += type + ":";
		if (capturedPiece != null) {
			message += capturedPiece.getID() + ":";
			message += capturedPiece.getUTF8Char() + ":";
		} else {
			message += ":";
			message += ":";
		}
		message += destination + ":";
		if (promotedPiece != null) {
			message += promotedPiece.getID() + ":";
			message += promotedPiece.getUTF8Char() + ":";
		} else {
			message += ":";
			message += ":";
		}
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

	public void display() {
		if (isInvalid())
			return;
		JComponent component = new JComponent() {
			private static final long serialVersionUID = -4492037153597816747L;

			public void paintComponent(Graphics g) {
				Graphics2D g2 = (Graphics2D) g;
				g2.setBackground(Color.BLUE);
				// The two points at the tail of the arrow (.25 units away).
				int square = ChessBoard.SQUARE_SIZE;
				int x1 = (int) ((position.getFile() - 0.5) * square);
				int y1 = (int) ((position.getRank() - 0.5) * square);
				int x2 = (int) ((destination.getRank() - 0.5) * square);
				int y2 = (int) ((destination.getFile() - 0.5) * square);
				double m = (y1 - y2) / (x1 - x2);
				double d =
						Math.sqrt(Math.pow(y1 - y2, 2) + Math.pow(x1 - x2, 2));

				// The points go around.
				Point[] arrow = new Point[7];
				arrow[0] =
						new Point(
								(int) (0.25 * m / Math.sqrt(m * m + 1)) + x1,
								(int) (0.25 / Math.sqrt(m * m + 1)) + y1);
				arrow[1] =
						new Point(
								(int) (0.25 * m / Math.sqrt(m * m + 1) + 0.62 *
										d * m / Math.sqrt(m * m + 1) + x1),
								(int) (0.25 / Math.sqrt(m * m + 1) + 0.62 * d /
										Math.sqrt(m * m + 1) + y1));
				arrow[2] =
						new Point((int) (0.5 * m / Math.sqrt(m * m + 1) + 0.62 *
								d * m / Math.sqrt(m * m + 1) + x1), (int) (0.5 /
								Math.sqrt(m * m + 1) + 0.62 * d /
								Math.sqrt(m * m + 1) + y1));
				arrow[3] = new Point(x2, y2);
				arrow[4] =
						new Point(
								(int) (-0.5 * m / Math.sqrt(m * m + 1) + 0.62 *
										d * m / Math.sqrt(m * m + 1) + x1),
								(int) (-0.5 / Math.sqrt(m * m + 1) + 0.62 * d /
										Math.sqrt(m * m + 1) + y1));
				arrow[5] =
						new Point(
								(int) (-0.25 * m / Math.sqrt(m * m + 1) + 0.62 *
										d * m / Math.sqrt(m * m + 1) + x1),
								(int) (-0.25 / Math.sqrt(m * m + 1) + 0.62 * d /
										Math.sqrt(m * m + 1) + y1));
				arrow[6] =
						new Point(
								(int) (-0.25 * m / Math.sqrt(m * m + 1)) + x1,
								(int) (-0.25 / Math.sqrt(m * m + 1)) + y1);
				Polygon polygon = new Polygon();
				for (Point point : arrow)
					polygon.addPoint(point.x, point.y);
				g2.draw(polygon);
			}
		};

		piece.board.getGame().display(component);
	}

	/**
	 * Converts a given string into a move.
	 *
	 * @param board
	 *            The given board that the pieces are on.
	 * @param line
	 *            A given string.
	 * @return The move corresponding to the given string.
	 */
	public static Move fromUTF8String(ChessBoard board, String line) {
		String[] information = line.split(":");
		int pieceID = Integer.parseInt(information[0]);
		ChessPiece piece = null;
		switch (information[1].charAt(0)) {
		case 'B':
			piece = new Bishop(board, Color.WHITE);
			break;
		case 'b':
			piece = new Bishop(board, Color.BLACK);
			break;
		case 'K':
			piece = new King(board, Color.WHITE);
			break;
		case 'k':
			piece = new King(board, Color.BLACK);
			break;
		case 'N':
			piece = new Knight(board, Color.WHITE);
			break;
		case 'n':
			piece = new Knight(board, Color.BLACK);
			break;
		case 'P':
			piece = new Pawn(board, Color.WHITE);
			break;
		case 'p':
			piece = new Pawn(board, Color.BLACK);
			break;
		case 'Q':
			piece = new Queen(board, Color.WHITE);
			break;
		case 'q':
			piece = new Queen(board, Color.BLACK);
			break;
		case 'R':
			piece = new Rook(board, Color.WHITE);
			break;
		case 'r':
			piece = new Rook(board, Color.BLACK);
			break;
		default:
			break;
		}
		Position position =
				new Position(
						information[2].charAt(0) - 'a' + 1,
						information[2].charAt(1) - '0');
		String type = information[3];
		ChessPiece capturedPiece = null;
		switch (information[4].charAt(0)) {
		case 'B':
			capturedPiece = new Bishop(board, Color.WHITE);
			break;
		case 'b':
			capturedPiece = new Bishop(board, Color.BLACK);
			break;
		case 'K':
			capturedPiece = new King(board, Color.WHITE);
			break;
		case 'k':
			capturedPiece = new King(board, Color.BLACK);
			break;
		case 'N':
			capturedPiece = new Knight(board, Color.WHITE);
			break;
		case 'n':
			capturedPiece = new Knight(board, Color.BLACK);
			break;
		case 'P':
			capturedPiece = new Pawn(board, Color.WHITE);
			break;
		case 'p':
			capturedPiece = new Pawn(board, Color.BLACK);
			break;
		case 'Q':
			capturedPiece = new Queen(board, Color.WHITE);
			break;
		case 'q':
			capturedPiece = new Queen(board, Color.BLACK);
			break;
		case 'R':
			capturedPiece = new Rook(board, Color.WHITE);
			break;
		case 'r':
			capturedPiece = new Rook(board, Color.BLACK);
			break;
		default:
			break;
		}
		int capturedPieceID = Integer.parseInt(information[5]);
		Position destination =
				new Position(
						information[6].charAt(0) - 'a' + 1,
						information[6].charAt(1) - '0');
		int promotedPieceID = Integer.parseInt(information[7]);
		ChessPiece promotedPiece = null;
		switch (information[8].charAt(0)) {
		case 'B':
			capturedPiece = new Bishop(board, Color.WHITE);
			break;
		case 'b':
			capturedPiece = new Bishop(board, Color.BLACK);
			break;
		case 'K':
			capturedPiece = new King(board, Color.WHITE);
			break;
		case 'k':
			capturedPiece = new King(board, Color.BLACK);
			break;
		case 'N':
			capturedPiece = new Knight(board, Color.WHITE);
			break;
		case 'n':
			capturedPiece = new Knight(board, Color.BLACK);
			break;
		case 'P':
			capturedPiece = new Pawn(board, Color.WHITE);
			break;
		case 'p':
			capturedPiece = new Pawn(board, Color.BLACK);
			break;
		case 'Q':
			capturedPiece = new Queen(board, Color.WHITE);
			break;
		case 'q':
			capturedPiece = new Queen(board, Color.BLACK);
			break;
		case 'R':
			capturedPiece = new Rook(board, Color.WHITE);
			break;
		case 'r':
			capturedPiece = new Rook(board, Color.BLACK);
			break;
		default:
			break;
		}
		String modifier = information[9];

		return new Move(
				pieceID,
				piece,
				position,
				type,
				capturedPieceID,
				capturedPiece,
				destination,
				promotedPieceID,
				promotedPiece,
				modifier);
	}

	private Move(
			int pieceID,
			ChessPiece piece,
			Position position,
			String type,
			int capturedPieceID,
			ChessPiece capturedPiece,
			Position destination,
			int promotedPieceID,
			ChessPiece promotedPiece,
			String modifier) {
		this.piece = piece;
		this.piece.setID(pieceID);
		this.position = position;
		this.type = type;
		this.destination = destination;
		if (capturedPiece != null) {
			this.capturedPiece = capturedPiece;
			this.capturedPiece.setID(capturedPieceID);
		}

	}
}
