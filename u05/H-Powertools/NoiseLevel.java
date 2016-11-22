/**
 * Class for Noise Level
 *
 * @author     Paul Suetterlin - 366676
 * @version    1.0
 */
public class NoiseLevel{
	/**
	 * aktueller Geräuschpegel
	 */
	public int geraeuschpegel;

	/**
	 * Konstruktor für das Objekt. Setzt den Geräuschpegel auf 0
	 */
	public NoiseLevel(){		
		geraeuschpegel = 0;
	}

	/**
	 * Erlaubt es das aktuelle Geräuschniveu aufgrund eines neuen Geräusches anzupassen.
	 * Dabei wird der Geraeuschpegel anhand des Maximums ausgewählt
	 * Sind der ursprüngliche und der neue Pegel jeweils minimum 10, so wird 1 addiert.
	 * @param      newLevel  Pegel des neuen Geräusches
	 */
	public void add(int newLevel){
		int add = (newLevel > 9 && geraeuschpegel > 9) ? 0 : 1;
		geraeuschpegel = (newLevel > geraeuschpegel) ? newLevel : geraeuschpegel;
		geraeuschpegel += add;
	}

	/**
	 * Liefert eine String Representation des Objektes zurück. Dabei gilt für 
	 * Geraeuschpegel < 2 die Ausgabe "leise",
	 * Geraeuschpegel >= 2 aber < 5 die Ausgabe "normal",
	 * Geraeuschpegel >= 5 aber < 10 die Ausgabe "laut",
	 * Geraeuschpegel >= 10 aber < 12 die Ausgabe "LAUT",
	 * und Geraeuschpegel >=12 die Ausgabe "fuchtbar laut".
	 *
	 * @return     Beschreibung der Lautstärke als String ("leise", "normal", "laut", "LAUT", "furchtbar laut") s. Methodenbeschreibung
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