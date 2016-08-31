package fh.twoplay.breakthrough;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet beispielhaft, ob fehlerhafte Z�ge erkannt werden.
 * 
 * @author M. Krone
 */

public class BreakthroughInvalidMovesTest extends ComparisonTest {
	
	@Test//(timeout=1000)
	public void testInvalidMoves() {
		IBoard board = new BreakBoard();

		String in = "WBB7 WBE5 WBF6";

		board.init(in);

		EStatus ret = board.move("B7B8");
		assertTrue(
				"Zug B7B8 geht �ber Spielfeldgrenzen",
				containsEnum(new EStatus[] { EStatus.SYNTAXERROR,
						EStatus.OUTOFRANGE, EStatus.ILLEGAL }, ret));

		ret = board.move("E5F6");
		assertEquals("Zug E5F6 nicht m�glich", EStatus.ILLEGAL, ret);

		ret = board.move("E5E7");
		assertEquals("Zug E5E7 nicht m�glich", EStatus.ILLEGAL, ret);

	}
}
