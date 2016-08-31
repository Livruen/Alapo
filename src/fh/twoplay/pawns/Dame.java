/**
 * 
 */
package fh.twoplay.pawns;

import java.awt.Point;

import fh.twoplay.behaviors.DiagonalUPandDOWN;
import fh.twoplay.behaviors.Horizontal;
import fh.twoplay.behaviors.VerticalUPandDOWN;

/**
 * @author Natasza Szczypien
 *
 */
public class Dame extends APawn {

	/**
	 * 
	 */
	private static final String SMALL_DAME = "d";
	/**
	 * 
	 */
	private static final String BIG_DAME = "D";

	/**
	 * @param color
	 * @param position
	 */
	public Dame(String color, Point position, boolean small) {
		super(color, position, small);
			moveDiagonalBehavior = new DiagonalUPandDOWN();
			moveVerticalBehavior = new VerticalUPandDOWN();
			moveHorizontalBehaviour = new Horizontal();
		
	}


	public String toString() {
		if (isSmall()) {
			return getColor() + SMALL_DAME;
		} else {
			return getColor() + BIG_DAME;
		}
	}
	
	public APawn clone(){
		Point temp = new Point(this.position.x,this.position.y);
		Dame clonePawn = new  Dame(this.getColor(), temp, this.isSmall);
		return clonePawn;
	}


	/* (non-Javadoc)
	 * @see fh.twoplay.pawns.APawn#getType()
	 */
	@Override
	public String getType() {
		if(isSmall()){
			return SMALL_DAME;
		}else {
			return BIG_DAME;
		}
	}

}
