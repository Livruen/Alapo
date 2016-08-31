package fh.twoplay.behaviors;

import fh.twoplay.pawns.APawn;

public class DiagonalUPandDOWN extends DiagonalUP {
	@Override
	public String getMoves(APawn[][] board, APawn pawn) {
		String moves = "";
		// Front move
		if(pawn.isSmall()){
			
			moves = getDiagonalLeftFrontMoves(board, pawn, moves);
			moves = getDiagonalRightFrontMoves(board, pawn, moves);
			moves = getDiagonalLeftBackMoves(board, pawn, moves);
			moves = getDiagonalRightBackMoves(board, pawn, moves);
		}else {
			moves = getDiagonalLeftFrontMoves(board, pawn, pawn.getX(), pawn.getY(), moves);
			moves = getDiagonalRightFrontMoves(board, pawn,pawn.getX(), pawn.getY(), moves);
			moves = getDiagonalLeftBackMoves(board, pawn,pawn.getX(), pawn.getY(), moves);
			moves = getDiagonalRightBackMoves(board, pawn,pawn.getX(), pawn.getY(), moves);
		}
		

		return moves;
	}
	
}
