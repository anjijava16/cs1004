import java.awt.Component;
import java.awt.Frame;
import java.awt.Image;

public class Chessboard{
	
	public static final int SQUARE_SIZE = 40;
	
	private Frame drawingCanvas;
	
	private Image[][] board;
	
	/**
	 * Sets the chessboard's dimensions and appropriates its squares' colors.
	 */
	public Chessboard(){
		drawingCanvas.setBounds(0, 0, 8 * SQUARE_SIZE, 8 * SQUARE_SIZE);
		drawingCanvas.validate();
	}
	
	public void drawSelf(){

	}
	
	public static void main(String[] args){
		Chessboard chessboard = new Chessboard();
		chessboard.drawSelf();
	}
	
}
