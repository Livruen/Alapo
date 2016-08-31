package fh.twoplay.match;

import org.junit.*;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.base.IPlayer;
import fh.twoplay.breakthrough.BreakBoard;

/** 
 * Testet, ob das Breakthrough-Spiel �ber 2 PlayerSimple irgendwann endet
 */
public class BreakthroughTerminateTest {
	
	@Test//(timeout = 5000)
	public void testBreakthroughTerminate(){
		final IBoard board = new BreakBoard();
		
		IPlayer white = new PlayerSimple(board.clone(), true);
		IPlayer black = new PlayerSimple(board.clone(), false);
		
		String move;
		EStatus eval;
		
		// abwechselnd ziehen
		IPlayer turn = white, opp = black;
		do {
			move = turn.nextOwnMove();  // ein Spieler liest den Zug des Gegners
			eval = opp.nextOppMove(move); // Uebergabe an den Gegner
			IPlayer tmp = turn; turn = opp; opp = tmp;
		} while (eval==EStatus.LEGAL);
		
		Assert.assertEquals("Es wurde ein ung�ltiger Zug ausgef�hrt", EStatus.FINAL, eval);
	}
}
