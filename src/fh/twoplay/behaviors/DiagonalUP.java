package fh.twoplay.behaviors;

import fh.twoplay.pawns.APawn;

public class DiagonalUP extends AMoveBehavior {

	@Override
	public String getMoves(APawn[][] board, APawn pawn) {
		String moves = "";
		// Front move
		if(pawn.isSmall()){
			if(pawn.isWhite()){
				moves = getDiagonalLeftFrontMoves(board, pawn, moves);
				moves = getDiagonalRightFrontMoves(board, pawn, moves);	
			}else {
				moves = getDiagonalLeftBackMoves(board, pawn, moves);
				moves = getDiagonalRightBackMoves(board, pawn, moves);
			}

		}else {
			if(pawn.isWhite()){
				moves = getDiagonalLeftFrontMoves(board, pawn, pawn.getX(), pawn.getY(), moves);
				moves = getDiagonalRightFrontMoves(board, pawn,pawn.getX(), pawn.getY(), moves);
			
			}else {
				moves = getDiagonalLeftBackMoves(board, pawn,pawn.getX(), pawn.getY(), moves);
				moves = getDiagonalRightBackMoves(board, pawn,pawn.getX(), pawn.getY(), moves);
			
			}
			
		}
	
		return moves;
	}
	/*-----------------------------------------
	 * SMALL DIAGONAL 
	 * ----------------------------------------
	 * */

	/**
	 * @param board
	 * @param pawn
	 * @param moves
	 * @return
	 */
	protected String getDiagonalRightFrontMoves(APawn[][] board, APawn pawn,
			String moves) {
		
			int x = pawn.getX() + pawn.UP;
			int y = pawn. getY() + pawn.RIGHT;
		
		

		return moves += getMove(board, pawn, x, y);

	}

	/**
	 * @param board
	 * @param pawn
	 * @param moves
	 * @return
	 */
	protected String getDiagonalLeftFrontMoves(APawn[][] board, APawn pawn,
			String moves) {
		
			int x = pawn.getX() + pawn.UP;
			int y = pawn.getY() + pawn.LEFT;
		
		

		return moves += getMove(board, pawn, x, y);

	}

	
	/*-----------------------------------------
	 * BIG DIAGONAL
	 * ----------------------------------------
	 * */
	

	/**
	 * @param board
	 * @param pawn
	 * @param directionY 
	 * @param directionX 
	 * @param moves
	 * @return
	 */
	protected String getDiagonalRightFrontMoves(APawn[][] board, APawn pawn,
			int directionX, int directionY, String moves) {
	
			directionX = directionX + pawn.UP;
			directionY = directionY + pawn.RIGHT;
		
	

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;
			
		} else {
			if(isblankField(board, pawn, directionX, directionY)){
				
				moves += getMove(board, pawn, directionX, directionY);
				return getDiagonalRightFrontMoves(board, pawn, directionX, directionY, moves);
				
			}else if(isenemy(board, pawn, directionX, directionY)){ //something to attack
				return moves += getMove(board, pawn, directionX, directionY);
				
			}else { // my own pawn
				return moves;
			}
		}
	}

	/**
	 * @param board
	 * @param pawn
	 * @param directionY 
	 * @param directionX 
	 * @param moves
	 * @return
	 */
	protected String getDiagonalLeftFrontMoves(APawn[][] board, APawn pawn,
			int directionX, int directionY, String moves) {
		directionX += -1;
		directionY +=  -1;

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;
			
		} else {
			if(isblankField(board, pawn, directionX, directionY)){
				
				moves += getMove(board, pawn, directionX, directionY);
				return getDiagonalLeftFrontMoves(board, pawn, directionX, directionY, moves);
				
			}else if(isenemy(board, pawn, directionX, directionY)){ //something to attack
				return moves += getMove(board, pawn, directionX, directionY);
				
			}else { // my own pawn
				return moves;
			}
		}

	}
	
	/*-------------------------------------------------------------
	 * Small DIAGONAL
	 * -------------------------------------------------------------
	 * */

	/**
	 * @param board
	 * @param pawn
	 * @param moves
	 * @return
	 */
	protected String getDiagonalRightBackMoves(APawn[][] board, APawn pawn,
			String moves) {
		int x = pawn.getX() +  pawn.DOWN;
		int y = pawn.getY() + pawn.RIGHT;

		moves += getMove(board, pawn, x, y);

		return moves;
	}

	/**
	 * @param board
	 * @param pawn
	 * @param moves
	 * @return
	 */
	protected String getDiagonalLeftBackMoves(APawn[][] board, APawn pawn,
			String moves) {
		int x = pawn.getX() + pawn.DOWN;
		int y = pawn.getY() + pawn.LEFT;

		return moves += getMove(board, pawn, x, y);

	}
	
	/*-------------------------------------------------------------
	 * BIG DIAGONAL
	 * ------------------------------------------------------------
	 * */
	/**
	 * @param board
	 * @param pawn
	 * @param directionY 
	 * @param directionX 
	 * @param moves
	 * @return
	 */
	protected String getDiagonalRightBackMoves(APawn[][] board, APawn pawn,
			int directionX, int directionY, String moves) {
		directionX += 1;
		directionY +=  1;

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;
			
		} else {
			if(isblankField(board, pawn, directionX, directionY)){
				
				moves += getMove(board, pawn, directionX, directionY);
				return getDiagonalRightBackMoves(board, pawn, directionX, directionY, moves);
				
			}else if(isenemy(board, pawn, directionX, directionY)){ //something to attack
				return moves += getMove(board, pawn, directionX, directionY);
				
			}else { // my own pawn
				return moves;
			}
		}
	}

	/**
	 * @param board
	 * @param pawn
	 * @param directionY 
	 * @param directionX 
	 * @param moves
	 * @return
	 */
	protected String getDiagonalLeftBackMoves(APawn[][] board, APawn pawn,
			int directionX, int directionY, String moves) {
		directionX = directionX +  1;
		directionY += -1;

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;
			
		} else {
			if(isblankField(board, pawn, directionX, directionY)){
				
				moves += getMove(board, pawn, directionX, directionY);
				return getDiagonalLeftBackMoves(board, pawn, directionX, directionY, moves);
				
			}else if(isenemy(board, pawn, directionX, directionY)){ //something to attack
				return moves += getMove(board, pawn, directionX, directionY);
				
			}else { // my own pawn
				return moves;
			}
		}

	}



}
