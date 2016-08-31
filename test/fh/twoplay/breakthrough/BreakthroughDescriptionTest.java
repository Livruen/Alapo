package fh.twoplay.breakthrough;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet, ob die Methoden init() und getDescription() gem�� der Spezifikation
 * arbeiten.
 * 
 * @author M. Krone
 */

public class BreakthroughDescriptionTest extends ComparisonTest {
	
	@Test(timeout=1000)
	public void testDefault() {
		IBoard board = new BreakBoard();

		String exp = "WBA1 WBB1 WBC1 WBD1 WBE1 WBF1 WBG1 WBA2 WBB2 WBC2 WBD2 WBE2 WBF2 WBG2 "
				+ "SBA6 SBB6 SBC6 SBD6 SBE6 SBF6 SBG6 SBA7 SBB7 SBC7 SBD7 SBE7 SBF7 SBG7";
		//board.init fehlt
		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));
	}

	/*
	 * Testet, ob die Methode "init" auch mit langen Eingaben (hier:
	 * Default-Platzierungen der Bauern) korrekt arbeitet.
	 */
	@Test(timeout=1000)
	public void testDefaultInit() {
		IBoard board = new BreakBoard();

		String exp = "WBA1 WBB1 WBC1 WBD1 WBE1 WBF1 WBG1 WBA2 WBB2 WBC2 WBD2 WBE2 WBF2 WBG2 "
				+ "SBA6 SBB6 SBC6 SBD6 SBE6 SBF6 SBG6 SBA7 SBB7 SBC7 SBD7 SBE7 SBF7 SBG7";

		board.init(exp);

		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));
	}

	@Test(timeout=1000)
	public void testInit() {
		IBoard board = new BreakBoard();

		String exp = "WBA1 WBA2 SBF6 SBD7";

		board.init(exp);

		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));
	}

	@Test(timeout=1000)
	public void testGetDescription() {
		IBoard board = new BreakBoard();

		String exp = "WBB2 WBD2 WBF2 SBA6 SBC6 SBE6 SBG6";

		board.init(exp);
		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));

		// -----------------------------------------------------------------------------------

		EStatus ret = board.move("B2B3");
		assertEquals("G�ltiger Zug B2B3 nicht erkannt", EStatus.LEGAL, ret);

		exp = "WBD2 WBF2 WBB3 SBA6 SBC6 SBE6 SBG6";
		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));

		// -----------------------------------------------------------------------------------

		ret = board.move("F5F4");
		assertTrue(
				"an F5 steht keine Figur",
				containsEnum(
						new EStatus[] { EStatus.NOTOKEN, EStatus.ILLEGAL }, ret));

		// -----------------------------------------------------------------------------------

		ret = board.move("A6A5");
		assertEquals("G�ltiger Zug A6A5 nicht erkannt", EStatus.LEGAL, ret);

		exp = "WBD2 WBF2 WBB3 SBA5 SBC6 SBE6 SBG6";
		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));

		// -----------------------------------------------------------------------------------

		ret = board.move("B3B4");
		assertEquals("G�ltiger Zug B3B4 nicht erkannt", EStatus.LEGAL, ret);

		exp = "WBD2 WBF2 WBB4 SBA5 SBC6 SBE6 SBG6";
		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));

		// -----------------------------------------------------------------------------------

		ret = board.move("A5B4");
		assertEquals("G�ltiger Zug A5B4 nicht erkannt", EStatus.LEGAL, ret);

		exp = "WBD2 WBF2 SBB4 SBC6 SBE6 SBG6";
		assertTrue("R�ckgabe von getDescription() ist falsch",
				this.compareCaseInsensitive(exp, board.getDescription()));

		// -----------------------------------------------------------------------------------

	}

}
