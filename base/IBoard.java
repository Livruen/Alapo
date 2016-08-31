package fh.twoplay.base;

/**
 * Interface f�r ein Spielfeld eines (beliebigen) Brettspiels.
 * 
 * @author F. Hoeppner
 */

public interface IBoard extends Cloneable {



	/**
	 * Liefert die Groesse des (quadratischen) Spielfelds, also bspw. 8 bei
	 * einem Schachbrett mit 8x8 Feldern. (Die Gr��e wird einstellig bleiben,
	 * also maximal den Wert 9 annehmen).
	 * 
	 * @return Anzahl der Feld in x- und y-Richtung auf dem quadratischen
	 *         Spielfeld.
	 */
	int getSize();

	/**
	 * Initialisiert das Spielfeld. Alle m�glicherweise zuvor existierenden
	 * Spielfiguren werden entfernt. Die Figuren werden gem�� der angegebenen
	 * Positionen gesetzt. Nach dem Aufruf ist Wei� als n�chstes am Zug.
	 * 
	 * @param description
	 *            Folge von Spielfigur-Positionen, pro Spielfigur folgen 4
	 *            Zeichen, wovon das erste Zeichen die Farbe der Figur (nur s/S
	 *            f�r schwarz und w/W f�r wei� erlaubt), die Spielfigur selbst
	 *            (zum Beispiel B f�r Bauer, erlaubt sind nur Buchstaben aus
	 *            {@link IBoard#getLabels()}. Genaue Spielfigurbezeichnung siehe
	 *            Aufgabenstellung), x-Position (Buchstabe, A bezeichnet die
	 *            erste x-Position), y-Position (Zahl, 1 bezeichnet die erste
	 *            y-Position). Die Figuren folgen hintereinander weg durch
	 *            Leerzeichen getrennt.
	 */
	void init(String description);

	/**
	 * Liefert eine Beschreibung des Spielfelds.
	 * 
	 * @return Liefert alle Spielfiguren auf dem Spielfeld im gleichen Format,
	 *         wie das Argument von {@link IBoard#init(String)} es erfordert.
	 */
	String getDescription();

	/**
	 * Liefert einen String mit allen m�glichen Spielz�gen einer Spielfigur an
	 * der angegebenen Position (durch Leerzeichen getrennt).
	 * 
	 * @param position
	 *            Position einer Spielfigur, f�r die die Zugm�glichkeiten
	 *            genannt werden sollen.
	 * @return Liefert einen String der m�glichen Spielz�ge entspricht (also ein
	 *         String "", wenn kein Zug m�glich ist). Jeder einzelne Zug erf�llt
	 *         das Format des Arguments von {@link IBoard#move(String)}. Mehrere
	 *         Z�ge sind durch Leerzeichen getrennt. Liefert null, wenn an der
	 *         position keine Spielfigur vorhanden ist.
	 */
	String getMoves(String position);

	/**
	 * F�hrt einen Zug aus, wenn er legal ist {@link EStatus#LEGAL}. Wei�
	 * beginnt. Beide Spieler ziehen abwechselnd.
	 * 
	 * @param turn
	 *            Zeichenkette, die den Zug beschreibt (bspw. B2B4): die ersten
	 *            beiden Zeichen beschreiben die Startposition der gezogenen
	 *            Spielfigur, die zweiten beiden Zeichen die Endposition.
	 * @return Der R�ckgabewert gibt, an ob der Zug legal war oder nicht.
	 */
	EStatus move(String turn);

	/**
	 * Bewertet ein Spielfeld.
	 * 
	 * @return Die Bewertung ist 0 bei einer ausgeglichenen Situation, positiv
	 *         bei einem Vorteil f�r wei�, negativ bei einem Vorteil f�r
	 *         schwarz. Wird eine Figur von wei� geschlagen, verringert sich die
	 *         Bewertung. Wird eine Figur von schwarz geschlagen, erh�ht sich
	 *         der Wert. Wenn wei� (bzw. schwarz) gewonnen hat, wird WHITEWINS
	 *         (bzw. BLACKWINS) geliefert.
	 */
	double evaluate();

	public static final double WHITEWINS = +Double.MAX_VALUE;
	public static final double BLACKWINS = -Double.MAX_VALUE;

	/**
	 * Liefert einen String mit einem gut lesbaren Spielfeld.
	 * 
	 * @return Entsprechend der Spielfeldgr��e {@link IBoard#getSize()} wird
	 *         eine Achsenbeschriftung erzeugt. Pro Feld wird der zweistellige
	 *         Buchstabencode der Figur (Farbe, Figur) ausgegeben. Die Zahl am
	 *         rechten Rand im Beispiel entspricht der Bewertung des Spielfelds
	 *         {@link IBoard#evaluate()}. Beispiel:
	 * 
	 *         <pre>
	 *   4|SB SB SB SB
	 *   3|
	 *   2|
	 *   1|WB WB WB WB
	 *    +----------- 0.0
	 *     A  B  C  D
	 * </pre>
	 */
	String toString();

	/**
	 * Standard Clone-Methode. Erst ab Aufgabe 3, kann bis dahin einfach null
	 * zur�ckliefern.
	 */
	IBoard clone();

}
