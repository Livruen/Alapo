package fh.twoplay.breakthrough;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet verschiedene Bewegungsmuster: Man darf sich nicht r�ckw�rts bewegen.
 * Man darf nicht seine eigene Figuren schlagen. Gegnerische Figuren kann man
 * nur diagonal schlagen.
 * 
 * @author F. H�ppner
 */

public class BreakthroughValidMoveTest extends ComparisonTest {

	@Test(timeout=1000)
	public void testIllegalBackward() {
		IBoard b = new BreakBoard();
		// wei� darf nicht zurueck gehen
		assertEquals(EStatus.ILLEGAL, b.move("A2A1"));
		assertEquals(EStatus.ILLEGAL, b.move("C2C1"));
		assertTrue(containsEnum(new EStatus[] { EStatus.SYNTAXERROR,
				EStatus.OUTOFRANGE, EStatus.ILLEGAL }, b.move("D1D0")));

		// ein gueltiger Zug, um danach schwarz zu testen
		assertEquals(EStatus.LEGAL, b.move("A2A3"));

		// schwarz darf nicht zurueck gehen
		assertEquals(EStatus.ILLEGAL, b.move("D6D7"));
		assertTrue(containsEnum(new EStatus[] { EStatus.SYNTAXERROR,
				EStatus.OUTOFRANGE, EStatus.ILLEGAL }, b.move("E7E8")));
		assertEquals(EStatus.ILLEGAL, b.move("A6A7"));
	}

	@Test(timeout=1000)
	public void testIllegalCaptureOwn() {
		IBoard b = new BreakBoard();
		// wei� darf keine eigenen Steine schlagen
		assertEquals(EStatus.ILLEGAL, b.move("A1A2"));
		assertEquals(EStatus.ILLEGAL, b.move("C1C2"));
		assertEquals(EStatus.ILLEGAL, b.move("E1E2"));
		assertEquals(EStatus.ILLEGAL, b.move("E2E1"));
		// ein gueltiger Zug, um danach schwarz zu testen
		assertEquals(EStatus.LEGAL, b.move("A2A3"));
		// schwarz darf keine eigenen Steine schlagen
		assertEquals(EStatus.ILLEGAL, b.move("D7D6"));
		assertEquals(EStatus.ILLEGAL, b.move("E7F6"));
		assertEquals(EStatus.ILLEGAL, b.move("E7D6"));
	}

}
