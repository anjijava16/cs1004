/*
 * Name: Kai-Zhan Lee UNI: kl2792 GUI Assignment
 */

import javax.swing.*;

import java.awt.*;

/**
 * This component has 5 equally spaced vertical lines and 5 equally spaced
 * horizontal lines, making a grid. These lines cover the entire frame. The
 * vertical lines alternate blue, green, blue, green, blue, and the horizontal
 * lines alternate red, yellow, red, yellow, red.
 * 
 * @author Kai-Zhan Lee
 * 
 */
public class MyComponent extends JComponent {

	private static final long serialVersionUID = 7467018618855750119L;

	public MyComponent() {
		setPreferredSize(new Dimension(220, 200));
	}

	public void paintComponent(Graphics g) {
		int height = getSize().height;
		int width = getSize().width;

		// Draw vertical lines
		for (int i = 1; i < 6; i++) {
			if (i % 2 == 1)
				g.setColor(Color.BLUE);
			else
				g.setColor(Color.GREEN);
			g.drawLine(
					(int) (i * (double) width / 6), 0,
					(int) (i * (double) width / 6), height);
		}

		// Draw horizontal lines.
		for (int i = 1; i < 6; i++) {
			if (i % 2 == 1)
				g.setColor(Color.RED);
			else
				g.setColor(Color.YELLOW);
			g.drawLine(
					0, (int) (i * (double) height / 6),
					width, (int) (i * (double) height / 6));
		}
	}
}
