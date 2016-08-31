package fh.twoplay.alapo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * Testet, ob die Methoden init() und getDescription() gem�� der Spezifikation arbeiten.
 * 
 * @author M. Krone
 */
public class AlapoGetDescriptionTest extends ComparisonTest
{
	private final static String START = "STA6 SLB6 SDC6 SDD6 SLE6 STF6 stA5 slB5 sdC5 sdD5 slE5 stF5 " + 
			 "WTA1 WLB1 WDC1 WDD1 WLE1 WTF1 wtA2 wlB2 wdC2 wdD2 wlE2 wtF2";
	
	
	/**
	 * Testet Default-Positionierung bei Alapo.
	 */
	@Test(timeout=1000)
	public void testDefault()
	{
		IBoard board = new AlapoBoard();

		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(START, board.getDescription()));
	}	
	
	
	/**
	 * Testet, ob auch Init mit der Default-Startposition korrekt arbeitet.
	 */
	@Test(timeout=1000)
	public void testDefaultInit()
	{
		IBoard board = new AlapoBoard();
		board.init(START);

		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(START, board.getDescription()));
	}		
	
	/**
	 * Test von init() mit Nicht-Default-Startpositionen
	 */
	@Test//(timeout=1000)
	public void testInit()
	{
		IBoard board = new AlapoBoard();
		
		String exp = "WTC5 SDD2 wdA4 STF1";
		
		board.init(exp);
		
		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
	}
	
	
	/**
	 * Test, ob die Ver�nderungen des Spielfeldes bei g�ltigen und ung�ltigen Z�gen realisiert werden.
	 */
	@Test(timeout=1000)
	public void testGetDescription()
	{
		IBoard board = new AlapoBoard();
		
		String exp = "wtA2 WLC3 WDE2 stB6 slC5 SDE6";
		
		board.init(exp);
		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
		
		// -----------------------------------------------------------------------------------
		
		EStatus ret = board.move("C3E5");
		assertEquals("G�ltiger Zug nicht erkannt", EStatus.LEGAL, ret);
		
		exp = "wtA2 WLE5 WDE2 stB6 slC5 SDE6";
		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
		
		// -----------------------------------------------------------------------------------
		
		ret = board.move("E2E3");
		assertEquals("Ung�ltiger Zug nicht erkannt (schwarz darf ziehen)", EStatus.WRONGCOLOR, ret);
		
		exp = "wtA2 WLE5 WDE2 stB6 slC5 SDE6";
		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));
		
		// -----------------------------------------------------------------------------------
		
		ret = board.move("E6E5");
		assertEquals("G�ltiger Zug nicht erkannt", EStatus.LEGAL, ret);
		
		exp = "wtA2 WDE2 stB6 slC5 SDE5";
		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));		

		// -----------------------------------------------------------------------------------
		
		ret = board.move("E2E5");
		assertEquals("G�ltiger Zug nicht erkannt", EStatus.LEGAL, ret);
		
		exp = "wtA2 stB6 slC5 WDE5";
		assertTrue("R�ckgabe von getDescription() ist falsch", this.compareCaseSensitive(exp, board.getDescription()));		
		
	}
	
}
