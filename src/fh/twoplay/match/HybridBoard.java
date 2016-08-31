package fh.twoplay.match;

import java.awt.Point;

import fh.twoplay.alapo.AlapoBoard;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.breakthrough.BreakBoard;
import fh.twoplay.games.ABoard;
import fh.twoplay.helper.ColumnNames;
import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.BlankField;

public class HybridBoard extends AlapoBoard {
	
	private static final int HYBRID_SIZE = 6;
	
	public HybridBoard() {
		
				defaultDescription = "WTA1 WLB1 WDC1 WDD1 WLE1 WTF1 WBA2 WBB2 WBC2 WBD2 WBE2 WBF2 "
						+ "STA6 SLB6 SDC6 SDD6 SLE6 STF6 SBA5 SBB5 SBC5 SBD5 SBE5 SBF5";
			//	defaultDescription ="WBA6 WBB7 WBF6 WBF7 SBC2 SBC1 SBF2 SBG1";
				setSize(HYBRID_SIZE);
				MAX_PAWNS_ON_FIELD = 14;
				board = new APawn[HYBRID_SIZE][HYBRID_SIZE];

				ColumnNames.setColumnIndex(createColumnIndex());
				ColumnNames.setColumnIndexS(createColumnIndexS());
				ColumnNames.setSize(HYBRID_SIZE);
				
				this.init(this.defaultDescription);
			
	}
	
	@Override
	public IBoard clone() {
		HybridBoard cloneBoard = new HybridBoard();
		

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
