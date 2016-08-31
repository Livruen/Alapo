package fh.twoplay.match;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.IBoard;

/**
 * @author M. Krone
 */
public class HybridGetMovesTest extends ComparisonTest 
{
	/**
	 * Test für Ausgangssituation von Hybrid
	 */
	@Test(timeout=1000)
	public void testInitGetMoves()
	{
		IBoard board = new HybridBoard();
		
		for (char x = 'A'; x <= 'F'; x++)
		{
			String pos = x + "1";
			assertEquals("getMoves(\"" + pos + "\") sollte leerer String sein, da kein Zug möglich", "", board.getMoves(pos));
			
			pos = x + "6";
			assertEquals("getMoves(\"" + pos + "\") sollte leerer String sein, da kein Zug möglich", "", board.getMoves(pos));
			
			for (int y = 3; y <= 4; y++)
			{
				pos = x + "" + y;
				assertNull("getMovies(\"" + pos + "\") sollte null sein, da dort keine Figur steht", board.getMoves(pos));
			}
		}
		
		assertTrue("getMoves(\"A2\") liefert falsches Ergebnis", this.compareCaseSensitive("A2A3 A2B3", board.getMoves("A2")));
		assertTrue("getMoves(\"B2\") liefert falsches Ergebnis", this.compareCaseSensitive("B2A3 B2B3 B2C3", board.getMoves("B2")));
		assertTrue("getMoves(\"C2\") liefert falsches Ergebnis", this.compareCaseSensitive("C2B3 C2C3 C2D3", board.getMoves("C2")));
		assertTrue("getMoves(\"D2\") liefert falsches Ergebnis", this.compareCaseSensitive("D2C3 D2D3 D2E3", board.getMoves("D2")));
		assertTrue("getMoves(\"E2\") liefert falsches Ergebnis", this.compareCaseSensitive("E2D3 E2E3 E2F3", board.getMoves("E2")));
		assertTrue("getMoves(\"F2\") liefert falsches Ergebnis", this.compareCaseSensitive("F2E3 F2F3", board.getMoves("F2")));

		
		assertTrue("getMoves(\"A5\") liefert falsches Ergebnis", this.compareCaseSensitive("A5A4 A5B4", board.getMoves("A5")));
		assertTrue("getMoves(\"B5\") liefert falsches Ergebnis", this.compareCaseSensitive("B5A4 B5B4 B5C4", board.getMoves("B5")));
		assertTrue("getMoves(\"C5\") liefert falsches Ergebnis", this.compareCaseSensitive("C5B4 C5C4 C5D4", board.getMoves("C5")));
		assertTrue("getMoves(\"D5\") liefert falsches Ergebnis", this.compareCaseSensitive("D5C4 D5D4 D5E4", board.getMoves("D5")));
		assertTrue("getMoves(\"E5\") liefert falsches Ergebnis", this.compareCaseSensitive("E5D4 E5E4 E5F4", board.getMoves("E5")));
		assertTrue("getMoves(\"F5\") liefert falsches Ergebnis", this.compareCaseSensitive("F5E4 F5F4", board.getMoves("F5")));		
	}
	
	/* ------------------------------------------------------------------------------------------------------------------ */
	/* Weisse Figuren */
	
	/**
	 * 6: .. .. .. .. .. .. 
	 * 5: .. SD SD .. .. .. 
	 * 4: .. .. .. .. .. .. 
	 * 3: .. wt WT .. .. WL 
	 * 2: .. .. .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
	 *    A  B  C  D  E  F
	 */
	@Test(timeout=1000)
	public void testTurmWeiss()
	{
		IBoard board = new HybridBoard();
		board.init("wtB3 WTC3 SDB5 SDC5 WLF3");
		
		assertTrue("getMoves(\"B3\") liefert falsches Ergebnis", this.compareCaseSensitive("B3B4 B3A3 B3B2", board.getMoves("B3")));
		assertTrue("getMoves(\"C3\") liefert falsches Ergebnis", this.compareCaseSensitive("C3C4 C3C5 C3C2 C3C1 C3D3 C3E3", board.getMoves("C3")));
	}
	
	/* ------------------------------------------------------------------------------------------------------------------ */	
	
	/**
	 * 6: .. .. .. .. .. .. 
	 * 5: .. WL .. .. .. .. 
	 * 4: .. .. .. wl .. .. 
	 * 3: .. .. .. .. ST .. 
	 * 2: .. wt .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
	 *    A  B  C  D  E  F
	 */
	@Test(timeout=1000)
	public void testLaeuferWeiss()
	{
		IBoard board = new HybridBoard();
		board.init("WLB5 wlD4 wtB2 STE3");
		
		assertTrue("getMoves(\"B5\") liefert falsches Ergebnis: " + board.getMoves("B5"), this.compareCaseSensitive("B5A6 B5C6 B5A4 B5C4 B5D3 B5E2 B5F1", board.getMoves("B5")));
		assertTrue("getMoves(\"D4\") liefert falsches Ergebnis: " + board.getMoves("D4"), this.compareCaseSensitive("D4C5 D4E5 D4C3 D4E3", board.getMoves("D4")));
	}	
	
	/* ------------------------------------------------------------------------------------------------------------------ */	
	
	/**
	 * 6: .. .. .. .. .. .. 
     * 5: .. .. SL wd .. .. 
     * 4: .. WD .. WT .. .. 
     * 3: .. .. .. .. .. .. 
     * 2: .. .. .. .. .. .. 
     * 1: .. .. .. .. .. .. 
     *    A  B  C  D  E  F
	 */
	@Test(timeout=1000)
	public void testDameWeiss()
	{
		IBoard board = new HybridBoard();
		board.init("WDB4 wdD5 SLC5 WTD4");
		
		assertTrue("getMoves(\"B4\") liefert falsches Ergebnis", this.compareCaseSensitive("B4B5 B4B6 B4A4 B4B3 B4B2 B4B1 B4C4 B4A5 B4A3 B4C3 B4D2 B4E1 B4C5", board.getMoves("B4")));
		assertTrue("getMoves(\"D5\") liefert falsches Ergebnis", this.compareCaseSensitive("D5D6 D5C6 D5E6 D5C5 D5C4 D5E4 D5E5", board.getMoves("D5")));
	}	
	
	/* ------------------------------------------------------------------------------------------------------------------ */	
	/* Schwarze Figuren */
	
	/**
	 * 6: .. .. .. .. .. .. 
	 * 5: .. ST WL .. st .. 
	 * 4: .. .. .. .. .. .. 
	 * 3: .. .. .. .. .. .. 
	 * 2: .. SL .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
   	 *    A  B  C  D  E  F 
	 */
	@Test(timeout=1000)
	public void testTurmSchwarz()
	{
		IBoard board = new HybridBoard();
		board.init("STB5 stE5 WLC5 SLB2");
		
		assertTrue("getMoves(\"B5\") liefert falsches Ergebnis", this.compareCaseSensitive("B5B6 B5A5 B5B4 B5B3 B5C5", board.getMoves("B5")));
		assertTrue("getMoves(\"E5\") liefert falsches Ergebnis", this.compareCaseSensitive("E5E6 E5D5 E5E4 E5F5", board.getMoves("E5")));
	}	
	
	/* ------------------------------------------------------------------------------------------------------------------ */	
	
	/**
	 * 6: .. .. WT .. .. .. 
	 * 5: .. SL .. .. .. .. 
	 * 4: .. .. sl .. .. .. 
	 * 3: .. .. .. .. .. .. 
	 * 2: .. .. .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
   	 *    A  B  C  D  E  F
	 */
	
	@Test(timeout=1000)
	public void testLaeuferSchwarz()
	{
		IBoard board = new HybridBoard();
		board.init("SLB5 slC4 WTC6");
		
		assertTrue("getMoves(\"B5\") liefert falsches Ergebnis", this.compareCaseSensitive("B5A6 B5C6 B5A4", board.getMoves("B5")));
		assertTrue("getMoves(\"C4\") liefert falsches Ergebnis", this.compareCaseSensitive("C4B3 C4D3 C4D5", board.getMoves("C4")));
	}		
	
	/* ------------------------------------------------------------------------------------------------------------------ */	
	/**
	 * 6: .. .. .. .. .. .. 
	 * 5: .. .. .. WT .. .. 
	 * 4: .. .. SD sd .. .. 
	 * 3: .. .. .. .. .. .. 
	 * 2: .. .. WT .. .. .. 
	 * 1: .. .. .. .. .. .. 
   	 *    A  B  C  D  E  F
	 */
	@Test(timeout=1000)
	public void testDameSchwarz()
	{
		IBoard board = new HybridBoard();
		board.init("SDC4 sdD4 WTC2 WTD5");
		
		assertTrue("getMoves(\"C4\") liefert falsches Ergebnis", this.compareCaseSensitive("C4C5 C4C6 C4B5 C4A6 C4B4 C4A4 C4B3 C4A2 C4C3 C4C2 C4D3 C4E2 C4F1 C4D5", board.getMoves("C4")));
		assertTrue("getMoves(\"D4\") liefert falsches Ergebnis", this.compareCaseSensitive("D4D5 D4C5 D4C3 D4D3 D4E3 D4E4 D4E5", board.getMoves("D4")));
	}			
	
}
