/**
 * Mareike Meyer
 * 
 * 03.11.2017
 * 
 * Diese Beispielklasse bietet Funktionen zur Berechnung von Pseudo-Zufallszahlen, dem Mittelwert dieser, der Varianz dieser,
 * von einer zuf�lligen PIN sowie von der Anzahl von Zahlen mit genau zwei Vieren im Wertebereich von 1 bis 9.999.
 * 
 */

package mypack;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.Arrays;

public class Aufgabe2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int a = 521;
		int b = 1;
		int m = 1234;
		int seed = 5;
		double erwarteterMittelwert = (m - 1.0) / 2.0;
		DecimalFormat f = new DecimalFormat("###,##0.00");
		testePeriode(a, b, m, seed);
		System.out.print("Mittelwert: " + f.format(testeMittelwert(a, b, m, seed)));
		System.out.println(" Erwartet: " + f.format(erwarteterMittelwert));
		System.out.print("Varianz: " + f.format(testeVarianz(a, b, m, seed)));
		System.out.println(" Erwartet: " + f.format(((Math.pow(m, 2) - 1) / 12)));
		System.out.println("Generierte PIN: " + pinGenerieren(a, b, m, seed));
		System.out.println("Anzahl Doppel-Vieren zwischen 1 und 9.999: " + zaehleVieren());
	}

	/**
	 * Diese Methode berechnet eine Pseudo-Zufallszahl.
	 * 
	 * @param a:
	 *            Parameter f�r Algorithmus der Pseudos-Zufallszahl-Berechnung
	 * @param b:
	 *            Parameter f�r Algorithmus der Pseudos-Zufallszahl-Berechnung
	 * @param m:
	 *            Parameter f�r Algorithmus der Pseudos-Zufallszahl-Berechnung
	 * @param seed:
	 *            Die "Saat", die als Basis f�r die Pseudo-Zufallszahl dient
	 * @return: Zufallszahl nach Algorithmus
	 */
	public static int naechsteZahl(int a, int b, int m, int seed) {
		int zufallszahl = (a * seed + b) % m;
		// seed = zufallszahl;

		return zufallszahl;
	}

	/**
	 * Diese Methode berechnet die Anzahl der berechneten Pseudo-Zufallszahlen, bis
	 * die zuerst berechnete Pseudo-Zufallszahl wieder kommt, also die Periode.
	 * 
	 * @param a:
	 *            s. naechsteZahl()
	 * @param b:
	 *            s. naechsteZahl()
	 * @param m:
	 *            s. naechsteZahl()
	 * @param seed:
	 *            s. naechsteZahl()
	 * @return: Ergebnis der Berechnung der Periode
	 */

	public static int testePeriode(int a, int b, int m, int seed) {

		int periode = 0;

		int naechstezahl = 0;
		int startzahl = naechsteZahl(a, b, m, seed);
		seed = startzahl;

		while (naechstezahl != startzahl) {
			naechstezahl = naechsteZahl(a, b, m, seed);
			seed = naechstezahl;
			periode = periode + 1;
		}

		System.out.println("Periode nach Schritt " + periode + " f�r Zahl " + startzahl);
		return periode;

	}

	/**
	 * Diese Methode berechnet eine Millionen Pseudo-Zufallszahlen und errechnet den
	 * Mittelwert.
	 * 
	 * @param a:
	 *            s. naechsteZahl()
	 * @param b:
	 *            s. naechsteZahl()
	 * @param m:
	 *            s. naechsteZahl()
	 * @param seed:
	 *            s. naechsteZahl()
	 * @return: Errechneter Mittelwert
	 */
	public static double testeMittelwert(int a, int b, int m, int seed) {
		int max = 1000000000;
		double sum = 0;
		int random = 0;
		double mittelwert = 0;

		for (int i = 1; i <= max; i++) {
			random = naechsteZahl(a, b, m, seed);
			sum = sum + random;
			seed = random;
		}

		mittelwert = sum / max;
		return mittelwert;

	}

	/**
	 * Diese Methode berechnet die Varianz der errechneten Pseudo-Zufallszahlen.
	 * 
	 * @param a:
	 *            s. naechsteZahl()
	 * @param b:
	 *            s. naechsteZahl()
	 * @param m:
	 *            s. naechsteZahl()
	 * @param seed:
	 *            s. naechsteZahl()
	 * @return: Errechnete Varianz von einer Millionen Pseudo-Zufallszahlen
	 */
	public static double testeVarianz(int a, int b, int m, int seed) {
		double varianz = 0;
		double mittelwert = testeMittelwert(a, b, m, seed);
		double sum = 0;
		int max = 1000000000;
		int random = 0;

		for (long i = 1; i <= max; i++) {
			random = naechsteZahl(a, b, m, seed);
			sum = Math.pow(random - mittelwert, 2.0) + sum;
			seed = random;
		}

		varianz = sum / max;
		return varianz;

	}

	/**
	 * Diese Methode generiert einen zuf�lligen PIN als String, der keine doppelten
	 * Zahlen enth�lt.
	 * 
	 * @param a:
	 *            s. naechsteZahl()
	 * @param b:
	 *            s. naechsteZahl()
	 * @param m:
	 *            s. naechsteZahl()
	 * @param seed:
	 *            s. naechsteZahl()
	 * @return: Berechneter PIN
	 */
	public static String pinGenerieren(int a, int b, int m, int seed) {
		String pin = "";

		int random = naechsteZahl(a, b, m, seed);
		pin = "" + random;
		// Bei erf�llen der Bedingungen ist enough = true, solange Pin noch nicht alle
		// Bedingungen erf�llt ist enough = false
		boolean enough = false;

		do {
			random = naechsteZahl(a, b, m, seed);
			pin = "" + random;
			seed = random;
			char[] pinCharacters = pin.toCharArray();

		} while (pin.length() > 4 || pin.length() < 4 && enough);

		if (pin.length() >= 3) {

			if (pin.length() == 3) {
				pin = "0" + pin;
			}
			// String der Pin-L�nge 4 in Char-Array "umwandeln"
			char[] pinCharacters = pin.toCharArray();

			if (pin.length() == 4) {
				enough = true;
				for (int i = 0; i < pin.length(); i++) {
					// abgleichen des derzeitigen Chars (Zahl) mit dem n�chsten Char (Zahl)
					char compare = pinCharacters[i];
					for (int j = i + 1; j < pin.length(); j++) {
						if (pinCharacters[j] == compare) {
							enough = false;
						}
					}
				}

			}

		}
		return pin;
	}

	/**
	 * Diese Methode berechnet die Anzahl von Zahlen, die genau zwei Vieren
	 * beinhalten in dem Wertebereich von 1 bis 9.999.
	 * 
	 * @return: Berechnete Anzahl von Zahlen, die genau zwei Vieren beinhalten
	 */
	public static int zaehleVieren() {
		int anzahlVieren = 0;
		// Character zum Abgleich der Zahl 4
		char a = '4';
		// Counter zum Z�hlen der Vieren in einer Zahl
		int counter = 0;
		String s = "";

		for (int i = 1; i < 10000; i++) {
			// Zahl in String "umwandeln"
			s = "" + i;
			// String in Char-Array "umwandeln"
			char[] stringCharacters = s.toCharArray();

			for (int j = 0; j < s.length(); j++) {

				if (stringCharacters[j] == a) {
					counter++;

				}

			}
			if (counter == 2) {
				anzahlVieren++;

			}
			counter = 0;

		}
		return anzahlVieren;
	}

}
