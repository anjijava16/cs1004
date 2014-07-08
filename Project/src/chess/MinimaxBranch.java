package chess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MinimaxBranch extends Thread {

	/**
	 * The last edge of this move.
	 */
	private final Move lastEdge;

	/**
	 * The node the branch is currently at.
	 */
	private final ChessBoard node;

	/**
	 * How many steps (branches) this branch should look ahead.
	 */
	private final int depth;

	/**
	 * Whether to maximize or minimize the score (i.e. whether it's the original
	 * caller's turn or the caller's opponent's turn)
	 */
	private final boolean maximize;

	/**
	 * The color of the caller.
	 */
	private final Color callerColor;

	/**
	 * The optimal move for the given player (the minimax algorithm assumes both
	 * players are perfect).
	 */
	protected Move chosenMove;

	/**
	 * The lock that tells the parent branch that this branch's run() method is
	 * still going on when it is.
	 */
	protected Lock lock;

	/**
	 * The minimax move branches (edge then node) that come from this node.
	 */
	private ArrayList<MinimaxBranch> children = new ArrayList<MinimaxBranch>();

	protected MinimaxBranch(
			ChessBoard node,
			Color callerColor,
			int depth,
			boolean maximize) {
		this.node = node;
		lastEdge = null;
		this.depth = depth;
		this.maximize = maximize;
		this.callerColor = callerColor;
		chosenMove = null;
		lock = new ReentrantLock(true);
		if (depth != 0)
			for (ChessPiece piece : node.getPieces(callerColor))
				for (Move move : piece.getMoves())
					children.add(new MinimaxBranch(
							node.unreportedMove(move),
							move,
							depth - 1,
							!maximize));
	}

	private MinimaxBranch(
			ChessBoard node,
			Move lastEdge,
			int depth,
			boolean maximize) {
		this.node = node;
		this.lastEdge = lastEdge;
		this.depth = depth;
		this.maximize = maximize;
		callerColor = node.oppositeColor(lastEdge.getPiece().getColor());
		chosenMove = null;
		lock = new ReentrantLock(true);
		if (depth != 0)
			for (ChessPiece piece : node.getPieces(callerColor))
				for (Move move : piece.getMoves())
					children.add(new MinimaxBranch(
							node.unreportedMove(move),
							move,
							depth - 1,
							!maximize));
	}

	public synchronized void run() {
		lock.lock();
		chosenMove = getOptimalMove();
		lock.unlock();
	}

	private synchronized Move getOptimalMove() {
		if (lastEdge != null)
			lastEdge.setScore();

		// If the last edge done does a checkmate, return that edge, which
		// contains the heuristic value of the node
		if (lastEdge != null && lastEdge.doesCheckMate())
			return lastEdge;

		// Get all of the possible moves.
		ArrayList<Move> moves = new ArrayList<Move>();
		for (ChessPiece piece : node.getPieces(callerColor))
			moves.addAll(piece.getMoves());

		// What will be the possible optimal moves, looking depth steps ahead.
		ArrayList<Move> choices = new ArrayList<Move>();

		// Get the best move
		if (maximize) {
			// Acquire childrens' optimal moves.
			for (MinimaxBranch branch : children)
				branch.start();

			// Acquire all of the branches' locks before evaluating a move.
			for (MinimaxBranch branch : children)
				branch.lock.lock();

			// Add the move(s) with the greatest score to choices
			for (MinimaxBranch branch : children) {
				Move move = branch.chosenMove;
				if (choices.size() == 0)
					choices.add(branch.lastEdge);
				else if (choices.get(0).getScore() < move.getScore()) {
					choices.clear();
					choices.add(branch.lastEdge);
				} else if (choices.get(0).getScore() == move.getScore())
					choices.add(branch.lastEdge);
			}
		} else {
			// Acquire childrens' optimal moves.
			for (MinimaxBranch branch : children)
				branch.start();

			// Lock all of the branches until they finish.
			for (MinimaxBranch branch : children)
				branch.lock.lock();

			// Add the move(s) with the greatest score to choices
			for (MinimaxBranch branch : children) {
				Move move = branch.chosenMove;
				if (choices.size() == 0)
					choices.add(branch.lastEdge);
				else if (choices.get(0).getScore() > move.getScore()) {
					choices.clear();
					choices.add(branch.lastEdge);
				} else if (choices.get(0).getScore() == move.getScore())
					choices.add(branch.lastEdge);
			}
		}

		// Return an optimal move, after looking ahead depth steps.
		return choices.get((int) (Math.random() * choices.size()));
	}

}
