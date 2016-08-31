package fh.twoplay.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet, ob die Reihenfolge der Spieler eingehalten wird: Wei� beginnt. Wei�
 * und Schwarz spielen abwechselnd.
 * 
 * @author F. H�ppner, angepasst von M. Krone
 */

public class HybridTurnOrderTest extends ComparisonTest
{
	@Test(timeout=1000)
	public void testTurn() {
		IBoard b = new HybridBoard();
		
		// Keine Figur auf A3 zu Anfang
		assertEquals(EStatus.NOTOKEN, b.move("A3B3"));
		
		// wei� muss beginnen, schwarzer Zug illegal
		assertEquals(EStatus.WRONGCOLOR, b.move("A5A4"));
		
		// legaler Zug von wei�
		assertEquals(EStatus.LEGAL, b.move("C2C3"));
		
		// wei� darf nicht noch einen Zug machen
		assertEquals(EStatus.WRONGCOLOR, b.move("C1C2"));
		
		// aber schwarz
		assertEquals(EStatus.LEGAL, b.move("E5F4"));
		
		// aber dann nicht noch einmal
		assertEquals(EStatus.WRONGCOLOR, b.move("C6C5"));
		
		// Zug F1F7 nicht m�glich -> OUTOFRANGE/ILLEGAL/SYNTAXERROR (da F7 keine Zelle auf dem Spielfeld mehr ist) 
		assertTrue("Zug F1F7 nicht m�glich", containsEnum(new EStatus[]{EStatus.OUTOFRANGE, EStatus.ILLEGAL, EStatus.SYNTAXERROR}, b.move("F1F7")));
	}

}
