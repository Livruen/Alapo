/**
 * 
 */
package fh.twoplay.breakthrough;

import java.awt.Point;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.games.ABoard;
import fh.twoplay.helper.ColumnNames;
import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.BlankField;

/**
 * @author Natasza Szczypien
 *
 */
public class BreakBoard extends ABoard {

	private static final int BREAKBOARD_SIZE = 7;

	/**
	 * ----------------------------- CONSTRUCTOR
	 * --------------------------------------------------------
	 * 
	 * 
	 * */

	public BreakBoard() {
		defaultDescription = "WBA1 WBB1 WBC1 WBD1 WBE1 WBF1 WBG1 WBA2 WBB2 WBC2 WBD2 WBE2 WBF2 WBG2 "
				+ "SBA6 SBB6 SBC6 SBD6 SBE6 SBF6 SBG6 SBA7 SBB7 SBC7 SBD7 SBE7 SBF7 SBG7";
		// defaultDescription ="WBA6 WBB7 WBF6 WBF7 SBC2 SBC1 SBF2 SBG1";
		setSize(BREAKBOARD_SIZE);
		MAX_PAWNS_ON_FIELD = 14;
		board = new APawn[BREAKBOARD_SIZE][BREAKBOARD_SIZE];

		ColumnNames.setColumnIndex(createColumnIndex());
		ColumnNames.setColumnIndexS(createColumnIndexS());
		ColumnNames.setSize(BREAKBOARD_SIZE);

		whitePawns = new ArrayList<APawn>();
		blackPawns = new ArrayList<APawn>();

		this.init(this.defaultDescription);
	}

	/**
	 * ----------------------------- BOARD FUNCTIONS
	 * --------------------------------------------------------
	 * 
	 * 
	 * */

	/*
	 * @see fh.twoplay.base.IBoard#move(java.lang.String)
	 */
	@Override
	public EStatus move(String turn) {

		turn = turn.toUpperCase();

		/* Pattern = A3A2 */
		if (Pattern.matches("[A-G][1-7][A-G][1-7]", turn)) {

			StringBuffer buffer = new StringBuffer(turn);

			String move1 = getFirstMove(buffer);
			String move2 = getSecondMove(buffer);

			Point p1 = getPawnPositionOnField(move1);
			Point p2 = getPawnPositionOnField(move2);

			/* Checks if the moves are in board range */
			if (inBoardRange(p1) && inBoardRange(p2)) {

				/*
				 * gets the pawns fron board, if there is no pawn then enemy =
				 * null (BLANK FIELD)
				 */
				APawn pawn = getPawn(p1);
				APawn enemy = getPawn(p2);

				if (pawn instanceof BlankField) {
					return EStatus.NOTOKEN;

				} else if (!isPlayersTurn(pawn)) {
					return EStatus.WRONGCOLOR;

				} else {
					EStatus status = null;

					if (legalMove(turn, pawn)) {

						movePawn(pawn, p1, p2);
						setPoints(pawn, enemy);

						if (whitePoints == MAX_PAWNS_ON_FIELD
								|| blackPoints == MAX_PAWNS_ON_FIELD) {
							status = EStatus.FINAL;
							setWinner(pawn);

						} else if (pawn.wins(board)) {

							status = EStatus.FINAL;
							setWinner(pawn);

						} else {
							status = EStatus.LEGAL;
						}

						changeTurn();

					} else {
						status = EStatus.ILLEGAL;
					}

					return status;
				}

			} else {
				return EStatus.OUTOFRANGE;
			}
		} else if (Pattern.matches("[A-F][1-7]", turn)) {
			System.out.println(getMoves(turn));
			return EStatus.POSSIBILITIES;
		} else {
			return EStatus.SYNTAXERROR;
		}

	}



	

	/*
	 * (non-Javadoc)
	 * 
	 * @see fh.twoplay.base.IBoard#evaluate()
	 */
	@Override
	public double evaluate() {
		if (whitePoints == blackPoints) {
			return 0;
		}
		if (whitePoints > blackPoints) {
			return whitePoints;
		} else {
			return -blackPoints;
		}
	}

	private HashMap<String, Integer> createColumnIndex() {

		HashMap<String, Integer> columnIndex = new HashMap<String, Integer>();
		columnIndex.put("A", 0);
		columnIndex.put("B", 1);
		columnIndex.put("C", 2);
		columnIndex.put("D", 3);
		columnIndex.put("E", 4);
		columnIndex.put("F", 5);
		columnIndex.put("G", 6);
		return columnIndex;
	}

	private HashMap<Integer, String> createColumnIndexS() {
		HashMap<Integer, String> columnIndexS = new HashMap<Integer, String>();
		columnIndexS.put(0, "A");
		columnIndexS.put(1, "B");
		columnIndexS.put(2, "C");
		columnIndexS.put(3, "D");
		columnIndexS.put(4, "E");
		columnIndexS.put(5, "F");
		columnIndexS.put(6, "G");
		return columnIndexS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fh.twoplay.base.IBoard#clone()
	 */

	@Override
	public IBoard clone() {
		BreakBoard cloneBoard = new BreakBoard();
		cloneBoard.boardSize = this.boardSize;
		cloneBoard.playerTurn = this.playerTurn;
		cloneBoard.defaultDescription = this.defaultDescription;
		cloneBoard.whitePoints = this.whitePoints;
		cloneBoard.blackPoints = this.blackPoints;
		cloneBoard.finalMove = this.finalMove;
		
		cloneBoard.blackPawns.clear();
		cloneBoard.whitePawns.clear();
		
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				APawn pawn = this.getPawn(new Point(i, j)).clone();
				cloneBoard.board[i][j] = pawn;
				
				if(pawn.isWhite()){
					cloneBoard.whitePawns.add(pawn);
				}else {
					if(!(pawn instanceof BlankField)){
						cloneBoard.blackPawns.add(pawn);
					}
				}
			}
		}
//		for (int i = 0; i < board.length; i++) {
//			for (int j = 0; j < board.length; j++) {
//				APawn pawn = this.getPawn(new Point(i, j));
//				cloneBoard.board[i][j] = pawn.clone();
//			}
//		}
//
//		cloneBoard.boardSize = this.boardSize;
//		cloneBoard.playerTurn = this.playerTurn;
//		cloneBoard.defaultDescription = this.defaultDescription;
//		cloneBoard.whitePoints = this.whitePoints;
//		cloneBoard.blackPoints = this.blackPoints;
//		cloneBoard.finalMove = this.finalMove;
//		
//		cloneBoard.blackPawns.clear();
//		cloneBoard.whitePawns.clear();
//		for (int i = 0; i < blackPawns.size(); i++) {
//			cloneBoard.blackPawns.add(this.blackPawns.get(i).clone());
//		}
//		
//		for (int i = 0; i < whitePawns.size(); i++) {
//			cloneBoard.whitePawns.add(this.whitePawns.get(i).clone());
//		}
		
		return cloneBoard;
	}

	public String toString() {
		display();
		return "Make move";
	}

	public void display() {
		for (int row = 0; row < board.length; row++) {
			System.out.print((board.length - row) + " |");
			for (int column = 0; column < board.length; column++) {
				APawn pawn = board[row][column];
				if (pawn != null) {
					System.out.print(pawn.toString() + " ");
				} else {
					System.out.print(".. ");
				}
			}
			System.out.println();

		}
		System.out.printf("------------------------[%.0f:%.0f] \n",
				whitePoints, blackPoints);
		System.out.print("   A  B  C  D  E  F  G\n");
	}
	
	public static void main(String[] args) {
		BreakBoard b = new BreakBoard();
		b.move("A2A3");
		System.out.println(b.getDescription());
	}

}
