Name: Kai-Zhan Lee
UNI: kl2792
Project README

Running the Program:
	- Copy and paste (or just type) the following into terminal in the
	  directory you downloaded the project to:
unzip Project.zip
rm -rf __MACOSX/ # Weird folder that's always generated with the unzip command.
cd Project/bin
javac Game.java # All other classes will be compiled with this command.
java Game # And enjoy the game!

	- Note: You MUST run this program in terminal, command prompt, or whatever
	  program your computer uses to open shells if you want to be able to save
	  or load files.
Overview:
	This program inquires what game the user would like to play (the only
	possible one currently is chess), then plays it. For the chess game, the
	user can choose to play against another user (or himself/herself) or a
	computer or watch two computers play each other. Each computer plays using
	the minimax algorithm, looking a user-specified number of steps ahead.
Notes:
	- You may (or may not) need to update to Java 8 / Java 7 because certain
	  earlier versions do not support iterator for loops with arrays.
	- We haven't gone over some of the classes I used; I just looked them up on
	  the Java API.
	- If you decide to play a setting with a computer, note that a 3-step
	  minimax algorithm takes about 24.857 seconds to complete.
	- The game log does not go any further than 41 moves (both player and
	  opponent), so please just export the game log if you would like to save
	  it.
	- The time it takes for the computer to look ahead:
	5 moves: A loooong time (probably will just take up too much memory)
	4 moves: <Needed to force-quit both tries>
	3 moves: 24.857 s
	2 moves: 1.717 s
	1 move: 0.077 s
	- Ideally, this computer would have a database of moves for up to about 20
	  moves.
Classes:
	class Game:
		I created the Game class in order to make it easy to redesign the
	 	"choosing" JFrame and implement other games by adding more buttons (or
	 	whatever selection method the redesigned code is using) and more
	 	packages.
	package chess: Everything a piece of code needs to play a chess game.
		class chess.ChessBoard: A chess board on which "ChessPiece"s can be
		                        moved.
		class chess.ChessGame: The top-level chess game that contains all of
		                       the graphics necessary for the user interface.
		class chess.ChessPiece: Encapsulates a chess piece that is on a chess
		                        board.
		class chess.GameLog: The log for the game.
		class chess.MinimaxBranch:
		class chess.Move: The class that represents a chess-game move.
		class chess.Position: A location (position) on the chess board.
		interface chess.ChessPieces: The characters and values of the chess
		                             pieces.
		interface chess.Moves: The possible types of moves, as well as the
		                       possible modifiers.
		package chess.piece: All of the chess pieces (as well as the moves they
		                     can make embedded in methods)
			class chess.piece.Bishop
			class chess.piece.King
			class chess.piece.Knight
			class chess.piece.Pawn
			class chess.piece.Queen
			class chess.piece.Rook