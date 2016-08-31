package fh.twoplay.alapo;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import fh.twoplay.base.IBoard;

@Ignore
public class AlapoPlayGameTest {

//	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
//
//	@Before
//	public void setUpStreams() {
//		System.setOut(new PrintStream(outContent));
//	}
//
//	@After
//	public void cleanUpStreams() {
//		System.setOut(null);
//	}
//
//	@Rule
//	public final TextFromStandardInputStream systemInMock = emptyStandardInputStream();
//
//	@Test
//	public void readTextFromStandardInputStream() {
//		String ls = System.getProperty("line.separator");
//
//		String[] zuege = { "A2A3", "A5A4", "A3A4", "A6A5", "A4A5", "B5A4", "A5A6" };
//
//		systemInMock.provideLines(zuege[0] + ls + zuege[1] + ls + zuege[2] + ls + zuege[3] + ls + zuege[4] + ls
//				+ zuege[5] + ls + zuege[6] + ls);
//
//		PlayGame.main(null);
//
//		String output = outContent.toString();
//		
//		String[] outputlines = output.split("\n");
//
//		IBoard b = new AlapoBoard();
//		int i = 0;
//		for (String line : outputlines) {
//			if (line.startsWith("=")) {
//				assertEquals("="+b.move(zuege[i]), line);
//				i++;
//			}
//		}
//
//		// Da System.ou.println nicht geht(umgeleitet) muss es über einen
//		// fehlgeschlagenen Test angezeigt werden.
//		//assertEquals("hello", res);
//
//	}

}
