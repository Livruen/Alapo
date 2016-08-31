package fh.twoplay.alapo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * 
 * @author Falk Morgenstern
 *
 */

public class AlapoNotCoveredTest extends ComparisonTest {

	@Test(timeout=1000)
	public void emptyMove() {
		IBoard board = new AlapoBoard();
		board.init("WTA1 STA6");
		
		assertNull("A3 enth�lt keine Figuren",board.getMoves("A3"));
	}

	@Test(timeout=1000)
	public void eStatusSyntaxerror() {
		IBoard board = new AlapoBoard();
		board.init("WTA1 STA6");
		
		assertEquals("A1 ist kein g�ltiger Zug (zu kurz)", EStatus.SYNTAXERROR, board.move("A1"));

		assertTrue("AA43 ist kein g�ltiger Zug (falsche Reihenfolge)", containsEnum(new EStatus[]{EStatus.OUTOFRANGE, EStatus.SYNTAXERROR}, board.move("AA43")));
		
		assertTrue("a0f2 ist kein g�ltiger Zug (falscher Index 0)", containsEnum(new EStatus[]{EStatus.OUTOFRANGE, EStatus.SYNTAXERROR}, board.move("a0f2")));

		assertTrue("a6g6 ist kein g�ltiger Zug (falscher Index G)", containsEnum(new EStatus[]{EStatus.OUTOFRANGE, EStatus.SYNTAXERROR}, board.move("a6g6")));
	}
	
	@Test(timeout=1000)
	public void eStatusIllegal() {
		IBoard board = new AlapoBoard();
		board.init("WTA1 STA6 WtB1 StB6");
		
		assertEquals("Turm kann nicht Diagonal gehen", EStatus.ILLEGAL, board.move("A1B2"));
		
		assertEquals("Kleiner Turm darf nur ein feld gehen", EStatus.ILLEGAL, board.move("B1B6"));
		
		assertEquals("Gro�er Turm kann schlagen", EStatus.LEGAL, board.move("A1A6"));
		
		
	}
	
	@Test(timeout=1000)
	public void evaluateTest() {
		IBoard board = new AlapoBoard();
		board.init("WTA1 STA6 WtB1 StB6");
		
		assertEquals("Aktuell herrscht Gleichstand!",0.0, board.evaluate(), 0.001);
		board.move("A1A6");
		
		assertTrue("Vorteil Wei�. Wei� hat zwei, Schwarz hat nur einen", board.evaluate() > 0);
		
		board.move("B6C6");
		board.move("A6C6");
		
		assertEquals("Wei� hat zwei, Schwarz hat nur einen.!",IBoard.WHITEWINS, board.evaluate(), 0.001);
		
		board.init("WTA1 STA6");
		board.move("A1A2");
		board.move("A6A2");
		
		assertEquals("Wei� hat zwei, Schwarz hat nur einen.!",IBoard.BLACKWINS, board.evaluate(), 0.001);
	}
	

}
