package fh.twoplay.base;

/**
 * Aufz�hlungstyp f�r auszuf�hrende Spielz�ge.
 * @author F. Hoeppner
 */

public enum EStatus {
	LEGAL("gueltiger Zug"), 
	FINAL("gueltiger Zug, der das Spiel entscheidet (Spielende)"),
	NOTOKEN("Ausgangsfeld enthaelt keine Spielfigur"), 
	ILLEGAL("Zielfeld von Spielfigur nicht erreichbar"), 
	WRONGCOLOR("Spielfigur der anderen Farbe muss gezogen werden"), 
	SYNTAXERROR("Zug entspricht nicht der vorgegebenen Syntax"), 
	OUTOFRANGE("Zugkoordinaten ausserhalb des Spielfelds"),
	POSSIBILITIES("Mögliche Zuege fuer Spielfigur.");
	
	final String text;

	EStatus(String t) {
		text = t;
	}
	
	public String toString() {
		return text;
	}
}