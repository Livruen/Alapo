package fh.twoplay;

import java.util.Arrays;

import fh.twoplay.base.EStatus;

/**
 * @author M. Krone
 */
public abstract class ComparisonTest
{
	protected boolean compareCaseInsensitive (String expected, String actual)
	{
		String[] in1 = expected.toUpperCase().trim().split(" ");
		String[] in2 = actual.toUpperCase().trim().split(" ");
		
		return this.equals(in1, in2);
	}
	
	protected boolean compareCaseSensitive (String expected, String actual)
	{
		String[] in1 = expected.trim().split(" ");
		String[] in2 = actual.trim().split(" ");
		
		return this.equals(in1, in2);
	}	
	
	protected boolean equals (String[] in1, String[] in2)
	{
		Arrays.sort(in1);
		Arrays.sort(in2);
		
		return Arrays.equals(in1, in2);		
	}
	
	protected boolean containsEnum(EStatus[] answers, EStatus answer)
	{
		return Arrays.asList(answers).contains(answer);
	}
}