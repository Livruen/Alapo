package fh.twoplay.match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ProcessBuilder.Redirect;

import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.base.IPlayer;
import fh.twoplay.games.ABoard;

/**
 * @author Natasza Szczypien
 *
 */
public class PlayerConsole implements IPlayer {
	
	private ABoard board;
	private boolean isWhite;

	public PlayerConsole(IBoard board, boolean isWhite) {
		super();
		this.board = (ABoard) board;
		this.isWhite = isWhite;
	}

	@Override
	public boolean isWhite() {
		return this.isWhite;
	}

	@Override
	public boolean hasWon() {
		return isWhite() ? board.evaluate() == board.WHITEWINS : board.evaluate() == board.BLACKWINS;
	}

	@Override
	public boolean hasLost() {
		return isWhite() ? board.evaluate() == board.BLACKWINS : board.evaluate() == board.WHITEWINS;
	}

	@Override
	public String nextOwnMove() {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String move = " ";
		EStatus status = null;
		boolean moving = true;

		
		try {
			while (moving) {
				System.out.println("\nType a move like [A2A3]");
				move = reader.readLine();
				status = board.move(move.toUpperCase());
				
				/*If Status is not LEGAL or FINAL the player can
				 * try again
				 * */
				if (status == EStatus.LEGAL || status == EStatus.FINAL) {
					moving = false;
				} else {
					System.out.printf("Player %s try Again", isWhite()?"white":"black" );
				}
			}
			//TODO Wieso funktioniert es hier nicht? Fehlermeldung beim asführen
//			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return move;
	}

	@Override
	public EStatus nextOppMove(String move) {
		return board.move(move);
	}

	public String toString() {
		board.toString();
		return isWhite() ? "true" : "false";
	}
}
