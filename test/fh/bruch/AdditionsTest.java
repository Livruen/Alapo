package fh.bruch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AdditionsTest
{
	@Test
	public void testAddition1()
	{
		Bruch b1 = new Bruch (true, 1, 2);
		Bruch b2 = new Bruch (true, 1, 4);
		
		b1.addiere(b2);
		assertEquals(3, b1.getZaehler());
		assertEquals(4, b1.getNenner());
		
		Bruch b3 = new Bruch (false, 1, 4);
		b1.addiere(b3);
		assertEquals(1, b1.getZaehler());
		assertEquals(2, b1.getNenner());
		
	}
	
	@Test
	public void testNotZero()
	{
		boolean res= false;
		try{
			Bruch b1 = new Bruch(true,1,0);
			
			
		}catch(ArithmeticException e){
			res = true;
		}
		
		assertEquals("Division by zero not catched ",true,res);	
	}
	
	@Test
	public void testVorzeichen(){
		Bruch b = new Bruch(false, 1, 1);
		assertEquals(b.getVorzeichen(), false);
		Bruch b2 = new Bruch(true,1,1);
		assertEquals(true, b2.getVorzeichen());
	}
	
	@Test
	public void testAddiereMitNull(){
		Bruch b1 = new Bruch(true,2,2);
		boolean cached = false;
		try{
			b1.addiere(null);
		}catch(java.lang.NullPointerException e){
			cached = true;
		}
		assertEquals("Cannot add to imaginary Object", false, cached);	
	}
	
	@Test
	public void testEqualObjects(){
		
		Bruch b1 = new Bruch(true, 1, 1);
		Bruch b2 = new Bruch(true, 1, 1);
		assertEquals(true, b1.istGleich(b2));
		Bruch b3 = new Bruch(false,1,1);
		assertEquals(false, b1.istGleich(b3));
	}
	
	@Test
	public void testVergleiche(){
		Bruch b1 = new Bruch(true, 1, 1);
		Bruch b2 = new Bruch(true, 1, 1);
		Bruch b3 = new Bruch(false,2,1);
		Bruch b4 = new Bruch(true, 2, 1);
		
		int gleich = b1.vergleiche(b2);
		assertEquals(0, gleich);
		
		int firstNegativ = b3.vergleiche(b1);
		assertEquals(-1, firstNegativ);
		
		int b1BiggerThenB3 = b1.vergleiche(b3);
		assertEquals(1, b1BiggerThenB3);
		
		
		int b4BiggerThenb1 = b4.vergleiche(b1);
		assertEquals(1, b4BiggerThenb1);
		
		int b1SmallerThenB4 = b1.vergleiche(b4);
		assertEquals(-1, b1SmallerThenB4);
	}
	
	@Test
	public void testMultipliziere(){
		Bruch b1 = new Bruch(true, 2, 3);
		Bruch b2 = new Bruch(true, 1, 2);
		
		b1.multipliziere(b2);
		assertEquals(1,b1.getZaehler());
		assertEquals(3,b1.getNenner());
	}
	
	@Test
	public void testSubtrahiere(){
		Bruch b1 = new Bruch(true, 2, 2);
		Bruch b2 = new Bruch(true, 1, 2);
		
		b1.subtrahiere(b2);
		assertEquals(1, b1.getZaehler());
		assertEquals(2, b1.getNenner());
	}
	
	@Test
	public void testToString(){
		Bruch b1 = new Bruch(true, 2, 3);
		assertEquals("2/3", b1.toString());
	}
}
