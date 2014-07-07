Name: Kai-Zhan Lee
UNI: kl2792
GUI Assignment Extra Credit

Overview:
	I made a ChessPieces interface that contained all of the UTF-16 char
	values for each piece, and implemented it in the ChessBoard class.
	Since ChessBoard had to extend JComponent to be displayable, I then
	proceeded to extend the ChessBoard class and override the
	paintComponent() (with parameters) method in JComponent. I used
	Graphics’ drawChar() (with parameters) method to draw each chess
	piece, its fillRect() (with parameters) method to draw each chess
	board square, and its setColor() (with parameters) method to set the
	color of each piece and square.