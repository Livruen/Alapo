package fh.twoplay.behaviors;

import fh.twoplay.pawns.APawn;

public class VerticalUP extends AMoveBehavior {

	@Override
	public String getMoves(APawn[][] board, APawn pawn) {
		String moves = "";
		// Front move
		if (pawn.isSmall()) {
			if (pawn.isWhite()) {
				moves = getFrontMove(board, pawn, moves);
			} else {
				moves = getBackMove(board, pawn, moves);
			}

		} else {
			if (pawn.isWhite()) {
				moves = getFrontMove(board, pawn, pawn.getX(), pawn.getY(), moves);
			} else {
				moves = getBackMove(board, pawn, pawn.getX(), pawn.getY(), moves);
			}
		}
		return moves;
	}

	/*-------------------------------------------
	 * Small moves
	 * ------------------------------------------
	 * */
	protected String getFrontMove(APawn[][] board, APawn pawn, String moves) {
		int x = pawn.getX() + pawn.UP;
		int y = pawn.getY() + pawn.FRONT;

		return moves += getMove(board, pawn, x, y);

	}

	/*-------------------------------------------
	 * Big moves
	 * ------------------------------------------
	 * */
	protected String getFrontMove(APawn[][] board, APawn pawn, int directionX, int directionY, String moves) {

		directionX += pawn.UP;
		directionY += pawn.FRONT;

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;

		} else {
			if (isblankField(board, pawn, directionX, directionY)) {

				moves += getMove(board, pawn, directionX, directionY);
				return getFrontMove(board, pawn, directionX, directionY, moves);

			} else if (isenemy(board, pawn, directionX, directionY)) { // something
																		// to
																		// attack
				return moves += getMove(board, pawn, directionX, directionY);

			} else { // my own pawn
				return moves;
			}
		}
	}

	protected String getBackMove(APawn[][] board, APawn pawn, String moves) {
		int x = pawn.getX() + pawn.DOWN;
		int y = pawn.getY() + pawn.FRONT;

		return moves += getMove(board, pawn, x, y);

	}

	protected String getBackMove(APawn[][] board, APawn pawn, int directionX, int directionY, String moves) {
		directionX += pawn.DOWN;
		directionY += pawn.FRONT;

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;

		} else {
			if (isblankField(board, pawn, directionX, directionY)) {

				moves += getMove(board, pawn, directionX, directionY);
				return getBackMove(board, pawn, directionX, directionY, moves);

			} else if (isenemy(board, pawn, directionX, directionY)) { // something
																		// to
																		// attack
				return moves += getMove(board, pawn, directionX, directionY);

			} else { // my own pawn
				return moves;
			}
		}
	}

}
