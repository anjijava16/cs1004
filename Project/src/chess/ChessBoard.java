package chess;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.JComponent;

import chess.piece.Bishop;
import chess.piece.King;
import chess.piece.Knight;
import chess.piece.Pawn;
import chess.piece.Queen;
import chess.piece.Rook;

/**
 * This is the chessboard of the project. It displays a chessboard with its
 * given piece arrangement, and chess can be played on it.
 *
 * @author Kai-Zhan Lee
 * @see chess.ChessGame
 * @see chess.ChessPiece
 * @see chess.ChessPieces
 * @see chess.Position
 *
 */
public class ChessBoard extends JComponent implements ChessPieces, ColumbiaBlue {

	/**
	 * Generates a ChessBoard based on the UTF-8 encoding.
	 *
	 * @param line
	 *            The UTF-8 string.
	 * @return A fully set chess board based on the given line.
	 */
	protected static ChessBoard fromUTF8String(ChessGame game, String line) {
		ChessPiece[][] pieces = new ChessPiece[8][8];
		ChessBoard board = new ChessBoard(game);
		int i = 0;
		int rank = 0;
		int file = 0;
		// Until the tab (the actual pieces)
		while (line.charAt(i) != '\t') {
			switch (line.charAt(i)) {
			case '/':
				rank++;
				// File is later increased by 1, so I have to set it to -1.
				file = -1;
				break;
			case 'B':
				pieces[rank][file] = new Bishop(board, Color.WHITE);
				break;
			case 'b':
				pieces[rank][file] = new Bishop(board, Color.BLACK);
				break;
			case 'K':
				pieces[rank][file] = new King(board, Color.WHITE);
				break;
			case 'k':
				pieces[rank][file] = new King(board, Color.BLACK);
				break;
			case 'N':
				pieces[rank][file] = new Knight(board, Color.WHITE);
				break;
			case 'n':
				pieces[rank][file] = new Knight(board, Color.BLACK);
				break;
			case 'P':
				pieces[rank][file] = new Pawn(board, Color.WHITE);
				break;
			case 'p':
				pieces[rank][file] = new Pawn(board, Color.BLACK);
				break;
			case 'Q':
				pieces[rank][file] = new Queen(board, Color.WHITE);
				break;
			case 'q':
				pieces[rank][file] = new Queen(board, Color.BLACK);
				break;
			case 'R':
				pieces[rank][file] = new Rook(board, Color.WHITE);
				break;
			case 'r':
				pieces[rank][file] = new Rook(board, Color.BLACK);
				break;
			default:
				break;
			}
			file++;
			i++;
		}
		// After the tab (the piece IDs)
		rank = file = 0;
		while (i < line.length()) {
			if (line.charAt(i) == '/') {
				rank++;
				file = 0;
				i++;
				continue;
			} else if (line.charAt(i) == '|') {
				i += 1;
				file++;
				continue;
			}
			int newID = 0;
			while (line.charAt(i) != '|') {
				newID *= 10;
				newID += Integer.parseInt(String.valueOf(line.charAt(i)));
			}
			pieces[rank][file].setID(newID);
			file++;
			i++;
		}

		board.setPieces(pieces);

		return board;
	}

	public static final boolean DEVELOPING = true;

	private static final long serialVersionUID = 6808000603055294539L;

	/**
	 * The size of each chess board square.
	 */
	public static final int SQUARE_SIZE = (int) (Toolkit.getDefaultToolkit()
			.getScreenSize().height / 10.5);

	/**
	 * The amount by which each chess piece character must be moved up to be
	 * centered.
	 */
	public static final int OFFSET_CORRECTION = SQUARE_SIZE / 5;

	/**
	 * The font that the chess board will use for the UTF-16 chess pieces.
	 */
	public static final Font FONT = new Font(
			"Helvetica",
			Font.PLAIN,
			SQUARE_SIZE);

	/**
	 * The current game that the board is being used in.
	 */
	private ChessGame game;

	/**
	 * Board is indexed by board[rank][file]. It represents the chessboard that
	 * the pieces will be on.
	 */
	private ChessPiece[][] pieces;

	/**
	 * The selected chess piece (if any)
	 */
	private ChessPiece selectedPiece;

	/**
	 * The white pieces' actual displayed color.
	 */
	public final Color WHITE = Color.WHITE;

	/**
	 * The black pieces' actual displayed color.
	 */
	public final Color BLACK = Color.BLACK;

	private ChessBoard(ChessBoard chessBoard) {
		super();

		game = chessBoard.game;

		setPreferredSize(new Dimension(8 * SQUARE_SIZE, 8 * SQUARE_SIZE));

		// Create the pieces and set them in their proper positions.
		pieces = new ChessPiece[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (chessBoard.pieces[i][j] != null) {
					pieces[i][j] = chessBoard.pieces[i][j];
					pieces[i][j].setPosition();
				}
	}

	/**
	 * Creates a fully arranged chessboard.
	 */
	public ChessBoard(ChessGame game) {
		super();

		this.game = game;

		setPreferredSize(new Dimension(8 * SQUARE_SIZE, 8 * SQUARE_SIZE));

		pieces = new ChessPiece[8][8];
		// put the pawns down
		for (int i = 0; i < 8; ++i) {
			pieces[1][i] = new Pawn(this, WHITE);
			pieces[6][i] = new Pawn(this, BLACK);
		}

		pieces[0][0] = new Rook(this, WHITE);
		pieces[0][7] = new Rook(this, WHITE);

		pieces[7][0] = new Rook(this, BLACK);
		pieces[7][7] = new Rook(this, BLACK);

		pieces[0][1] = new Knight(this, WHITE);
		pieces[0][6] = new Knight(this, WHITE);

		pieces[7][1] = new Knight(this, BLACK);
		pieces[7][6] = new Knight(this, BLACK);

		pieces[0][2] = new Bishop(this, WHITE);
		pieces[0][5] = new Bishop(this, WHITE);

		pieces[7][2] = new Bishop(this, BLACK);
		pieces[7][5] = new Bishop(this, BLACK);

		pieces[0][3] = new Queen(this, WHITE);
		pieces[7][3] = new Queen(this, BLACK);

		pieces[0][4] = new King(this, WHITE);
		pieces[7][4] = new King(this, BLACK);

		// Set the pieces in their proper positions.
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (pieces[i][j] != null)
					pieces[i][j].setPosition();
	}

	/**
	 * Moves the given piece to the given position on the ChessPiece array and
	 * updates the game's move log.
	 *
	 * @param move
	 *            A given move.
	 */
	public void execute(Move move) {
		ChessPiece piece = move.getPiece();
		Position position = move.getPosition();
		Position destination = move.getDestination();

		// Move the pieces.
		pieces[position.getRank() - 1][position.getFile() - 1] = null;
		pieces[destination.getRank() - 1][destination.getFile() - 1] = piece;

		// Special moves
		if (move.isEnPassante())
			executeEnPassante(move);
		else if (move.isKingCastle())
			executeKingCastle(move);
		else if (move.isQueenCastle())
			executeQueenCastle(move);
		else if (move.isPromotion())
			executePromotion(move);

		// Update the displayed board.
		repaint();

		// Update the moves log.
		game.addMove(move);
		game.gameLog.update(game.getMoves());
	}

	/**
	 * @param color
	 *            A given color.
	 * @return The king of the given color.
	 */
	public King getKing(Color color) {
		ChessPiece piece;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				piece = getPieceAt(new Position(i + 1, j + 1));
				if (piece instanceof King && piece.getColor().equals(color))
					return (King) piece;
			}
		return null;
	}

	/**
	 * Finds all pieces of a given color.
	 *
	 * @param color
	 *            The given color.
	 * @return A vector of all of the pieces of the given color.
	 */
	public ArrayList<ChessPiece> getPieces(Color color) {
		ArrayList<ChessPiece> chessPieces = new ArrayList<ChessPiece>();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (pieces[i][j] != null &&
						pieces[i][j].getColor().equals(color))
					chessPieces.add(pieces[i][j]);
		return chessPieces;
	}

	/**
	 * An accessor method for game.
	 *
	 * @return The game in which this board is being used.
	 */
	public ChessGame getGame() {
		return game;
	}

	/**
	 * Undoes the given move.
	 *
	 * @param move
	 *            A given move.
	 * @return Whether it is possible to undo the move.
	 */
	protected boolean undoMove(Move move) {
		ArrayList<Move> moves = game.getMoves();
		if (!moves.get(moves.size() - 1).equals(move))
			return false;

		// Move the pieces
		ChessPiece piece = move.getPiece();
		int beginRank = move.getPosition().getRank();
		int beginFile = move.getPosition().getFile();
		int endRank = move.getDestination().getRank();
		int endFile = move.getDestination().getFile();
		pieces[beginRank - 1][beginFile - 1] = piece;
		pieces[endRank - 1][endFile - 1] = null;

		// Special moves
		if (move.isEnPassante())
			pieces[beginRank - 1][endFile - 1] = move.getCapturedPiece();
		else if (move.isCapture())
			pieces[endRank - 1][endFile - 1] = move.getCapturedPiece();
		else if (move.isKingCastle())
			pieces[endRank - 1][7] = move.getCastledRook();
		else if (move.isQueenCastle())
			pieces[endRank - 1][0] = move.getCastledRook();
		else if (move.isInvalid())
			return false;

		return true;
	}

	/**
	 * An method that gets an element in the board array.
	 *
	 * @param position
	 *            A given position.
	 * @return The element at that position.
	 */
	public ChessPiece getPieceAt(Position position) {
		// I have to subtract 1 from rank and file because getRank() and
		// getFile() return integers from 1 to 8.
		if (!position.isInBounds())
			return null;
		return pieces[position.getRank() - 1][position.getFile() - 1];
	}

	/**
	 * An accessor method for board.
	 *
	 * @return The board array.
	 */
	public ChessPiece[][] getPieces() {
		return pieces;
	}

	/**
	 * Gets the position of a given piece, using its ID.
	 *
	 * @param piece
	 *            A given piece.
	 * @return The position of the given piece.
	 */
	public Position getPositionOf(ChessPiece piece) {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (pieces[i][j] != null)
					if (pieces[i][j].equals(piece))
						return new Position(i + 1, j + 1);
		return null;
	}

	/**
	 * An accessor method for selectedPiece.
	 *
	 * @return The currently selected chess piece.
	 */
	public ChessPiece getSelectedPiece() {
		return selectedPiece;
	}

	public void paintComponent(Graphics g) {
		boolean isWhite = true;
		g.setFont(FONT);

		// Print the chessboard squares.
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (isWhite)
					g.setColor(Color.LIGHT_GRAY);
				else
					g.setColor(Color.DARK_GRAY);
				isWhite = !isWhite;
				g.fillRect(
						SQUARE_SIZE * i,
						SQUARE_SIZE * j,
						SQUARE_SIZE,
						SQUARE_SIZE);
			}
			isWhite = !isWhite;
		}

		// Draw the pieces (not including the selected piece, if any)
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				if (pieces[i][j] == null || pieces[i][j].equals(selectedPiece))
					continue;
				pieces[i][j].setPosition();
				pieces[i][j].paintComponent(g);
			}

		// Draw the possible moves for the selected piece.
		g.setColor(COLUMBIA_BLUE);
		if (selectedPiece != null && game.showMoves) {
			for (Move move : selectedPiece.getMoves()) {
				Position end = move.getDestination();
				if (move.isEnPassante()) {
					g.fillOval(
							SQUARE_SIZE * (end.getFile() - 1) + SQUARE_SIZE /
									2 - SQUARE_SIZE / 10,
							SQUARE_SIZE * (2 * 4 - end.getRank()) +
									SQUARE_SIZE / 2 - SQUARE_SIZE / 10,
							SQUARE_SIZE / 5,
							SQUARE_SIZE / 5);
					if (move.getPiece().getColor().equals(WHITE))
						getPieceAt(end.relative(-1, 0)).paint(g);
					else if (move.getPiece().getColor().equals(BLACK))
						getPieceAt(end.relative(1, 0)).paint(g);
				} else if (move.isCapture())
					getPieceAt(end).paint(g);
				else
					g.fillOval(
							SQUARE_SIZE * (end.getFile() - 1) + SQUARE_SIZE /
									2 - SQUARE_SIZE / 10,
							SQUARE_SIZE * (2 * 4 - end.getRank()) +
									SQUARE_SIZE / 2 - SQUARE_SIZE / 10,
							SQUARE_SIZE / 5,
							SQUARE_SIZE / 5);
			}

			// The selected piece is drawn after the possible moves to make it
			// display above the circles/other pieces.
			selectedPiece.paintComponent(g);
		}
	}

	/**
	 * A mutator method for selectedPiece. Sets the selected piece to the
	 * parameter.
	 *
	 * @param piece
	 *            A given chess piece.
	 */
	public void setSelectedPiece(ChessPiece piece) {
		selectedPiece = piece;
	}

	// TODO Remove.
	public String toIndentedString(int indents) {
		String text = "";
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < indents; j++)
				text += ' ';
			text += i + 1;
			for (int j = 0; j < 8; j++)
				text += pieces[i][j] == null ? ' ' : pieces[i][j].getUTF8Char();
			text += '\n';
		}
		for (int i = 0; i < indents; i++)
			text += ' ';
		text += " abcdefgh";
		return text;
	}

	/**
	 * Converts this board to an easily readable UTF-8 string.
	 *
	 * @return This board, converted to a UTF-8 string.
	 */
	public String toSaveString() {
		String text = "";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				if (pieces[i][j] == null)
					if (i != 0 && '1' <= text.charAt(text.length() - 1) &&
							text.charAt(text.length() - 1) <= '7')
						text =
								text.substring(0, text.length() - 1) +
										(char) (text.charAt(text.length() - 1) + 1);
					else
						text += '1';
				else
					text += pieces[i][j].getUTF8Char();
			text += '/';
		}
		text += '\t';
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++)
				if (pieces[i][j] == null)
					text += "|";
				else
					text += pieces[i][j].getID() + "|";
			text += '/';
		}
		return text;
	}

	public String toString() {
		String text = "";
		for (int i = 7; i >= 0; i--) {
			text += i + 1;
			for (int j = 0; j < 8; j++)
				text += pieces[i][j] == null ? ' ' : pieces[i][j].getUTF8Char();
			text += '\n';
		}
		text += " abcdefgh";
		return text;
	}

	/**
	 * Makes the board execute an en passante pawn move.
	 */
	protected void executeEnPassante(Move move) {
		// If the move is an en passante move, remove the other pawn.
		Position position = move.getDestination();
		Color pieceColor = move.getPiece().getColor();
		int rank = position.getRank() - 1;
		int file = position.getFile() - 1;
		if (move.isEnPassante())
			if (pieceColor.equals(WHITE))
				pieces[rank - 1][file] = null;
			else if (pieceColor.equals(BLACK))
				pieces[rank + 1][file] = null;

		// Update the chess board.
		repaint();
	}

	/**
	 * Makes the board execute a king-side castle move.
	 */
	protected void executeKingCastle(Move move) {
		// If the move is a king-castle, move the rook to its proper location.
		int rank = move.getDestination().getRank();
		if (move.isKingCastle()) {
			pieces[rank - 1][5] = pieces[rank - 1][7];
			pieces[rank - 1][7] = null;
			try {
				pieces[rank - 1][5].setPosition();
			} catch (NullPointerException npe) {}
		}

		// Update the chess board
		repaint();
	}

	/**
	 * Makes the board execute a pawn promotion.
	 */
	protected void executePromotion(Move move) {
		int rank = move.getDestination().getRank();
		int file = move.getDestination().getFile();
		ChessPiece promotedPiece = move.getPromotedPiece();
		promotedPiece.setID(move.getPiece().getID());

		// Set the piece at the board location.
		pieces[rank - 1][file - 1] = promotedPiece;

		// Initialize the promoted piece's position.
		try {
			promotedPiece.setPosition();
		} catch (NullPointerException npe) {}

		// Update the chess board.
		repaint();
	}

	/**
	 * Makes the board execute a queen-side castle move.
	 */
	protected void executeQueenCastle(Move move) {
		// If the move is a king-castle, move the rook to its proper location.
		int rank = move.getDestination().getRank();
		if (move.isQueenCastle()) {
			pieces[rank - 1][3] = pieces[rank - 1][0];
			pieces[rank - 1][0] = null;
			try {
				pieces[rank - 1][3].setPosition();
			} catch (NullPointerException npe) {}
		}

		// Update the chess board.
		repaint();
	}

	/**
	 * A mutator method for game. It changes the board's current game. Used only
	 * for loading games (to make sure different boards' games don't intersect
	 * somehow).
	 *
	 * @param game
	 *            A given game to switch to.
	 */
	protected void setGame(ChessGame game) {
		this.game = game;
	}

	/**
	 * Moves the given piece to the given position on the ChessPiece array, but
	 * does NOT update the game log. Updates the pieces with the new board.
	 *
	 * @param move
	 *            A given move.
	 * @return The new chessboard after the move.
	 */
	protected ChessBoard unreportedMove(Move move) {
		ChessBoard board = new ChessBoard(this);
		board.game = new ChessGame(false);

		// Move the piece(s).
		ChessPiece piece = move.getPiece();
		Position position = move.getPosition();
		int endRank = move.getDestination().getRank();
		int endFile = move.getDestination().getFile();
		board.pieces[position.getRank() - 1][position.getFile() - 1] = null;
		board.pieces[endRank - 1][endFile - 1] = piece;
		board.pieces[endRank - 1][endFile - 1].setPosition();

		// Reset each piece (to sever all ties to this board).
		// TODO Make this work.
		int ID;
		Color color;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				// To avoid a NullPointerException
				if (board.pieces[i][j] == null)
					continue;

				ID = board.pieces[i][j].getID();
				color = board.pieces[i][j].getColor();

				// Reset the piece
				if (board.pieces[i][j] instanceof Bishop)
					board.pieces[i][j] = new Bishop(board, color);
				else if (board.pieces[i][j] instanceof King)
					board.pieces[i][j] = new King(board, color);
				else if (board.pieces[i][j] instanceof Knight)
					board.pieces[i][j] = new Knight(board, color);
				else if (board.pieces[i][j] instanceof Pawn)
					board.pieces[i][j] = new Pawn(board, color);
				else if (board.pieces[i][j] instanceof Queen)
					board.pieces[i][j] = new Queen(board, color);
				else if (board.pieces[i][j] instanceof Rook)
					board.pieces[i][j] = new Rook(board, color);

				// Initialize the pieces.
				board.pieces[i][j].setID(ID);
				board.pieces[i][j].setPosition();
			}

		// Special moves
		if (move.isEnPassante())
			board.executeEnPassante(move);
		else if (move.isKingCastle())
			board.executeKingCastle(move);
		else if (move.isQueenCastle())
			board.executeQueenCastle(move);
		else if (move.isPromotion())
			board.executePromotion(move);

		// Return the modified chess board.
		return board;
	}

	/**
	 * @param color
	 *            A given color.
	 * @return The color of the opposing pieces of the pieces of the given
	 *         color.
	 */
	public Color oppositeColor(Color color) {
		return color.equals(WHITE) ? BLACK : WHITE;
	}

	/**
	 * Sets up a board with a given piece setup.
	 *
	 * @param board
	 *            A given piece setup.
	 */
	private void setPieces(ChessPiece[][] board) {
		pieces = board;
	}
}
