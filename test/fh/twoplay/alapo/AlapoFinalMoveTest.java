package fh.twoplay.alapo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;

/**
 * @author M. Krone
 */
public class AlapoFinalMoveTest
{
	
	/*
	 * 6: .. .. .. .. .. .. 
	 * 5: .. .. .. WL .. .. 
	 * 4: .. .. .. .. SL .. 
	 * 3: .. .. .. .. .. .. 
	 * 2: .. .. .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
   	 *    A  B  C  D  E  F 
   	 *    
   	 *  WL zieht auf Grundlinie von schwarz -> LEGAL
   	 *  SL schl�gt WL -> FINAL (da weiss keine Figuren mehr besitzt)
	 */
	@Test(timeout=1000)

	public void testFinalMoveWeiss1()
	{
		IBoard board = new AlapoBoard();
		board.init("WLD5 SLE4");
		
		// Zug von weiss
		assertEquals("D5C6 ist ein LEGAL, aber kein finaler Zug", EStatus.LEGAL, board.move("D5C6"));
		
		// Zug von schwarz
		assertEquals("E4C6 ist ein FINAL-Zug, da keine weissen Figuren mehr �brig sind", EStatus.FINAL, board.move("E4C6"));
	}
	
	/* ---------------------------------------------------------------------------------------------*/
	
	/*
	 * 6: .. .. .. .. .. .. 
	 * 5: .. .. .. WL .. .. 
	 * 4: .. .. .. .. .. .. 
	 * 3: .. .. .. .. SL .. 
	 * 2: .. .. .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
   	 *    A  B  C  D  E  F 
   	 *    
   	 *  WL zieht D5C6 und steht somit auf gegnerische Grundlinie -> LEGAL
   	 *  SL schl�gt WL mit E3F4 *nicht* -> FINAL (Grundlinie erreicht)
	 */
	@Test(timeout=1000)
	public void testFinalMoveWeiss2()
	{
		IBoard board = new AlapoBoard();
		board.init("WLD5 SLE3");
		
		// Zug von weiss
		assertEquals("D5C6 ist ein LEGAL-Zug", EStatus.LEGAL, board.move("D5C6"));
		
		// Zug von schwarz
		assertEquals("E3F4 ist ein FINAL-Zug", EStatus.FINAL, board.move("E3F4"));
	}	
	
	/* ---------------------------------------------------------------------------------------------*/
	
	/*
	 * 6: .. .. .. .. .. .. 
	 * 5: .. .. .. .. .. .. 
	 * 4: .. .. .. .. .. .. 
	 * 3: .. .. WT .. .. .. 
	 * 2: .. SL .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
   	 *    A  B  C  D  E  F 
   	 *    
   	 *  WT zieht C3 -> C4: LEGAL (diesen Zug machen wir nur, da weiss ja immer anf�ngt)
   	 *  SL zieht B2 -> C1: LEGAL
   	 *  WT zieht C4 -> C1 (Rettung): FINAL, da keine schwarze Figur mehr �brig 
	 */
	@Test(timeout=1000)
	public void testFinalMoveSchwarz1()
	{
		IBoard board = new AlapoBoard();
		board.init("SLB2 WTC3");
		
		// Zug von weiss
		assertEquals("C3C4 ist ein legaler Zug", EStatus.LEGAL, board.move("C3C4"));
		
		// Zug von schwarz
		assertEquals("B2C1 ist ein legaler, aber kein finaler Zug", EStatus.LEGAL, board.move("B2C1"));
		
		// Zug von weiss
		assertEquals("C4C1 ist ein finaler Zug, da keine schwarze Figur mehr �brig ist", EStatus.FINAL, board.move("C4C1"));		
	}	

	
	/*
	 * 6: .. .. .. .. .. .. 
	 * 5: .. .. .. .. .. .. 
	 * 4: .. ST .. wl .. .. 
	 * 3: .. .. .. .. .. .. 
	 * 2: .. .. .. .. .. 
	 * 1: .. .. .. .. .. .. 
   	 *    A  B  C  D  E  F 
   	 *    
   	 *  wl zieht D4 -> C5: LEGAL
   	 *  ST zieht B4 -> B1: LEGAL (noch nicht final, weiss darf noch ziehen)
   	 *  wl zieht C5 -> D4: FINAL (weisser Zug brachte keine Rettung)
	 */
	@Test(timeout=1000)
//	@Test
	public void testFinalMoveSchwarz2()
	{
		IBoard board = new AlapoBoard();
		board.init("STB4 wlD4");
		
		// Zug von weiss
		assertEquals("D4C5 ist ein legaler Zug", EStatus.LEGAL, board.move("D4C5"));
		
		// Zug von schwarz
		assertEquals("B4B1 ist ein legaler Zug", EStatus.LEGAL, board.move("B4B1"));
		
		// Zug von weiss
		assertEquals("C5D4 ist ein finaler Zug", EStatus.FINAL, board.move("C5D4"));		
	}		
	
}


