package fh.twoplay.match;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Method;

import org.junit.Test;

import fh.twoplay.base.IBoard;


/**
 * 
 * Test zum Überprüfen, ob die Methode toString() wirklich selbst implementiert wurde.
 * Ebenfalls wird die Rückgabe von toString() auf null oder den leeren String verglichen.
 *
 * @author M. Krone
 */
public class HybridToStringTest
{
	@Test(timeout=1000)
	public void testToString() throws Exception
	{
		Class<?> clazz = HybridBoard.class;
		Method method = clazz.getMethod("toString");

		assertNotEquals("Die Methode toString() wurde nicht implementiert", "java.lang.Object", method
				.getDeclaringClass().getCanonicalName());

		String ret = ((IBoard) clazz.newInstance()).toString();

		assertTrue("Rückgabe von toString() ist null oder leer", ret != null && ret.length() > 0);
	}
}
