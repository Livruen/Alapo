package fh.twoplay.games;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.helper.ColumnNames;
import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.BlankField;
import fh.twoplay.pawns.PawnFabric;
import fh.twoplay.shared.IllegalPositionOrPin;

public abstract class ABoard implements IBoard {

	/**
	 * 
	 */
	public static int MAX_PAWNS_ON_FIELD;
	// --------------------------------------------------------------------
	// Constants
	// --------------------------------------------------------------------
	private static final int BEFORE = 0;

	/* The board is a two dimensional Array filled with Pawns */
	protected APawn[][] board;

	protected int boardSize;

	/* Knows which player is now on turn */
	protected String playerTurn;

	/* the start parameters that creates the board */
	protected String defaultDescription;

	/* Points from White Player */
	protected double whitePoints;

	/* Points from Black player */
	protected double blackPoints;

	/* remembers a final move */
	public int finalMove;

	/* White Pawn list */
	protected ArrayList<APawn> whitePawns;

	/* Black Pawn list */
	protected ArrayList<APawn> blackPawns;

	/*-----------------------------------------
	 * GETTERS SETTERS
	 * -----------------------------------------
	 * */

	@Override
	public int getSize() {
		return boardSize;
	}

	/* When move is A3A2, returns A3 */
	protected String getFirstMove(StringBuffer buffer) {
		return "" + buffer.charAt(0) + buffer.charAt(1);
	}

	/* When move is A3A2, returns A2 */
	protected String getSecondMove(StringBuffer buffer) {
		return "" + buffer.charAt(2) + buffer.charAt(3);
	}

	public APawn[][] getBoard() {
		return board;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fh.twoplay.base.IBoard#getDescription()
	 */
	@Override
	public String getDescription() {
		String description = "";
		for (int row = 0; row < board.length; row++) {
			for (int column = 0; column < board.length; column++) {
				APawn pawn = board[row][column];
				if (!(pawn instanceof BlankField)) {
					description += pawn.toString() + pawn.positionToString()
							+ " ";
				}
			}
		}
		return description;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fh.twoplay.base.IBoard#getMoves(java.lang.String)
	 */
	@Override
	public String getMoves(String position) {

		if ((position == null)
				|| (!Pattern.matches(
						"[A-" + ColumnNames.getXtoString((boardSize - 1))
								+ "][1-" + boardSize + "]", position))) {
			throw new IllegalPositionOrPin();
		}

		StringBuffer buffer = new StringBuffer(position.toUpperCase());
		Point p = ColumnNames.preparePoint(buffer);

		if (isblankField(p)) {
			return null;
		} else {
			APawn pawn = getPawn(p);
			return pawn.getMoves(board);
		}
	}

	/* @return APawn on specific position on board */
	public APawn getPawn(Point position) {
		return board[position.x][position.y];
	}

	/**
	 * @param pawn
	 * @param sourc
	 * @param target
	 */
	protected void movePawn(APawn pawn, Point sourc, Point target) {
//		pawn.move(target, this.board);

		if (pawn.isWhite()) {
			removePawn(whitePawns, pawn);
			pawn.move(target, this.board);
			whitePawns.add(pawn);

		} else {
			removePawn(blackPawns, pawn);
			pawn.move(target, this.board);
			blackPawns.add(pawn);
		}
	}



	/*
	 * gets a string A3 returns the pawn position
	 */
	public Point getPawnPositionOnField(String position) {

		StringBuffer buffer = new StringBuffer(position.toUpperCase());

		Point p = ColumnNames.preparePoint(buffer);
		if (inBoardRange(p)) {
			return p;
		} else {
			return null;
		}
	}

	/* Sets the board size */
	protected void setSize(int size) {
		this.boardSize = size;
	}

	/*
	 * Sets the board with pawns. Uses a PawnFabric that gives him a Pawn by
	 * getting a pawn description
	 */
	protected void setPawnsOnBoard(String[] pawns) throws IllegalPositionOrPin {

		/* Fill board with blank fields */
		for (int i = 0; i < getSize(); i++) {
			for (int j = 0; j < getSize(); j++) {
				board[i][j] = new BlankField(new Point(i, j));
			}
		}
		whitePawns.clear();
		blackPawns.clear();
		for (String paw : pawns) {

			APawn pawn;
			pawn = PawnFabric.getPawn(paw, getSize());
			if (pawn.isWhite()) {
				whitePawns.add(pawn);
			} else {
				blackPawns.add(pawn);
			}
			this.board[pawn.getX()][pawn.getY()] = pawn;
			countPawns(pawn);
		}
	}

	/**
	 * @param pawn
	 * @param pawn
	 * @param enemy
	 */
	protected void setPoints(APawn pawn, APawn enemy) {
		if (!(enemy instanceof BlankField)) {
			if (pawn.isWhite()) {
				whitePoints++;
				removePawn(blackPawns, enemy);
			} else {
				blackPoints++;
				removePawn(whitePawns, enemy);
			}

		}
	}

	/**
	 * @param pawn
	 */
	protected void setWinner(APawn pawn) {
		if (pawn.isWhite()) {
			whitePoints = WHITEWINS;
		} else {
			blackPoints = BLACKWINS;
		}

	}

	// --------------------------------------------------------------------
	// Questions
	// --------------------------------------------------------------------

	/**
	 * Checks if the field is blank at a position p
	 */
	private boolean isblankField(Point p) {
		return getPawn(p) instanceof BlankField;
	}

	/**
	 * @return is the move in the board range or in the universe
	 */
	protected boolean inBoardRange(Point p1) {
		if (p1.getX() < BEFORE | p1.getX() > getSize() - 1 | p1.getY() < 0
				| p1.getY() > 6) {
			return false;
		}
		return true;
	}

	/* Checks if player is on turn */
	protected boolean isPlayersTurn(APawn pawn) {
		return (pawn.getColor().toUpperCase().equals(playerTurn));
	}

	// -----------------------------------------------------------
	// OTHERS
	// -----------------------------------------------------------
	/*
	 * @see fh.twoplay.base.IBoard#init(java.lang.String)
	 */
	@Override
	public void init(String description) {

		if (description == null) {
			throw new IllegalPositionOrPin();
		}

		if (!defaultDescription.equals(description)) {
			defaultDescription = description;
		}

		finalMove = 0;

		String[] pawns = description.trim().split(" ");

		/* Set points */
		blackPoints = whitePoints = MAX_PAWNS_ON_FIELD;

		try {

			setPawnsOnBoard(pawns);

		} catch (IllegalPositionOrPin e) {
			throw new IllegalPositionOrPin();
		}

		/* player white starts the game */
		playerTurn = APawn.WHITE_COLOR;
	}

	/**
	 * Change the players turn
	 */
	public void changeTurn() {
		if (playerTurn.equals(APawn.WHITE_COLOR)) {
			playerTurn = APawn.BLACK_COLOR;
		} else {
			playerTurn = APawn.WHITE_COLOR;
		}
	}

	/*
	 * Gets all moves from a specific pawn and checks if it contains the player
	 * move
	 */
	protected boolean legalMove(String turn, APawn pawn) {
		return pawn.getMoves(board).contains(turn);
	}

	/* Counts the white and black pawns on field */
	private void countPawns(APawn pawn) {
		if (pawn.isWhite()) {
			whitePoints--;
		} else {
			blackPoints--;
		}
	}

	/*
	 * --------------------------------------------- ABSTRACT
	 * --------------------------------------------
	 */
	@Override
	public abstract EStatus move(String turn);

	@Override
	public abstract double evaluate();

	public abstract IBoard clone();

	public abstract void display();
	
	/* SIMPLE PLAYER*/
	public ArrayList<APawn> getWhitePawns() {
		return whitePawns;
	}

	public ArrayList<APawn> getBlackPawns() {
		return blackPawns;
	}

	public void removePawn(ArrayList<APawn> list, APawn pawn) {
		int index = getIndex(list, pawn);
		if(index != -1){
			list.remove(index);
		}else {
			System.out.println("Cannot remove from list.");
		}
		
	}

	public APawn getPawnFromList(ArrayList<APawn> list, Point point) {
		for (APawn aPawn : list) {
			int tempX = aPawn.getX();
			int tempY = aPawn.getY();
			System.out.printf("Temp %d%d",tempX,tempY);
			System.out.printf("PAwn %d%d",point.getX(),point.getY());
			if (tempX == point.getX() && tempY == point.getY()) {
				return aPawn;
			}
		}
		return null;
	}
	
	public int getIndex(ArrayList<APawn> list, APawn pawn){
		for (int i = 0; i < list.size(); i++) {
			APawn temp = list.get(i);
			if(temp.getX() == pawn.getX() && temp.getY() == pawn.getY()){
				return i;
			}
		}
		return -1;
		
	}

}
