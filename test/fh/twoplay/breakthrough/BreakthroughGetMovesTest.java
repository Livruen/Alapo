package fh.twoplay.breakthrough;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.IBoard;

/**
 * Testet die Methode getMoves() in der Klasse Breakboard.
 * 
 * @author M. Krone
 */

public class BreakthroughGetMovesTest extends ComparisonTest {

	/**
	 * Bauern, die an der schwarzen oder weissen Grundlinie stehen, haben bei
	 * der Default-Positionierung am Anfang des Spiels keine
	 * Bewegungsm�glichkeit.
	 */
	@Test(timeout=1000)
	public void testDefaultBaseLine() {
		IBoard board = new BreakBoard();

		String[] baseLine = { "A1", "B1", "C1", "D1", "E1", "F1", "G1", "A7",
				"B7", "C7", "D7", "E7", "F7", "G7" };

		for (String b : baseLine) {
			assertEquals("R�ckgabewert von getMoves(\"" + b + "\") falsch", "",
					board.getMoves(b));
		}
	}

	/**
	 * Test der schwarzen Bauern in Zeile 6 bei der Default-Positionierung.
	 */
	@Test(timeout=1000)
	public void testSchwarzeBauernZeile6() {
		IBoard board = new BreakBoard();

		int row = 6;

		for (char x = 'A'; x <= 'G'; x++) {
			String pos = x + "" + row;

			String exp = ((x != 'A') ? (pos + (((char) (x - 1)) + "" + (row - 1)))
					: "")
					+ " "
					+ (pos + (x + "" + (row - 1)))
					+ " "
					+ ((x != 'G') ? (pos + (((char) (x + 1)) + "" + (row - 1)))
							: "");

			assertTrue("R�ckgabewert von getMoves(\"" + pos + "\") falsch",
					this.compareCaseSensitive(exp, board.getMoves(pos)));
		}
	}

	/**
	 * Test der weissen Bauern in Zeile 2 bei der Default-Positionierung.
	 */
	@Test(timeout=1000)
	public void testWeisseBauernZeile2() {
		IBoard board = new BreakBoard();

		int row = 2;

		for (char x = 'A'; x <= 'G'; x++) {
			String pos = x + "" + row;

			String exp = ((x != 'A') ? (pos + (((char) (x - 1)) + "" + (row + 1)))
					: "")
					+ " "
					+ (pos + (x + "" + (row + 1)))
					+ " "
					+ ((x != 'G') ? (pos + (((char) (x + 1)) + "" + (row + 1)))
							: "");

			assertTrue("R�ckgabewert von getMoves(\"" + pos + "\") falsch",
					this.compareCaseSensitive(exp, board.getMoves(pos)));
		}
	}

	/**
	 * Test der Zugm�glichkeiten nach Initialisierung der Figuren �ber Init
	 */
	@Test(timeout=1000)
	public void testInit() {
		IBoard board = new BreakBoard();

		String init = "WBA6 WBB7 WBF6 WBF7 SBC2 SBC1 SBF2 SBG1";

		board.init(init);

		// Wei�e Bauern
		assertTrue("R�ckgabewert von getMoves(\"A6\") falsch",
				this.compareCaseSensitive("A6A7", board.getMoves("A6")));
		assertEquals("R�ckgabewert von getMoves(\"B7\") falsch", "",
				board.getMoves("B7"));

		assertTrue("R�ckgabewert von getMoves(\"F6\") falsch",
				this.compareCaseSensitive("F6E7 F6G7", board.getMoves("F6")));
		assertEquals("R�ckgabewert von getMoves(\"F7\") falsch", "",
				board.getMoves("F7"));

		// Schwarze Bauern
		assertTrue("R�ckgabewert von getMoves(\"C2\") falsch",
				this.compareCaseSensitive("C2B1 C2D1", board.getMoves("C2")));
		assertEquals("R�ckgabewert von getMoves(\"C1\") falsch", "",
				board.getMoves("C1"));

		assertTrue("R�ckgabewert von getMoves(\"F2\") falsch",
				this.compareCaseSensitive("F2E1 F2F1", board.getMoves("F2")));
		assertEquals("R�ckgabewert von getMoves(\"G1\") falsch", "",
				board.getMoves("G1"));
	}

	/**
	 * Test der Zugm�glichkeiten bei Feldern, auf denen keine Figur steht. Hier
	 * wird die Default-Positionierung gew�hlt.
	 */
	@Test(timeout=1000)
	public void testGetMovesNoFigure() {
		IBoard board = new BreakBoard();

		for (int y = 3; y <= 5; y++) {
			for (char x = 'A'; x <= 'G'; x++) {
				String pos = x + "" + y;

				assertEquals("Auf " + pos
						+ " steht keine Figur. R�ckgabewert von getMoves(\""
						+ pos + "\") falsch", null, board.getMoves(pos));
			}
		}

	}
	
}
