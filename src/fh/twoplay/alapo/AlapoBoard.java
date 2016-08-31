/**
 * 
 */
package fh.twoplay.alapo;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Pattern;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.breakthrough.BreakBoard;
import fh.twoplay.games.ABoard;
import fh.twoplay.helper.ColumnNames;
import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.BlankField;
import fh.twoplay.pawns.PawnFabric;

/**
 * @author Natasza Szczypien
 *
 */
public class AlapoBoard extends ABoard {

	// --------------------------------------------------------------------
	// Constructor
	// --------------------------------------------------------------------

	private static final int ALAPO_SIZE = 6;

	public AlapoBoard() {
		
		defaultDescription = "STA6 SLB6 SDC6 SDD6 SLE6 STF6 stA5 slB5 sdC5 sdD5 slE5 stF5 "
				+ "WTA1 WLB1 WDC1 WDD1 WLE1 WTF1 wtA2 wlB2 wdC2 wdD2 wlE2 wtF2";
		
		setSize(ALAPO_SIZE);
		
		board = new APawn[ALAPO_SIZE][ALAPO_SIZE];
		MAX_PAWNS_ON_FIELD = 12;

		ColumnNames.setColumnIndex(createColumnIndex());
		ColumnNames.setColumnIndexS(createColumnIndexS());
		ColumnNames.setSize(ALAPO_SIZE);
		
		whitePawns = new ArrayList<APawn>();
		blackPawns = new ArrayList<APawn>();

		this.init(defaultDescription);
	}


	// --------------------------------------------------------------------
	// Board functions
	// --------------------------------------------------------------------

	/*
	 * @see fh.twoplay.base.IBoard#move(java.lang.String)
	 */
	@Override
	public EStatus move(String turn) {

		turn = turn.toUpperCase();

		/* Pattern = A3A2 */
		if (Pattern.matches("[A-F][1-6][A-F][1-6]", turn)) {

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

						if (whitePoints == MAX_PAWNS_ON_FIELD || blackPoints == MAX_PAWNS_ON_FIELD || finalMove == 1) {
							status = EStatus.FINAL;
							setWinner(pawn);

						} else if (pawn.wins(board)) {
							if (finalMove == 0) {
								//TODO: Hier sollte der pawn gemerkt werden
								status = firstFinal();
							} else if (finalMove == 1) {
								status = EStatus.FINAL;
								setWinner(pawn);
							}
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
		} else {
			return EStatus.SYNTAXERROR;
		}

	}


	private EStatus firstFinal() {
		EStatus status;
		finalMove = 1;
		status = EStatus.LEGAL;
		return status;
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
		if (whitePoints == WHITEWINS) {
			return WHITEWINS;
		}
		if (blackPoints == BLACKWINS) {
			return BLACKWINS;
		}
		if (whitePoints > blackPoints) {
			return whitePoints;
		} else {
			return -blackPoints;
		}
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
		System.out.printf("------------------------[%.0f:%.0f] \n", whitePoints, blackPoints);
		System.out.print("   A  B  C  D  E  F  \n");
	}
	
	protected HashMap<String, Integer> createColumnIndex() {
		HashMap<String, Integer> columnIndex = new HashMap<String, Integer>();
		columnIndex.put("A", 0);
		columnIndex.put("B", 1);
		columnIndex.put("C", 2);
		columnIndex.put("D", 3);
		columnIndex.put("E", 4);
		columnIndex.put("F", 5);
		return columnIndex;		
	}
	
	public HashMap<Integer, String> createColumnIndexS() {
		HashMap<Integer, String> columnIndexS = new HashMap<Integer, String>();
		columnIndexS.put(0, "A");
		columnIndexS.put(1, "B");
		columnIndexS.put(2, "C");
		columnIndexS.put(3, "D");
		columnIndexS.put(4, "E");
		columnIndexS.put(5, "F");
		return columnIndexS;
	
	}
	
	@Override
	public IBoard clone() {
		AlapoBoard cloneBoard = new AlapoBoard();
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

		return cloneBoard;
	}

}
