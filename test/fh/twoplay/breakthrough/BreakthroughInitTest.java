package fh.twoplay.breakthrough;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Point;

import javax.swing.text.AbstractDocument.BranchElement;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.base.IPlayer;

public class BreakthroughInitTest extends ComparisonTest {

	final static String start = "SBA7 SBB7 SBC7 SBD7 SBE7 SBF7 SBG7 "
			+ "SBA6 SBB6 SBC6 SBD6 SBE6 SBF6 SBG6 "
			+ "WBA2 WBB2 WBC2 WBD2 WBE2 WBF2 WBG2 "
			+ "WBA1 WBB1 WBC1 WBD1 WBE1 WBF1 WBG1";

	/** einfacher Test auf Startaufstellung in einem neuen Bord. */
//	@Test(timeout=1000)
	public void testDescription() {
		IBoard b =  new BreakBoard();

		assertTrue("getDescription() liefert falschen Wert",
				compareCaseInsensitive(start, b.getDescription()));
	}
	

	/**
	 * Test: Aufruf von init() setzt neben dem Spielfeld auch die Reihenfolge
	 * zur�ck (wei� beginnt wieder).
	 */
@Test(timeout=1000)
	public void testInit() {
		IBoard b = new BreakBoard();

		// wei� zieht
		assertEquals(EStatus.LEGAL, b.move("A2A3"));

		b.init(start);
	
		assertTrue("getDescription() liefert falschen Wert",
				compareCaseInsensitive(start, b.getDescription()));

		// schwarz kann nicht ziehen, weil nach init wieder wei� dran ist.
		assertEquals(EStatus.WRONGCOLOR, b.move("C6C5"));
		// wei� zieht schon wieder (geht, weil zwischendurch ein init kam)
		assertEquals(EStatus.LEGAL, b.move("A2A3"));
	}
	
	@Test
	public void testBoardSize(){
		BreakBoard b = new BreakBoard();
		assertEquals(7, b.getSize());
	}
	
}
