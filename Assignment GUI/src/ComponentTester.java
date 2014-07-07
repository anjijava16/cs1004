import java.awt.*;
import javax.swing.*;

public class ComponentTester {

	public static void main(String[] args) {
		JFrame frame = new JFrame();

		frame.setLayout(new BorderLayout());
		frame.add(new MyComponent(), BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}
