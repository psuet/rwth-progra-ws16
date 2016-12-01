/**
 * Class for Noise Level
 *
 * @author     Paul Suetterlin - 366676
 * @version    1.0
 */
public class NoiseLevel{
	/**
	 * aktueller Geraeuschpegel
	 */
	private int geraeuschpegel;

	/**
	 * Konstruktor fuer das Objekt. Setzt den Geraeuschpegel auf 0
	 */
	public NoiseLevel(){		
		geraeuschpegel = 0;
	}

	/**
	 * Erlaubt es das aktuelle Geraeuschniveu aufgrund eines neuen Geraeusches anzupassen.
	 * Dabei wird der neue Geraeuschpegel anhand des Maximums ausgewaehlt
	 * Sind der urspruengliche und der neue Pegel jeweils minimum 10, so wird 1 addiert.
	 * @param      newLevel  Pegel des neuen Geraeusches
	 */
	public void add(int newLevel){
		int extra = (newLevel > 9 && geraeuschpegel > 9) ? 1 : 0;
		geraeuschpegel = Math.max(geraeuschpegel, newLevel) + extra;
	}

	/**
	 * Liefert eine String Representation des Objektes zurueck. Dabei gilt fuer 
	 * Geraeuschpegel < 2 die Ausgabe "leise",
	 * Geraeuschpegel >= 2 aber < 5 die Ausgabe "normal",
	 * Geraeuschpegel >= 5 aber < 10 die Ausgabe "laut",
	 * Geraeuschpegel >= 10 aber < 12 die Ausgabe "LAUT",
	 * und Geraeuschpegel >=12 die Ausgabe "fuchtbar laut".
	 *
	 * @return     Beschreibung der Lautstaerke als String ("leise", "normal", "laut", "LAUT", "furchtbar laut") s. Methodenbeschreibung
	 */
	public String toString(){
		if(geraeuschpegel < 2){
			return "leise";
		} else if (geraeuschpegel < 5){
			return "normal";
		} else if (geraeuschpegel < 10){
			return "laut";
		} else if (geraeuschpegel < 12){
			return "LAUT";
		} else {
			return "furchtbar laut";
		}
	}
}
