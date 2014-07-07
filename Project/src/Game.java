import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import chess.ChessGame;

/**
 * This is the top-level game class that allows the user to play any of the
 * encoded games. Currently, the only games are: chess. The game class is
 * written as it is to allow any other programmers to easily implement more
 * games by merely adding more packages to the project, and more buttons (with
 * their appropriate call-methods and listeners) to this class.
 *
 * @author Kai-Zhan Lee
 *
 */
public class Game {

	public static void main(String[] args) {
		new Game();
	}

	/**
	 * The button array. Currently, the only one is to play a chess game. This
	 * is just to make it easy to implement new games.
	 */
	public JButton[] buttons = { new JButton("Chess") };

	/**
	 * The frame that will be displayed.
	 */
	public JFrame frame;

	private boolean playedGame = true;

	public Game() {
		frame = new JFrame();
		frame.setBackground(new Color(196, 216, 226));
		for (Component component : frame.getComponents())
			component.setBackground(frame.getBackground());
		initialize();
	}

	public void initialize() {
		frame.setBackground(Color.WHITE);
		// frame.setPreferredSize(new Dimension(
		// Toolkit.getDefaultToolkit().getScreenSize().width/2,
		// Toolkit.getDefaultToolkit().getScreenSize().height/2));
		frame.setLayout(new BorderLayout());
		frame.setTitle("Choose your game");

		frame.add(new JLabel("Choose your game:"), BorderLayout.NORTH);

		// Add the buttons
		for (JButton button : buttons) {
			frame.add(button, BorderLayout.CENTER);
			button.setPreferredSize(new Dimension(100, 25));
			frame.setPreferredSize(new Dimension(
					100,
					frame.getPreferredSize().height + 50));
		}

		// Set each button's listener manually
		buttons[0].addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent ce) {
				playChess();
				frame.dispose();
			}
		});

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setSize(new Dimension(200, 25 * (2 * buttons.length + 1)));
		frame.setVisible(true);
	}

	private void playChess() {
		if (playedGame)
			new ChessGame().play();
		playedGame = false;
	}

}
