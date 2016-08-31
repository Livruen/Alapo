package fh.twoplay.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet beispielhaft, ob fehlerhafte Züge erkannt werden.
 * 
 * @author M. Krone
 */

public class HybridInvalidMovesTest extends ComparisonTest {
	
	@Test(timeout=1000)
	public void testInvalidMoves() {
		IBoard board = new HybridBoard();

		String in = "WBB6 WBE4 WBF5";

		board.init(in);

		EStatus ret = board.move("B6B7");
		assertTrue(
				"Zug B6B7 geht über Spielfeldgrenzen",
				containsEnum(new EStatus[] { EStatus.SYNTAXERROR,
						EStatus.OUTOFRANGE, EStatus.ILLEGAL }, ret));

		ret = board.move("E4F5");
		assertEquals("Zug E4F5 nicht möglich", EStatus.ILLEGAL, ret);

		ret = board.move("E4E6");
		assertEquals("Zug E4E6 nicht möglich", EStatus.ILLEGAL, ret);
	}
	
	@Test(timeout=1000)
	public void testIllegalBackward() {
		IBoard b = new HybridBoard();
		// weiß darf nicht zurueck gehen
		assertEquals(EStatus.ILLEGAL, b.move("A2A1"));
		assertEquals(EStatus.ILLEGAL, b.move("C2C1"));
		assertTrue(containsEnum(new EStatus[] { EStatus.SYNTAXERROR,
				EStatus.OUTOFRANGE, EStatus.ILLEGAL }, b.move("D1D0")));

		// ein gueltiger Zug, um danach schwarz zu testen
		assertEquals(EStatus.LEGAL, b.move("A2A3"));

		// schwarz darf nicht zurueck gehen
		assertEquals(EStatus.ILLEGAL, b.move("D5D6"));
		assertTrue(containsEnum(new EStatus[] { EStatus.SYNTAXERROR,
				EStatus.OUTOFRANGE, EStatus.ILLEGAL }, b.move("E6E7")));
		assertEquals(EStatus.ILLEGAL, b.move("A5A6"));
	}

	@Test(timeout=1000)
	public void testIllegalCaptureOwn() {
		IBoard b = new HybridBoard();
		// weiß darf keine eigenen Steine schlagen
		assertEquals(EStatus.ILLEGAL, b.move("A1A2"));
		assertEquals(EStatus.ILLEGAL, b.move("C1C2"));
		assertEquals(EStatus.ILLEGAL, b.move("E1E2"));
		assertEquals(EStatus.ILLEGAL, b.move("E2E1"));
		// ein gueltiger Zug, um danach schwarz zu testen
		assertEquals(EStatus.LEGAL, b.move("A2A3"));
		// schwarz darf keine eigenen Steine schlagen
		assertEquals(EStatus.ILLEGAL, b.move("D6D5"));
		assertEquals(EStatus.ILLEGAL, b.move("E6F5"));
		assertEquals(EStatus.ILLEGAL, b.move("E6D5"));
	}
}
