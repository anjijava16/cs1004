package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.KeyStroke;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Queen;
import chess.pieces.Rook;

/**
 * ChessGame is the top-level game class for chess games.
 *
 * @author Kai-Zhan Lee
 * @see chess.ChessBoard
 * @see chess.ColumbiaBlue
 * @see chess.Move
 */
public class ChessGame implements ColumbiaBlue {

	/**
	 * The number of chess games currently in use.
	 */
	private static int chessGames = 0;

	/**
	 * The chess board that the game is being played on.
	 */
	private ChessBoard chessBoard;

	/**
	 * The frame that will be displayed.
	 */
	private JFrame frame;

	/**
	 * The menu bar to be displayed.
	 */
	private JMenuBar menuBar = getJMenuBar();

	/**
	 * @return The menu bar to be displayed.
	 */
	private JMenuBar getJMenuBar() {
		// @formatter:off
		JMenuBar menuBar = new JMenuBar();
		// The menus on the menu bar.
		JMenu[] menus = {
				new JMenu("Chess"),
				new JMenu("File"),
				new JMenu("Game"),
				new JMenu("Moves")
		};
		menus[0].setFont(new Font(menus[0].getFont().getName(), Font.BOLD, menus[0].getFont().getSize()));
		// The menu items in the menus.
		JMenuItem[][] menuItems = {
				// The "Chess" menu items.
				{
					new JMenuItem("Quit") // ctrl-q
				},
				// The "File" menu items.
				{
					new JMenuItem("New Game"), // ctrl-n
					new JMenuItem("Open Game"), // ctrl-o
					new JMenuItem("Close"), // ctrl-w
					new JMenuItem("Save Game"), // ctrl-s
					new JMenuItem("Save As") // ctrl-S
				},
				// The "Game" menu items.
				{
					new JMenuItem("Show Hint"), // ctrl-h
					new JMenuItem("Hide Possible Moves") // ctrl-p
				},
				// The "Moves" menu items.
				{
					new JMenuItem("Undo Move"), // ctrl-z
					new JMenuItem("Show Last Move"), // ctrl-m
					new JMenuItem("Show Game Log"), // ctrl-l
				}
		};
		int[][] keyCodes = {
				// The "Chess" menu items' key codes for action listeners.
				{
					KeyEvent.VK_Q
				},
				// The "File" menu items' key codes for action listeners.
				{
					KeyEvent.VK_N,
					KeyEvent.VK_O,
					KeyEvent.VK_W,
					KeyEvent.VK_S,
					KeyEvent.VK_S // Must be capital S.
				},
				// The "Game" menu items's key codes for action listeners.
				{
					KeyEvent.VK_H,
					KeyEvent.VK_P
				},
				// The "Moves" menu items' key codes for action listeners.
				{
					KeyEvent.VK_Z,
					KeyEvent.VK_M,
					KeyEvent.VK_L
				}
		};
		ActionListener[][] menuItemActionListeners = {
				// The "Chess" menu items' action listeners.
				{
					new ActionListener() { // Quit
						public void actionPerformed(ActionEvent ae) {
							System.exit(0);
						}
					}
				},
				// The "File" menu items' action listeners.
				{
					new ActionListener() { // New game
						public void actionPerformed(ActionEvent ae) {
							newGame();
						}
					},
					new ActionListener() { // Open game
						public void actionPerformed(ActionEvent ae) {
							openGame();
						}
					},
					new ActionListener() { // Close window
						public void actionPerformed(ActionEvent ae) {
							closeWindow();
						}
					},
					new ActionListener() { // Save game
						public void actionPerformed(ActionEvent ae) {
							saveGame();
						}
					},
					new ActionListener() { // Save as
						public void actionPerformed(ActionEvent ae) {
							saveGameAs();
						}
					}
				},
				// The "Game" menu items.
				{

					new ActionListener() { // Show hint
						public void actionPerformed(ActionEvent ae) {
							showHint();
						}
					},
					new ActionListener() { // Show/don't show possible moves
						public void actionPerformed(ActionEvent ae) {
							toggleShowMoves();
						}
					}
				},
				// The "Moves" menu items' action listeners.
				{
					new ActionListener() { // Undo move
						public void actionPerformed(ActionEvent ae) {
							undoMove();
						}
					},
					new ActionListener() { // Show last move
						public void actionPerformed(ActionEvent ae) {
							showLastMove();
						}
					},
					new ActionListener() { // Show/hide game log
						public void actionPerformed(ActionEvent ae) {
							toggleGameLog();
						}
					},
				}
		};
		boolean[][] menuItemsEnabled = {
				{
					true
				},
				{
					true,
					true,
					true,
					false,
					false
				},
				{
					false,
					false
				},
				{
					false,
					false,
					false
				}
		};
		int menuMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
		// @formatter:on
		for (int i = 0; i < menuItems.length; i++) {
			for (int j = 0; j < menuItems[i].length; j++) {
				menus[i].add(menuItems[i][j]);
				menuItems[i][j]
						.addActionListener(menuItemActionListeners[i][j]);
				menuItems[i][j].setAccelerator(KeyStroke.getKeyStroke(
						keyCodes[i][j],
						menuMask));
				if (i == 1 && j == 4) // Gets a capital ctrl-S for save as.
					menuItems[i][j].setAccelerator(KeyStroke.getKeyStroke(
							keyCodes[i][j],
							menuMask + InputEvent.SHIFT_DOWN_MASK));
				menuItems[i][j].setEnabled(menuItemsEnabled[i][j]);
			}
			menus[i].setBackground(COLUMBIA_BLUE);
			menuBar.add(menus[i]);
		}
		menuBar.setName("Chess");
		menuBar.setBackground(COLUMBIA_BLUE);
		menuBar.validate();
		return menuBar;
	}

	/**
	 * Opens a new chess game in a new window.
	 */
	private void newGame() {
		new ChessGame().play();
	}

	/**
	 * Opens a previously saved game.
	 */
	private void openGame() {
		// Create a file chooser for the user to choose a file.
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Open");

		// Filter out all files except for those with extensions of ".chs".
		FileNameExtensionFilter filter =
				new FileNameExtensionFilter(null, "chs");
		fileChooser.setFileFilter(filter);

		// Get the file.
		int result;
		do {
			result = fileChooser.showOpenDialog(new JFrame());
			if (result == JFileChooser.CANCEL_OPTION)
				return;
		} while (result != JFileChooser.APPROVE_OPTION ||
				fileChooser.getSelectedFile().isDirectory());
		File openFile = fileChooser.getSelectedFile();

		// Create a BufferedReader to read in the file.
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(openFile));
		} catch (IOException ioe) {}

		// Open up the old game.
		// Need to get:
		// gameSetting (who's playing whom)
		// chessBoard (current board arrangement)
		// moves (moves that have occured so far)
		// minimaxSteps (how many steps of minimax to use)
		int gameSetting = 0;
		ChessBoard chessBoard = null;
		ArrayList<Move> moves = null;
		int minimaxSteps = 0;
		try {
			// Read in the game setting.
			gameSetting = Integer.parseInt(reader.readLine());
			// Read in the chess board.
			chessBoard = ChessBoard.fromUTF8String(this, reader.readLine());
			// Read in the moves.
			moves = new ArrayList<Move>();
			int i = 0;
			String line = reader.readLine();
			while (i < line.length()) {
				String moveString = "";
				while (line.charAt(i) != '\t') {
					moveString += line.charAt(i);
					i++;
				}
				moves.add(Move.fromUTF8String(moveString));
				i++;
			}
			// Read in the number of minimax steps per move.
			minimaxSteps = Integer.parseInt(reader.readLine());
		} catch (IOException e) {}
		new ChessGame(openFile, gameSetting, chessBoard, moves, minimaxSteps);
	}

	/**
	 * Closes the current chess game window.
	 */
	private void closeWindow() {
		frame.dispose();
		chessGames--;
		if (chessGames == 0)
			System.exit(0);
	}

	/**
	 * The file to write to when saving the game.
	 */
	private File saveFile;

	/**
	 * Saves the current game to the given file. Enables "Save As".
	 */
	private void saveGame() {
		if (saveFile == null) {
			// Enable "Save As"
			menuBar.getMenu(1).getItem(4).setEnabled(true);

			// Create a file chooser for the user to choose a file.
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Save");

			// Get the file name and path.
			int result;
			do {
				result = fileChooser.showSaveDialog(new JFrame());
				if (result == JFileChooser.CANCEL_OPTION)
					return;
			} while (result != JFileChooser.APPROVE_OPTION ||
					fileChooser.getSelectedFile().isDirectory());

			// Change the file's extension to ".chs".
			saveFile = withExtension(fileChooser.getSelectedFile(), "chs");

			// Create an PrintWriter to write the game out.
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(saveFile);
			} catch (FileNotFoundException fnfe) {
				System.err.println("Error: File not found. Returning...");
				return;
			}

			writer.println(gameSetting);
			writer.println(chessBoard.toSaveString());
			for (Move move : moves)
				writer.print(move.getPiece().getID() + "|" +
						move.getPosition() + move.getType() +
						move.getDestination() + '\t');
			writer.println();
			writer.println(minimaxDepth);

			// Reset the frame title.
			String saveFileName = saveFile.getName();
			frame.setTitle(saveFileName.substring(0, saveFileName.length() - 4) +
					" - " + settingsButtons[gameSetting].getText());
			writer.close();
		} else {
			// Get rid of the save file's current contents.
			saveFile.delete();

			// Create an PrintWriter to write the game out.
			PrintWriter writer = null;
			try {
				writer = new PrintWriter(saveFile);
			} catch (FileNotFoundException fnfe) {
				System.err.println("Error: File not found. Returning...");
				return;
			}

			writer.println(gameSetting);
			writer.println(chessBoard.toSaveString());
			for (Move move : moves)
				writer.print(move.getPiece().getID() + "|" +
						move.getPosition() + move.getType() +
						move.getDestination() + '\t');
			writer.println();
			writer.println(minimaxDepth);

			// Reset the frame title.
			String saveFileName = saveFile.getName();
			frame.setTitle(saveFileName.substring(0, saveFileName.length() - 4) +
					" - " + settingsButtons[gameSetting].getText());
			writer.close();
		}
	}

	/**
	 * Saves the current game with a different name and/or path than already
	 * given (only callable if the game has been saved, saveGame() has been
	 * called).
	 */
	private void saveGameAs() {
		// Create a file chooser for the user to choose a file.
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Save As");

		// Get the file name and path.
		int result;
		do {
			result = fileChooser.showSaveDialog(new JFrame());
			if (result == JFileChooser.CANCEL_OPTION)
				return;
		} while (result != JFileChooser.APPROVE_OPTION ||
				fileChooser.getSelectedFile().isDirectory());

		// Change the file's extension to ".chs".
		saveFile = withExtension(fileChooser.getSelectedFile(), "chs");

		// Create an ObjectOutputStream to write the game out.
		PrintWriter writer = null;

		try {
			writer = new PrintWriter(new FileWriter(saveFile), true);
		} catch (IOException ioe) {}

		writer.println(gameSetting);
		writer.println(chessBoard.toSaveString());
		for (Move move : moves)
			writer.print(move.getPiece().getID() + "|" + move.getPosition() +
					"|" + move.getType() + "|" + move.getDestination() + '\t');
		writer.println();
		writer.println(minimaxDepth);

		// Reset the frame title.
		String saveFileName = saveFile.getName();
		frame.setTitle(saveFileName.substring(0, saveFileName.length() - 4) +
				" - " + settingsButtons[gameSetting].getText());
	}

	/**
	 * A convenience method that returns a new file with the given file's name,
	 * stripped of any extensions, and the extension.
	 *
	 * @param file
	 *            A given file.
	 * @param extension
	 *            A given file extension.
	 * @return A new file with the given file's name, stripped of any
	 *         extensions, and the extension.
	 */
	private File withExtension(File file, String extension) {
		String newName = "";
		try {
			newName = file.getCanonicalPath();
		} catch (IOException e) {}
		for (int i = 0; i < newName.length(); i++)
			if (newName.charAt(i) == '.')
				return new File(newName.substring(0, i) + "." + extension);
		return new File(newName + "." + extension);
	}

	/**
	 * Undoes the last move executed. TODO
	 */
	private void undoMove() {
		System.err.println("undoMove() called.");
		System.err.println();
		moves.get(moves.size() - 1).undo();
		moves.remove(moves.size() - 1);
	}

	/**
	 * Shows the human a hint. TODO
	 */
	private void showHint() {
		System.err.println("showHint() called.");
		System.err.println();
	}

	/**
	 * Shows the last move executed. TODO
	 */
	private void showLastMove() {
		System.err.println("showLastMove() called.");
		System.err.println();
	}

	/**
	 * The game log of the chess game.
	 */
	GameLog gameLog;

	/**
	 * Either hides or shows the game log.
	 */
	private void toggleGameLog() {
		gameLog.toggle();
		if (gameLog.inFrame())
			menuBar.getMenu(3).getItem(2).setText("Hide Game Log");
		else
			menuBar.getMenu(3).getItem(2).setText("Show Game Log");
		gameLog.update(moves);
	}

	protected boolean showMoves;

	/**
	 * Toggles whether to show or hide possible moves from the player.
	 */
	private void toggleShowMoves() {
		showMoves = !showMoves;
		if (showMoves)
			menuBar.getMenu(2).getItem(4).setText("Hide Possible Moves");
		else
			menuBar.getMenu(2).getItem(4).setText("Show Possible Moves");
		frame.repaint();
	}

	/**
	 * The playing options frame. Only used in initialization.
	 */
	JPanel playingOptionsPanel;

	/**
	 * The items on the chess settings buttons. Currently, they are just player
	 * vs. player, player vs. computer, and computer vs. computer.
	 */
	private JButton[] settingsButtons;

	/**
	 * All of the moves in the game so far.
	 */
	private ArrayList<Move> moves;

	/**
	 * The number of steps the recursive minimax method will use to determine
	 * the best move.
	 */
	private int minimaxDepth;

	/**
	 * The color that describes whose turn it is. Only used for player versus
	 * computer.
	 */
	private Color turn;

	/**
	 * The current game setting (player vs. player, player vs. computer,
	 * computer vs. computer)
	 */
	private int gameSetting;

	public ChessGame() {
		chessGames++;
		// Initialize all fields
		frame = new JFrame();
		frame.setResizable(false);
		chessBoard = new ChessBoard(this);
		menuBar = getJMenuBar();
		showMoves = true;
		playingOptionsPanel = new JPanel();
		settingsButtons =
				new JButton[] {
						new JButton("Human vs. Human"),
						new JButton("Human vs. Computer"),
						new JButton("Computer vs. Computer") };
		gameLog = new GameLog(frame);
		moves = new ArrayList<Move>();
		minimaxDepth = 5;
		turn = chessBoard.WHITE;
	}

	protected ChessGame(boolean incrementChessGames) {
		if (incrementChessGames)
			chessGames++;
		// Initialize all fields
		frame = new JFrame();
		frame.setResizable(false);
		chessBoard = new ChessBoard(this);
		menuBar = getJMenuBar();
		showMoves = true;
		playingOptionsPanel = new JPanel();
		settingsButtons =
				new JButton[] {
						new JButton("Human vs. Human"),
						new JButton("Human vs. Computer"),
						new JButton("Computer vs. Computer") };
		gameLog = new GameLog(frame);
		moves = new ArrayList<Move>();
		minimaxDepth = 5;
		turn = chessBoard.WHITE;
	}

	/**
	 * Private because it's only used for saved and reopened chess games.
	 */
	private ChessGame(
			File saveFile,
			Integer gameSetting,
			ChessBoard chessBoard,
			ArrayList<Move> moves,
			Integer minimaxSteps) {
		chessGames++;
		// Initialize all fields
		frame = new JFrame();
		frame.setResizable(false);
		this.saveFile = saveFile;
		this.chessBoard = chessBoard;
		menuBar = getJMenuBar();
		showMoves = true;
		gameLog = new GameLog(frame);
		this.moves = moves;
		minimaxDepth = minimaxSteps.intValue();
		turn = moves.size() % 2 == 0 ? chessBoard.WHITE : chessBoard.BLACK;
		this.gameSetting = gameSetting.intValue();

		// Reset the frame title.
		String saveFileName = saveFile.getName();
		frame.setTitle(saveFileName.substring(0, saveFileName.length() - 4) +
				" - " + settingsButtons[gameSetting].getText());

		startUp();
	}

	/**
	 * Starts up an already initialized chess game.
	 */
	private void startUp() {
		switch (gameSetting) {
		// Player vs. Player.
		case 0:
			playerVsPlayer();
			break;
		// Player vs. Computer
		case 1:
			playerVsComputer();
			break;
		// Computer vs. Computer
		case 2:
			computerVsComputer();
			break;
		}
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Plays a chess game in a new window.
	 */
	public void play() {
		frame.setJMenuBar(menuBar);

		frame.add(playingOptionsPanel, BorderLayout.NORTH);
		playingOptionsPanel.setBackground(COLUMBIA_BLUE);
		// Add all of the buttons.
		for (JButton button : settingsButtons)
			playingOptionsPanel.add(button, BorderLayout.NORTH);

		// Manually create each button's change listener.
		settingsButtons[0].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				settingsButtons[0].setPressedIcon(settingsButtons[0]
						.getPressedIcon());
				frame.remove(playingOptionsPanel);
				frame.pack();
				gameSetting = 0;
				frame.setTitle("New Game" + " - " +
						settingsButtons[gameSetting].getText() + " (Edited)");
				playerVsPlayer();
			}
		});
		settingsButtons[1].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				settingsButtons[1].setPressedIcon(settingsButtons[1]
						.getPressedIcon());
				frame.remove(playingOptionsPanel);
				frame.pack();
				gameSetting = 1;
				frame.setTitle("New Game" + " - " +
						settingsButtons[gameSetting].getText() + " (Edited)");
				playerVsComputer();
			}
		});
		settingsButtons[2].addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				settingsButtons[2].setPressedIcon(settingsButtons[2]
						.getPressedIcon());
				frame.remove(playingOptionsPanel);
				frame.pack();
				gameSetting = 2;
				frame.setTitle("New Game" + " - " +
						settingsButtons[gameSetting].getText() + " (Edited)");
				computerVsComputer();
			}
		});

		frame.add(chessBoard, BorderLayout.CENTER);

		// Let the user know that an option has to be selected.
		chessBoard.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				playingOptionsPanel.setBackground(Color.RED);
				frame.pack();
			}

			public void mouseReleased(MouseEvent me) {
				playingOptionsPanel.setBackground(COLUMBIA_BLUE);
				frame.pack();
			}
		});

		frame.setTitle("Chess");
		frame.setBackground(Color.WHITE);

		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				frame.dispose();
				chessGames--;
				if (chessGames == 0)
					System.exit(0);
			}
		});

		frame.pack();
		frame.setBounds(
				22 * (chessGames - 1) %
						Toolkit.getDefaultToolkit().getScreenSize().width,
				0,
				frame.getSize().width,
				frame.getSize().height);
		frame.setVisible(true);
	}

	/**
	 * The spinner for the number of steps used in the minimax algorithm. Only
	 * used in player vs. computer and computer vs. computer. Coordinated with
	 * the slider.
	 */
	JSpinner spinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 1));

	/**
	 * The slider for the number of steps used in the minimax algorithm. Only
	 * used in player vs. computer and computer vs. computer. Coordinated with
	 * the spinner.
	 */
	JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 0, 10, 5);

	/**
	 * The method that starts a game of chess for two human players.
	 */
	private void playerVsPlayer() {
		// Enable save, view game log, show moves.
		menuBar.getMenu(1).getItem(3).setEnabled(true);
		menuBar.getMenu(2).getItem(1).setEnabled(true);
		menuBar.getMenu(3).getItem(2).setEnabled(true);

		for (MouseListener ml : chessBoard.getMouseListeners())
			chessBoard.removeMouseListener(ml);

		frame.add(chessBoard, BorderLayout.CENTER);

		// Add the mouse listener.
		chessBoard.addMouseListener(new MouseAdapter() {
			// If the mouse is pressed, set the selected piece to the selected
			// piece (if there is one).
			public void mousePressed(MouseEvent me) {
				// Convert the press location to a position on the board.
				Position pressLocation =
						new Position(
								2 * 4 - me.getY() / ChessBoard.SQUARE_SIZE,
								me.getX() / ChessBoard.SQUARE_SIZE + 1);

				// The piece at the click location.
				ChessPiece piece = chessBoard.getPieceAt(pressLocation);

				if (piece == null || !piece.getColor().equals(turn))
					return;

				// Grab the selected piece
				chessBoard.setSelectedPiece(piece);

				// Update the chessBoard (and frame)
				chessBoard.repaint();
			}

			public void mouseReleased(MouseEvent me) {
				// Convert the release location to a position on the board.
				Position releaseLocation =
						new Position(
								2 * 4 - me.getY() / ChessBoard.SQUARE_SIZE,
								me.getX() / ChessBoard.SQUARE_SIZE + 1);

				System.out.println("Release Location: " + releaseLocation);
				System.out.println("Selected piece: " +
						chessBoard.getSelectedPiece());

				// Use the moveTo method to move the piece to the mouse-release
				// location (which will only move it to the location if it is
				// possible).
				if (chessBoard.getSelectedPiece() != null &&
						chessBoard.getSelectedPiece().moveTo(releaseLocation))
					playerTurnFinished();

				// Let go of the selected piece.
				chessBoard.setSelectedPiece(null);

				// Update the frame
				frame.repaint();
			}
		});

		chessBoard.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				chessBoard.repaint();
			}
		});

		frame.validate();
		frame.pack();
	}

	public void playerTurnFinished() {
		if (isGameOver()) {
			endGame();
			return;
		}
		toggleTurn();
		if (isGameOver()) {
			endGame();
			return;
		}
	}

	// Only used for player vs. computer
	private Color playerColor;
	private Color computerColor;

	private void playerVsComputer() {
		playingOptionsPanel = new JPanel();
		playingOptionsPanel.setBackground(COLUMBIA_BLUE);

		// Reset the frame title.
		frame.setTitle("New Game - " + settingsButtons[gameSetting].getText() +
				" (Edited)");

		frame.add(playingOptionsPanel, BorderLayout.NORTH);

		JPanel colorPanel = new JPanel();
		colorPanel.setBackground(COLUMBIA_BLUE);

		colorPanel.add(new JLabel("Play as "), BorderLayout.WEST);

		final JRadioButton whiteButton = new JRadioButton("White");
		final JRadioButton blackButton = new JRadioButton("Black");
		ButtonGroup group = new ButtonGroup();

		group.add(whiteButton);
		group.add(blackButton);

		// Default white.
		whiteButton.setSelected(true);
		playerColor = chessBoard.WHITE;
		computerColor = chessBoard.BLACK;

		whiteButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				playerColor = chessBoard.WHITE;
				computerColor = chessBoard.BLACK;
			}
		});
		blackButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				playerColor = chessBoard.BLACK;
				computerColor = chessBoard.WHITE;
			}
		});

		colorPanel.add(whiteButton, BorderLayout.NORTH);
		colorPanel.add(blackButton, BorderLayout.SOUTH);

		playingOptionsPanel.add(colorPanel, BorderLayout.NORTH);

		JPanel difficultyPanel = new JPanel();

		difficultyPanel.setBackground(COLUMBIA_BLUE);

		difficultyPanel.add(new JLabel("Difficulty"), BorderLayout.WEST);

		difficultyPanel.add(spinner, BorderLayout.CENTER);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				minimaxDepth = ((Integer) spinner.getValue()).intValue();
				slider.setValue(((Integer) spinner.getValue()).intValue());
			}
		});

		difficultyPanel.add(slider, BorderLayout.EAST);
		slider.setMajorTickSpacing(5);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setSnapToTicks(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				minimaxDepth = slider.getValue();
				spinner.setValue(slider.getValue());
			}
		});

		playingOptionsPanel.add(difficultyPanel, BorderLayout.SOUTH);

		JButton continueButton = new JButton("Play Chess!");

		continueButton.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent ce) {
				// Enable save, view game log, show moves.
				menuBar.getMenu(1).getItem(3).setEnabled(true);
				menuBar.getMenu(2).getItem(1).setEnabled(true);
				menuBar.getMenu(3).getItem(2).setEnabled(true);

				frame.remove(playingOptionsPanel);
				frame.pack();
				pcStartGame();
			}
		});

		playingOptionsPanel.add(continueButton, BorderLayout.EAST);

		// Let the user know that an option has to be selected.
		chessBoard.removeMouseListener(chessBoard.getMouseListeners()[0]);
		chessBoard.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				playingOptionsPanel.setBackground(Color.RED);
				for (Component component : playingOptionsPanel.getComponents())
					component.setBackground(Color.RED);
				frame.pack();
			}

			public void mouseReleased(MouseEvent me) {
				playingOptionsPanel.setBackground(COLUMBIA_BLUE);
				for (Component component : playingOptionsPanel.getComponents())
					component.setBackground(COLUMBIA_BLUE);
				frame.pack();
			}
		});

		frame.pack();
		frame.setVisible(true);
	}

	private void pcStartGame() {
		// Reset the frame title.
		frame.setTitle("New Game - " + settingsButtons[gameSetting].getText() +
				" (Edited)");

		for (MouseListener ml : chessBoard.getMouseListeners())
			chessBoard.removeMouseListener(ml);

		frame.add(chessBoard, BorderLayout.CENTER);

		// Add the mouse listener.
		chessBoard.addMouseListener(new MouseAdapter() {
			// If the mouse is pressed, set the selected piece to the selected
			// piece (if there is one).
			public void mousePressed(MouseEvent me) {
				if (!turn.equals(playerColor))
					return;

				Position pressLocation = new Position(
				// The below '2 * 4' is due to the fact that x is 0 at
				// the top of the window. I have to reflect it across
				// the horizontal center-line of the board (i.e. 4).
						2 * 4 - me.getY() / ChessBoard.SQUARE_SIZE,
						me.getX() / ChessBoard.SQUARE_SIZE + 1);

				// The piece at the click location.
				ChessPiece piece = chessBoard.getPieceAt(pressLocation);

				if (piece == null || !piece.getColor().equals(playerColor))
					return;

				// Grab the selected piece
				chessBoard.setSelectedPiece(piece);

				// Update the frame
				frame.repaint();
			}

			public void mouseReleased(MouseEvent me) {
				if (!turn.equals(playerColor))
					return;

				Position releaseLocation =
						new Position(
								2 * 4 - me.getY() / ChessBoard.SQUARE_SIZE,
								me.getX() / ChessBoard.SQUARE_SIZE + 1);

				// Use the moveTo method to move the piece to the mouse-release
				// location (which will only move it to the location if it is
				// possible).
				if (chessBoard.getSelectedPiece() != null &&
						chessBoard.getSelectedPiece().moveTo(releaseLocation))
					userTurn();

				// Let go of the selected piece.
				chessBoard.setSelectedPiece(null);

				// Update the frame
				frame.repaint();
			}
		});

		chessBoard.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent me) {
				chessBoard.repaint();
			}
		});

		frame.pack();

		pcPlayGame();
	}

	/**
	 * The to-be-executed move of the computer. Calculated while the user
	 * thinks.
	 */
	private Move computerMove;

	private void pcPlayGame() {
		frame.repaint();
		computerMove = minimax();
		if (turn.equals(computerColor))
			computerTurn();
	}

	/**
	 * Called once the user makes a move. The computer decides on the best move
	 * and executes it. Only used for player vs. computer.
	 */
	private void computerTurn() {
		System.out.println("Computer's turn executing");

		if (computerMove == null) {
			// Make it so that the _user_ wins.
			toggleTurn();
			endGame();
			return;
		}

		moves.add(computerMove);
		computerMove.execute();
		System.err.println("Executed move: " + computerMove);
		if (isGameOver()) {
			endGame();
			return;
		}
		toggleTurn();
		if (isGameOver()) {
			endGame();
			return;
		}
		minimax();
	}

	/**
	 * Called when the user makes a move, which is only possible if it's not the
	 * computer's turn. Only used for player vs. computer.
	 */
	private void userTurn() {
		System.out.println("User's turn executed");
		if (isGameOver()) {
			endGame();
			return;
		}
		toggleTurn();
		if (isGameOver()) {
			endGame();
			return;
		}
		computerTurn();
	}

	// /**
	// * @return The best random possible move (basically a 0-step minimax).
	// */
	// private Move random() {
	// ArrayList<Move> allMoves = new ArrayList<Move>();
	//
	// // Initialize allPossibleMoves.
	// for (ChessPiece piece : chessBoard.getAllPieces(turn))
	// allMoves.addAll(piece.getMoves());
	//
	// ArrayList<Move> choices = new ArrayList<Move>();
	// for (Move move : allMoves)
	// if (choices.size() == 0)
	// choices.add(move);
	// else if (choices.get(0).getScore() < move.getScore()) {
	// choices.clear();
	// choices.add(move);
	// } else if (choices.get(0).getScore() == move.getScore())
	// choices.add(move);
	//
	// return random(choices);
	// }

	private synchronized Move minimax() {
		// Create a new branch.
		MinimaxBranch branch =
				new MinimaxBranch(chessBoard, null, minimaxDepth, true);

		// Set the optimal move.
		branch.run();

		// Wait until it's complete.
		branch.lock.lock();

		// Return the optimal move.
		return branch.chosenMove;
	}

	/**
	 * This uses the minimax algorithm (which is recursive) to decide which move
	 * is best.
	 *
	 * @param node
	 *            The current node, or ChessBoard, being analyzed.
	 * @param edge
	 *            The last edge, or Move, moved along to get to this node.
	 * @param depth
	 *            The number of steps to look ahead with this recursive method.
	 * @param maximizingPlayer
	 *            Whether it's the turn of the player trying to maximize the
	 *            score.
	 * @return The optimal move for this player, after looking the given number
	 *         of times ahead.
	 */
	// private Move minimax(
	// ChessBoard node,
	// Move edge,
	// int depth,
	// boolean maximizingPlayer) {
	// if (edge != null)
	// edge.setScore();
	// { // TODO Remove debugging block
	// for (int i = 0; i < minimaxDepth - depth; i++)
	// System.out.print("##");
	// if (edge != null)
	// System.out.println("Edge: " + edge);
	// for (int i = 0; i < minimaxDepth - depth; i++)
	// System.out.print("##");
	// if (edge != null)
	// System.out.println("Score: " + edge.getScore());
	// for (int i = 0; i < minimaxDepth - depth; i++)
	// System.out.print("##");
	// System.out.println("Depth: " + depth);
	// for (int i = 0; i < minimaxDepth - depth; i++)
	// System.out.print("##");
	// System.out.println("Node: \n" +
	// node.toIndentedString(2 * (minimaxDepth - depth)));
	// }
	//
	// // If the depth is 0 or minimax is at a terminating node, return the
	// // last element in the edges vector, the heuristic value of the node.
	// if (depth == 0 || edge != null && edge.doesCheckMate())
	// return edge;
	//
	// // The Move ArrayList that will contain all of the possible moves.
	// ArrayList<Move> allMoves = new ArrayList<Move>();
	//
	// // The color of whichever player is calling this method.
	// Color callerColor = turn;
	// if (!maximizingPlayer)
	// callerColor = chessBoard.oppositeColor(turn);
	//
	// // Get all of the possible moves.
	// for (ChessPiece piece : chessBoard.getPieces(callerColor))
	// allMoves.addAll(piece.getMoves());
	//
	// { // TODO Remove debugging block
	// for (int i = 0; i < minimaxDepth - depth; i++)
	// System.out.print("##");
	// System.out.println(allMoves.size() + " Possible Moves:");
	// for (Move move : allMoves) {
	// for (int i = 0; i < minimaxDepth - depth; i++)
	// System.out.print("##");
	// System.out.print("#");
	// System.out.println(move);
	// }
	// }
	//
	// // What will be the possible optimal moves, looking depth steps ahead.
	// ArrayList<Move> choices = new ArrayList<Move>();
	// // The move after applying the minimax algorithm
	// Move minimax;
	// if (maximizingPlayer) {
	// // Set the moves' heuristic values after looking depth steps ahead
	// for (Move move : allMoves) {
	// // Get what the child node says is the optimal move, assuming
	// // the current edge is traveled down.
	//
	// // Set the minimax move to what the child node says it will be.
	// minimax = minimax(
	// // The child node (the board with the move executed).
	// node.unreportedMove(move),
	// move,
	// depth - 1,
	// false);
	//
	// minimax.setScore();
	//
	// // Max score for other player.
	// int maxScoreForThisPlayer = 0;
	//
	// // Maximize the scores in the choices vector
	// if (maxScoreForThisPlayer < minimax.getScore()) {
	// maxScoreForThisPlayer = minimax.getScore();
	// continue;
	// }
	//
	// move.addScore(maxScoreForThisPlayer);
	// }
	//
	// // Choose a possible move among the choices
	// for (Move move : allMoves)
	// if (choices.size() == 0)
	// choices.add(move);
	// else if (choices.get(0).getScore() < move.getScore()) {
	// choices.clear();
	// choices.add(move);
	// } else if (choices.get(0).getScore() == move.getScore())
	// choices.add(move);
	// } else {
	// // Set the moves' heuristic values after looking depth steps ahead
	// for (Move move : allMoves) {
	// // Get what the child node says is the optimal move, assuming
	// // the current edge is traveled down.
	//
	// // Set the minimax move to what the child node says it will be.
	// minimax = minimax(
	// // The child node (the board with the move executed).
	// node.unreportedMove(move),
	// move,
	// depth - 1,
	// true);
	//
	// minimax.setScore();
	//
	// // Max score for other player.
	// int maxScoreForOtherPlayer = 0;
	//
	// // Maximize the scores in the choices vector
	// if (maxScoreForOtherPlayer > minimax.getScore()) {
	// maxScoreForOtherPlayer = minimax.getScore();
	// continue;
	// }
	//
	// move.addScore(-1 * maxScoreForOtherPlayer);
	// }
	//
	// for (Move move : allMoves)
	// if (choices.size() == 0)
	// choices.add(move);
	// else if (choices.get(0).getScore() > move.getScore()) {
	// choices.clear();
	// choices.add(move);
	// } else if (choices.get(0).getScore() == move.getScore())
	// choices.add(move);
	// }
	//
	// // Return an optimal move, after looking ahead depth steps.
	// { // TODO Remove debugging block
	// for (int i = 0; i < minimaxDepth - depth; i++)
	// System.out.print("##");
	// System.out.println("Choices: ");
	// for (int i = 0; i < choices.size(); i++)
	// System.out.println(choices.get(i));
	// }
	// return choices.get((int) (Math.random() * choices.size()));
	// }

	private void computerVsComputer() {
		// Enable save, view game log, show moves.
		menuBar.getMenu(1).getItem(3).setEnabled(true);
		menuBar.getMenu(2).getItem(1).setEnabled(true);
		menuBar.getMenu(3).getItem(2).setEnabled(true);

	}

	/**
	 * Displays the ending screen (i.e. when the game is over)
	 */
	private void endGame() {
		chessBoard.setEnabled(false);
		String saveFileName = "";
		if (saveFile != null)
			saveFileName = saveFile.getName();
		String saveName;
		try {
			saveName = saveFileName.substring(0, saveFileName.length() - 4);
		} catch (IndexOutOfBoundsException ioobe) {
			saveName = "New Game";
		}
		frame.setTitle(saveName + " - " +
				settingsButtons[gameSetting].getText() + " - " +
				(turn.equals(chessBoard.WHITE) ? "White" : "Black") +
				" wins! " + " (Edited)");
	}

	/**
	 * An accessor method for mouseX.
	 *
	 * @return The x position of the mouse.
	 */
	protected int getMouseX() {
		return (int) (MouseInfo.getPointerInfo().getLocation().getX() - frame
				.getLocation().getX());
	}

	/**
	 * An accessor method for mouseY.
	 *
	 * @return The y position of the mouse.
	 */
	protected int getMouseY() {
		return (int) (MouseInfo.getPointerInfo().getLocation().getY() - frame
				.getLocation().getY());
	}

	/**
	 * An accessor method for moves.
	 *
	 * @return The vector of all moves.
	 */
	public ArrayList<Move> getMoves() {
		return moves;
	}

	/**
	 * Checks whether the game is over or not (i.e. a king is captured).
	 *
	 * @return Whether the game is over.
	 */
	public boolean isGameOver() {
		// If a king is checkmated, return true. Otherwise, false.
		King whiteKing = null;
		King blackKing = null;
		ChessPiece[][] pieces = chessBoard.getPieces();
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				if (pieces[i][j] instanceof King)
					if (pieces[i][j].getColor().equals(chessBoard.WHITE))
						whiteKing = (King) pieces[i][j];
					else if (pieces[i][j].getColor().equals(chessBoard.BLACK))
						blackKing = (King) pieces[i][j];

		// Check to see if the current player has possible moves.
		ArrayList<Move> moves = new ArrayList<Move>();
		for (ChessPiece piece : chessBoard.getPieces(turn))
			moves.addAll(piece.getMoves());

		if (whiteKing != null && whiteKing.isCheckMated())
			return true;
		else if (blackKing != null && blackKing.isCheckMated())
			return true;
		else if (moves.size() == 0)
			return true;
		else
			return false;
	}

	/**
	 * Changes the turn.
	 */
	protected void toggleTurn() {
		turn = chessBoard.oppositeColor(turn);
		frame.repaint();
	}

	/**
	 * Adds the given move to the moves ArrayList.
	 *
	 * @param move
	 *            A given move.
	 */
	protected void addMove(Move move) {
		// Reset the frame title, if necessary.
		String saveName = "New Game";
		if (saveFile != null) {
			saveName = saveFile.getName();
			saveName.substring(0, saveName.length() - 4);
		}
		frame.setTitle(saveName + " - " +
				settingsButtons[gameSetting].getText() + " (Edited)");

		// Set the move ID so that the program knows it has been executed.
		move.setID(moves.size());
		move.setModifier();

		// Add the move to the moves ArrayList.
		moves.add(move);
	}

	/**
	 * Gets whether the king of the color opposite the given color is checked,
	 * double checked, or checkmated.
	 *
	 * @param color
	 *            A given color.
	 * @return Whether the king of the color opposite the given color is
	 *         checked, double checked, or checkmated.
	 */
	protected String getOpponentKingChecked(Color color) {
		King king = chessBoard.getKing(chessBoard.oppositeColor(color));
		if (king == null)
			return Moves.CHECKMATE;
		else if (!king.isChecked())
			return Moves.NONE;
		else if (king.isDoubleChecked())
			return Moves.DOUBLE_CHECK;
		else if (king.isChecked())
			return Moves.CHECK;
		else if (king.isCheckMated())
			return Moves.CHECKMATE;
		return Moves.NONE;
	}

	/**
	 * @return The promoted piece the user will choose.
	 */

	protected ChessPiece getPromotedPieceFromUser() {
		ChessPiece[] pieces =
				{
						new Queen(chessBoard, turn),
						new Rook(chessBoard, turn),
						new Bishop(chessBoard, turn),
						new Knight(chessBoard, turn) };
		String[] strings = new String[pieces.length];
		for (int i = 0; i < pieces.length; i++)
			strings[i] = pieces[i].toString();
		int option =
				JOptionPane.showOptionDialog(
						new JFrame(),
						"Choose your piece:",
						"Promoted Pawn",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						strings,
						strings[0]);
		if (option == -1)
			return pieces[0];
		return pieces[option];
	}
}
