package fh.twoplay.match;

import static org.junit.Assert.assertNull;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.IBoard;
import fh.twoplay.shared.IllegalPositionOrPin;

/**
 * 
 * @author Falk Morgenstern
 *
 */

public class ExceptionTest extends ComparisonTest {

	@Test(expected=IllegalPositionOrPin.class,timeout=1000)
	public void illegalMove1() {
		IBoard board = new HybridBoard();
		board.init("WTA1 STA6");
		assertNull("keine Position angegeben EMPTY",board.getMoves("")); // kein Zug
	}

	@Test(expected=IllegalPositionOrPin.class,timeout=1000)
	public void illegalMove2() {
		IBoard board = new HybridBoard();
		board.init("WTA1 STA6");
		assertNull("keine Position angegeben NULL",board.getMoves(null)); // null Übergabe
	}

	@Test(expected=IllegalPositionOrPin.class,timeout=1000)
	public void unknownPin() {
		IBoard board = new HybridBoard();
		board.init("WXA1 SAA4"); // Figur X und A nicht bekannt
	}
}
