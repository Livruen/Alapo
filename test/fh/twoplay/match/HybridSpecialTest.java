package fh.twoplay.match;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

public class HybridSpecialTest
{
	@Test(timeout=1000)
	public void testAlapoFinalFH()
	{
		IBoard board = new HybridBoard();
		board.init("STA6 WTF1");
		
		assertEquals("F1F6 ist ein LEGAL-Zug", EStatus.LEGAL, board.move("F1F6"));
		assertEquals("A6A1 ist ein FINAL-Zug", EStatus.FINAL, board.move("A6A1"));
	}
}
