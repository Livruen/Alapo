package fh.twoplay.match;

import org.junit.Assert;

import org.junit.Test;

import fh.twoplay.ComparisonTest;
import fh.twoplay.base.IBoard;
import fh.twoplay.base.IPlayer;


public class PlayerSimpleBestMoveTest extends ComparisonTest{
	@Test//(timeout=1000)
	public void testInvalidMoves() {
		IBoard board = new HybridBoard();
		String in = "WTB1 WTC1 SBC5";
		board.init(in);
		
		IPlayer player = new PlayerSimple(board.clone(), true);
		String move = player.nextOwnMove();
		
		Assert.assertTrue("C1C5 ist der beste Zug",
				compareCaseSensitive("C1C5", move));
	}
}
