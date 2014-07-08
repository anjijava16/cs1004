package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

public class GameLog {

	private ChessGame game;

	private JFrame parentFrame;

	private JTable table;

	private JPanel panel;

	protected GameLog(ChessGame game, JFrame parentFrame) {
		this.game = game;
		this.parentFrame = parentFrame;
		table = new JTable();
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(223, parentFrame.getHeight()));
		panel.setVisible(false);
	}

	public void paintComponent(Graphics g) {
		panel.setBackground(Color.BLUE);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Helvetica", Font.PLAIN, 14));
		update();
	}

	public boolean inFrame() {
		for (Component component : parentFrame.getComponents())
			if (component.equals(panel))
				return true;
		return false;
	}

	public void toggle() {
		panel.setVisible(!panel.isVisible());

		if (inFrame())
			parentFrame.remove(panel);
		else
			parentFrame.add(panel, BorderLayout.EAST);

		update();
	}

	public synchronized void update() {
		table = null;

		// Set the data for the JTable
		Object[] columnNames = new String[] { "#", "White", "Black" };
		Object[][] data = new String[(game.moves.size() + 1) / 2 + 1][3];
		data[0] = new String[] { "#", "White", "Black" };
		for (int i = 1; i / 2 + 1 < data.length; i += 2)
			if (i < game.moves.size())
				data[i / 2 + 1] =
						new String[] {
								String.valueOf(i / 2 + 1) + ".",
								game.moves.get(i - 1).toGameLogString(),
								game.moves.get(i).toGameLogString() };
			else
				data[i / 2 + 1] =
						new String[] {
								String.valueOf(i / 2 + 1) + ".",
								game.moves.get(i - 1).toGameLogString(),
								"" };

		// Set the JTable itself
		table = new JTable(data, columnNames);
		table.setEnabled(false);

		// Set the column widths.
		TableColumn column;
		for (int i = 0; i < 3; i++) {
			column = table.getColumnModel().getColumn(i);
			if (i == 0)
				column.setWidth(20);
			else
				column.setWidth(100);
		}

		panel.setBackground(Color.WHITE);
		panel.removeAll();
		panel.add(table, BorderLayout.CENTER);

		panel.repaint();
		parentFrame.validate();
		parentFrame.pack();
	}
}
