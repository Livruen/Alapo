package fh.twoplay.breakthrough;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet, ob die Reihenfolge der Spieler eingehalten wird: Wei� beginnt. Wei�
 * und Schwarz spielen abwechselnd.
 * 
 * @author F. H�ppner
 */

public class BreakthroughTurnOrderTest {

	@Test(timeout=1000)
	public void testSieg() {
		IBoard b = new BreakBoard();
		
		// wei� muss beginnen, schwarzer Zug illegal
		assertEquals(EStatus.WRONGCOLOR, b.move("A6A5"));
		
		// legaler Zug von wei�
		assertEquals(EStatus.LEGAL, b.move("C2C3"));
		
		// wei� darf nicht noch einen Zug machen
		assertEquals(EStatus.WRONGCOLOR, b.move("C1C2"));
		
		// aber schwarz
		assertEquals(EStatus.LEGAL, b.move("B6B5"));
		
		// aber dann nicht noch einmal
		assertEquals(EStatus.WRONGCOLOR, b.move("C6C5"));
	}

}
