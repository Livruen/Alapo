/**
 * 
 */
package fh.twoplay.pawns;

import java.awt.Point;

import fh.twoplay.behaviors.CannotMove;


/**
 * @author Natasza Szczypien
 *
 */
public class BlankField extends APawn {

	/**
	 * 
	 */
	private static final String BLANK_FIELD = " ";

	public BlankField(Point position) {
		super(BLANK_FIELD, position, false);

		moveDiagonalBehavior = new CannotMove();
		moveVerticalBehavior = new CannotMove();
		moveHorizontalBehaviour = new CannotMove();

	}

	public String getColor() {
		return BLANK_FIELD;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see fh.twoplay.alapo.pawns.APawn#wins(fh.twoplay.alapo.pawns.APawn[][])
	 */
	@Override
	public boolean wins(APawn[][] board) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fh.twoplay.alapo.pawns.APawn#move(java.awt.Point,
	 * fh.twoplay.alapo.pawns.APawn[][])
	 */
	@Override
	public void move(Point direction, APawn[][] board) {

		System.out.println("I am a blank field a cannot move");

	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "..";
	}
	
	public APawn clone(){
		Point temp = new Point(this.position.x,this.position.y);
		BlankField clonePawn = new  BlankField(temp);
		return clonePawn;
	}

	/* (non-Javadoc)
	 * @see fh.twoplay.pawns.APawn#getType()
	 */
	@Override
	public String getType() {
		return BLANK_FIELD;
	}

}
