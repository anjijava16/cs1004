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

	private JFrame parentFrame;

	private ArrayList<String[]> moves;

	protected GameLog(JFrame parentFrame) {
		this.parentFrame = parentFrame;
		moves = new ArrayList<String[]>();
		setBackground(Color.WHITE);
		setVisible(false);
	}

	public void paintComponent(Graphics g) {
		setSize(new Dimension(2 * COLUMN_WIDTH, parentFrame.getHeight()));

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
		for (int i = 0; i < moves.size(); i++)
			for (int j = 0; j < 2; j++) {
				System.out.println(moves.get(i)[j]);
				if (moves.get(i)[j] != null)
					g.drawChars(
							moves.get(i)[j].toCharArray(),
							0,
							moves.get(i)[j].length(),
							(j + 1) * COLUMN_WIDTH,
							i * ROW_HEIGHT);
			}
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

	public void update(ArrayList<Move> moves) {
		System.err.println("Updating game log...");
		for (Move move : moves)
			System.out.println(move);
		this.moves.clear();
		for (int i = 0; i < moves.size(); i += 2)
			try {
				this.moves.add(new String[] {
						moves.get(i).toGameLogString(),
						moves.get(i + 1).toGameLogString() });
			} catch (IndexOutOfBoundsException ioobe) {
				this.moves.add(new String[] {
						moves.get(i).toGameLogString(),
						"" });
			}
		repaint();
		parentFrame.repaint();
	}
}
