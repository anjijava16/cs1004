package chess;

import java.awt.Color;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;

public class MinimaxBranch implements Runnable {

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
	private ArrayList<MinimaxBranch> branches = new ArrayList<MinimaxBranch>();

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
	}

	protected MinimaxBranch(
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
	}

	public synchronized void run() {
		chosenMove = getOptimalMove();
		lock.unlock();
	}

	private synchronized Move getOptimalMove() {
		if (lastEdge != null)
			lastEdge.setScore();

		// If the depth is 0 or minimax is at a terminating node, return the
		// last element in the edges vector, the heuristic value of the node.
		if (depth == 0 || lastEdge != null && lastEdge.doesCheckMate())
			return lastEdge;

		// Get all of the possible moves.
		ArrayList<Move> moves = new ArrayList<Move>();
		Color callerColor = node.oppositeColor(lastEdge.getPiece().getColor());
		for (ChessPiece piece : node.getPieces(callerColor))
			moves.addAll(piece.getMoves());

		// What will be the possible optimal moves, looking depth steps ahead.
		ArrayList<Move> choices = new ArrayList<Move>();

		// Get the best move
		if (maximize) {
			// Create more MinimaxBranchs and acquire their optimal moves.
			for (Move move : moves) {
				// Create another branch that will find its own optimal move.
				MinimaxBranch branch =
						new MinimaxBranch(
								node.unreportedMove(move),
								move,
								depth - 1,
								false);

				// Get the optimal move.
				branch.run();

				// Add this branch to the branches ArrayList
				branches.add(branch);
			}

			// Lock all of the branches until they finish.
			for (MinimaxBranch branch : branches)
				branch.lock.lock();

			// Add the move(s) with the greatest score to choices
			for (MinimaxBranch branch : branches) {
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
			// Create more MinimaxBranchs and acquire their optimal moves.
			for (Move move : moves) {
				// Create another branch that will find its own optimal move.
				MinimaxBranch branch =
						new MinimaxBranch(
								node.unreportedMove(move),
								move,
								depth - 1,
								true);

				// Get the optimal move.
				branch.run();

				// Add this branch to the branches ArrayList
				branches.add(branch);
			}

			// Lock all of the branches until they finish.
			for (MinimaxBranch branch : branches)
				branch.lock.lock();

			// Add the move(s) with the greatest score to choices
			for (MinimaxBranch branch : branches) {
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
