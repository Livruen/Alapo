/**
 * 
 */
package fh.twoplay.pawns;

import java.awt.Point;
import java.awt.geom.IllegalPathStateException;

import fh.twoplay.helper.ColumnNames;
import fh.twoplay.shared.IllegalPositionOrPin;

/**
 * @author Natasza Szczypien
 * 
 * 
 *         This is a class made by the design Pattern: Fabrik For each input
 *         like SDD6 or slE5 the PawnFabric returns the specific Pawn type
 * 
 * 
 */
public class PawnFabric {

	private static final boolean SMALL_PAWN = true;
	private static final boolean BIG_PAWN = false;
	private static final int TYPE_INDEX = 1;
	private static final int COLOR_INDEX = 0;
	

	/* 
	 * @return Pawn 
	 * 
	 * The funktion gets an input, prepares the String for making a specific
	 * Pawn Object, 
	 * if the input consists wrong details the funktions throws an Exception
	 * 
	 * */
	public static APawn getPawn(String input, int boardSize) throws IllegalPositionOrPin {

		APawn pawn = null;
		StringBuffer buffer = new StringBuffer(input);

		String color = buffer.charAt(COLOR_INDEX) + "";
		color = color.toUpperCase();
		String type = buffer.charAt(TYPE_INDEX) + "";

		buffer.deleteCharAt(0);
		buffer.deleteCharAt(0);

		Point point = ColumnNames.preparePoint(buffer);
		if (isBehindBoard(point, boardSize)) {
			throw new IllegalPositionOrPin();
		}

		switch (type) {
		case "T":
			pawn = new Turm(color, point, BIG_PAWN);
			break;
		case "D":
			pawn = new Dame(color, point, BIG_PAWN);
			break;
		case "L":
			pawn = new Laufer(color, point, BIG_PAWN);
			break;
		case "B":
			/* ! There is no small 'Bauer' Pawn, but the moves behave 
			 * like by small one */
			pawn = new Bauer(color, point, SMALL_PAWN);
			break;
		case "t":
			pawn = new Turm(color, point, SMALL_PAWN);
			break;
		case "d":
			pawn = new Dame(color, point, SMALL_PAWN);
			break;
		case "l":
			pawn = new Laufer(color, point, SMALL_PAWN);
			break;
		default:
			throw new IllegalPositionOrPin();
		}
		
		return pawn;
	}

	/* Checks if the point consist wrong information about the position */
	private static boolean isBehindBoard(Point point, int boardSize) {
		return point.getX() < 0 || point.getY() < 0 || point.getX() > boardSize || point.getY() > boardSize;
	}
}
