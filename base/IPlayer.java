package fh.twoplay.base;



/**
 * Interface fuer einen Spieler, der ein Brettspiel {@link IBoard} spielen kann.
 * Eine Umsetzung von {@link IPlayer} sollte einen Konstruktor bereitstellen,
 * der zwei Argumente entgegennimmt, das Spielfeld vom Typ {@link IBoard} und ob
 * es sich um den wei�en Spieler handelt (vom Typ boolean). Jeder Spieler
 * benutzt seine eigene Repraesentation des Spielbretts (wie beim Fernschach)
 * und hat daher (wie im echten Leben) moeglicherweise eine andere Meinung zum
 * Spielausgang als sein Gegenspieler.
 * 
 * @author F. Hoeppner
 */

public interface IPlayer {


	/**
	 * Ist dies der wei�e Spieler?
	 * 
	 * @return true, wenn der Spieler "wei�" spielt.
	 */
	boolean isWhite();

	/**
	 * Sieg feststellen.
	 * 
	 * @return true, wenn der Spieler meint, dass er gewonnen hat.
	 */
	boolean hasWon();

	/**
	 * Niederlage feststellen.
	 * 
	 * @return true, wenn der Spieler meint, dass er verloren hat.
	 */
	boolean hasLost();

	/**
	 * Dieser Spieler macht einen gueltigen Zug und liefert ihn f�r die
	 * Weitergabe an den Gegner.
	 * 
	 * @return Liefert den Zug, den der Spieler gespielt hat (String ist also
	 *         ein m�gliches Argument f�r {@Link IBoard#move(String)})
	 */
	String nextOwnMove();

	/**
	 * Fuehrt den uebergebenen Zug des Gegners auf seinem eigenen Spielfeld aus
	 * und liefert die Bewertung des gegnerischen Zugs. (Nat�rlich hat sich der
	 * Gegenspieler selbst Muehe gegeben, einen gueltigen Zug zu spielen, aber
	 * er koennte sich irren bzw. dieser Spieler koennte das anders sehen.)
	 * 
	 * @param move
	 *            Zug des Gegners
	 * @return Bewertung des gegnerischen Zugs
	 */
	EStatus nextOppMove(String move);

	/**
	 * Liefert das aktuelle Spielbrett (wie {@link IBoard#toString()}).
	 * 
	 * @return true, wenn der Spieler "wei�" spielt.
	 */
	String toString();

}
