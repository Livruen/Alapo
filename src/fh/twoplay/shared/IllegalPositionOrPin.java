package fh.twoplay.shared;

public class IllegalPositionOrPin extends RuntimeException {
	
	public IllegalPositionOrPin(){
		System.out.println("Illegal position or pawn.");
	}
}
