package fh.twoplay.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet, ob die Methoden init() und getDescription() gemäß der Spezifikation arbeiten.
 * 
 * @author M. Krone
 */
public class HybridGetDescriptionTest extends ComparisonTest
{
	private final static String START = "WTA1 WLB1 WDC1 WDD1 WLE1 WTF1 WBA2 WBB2 WBC2 WBD2 WBE2 WBF2 "
				+ "STA6 SLB6 SDC6 SDD6 SLE6 STF6 SBA5 SBB5 SBC5 SBD5 SBE5 SBF5";
	
	
	/**
	 * Testet Default-Positionierung bei Alapo.
	 */
	@Test(timeout=1000)
	public void testDefault()
	{
		IBoard board = new HybridBoard();

		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(START, board.getDescription()));
	}	
	
	
	/**
	 * Testet, ob auch Init mit der Default-Startposition korrekt arbeitet.
	 */
	@Test(timeout=1000)
	public void testDefaultInit()
	{
		IBoard board = new HybridBoard();
		board.init(START);

		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(START, board.getDescription()));
	}		
	
	/**
	 * Test von init() mit Nicht-Default-Startpositionen
	 */
	@Test(timeout=1000)
	public void testInit()
	{
		IBoard board = new HybridBoard();
		
		String exp = "WTC5 SDD2 wdA4 STF1";
		
		board.init(exp);
		
		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
	}
	
	
	/**
	 * Test, ob die Veränderungen des Spielfeldes bei gültigen und ungültigen Zügen realisiert werden.
	 */
	@Test(timeout=1000)
	public void testGetDescription()
	{
		IBoard board = new HybridBoard();
		
		String exp = "wtA2 WLC3 WDE2 stB6 slC5 SDE6";
		
		board.init(exp);
		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
		
		// -----------------------------------------------------------------------------------
		
		EStatus ret = board.move("C3E5");
		assertEquals("Gültiger Zug nicht erkannt", EStatus.LEGAL, ret);
		
		exp = "wtA2 WLE5 WDE2 stB6 slC5 SDE6";
		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
		
		// -----------------------------------------------------------------------------------
		
		ret = board.move("E2E3");
		assertEquals("Ungültiger Zug nicht erkannt (schwarz darf ziehen)", EStatus.WRONGCOLOR, ret);
		
		exp = "wtA2 WLE5 WDE2 stB6 slC5 SDE6";
		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
		
		// -----------------------------------------------------------------------------------
		
		ret = board.move("E6E5");
		assertEquals("Gültiger Zug nicht erkannt", EStatus.LEGAL, ret);
		
		exp = "wtA2 WDE2 stB6 slC5 SDE5";
		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));		

		// -----------------------------------------------------------------------------------
		
		ret = board.move("E2E5");
		assertEquals("Gültiger Zug nicht erkannt", EStatus.LEGAL, ret);
		
		exp = "wtA2 stB6 slC5 WDE5";
		assertTrue("Rückgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));		
		
	}
	
}
