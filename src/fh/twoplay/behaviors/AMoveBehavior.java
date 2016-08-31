/**
 * 
 */
package fh.twoplay.behaviors;

import fh.twoplay.helper.ColumnNames;
import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.BlankField;
import fh.twoplay.pawns.Dame;

/**
 * @author Natasza Szczypien
 *
 */
public abstract class AMoveBehavior {
	
// ---------------------------------------------------------------------------------
// Constants
// ---------------------------------------------------------------------------------
	static final double BEHIND_LEFT = 0;
	static final double OVER_BOARD = 0;

	final String BLANK_FIELD = " ";

	/**
	 * @param board
	 * @param pawn
	 * @return all possible moves
	 */
	public abstract String getMoves(APawn[][] board, APawn pawn);
	
	/**
	 * @return one move
	 */
	protected String getMove(APawn[][] board, APawn pawn, int directionX, int directionY) {
		if (!isBehindBoard(directionX, directionY, board)
				&& enemyOrBlankField(board, pawn, directionX, directionY)) {
			return pawn.positionToString()
					+ ColumnNames.getXtoString(( directionY))
					+ (ColumnNames.getYtoString(directionX))
					+ BLANK_FIELD;
		} else {
			return "";
		}
	}
	
	//---------------------------------------------------------------------------------
	// Questions
	//---------------------------------------------------------------------------------	
		protected boolean isBehindBoard(int x, int y, APawn[][] board) {
			return x < BEHIND_LEFT | y < OVER_BOARD | y > board.length - 1
					| x > board.length -1;
		}
		
		/**
		 * @param board
		 * @param pawn
		 * @param x
		 * @param y
		 * @return
		 */
		protected boolean enemyOrBlankField(APawn[][] board, APawn pawn, int x, int y) {
			APawn enemy = board[x][y];
			return (enemy.getColor().toUpperCase().equals(pawn.getColor()
					.toUpperCase())) ? false : true;
		}
		
		/**
		 * @param board
		 * @param pawn
		 * @param x
		 * @param y
		 * @return
		 */
		protected boolean isenemy(APawn[][] board, APawn pawn, int x, int y) {
			APawn enemy = board[x][y];
			return (enemy.getColor().toUpperCase().equals(pawn.getColor()
					.toUpperCase())) ? false : true;
		}

		/**
		 * @param board
		 * @param pawn
		 * @param directionX
		 * @param directionY
		 * @return
		 */
		protected boolean isblankField(APawn[][] board, APawn pawn, int directionX,
				int directionY) {
			APawn enemy = board[directionX][directionY];
			return enemy instanceof BlankField;
		}
		
		

}
