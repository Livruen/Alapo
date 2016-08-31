package fh.twoplay.breakthrough;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet, ob ein "finale Zug" (Spielende) von der move-Methode erkennt wird.
 * Au�erdem wird gepr�ft, ob die Bewertungsfunktion (evaluate) den Vorteil einer
 * Farbe entsprechend ber�cksichtigt.
 * 
 * @author F. H�ppner
 */

public class BreakthroughFinalMoveTest extends ComparisonTest
{

	@Test(timeout=1000)
	public void testFinalState() {
		IBoard b = new BreakBoard();
		
		assertEquals(0.0, b.evaluate(), 1E-10);
		
		assertEquals(EStatus.LEGAL, b.move("A2A3"));
		assertEquals(0.0, b.evaluate(), 1E-10);
		
		assertEquals(EStatus.LEGAL, b.move("A6A5"));
		assertEquals(0.0, b.evaluate(), 1E-10);
		
		assertEquals(EStatus.LEGAL, b.move("A3A4"));
		assertEquals(0.0, b.evaluate(), 1E-10);
		
		assertEquals(EStatus.LEGAL, b.move("B6B5"));
		assertEquals(0.0, b.evaluate(), 1E-10);
		
		assertEquals(EStatus.LEGAL, b.move("A4B5")); // wei� schlaegt schwarz
		assertTrue(b.evaluate() > +1E-10); // Vorteil wei�
		
		assertEquals(EStatus.LEGAL, b.move("C6C5"));
		assertTrue(b.evaluate() > +1E-10); // Vorteil wei�
		
		assertEquals(EStatus.LEGAL, b.move("B5A6"));
		assertTrue(b.evaluate() > +1E-10); // Vorteil wei�
		
		assertEquals(EStatus.LEGAL, b.move("D6D5"));
		assertTrue(b.evaluate() > +1E-10); // Vorteil wei�
		
		assertTrue(containsEnum(new EStatus[]{EStatus.LEGAL, EStatus.FINAL}, b.move("A6B7"))); // wei� erreicht Linie 7
		assertEquals(IBoard.WHITEWINS, b.evaluate(), 1E-10); // Sieg wei�
	}

}
