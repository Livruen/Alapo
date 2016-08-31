package fh.twoplay.match;


import fh.twoplay.base.EStatus;
import fh.twoplay.base.IPlayer;

public class Match {
	private IPlayer white;
	private IPlayer black;

	public Match(IPlayer white, IPlayer black) {
		super();
		this.white = white;
		this.black = black;
	}

	public void engine() {
		
		EStatus status = null;
		IPlayer player = white;
		IPlayer enemy = black;
		
 		while (status != EStatus.FINAL) {	
		player.toString();

			status = turn(player,enemy);

			if(player == white){
				player = black;
				enemy = white;
			} else{
				player = white;
				enemy = black;
			}
		}
 		
		System.out.println("The End");
	}

	private EStatus turn(IPlayer player, IPlayer enemy) {
		EStatus status;
		String move = player.nextOwnMove();
		status = enemy.nextOppMove(move);
		System.out.println(status);
		return status;
	}
}
