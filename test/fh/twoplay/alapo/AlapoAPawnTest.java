/**
 * 
 */
package fh.twoplay.alapo;

import static org.junit.Assert.*;

import org.junit.Test;

import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.PawnFabric;
import fh.twoplay.shared.IllegalPositionOrPin;

/**
 * @author Natasza Szczypien
 *
 */
public class AlapoAPawnTest {

	@Test
	public void test() {
		String _1 = "SDD6";
		String _2 = "slE5";
		
		APawn pawn1;
		try {
			pawn1 = PawnFabric.getPawn(_1,6);
	
		assertEquals("D6", pawn1.positionToString());
		assertEquals(_1, pawn1.toString() + pawn1.positionToString());
		
		APawn pawn2 = PawnFabric.getPawn(_2,6);
		assertEquals("E5", pawn2.positionToString());
		assertEquals(_2, pawn2.toString() + pawn2.positionToString());
		} catch (IllegalPositionOrPin e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
