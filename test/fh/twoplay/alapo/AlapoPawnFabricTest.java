/**
 * 
 */
package fh.twoplay.alapo;

import static org.junit.Assert.*;

import org.junit.Test;

import fh.twoplay.pawns.APawn;
import fh.twoplay.pawns.Dame;
import fh.twoplay.pawns.Laufer;
import fh.twoplay.pawns.PawnFabric;
import fh.twoplay.pawns.Turm;
import fh.twoplay.shared.IllegalPositionOrPin;

/**
 * @author Natasza Szczypien
 *
 */
public class AlapoPawnFabricTest {

	boolean false_b = false;

	@Test
	public void test() {

		/* Teste türme */
		APawn T;
		try {
			T = PawnFabric.getPawn("STA6", 6);

			assertEquals(Turm.class, T.getClass());
			assertFalse(T.isSmall());

			APawn t = PawnFabric.getPawn("StA6", 6);
			assertEquals(Turm.class, t.getClass());
			assertTrue(t.isSmall());

			/* Teste Dame */
			APawn D = PawnFabric.getPawn("SDA6", 6);
			assertEquals(Dame.class, D.getClass());
			assertFalse(D.isSmall());

			APawn d = PawnFabric.getPawn("SdA6", 6);
			assertEquals(Dame.class, d.getClass());
			assertTrue(d.isSmall());

			/* Teste LAufer */
			APawn L = PawnFabric.getPawn("SLA6", 6);
			assertEquals(Laufer.class, L.getClass());
			assertFalse(L.isSmall());

			APawn l = PawnFabric.getPawn("SlA6", 6);
			assertEquals(Laufer.class, l.getClass());
			assertTrue(l.isSmall());
		} catch (IllegalPositionOrPin e) {

			e.printStackTrace();
		}
	}

}
