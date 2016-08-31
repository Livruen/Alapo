package fh.twoplay.behaviors;

import fh.twoplay.pawns.APawn;

public class VerticalUPandDOWN extends VerticalUP {
	
	/*
	 * @see
	 * fh.twoplay.alapo.behaviors.MoveForwardBehavior#getMoves(fh.twoplay.alapo
	 * .pawns.APawn[][], fh.twoplay.alapo.pawns.APawn)
	 */
	@Override
	public String getMoves(APawn[][] board, APawn pawn) {
		String moves = "";
		if(pawn.isSmall()){
			moves = getFrontMove(board,pawn, moves);
			moves = getBackMove(board,pawn, moves);
		}else {
			moves = getFrontMove(board, pawn, pawn.getX(), pawn.getY(), moves);
			moves = getBackMove(board, pawn, pawn.getX(), pawn.getY(), moves);

		}
		return moves;
	}

}
