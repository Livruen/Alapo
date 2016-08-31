package fh.twoplay.pawns;

import java.awt.Point;

import fh.twoplay.behaviors.CannotMove;
import fh.twoplay.behaviors.DiagonalUP;
import fh.twoplay.behaviors.Horizontal;
import fh.twoplay.behaviors.VerticalUP;

public class Bauer extends APawn {

	/**
	 * 
	 */
	private static final String BAUER = "B";

	public Bauer(String color, Point position, boolean small) {
		super(color, position, small);
		moveDiagonalBehavior = new DiagonalUP();
		moveHorizontalBehaviour = new CannotMove();
		moveVerticalBehavior = new VerticalUP();
	}

	public String toString() {
		return getColor().toUpperCase() + BAUER;
	}
	
	public APawn clone(){
		Point temp = new Point(this.position.x,this.position.y);
		Bauer clonePawn = new  Bauer(this.getColor(), temp, this.isSmall);
		return clonePawn;
	}

	/* (non-Javadoc)
	 * @see fh.twoplay.pawns.APawn#getType()
	 */
	@Override
	public String getType() {
		return BAUER;
	}
}
