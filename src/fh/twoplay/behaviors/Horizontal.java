package fh.twoplay.behaviors;

import fh.twoplay.pawns.APawn;

public class Horizontal extends AMoveBehavior {

	@Override
	public String getMoves(APawn[][] board, APawn pawn) {
		
		String moves = "";
		if(pawn.isSmall()){
			moves = getLeftMove(board,pawn, moves);
			moves = getRightMove(board,pawn, moves);
		}else {
			moves = getLeftMove(board, pawn,pawn.getX(), pawn.getY(), moves);
			moves = getRightMove(board, pawn, pawn.getX(), pawn.getY(), moves);
		}
		
		return moves;
	}
	
	/*----------------------------
	 * Small moves
	 * ---------------------------
	 * */
	
	private String getRightMove(APawn[][] board, APawn pawn, String moves) {
		int x =pawn.getX() +  pawn.FRONT;
		int y = pawn.getY() + pawn.RIGHT;
	
		return moves += getMove(board, pawn, x, y);
		
	}

	private String getLeftMove(APawn[][] board,APawn pawn, String moves) {
		int x = pawn.getX() + pawn.FRONT;
		int y = pawn.getY() + pawn.LEFT;
		
		return moves += getMove(board, pawn, x, y);
		
	}
	
	/*----------------------------
	 * Big moves
	 * ---------------------------
	 * */
	private String getRightMove(APawn[][] board, APawn pawn,int directionX,
			int directionY,  String moves) {
		directionX += pawn.FRONT;
		directionY += + pawn.RIGHT;

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;
			
		} else {
			if(isblankField(board, pawn, directionX, directionY)){
				
				moves += getMove(board, pawn, directionX, directionY);
				return getRightMove(board, pawn, directionX, directionY, moves);
				
			}else if(isenemy(board, pawn, directionX, directionY)){ //something to attack
				return moves += getMove(board, pawn, directionX, directionY);
				
			}else { // my own pawn
				return moves;
			}
		}
	
	}

	private String getLeftMove(APawn[][] board, APawn pawn,int directionX,
			int directionY, String moves) {
		directionX += pawn.FRONT;
		directionY +=  pawn.LEFT;

		if (isBehindBoard(directionX, directionY, board)) {
			return moves;
			
		} else {
			if(isblankField(board, pawn, directionX, directionY)){
				
				moves += getMove(board, pawn, directionX, directionY);
				return getLeftMove(board, pawn, directionX, directionY, moves);
				
			}else if(isenemy(board, pawn, directionX, directionY)){ //something to attack
				return moves += getMove(board, pawn, directionX, directionY);
				
			}else { // my own pawn
				return moves;
			}
		}
	}

}
