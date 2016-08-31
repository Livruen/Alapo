/**
 * 
 */
package fh.twoplay.behaviors;

import fh.twoplay.pawns.APawn;

/**
 * @author Natasza Szczypien
 *
 */
public class CannotMove extends AMoveBehavior {

	/* (non-Javadoc)
	 * @see fh.twoplay.alapo.behaviors.MoveDiagonalBehavior#getMoves(fh.twoplay.alapo.pawns.APawn[][], fh.twoplay.alapo.pawns.APawn)
	 */
	@Override
	public String getMoves(APawn[][] board, APawn pawn) {
		return "";
	}

}
