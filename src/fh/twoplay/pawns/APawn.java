/**
 * 
 */
package fh.twoplay.pawns;

import java.awt.Point;

import fh.twoplay.alapo.AlapoBoard;
import fh.twoplay.behaviors.AMoveBehavior;
import fh.twoplay.helper.ColumnNames;


/**
 * @author Natasza Szczypien
 *
 */
public abstract class APawn implements Cloneable {


	/* Constant variables */
	protected static final int OVER_BOARD = 0;
	protected static final int BEHIND_LEFT = 0;

	public static final int UP = -1;
	public static final int DOWN = 1;
	public static final int LEFT = -1;
	public static final int RIGHT = 1;
	public static final int FRONT = 0;

	
	
	protected static final String BLANK_FIELD = " ";
	public static final String WHITE_COLOR = "W";
	public static final String BLACK_COLOR = "S";
	protected static final String BAUER = "B";
	
	protected boolean isWhite;
	protected boolean isSmall;
	protected Point position;

	protected AMoveBehavior moveVerticalBehavior ;
	protected AMoveBehavior moveHorizontalBehaviour;
	protected AMoveBehavior moveDiagonalBehavior ;
	
	
//---------------------------------------------------------
// Constructor
//---------------------------------------------------------
	public APawn(String color, Point position, boolean small) {
		this.position = position;
		
		isSmall = small;
		isWhite = color.toUpperCase().equals(WHITE_COLOR)?true:false;
	}
	
//---------------------------------------------------------
// Getters and Setters
//---------------------------------------------------------
	
	/**
	 * @return pawn color White ore black
	 */
	public String getColor(){
		if(isSmall()){
			return this.isWhite? WHITE_COLOR.toLowerCase():BLACK_COLOR.toLowerCase();
		} else {
			return this.isWhite? WHITE_COLOR:BLACK_COLOR;
		}
	}
	
	
	public void setSmall(boolean isSmall) {
		this.isSmall = isSmall;
	}
	/**
	 * @return
	 */
	public int getY(){
		return this.position.y;
	}

	/**
	 * @return
	 */
	public int getX() {
		return this.position.x;
	}
	/**
	 * @return
	 */
	public String getYasString(){
		return ColumnNames.getYtoString(this.getX());
	}

	/**
	 * @return
	 */
	public String getXasString() {
		return ColumnNames.getXtoString(this.getY());
	}
	
	/**
	 * 
	 * @return pawn position as point
	 */
	public Point getPosition() {
		return this.position;
	}
	
	
	public void setPosition(Point move){
		this.position = move;
	}
	
	/**
	 * prints the pawn position like A3
	 * @return
	 */
	public  String positionToString(){
		return getXasString() + getYasString();
	}
	
//---------------------------------------------------------
// Abstract
//---------------------------------------------------------

	
	/**
	 * @param board
	 * @return possible moves from a pawn
	 */
	public  String getMoves(APawn[][] board){
	String moves = "";
		
		moves += moveDiagonalBehavior.getMoves(board, this);
		moves += moveVerticalBehavior.getMoves(board, this);
		moves += moveHorizontalBehaviour.getMoves(board, this);

		return moves;
	}
	
	/**
	 * if pawn is on the enemy line on board
	 * @param board
	 * @return
	 */
	public  boolean wins(APawn[][] board){
		if(isWhite()){
			return getPosition().x == 0;
		} else {
			return getPosition().x == board.length -1;
		}
	}
	
	
	/**
	 * change the position of a pawn on bord
	 * @param move2 
	 */
	public  void move(Point direction, APawn[][] board) {
		board[this.getX()][this.getY()] = new BlankField(this.getPosition());
        setPosition(direction);
		board[getPosition().x][getPosition().y] = this;
		
	}
	
	
//---------------------------------------------------------
// Questions
//---------------------------------------------------------	
	
	public boolean isSmall() {
		return isSmall;
	}
	
	/**
	 * 
	 * @return answers question if pawn is white
	 */
	public boolean isWhite() {
		return this.isWhite;
	}
	
//---------------------------------------------------------
// Behaviors
//---------------------------------------------------------	
	

	public AMoveBehavior getMoveForwardBehavior() {
		return moveVerticalBehavior;
	}

	public AMoveBehavior getMoveDiagonalBehavior() {
		return moveDiagonalBehavior;
	}
	
	public abstract APawn clone();
	public abstract String getType();
	
}
