package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameLog extends JPanel {

	private static final long serialVersionUID = 2443982736237939638L;

	private static final int ROW_HEIGHT = 20;

	private static final int COLUMN_WIDTH = 100;

	private ChessGame game;

	private JFrame parentFrame;

	private ArrayList<String[]> table;

	protected GameLog(ChessGame game, JFrame parentFrame) {
		this.game = game;
		this.parentFrame = parentFrame;
		table = new ArrayList<String[]>();
		setPreferredSize(new Dimension(
				2 * COLUMN_WIDTH,
				parentFrame.getHeight()));
		setBackground(Color.WHITE);
		setVisible(false);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);

		// Draw the row lines.
		for (int i = 0; i * ROW_HEIGHT < parentFrame.getHeight(); i++)
			g.drawLine(0, i * ROW_HEIGHT, 2 * COLUMN_WIDTH, i * ROW_HEIGHT);

		// Draw the column lines.
		for (int i = 0; i < 2; i++)
			g.drawLine(
					i * COLUMN_WIDTH,
					0,
					i * COLUMN_WIDTH,
					parentFrame.getHeight());

		// Draw the actual table (the moves)
		for (int i = 0; i < table.size(); i++)
			for (int j = 0; j < 2; j++)
				if (table.get(i)[j] != null)
					g.drawChars(
							table.get(i)[j].toCharArray(),
							0,
							table.get(i)[j].length(),
							j * COLUMN_WIDTH + 3,
							(i + 1) * ROW_HEIGHT - 3);
	}

	public boolean inFrame() {
		for (Component component : parentFrame.getComponents())
			if (component.equals(this))
				return true;
		return false;
	}

	public void toggle() {
		setVisible(!isVisible());

		if (inFrame())
			parentFrame.remove(this);
		else
			parentFrame.add(this, BorderLayout.EAST);

		parentFrame.validate();
		parentFrame.pack();
	}

	public void update() {
		for (Move move : game.moves)
			System.out.println(move);

		table.clear();

		table.add(new String[] { "White", "Black" });

		for (int i = 0; i < table.size(); i += 2)
			try {
				table.add(new String[] {
						game.moves.get(i).toGameLogString(),
						game.moves.get(i + 1).toGameLogString() });
			} catch (IndexOutOfBoundsException ioobe) {
				table.add(new String[] {
						game.moves.get(i).toGameLogString(),
						"" });
			}

		parentFrame.pack();
	}
}
