


import java.awt.*;

import javax.swing.*;

/**
 * This is the chessboard of the project. It displays a chessboard with properly
 * arranged pieces.
 * 
 * @author Kai-Zhan Lee
 * 
 */
public class ChessBoard extends JComponent implements ChessPieces {

	private static final long serialVersionUID = 6808000603055294539L;

	/**
	 * Board is indexed by board[rank][file]. It represents the chessboard that
	 * the pieces will be on.
	 */
	private char[][] board;
	
	/**
	 * The size of each chess board square.
	 */
	public static final int SQUARE_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height/10;

	/**
	 * The amount by which each chess piece character must be moved up to be centered.
	 */
	public static final int OFFSET_CORRECTION = SQUARE_SIZE/5;
	
	/**
	 * The font that the chess board will use for the UTF-16 chess pieces.
	 */
	public static final String FONT_NAME = "Helvetica";

	/**
	 * Creates a fully arranged chessboard.
	 */
	public ChessBoard() {
		setPreferredSize(new Dimension(8 * SQUARE_SIZE, 8 * SQUARE_SIZE));
		board = new char[8][8];
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				board[i][j] = ' ';

		// put the pawns down
		for (int i = 0; i < 8; ++i) {
			board[1][i] = WHITE_PAWN;
			board[6][i] = BLACK_PAWN;
		}

		board[0][0] = board[0][7] = WHITE_ROOK;
		board[7][0] = board[7][7] = BLACK_ROOK;

		board[0][1] = board[0][6] = WHITE_KNIGHT;
		board[7][1] = board[7][6] = BLACK_KNIGHT;

		board[0][2] = board[0][5] = WHITE_BISHOP;
		board[7][2] = board[7][5] = BLACK_BISHOP;

		board[0][3] = WHITE_QUEEN;
		board[7][3] = BLACK_QUEEN;

		board[0][4] = WHITE_KING;
		board[7][4] = BLACK_KING;
	}

	public void paintComponent(Graphics g) {
		boolean isWhite = true;
		g.setFont(new Font(FONT_NAME, SQUARE_SIZE, SQUARE_SIZE));
		for (int i = 0; i < 8; i++) {
			// Print the chessboard.
			for (int j = 0; j < 8; j++) {
				if (isWhite)
					g.setColor(Color.LIGHT_GRAY);
				else
					g.setColor(Color.DARK_GRAY);
				isWhite = !isWhite;
				g.fillRect(SQUARE_SIZE * i, SQUARE_SIZE * j, SQUARE_SIZE, SQUARE_SIZE);
			}
			isWhite = !isWhite;
		}
		// Draw the pieces
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if ('\u2654' <= board[i][j] && board[i][j] <= '\u2659')
					g.setColor(Color.WHITE);
				else
					g.setColor(Color.BLACK);
				//The y coordinate is formatted like this to make sure the ranks and files are properly displayed.
				g.drawChars(board[i], j, 1, SQUARE_SIZE * j, SQUARE_SIZE * (2 * 4 - i) - OFFSET_CORRECTION);
			}
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		ChessBoard board = new ChessBoard();

		frame.setTitle("Chess Board");
		frame.setBackground(Color.WHITE);
		
		frame.setLayout(new BorderLayout());
		frame.add(board, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}