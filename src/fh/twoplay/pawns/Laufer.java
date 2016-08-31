/**
 * 
 */
package fh.twoplay.pawns;

import java.awt.Point;

import fh.twoplay.behaviors.CannotMove;
import fh.twoplay.behaviors.DiagonalUPandDOWN;



/**
 * @author Natasza Szczypien
 * Laufer moves:
 *                 DiagLEFT_UP   DiagRIGHT_UP
 *                 DiagLEFT_DOWN   DiagRIGHT_DOWN
 */
public class Laufer extends APawn {

	/**
	 * 
	 */
	private static final String BIG_LAUFER = "L";
	/**
	 * 
	 */
	private static final String SMALL_LAUFER = "l";

	/**
	 * @param color
	 * @param position
	 */
	public Laufer(String color, Point position, boolean small) {
		super(color, position, small);

		moveDiagonalBehavior = new DiagonalUPandDOWN();
		moveHorizontalBehaviour = new CannotMove();
		moveVerticalBehavior = new CannotMove();
	}

	public String toString() {
		if (isSmall()) {
			return getColor() + SMALL_LAUFER;
		} else {
			return getColor() + BIG_LAUFER;
		}
	}
	
	public APawn clone(){
		Point temp = new Point(this.position.x,this.position.y);

		Laufer clonePawn = new  Laufer(this.getColor(), temp, this.isSmall);
		return clonePawn;
	}

	/* (non-Javadoc)
	 * @see fh.twoplay.pawns.APawn#getType()
	 */
	@Override
	public String getType() {
		if(isSmall()){
			return SMALL_LAUFER;
		}else {
			return BIG_LAUFER;
		}
	}

}
