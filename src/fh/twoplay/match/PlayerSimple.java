package fh.twoplay.match;


import java.util.List;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.base.IPlayer;
import fh.twoplay.games.ABoard;
import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.BlankField;

public class PlayerSimple implements IPlayer {

	private ABoard board;
	private boolean isWhite;

	public PlayerSimple(IBoard board, boolean isWhite) {
		super();
		this.board = (ABoard) board;
		this.isWhite = isWhite;

	}

	@Override
	public boolean isWhite() {
		return this.isWhite;
	}

	@Override
	public boolean hasWon() {
		return isWhite() ? board.evaluate() == board.WHITEWINS : board
				.evaluate() == board.BLACKWINS;
	}

	@Override
	public boolean hasLost() {
		return isWhite() ? board.evaluate() == board.BLACKWINS : board
				.evaluate() == board.WHITEWINS;
	}

	@Override
	public String nextOwnMove() {

		String choosedMove = "";
		String betterMove = "";
		double points = board.evaluate();

		List<APawn> tempList = null;
		if (isWhite()) {
			tempList = board.getWhitePawns();
		} else {
			tempList = board.getBlackPawns();
		}
		
		for (APawn aPawn : tempList) { // get moves from every pawn

			String temp = board.getMoves(aPawn.positionToString());

			String[] moves = temp.split(" "); // makes an array with moves

			// System.out.println(board.getMoves(aPawn.positionToString()));
			for (String move : moves) { // for every move

				// System.out.println("move is" + move);
				ABoard cloneBoard = (ABoard) this.board.clone(); // clone the
																	// board
				EStatus status = cloneBoard.move(move); // make the move on the
														// cloned

				if (status == EStatus.LEGAL || status == EStatus.FINAL) {
					double currentPoints = cloneBoard.evaluate(); // evaluate
																	// the move
					choosedMove = move;
					if (isWhite()) {
						if (currentPoints > points) {
							points = currentPoints;
							betterMove = move;
						}

					} else if (!isWhite()) {
						if (currentPoints < points) {
							points = currentPoints;
							betterMove = move;

						}
					}
					
					if (status == EStatus.FINAL) {
						board.move(choosedMove.toUpperCase());
						return choosedMove;
					}
				} else {
					continue;
				}

			}
		}
		if (betterMove.equals("")) {
			this.board.move(choosedMove);
			return choosedMove.toUpperCase();
		} else {
			this.board.move(betterMove);
			return betterMove.toUpperCase();
		}
	}

	@Override
	public EStatus nextOppMove(String move) {
		return this.board.move(move);
	}

	public String toString() {
		board.toString();
		return isWhite() ? "true" : "false";

	}

	private void miniMax() {
		// //minimax(level, player) // player may be "computer" or "opponent"
		// if (gameover || level == 0)
		// return score
		// children = all legal moves for this player
		// if (player is computer, i.e., maximizing player)
		// // find max
		// bestScore = -inf
		// for each child
		// score = minimax(level - 1, opponent)
		// if (score > bestScore) bestScore = score
		// return bestScore
		// else (player is opponent, i.e., minimizing player)
		// // find min
		// bestScore = +inf
		// for each child
		// score = minimax(level - 1, computer)
		// if (score < bestScore) bestScore = score
		// return bestScore
		//
		// // Initial Call
		// minimax(2, computer)
	}

}
