package fh.twoplay.games;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fh.twoplay.alapo.AlapoBoard;
import fh.twoplay.base.EStatus;
import fh.twoplay.base.IBoard;
import fh.twoplay.base.IPlayer;
import fh.twoplay.breakthrough.BreakBoard;
import fh.twoplay.match.HybridBoard;
import fh.twoplay.match.Match;
import fh.twoplay.match.PlayerConsole;
import fh.twoplay.match.PlayerSimple;

public class PlayGame {

	/**
	 * 
	 */
	public static final boolean BLACK = false;
	/**
	 * 
	 */
	public static final boolean WHITE = true;

	public static void main(String[] args) {
		engine();
	}

	private static void engine() {

		ABoard board = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int inputGame = 0;
		IPlayer player1 = null;
		IPlayer player2 = null;
		boolean choosing = true;

		System.out.println("\t----------------- Welcome -----------------\n");
		while (choosing) {
			try {
				System.out.println("Chose a game:");
				System.out.println("[1] BREAKTHROUGH \n[2] ALAPO\n[3] HYBRID");
				inputGame = Integer.parseInt(reader.readLine());
				if (inputGame == 1) {
					board = new BreakBoard();
					choosing = false;
				} else if (inputGame == 2) {
					board = new AlapoBoard();
					choosing = false;
				} else if (inputGame == 3) {
					board = new HybridBoard();
					choosing = false;
				} else {
					System.out.println("Try again.");
				}
			} catch (NumberFormatException e1) {
				System.out.println("Try again.");
			} catch (IOException e1) {
				System.out.println("Try again.");
			}
		}
		choosing = true;
		
		while (choosing) {
			System.out.println("How do you want to play?\n[1] Singleplayer \n[2] Multiplayer \n[3] AI vs. AI");

			try {
				inputGame = Integer.parseInt(reader.readLine());

				switch (inputGame) {
				case 1:
					player1 = new PlayerConsole(board, WHITE);
					player2 = new PlayerSimple(board.clone(), BLACK);
					choosing = false;
					break;
				case 2:
					player1 = new PlayerConsole(board, WHITE);
					player2 = new PlayerConsole(board.clone(), BLACK);
					choosing = false;
					break;
				case 3:
					player1 = new PlayerSimple(board, WHITE);
					player2 = new PlayerSimple(board.clone(), BLACK);
					choosing = false;
					break;
				default:
					System.out.println("Wrong input");

				}
			} catch (NumberFormatException e1) {
				System.out.println("Try again.");
			} catch (IOException e1) {
				System.out.println("Try again.");
			}
			
		}

		/* Starts the game engine */
		Match match = new Match(player1, player2);
		match.engine();

		System.out.println("The end");
	}

}
