/**
 * 
 */
package fh.twoplay.pawns;

import java.awt.Point;

import fh.twoplay.behaviors.CannotMove;
import fh.twoplay.behaviors.Horizontal;
import fh.twoplay.behaviors.VerticalUPandDOWN;

/**
 * @author Natasza Szczypien
 * 
 * Turm moves:      UP
 *             LEFT    RIGHT
 *                 DOWN              
 *
 */
public class Turm extends APawn {

	/**
	 * 
	 */
	private static final String BIG_TURM = "T";
	/**
	 * 
	 */
	private static final String SMALL_TURM = "t";

	/**
	 * @param color
	 * @param position
	 */
	public Turm(String color, Point position, boolean small) {
		super(color, position, small);

		moveVerticalBehavior = new VerticalUPandDOWN();
		moveHorizontalBehaviour = new Horizontal();
		moveDiagonalBehavior = new CannotMove();
	}

	public String toString() {

		if (isSmall()) {
			return getColor() + SMALL_TURM;
		} else {
			return getColor() + BIG_TURM;
		}
	}
	
	public APawn clone(){
		Point temp = new Point(this.position.x,this.position.y);
		Turm clonePawn = new  Turm(this.getColor(), temp, this.isSmall);
		return clonePawn;
	}

	/* (non-Javadoc)
	 * @see fh.twoplay.pawns.APawn#getType()
	 */
	@Override
	public String getType() {
		if(isSmall()){
			return SMALL_TURM;
		}else {
			return BIG_TURM;
		}
	}

}
