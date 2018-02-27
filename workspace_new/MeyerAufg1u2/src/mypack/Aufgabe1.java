/**
 * Mareike Meyer
 * 
 * 03.11.2017
 * 
 * Dieses Klasse bietet eine Annuit�ten- sowie eine Ratentilgungsrechnung in Tabellendarstellung.
 * Die dargestellten Werte sind Jahr, Restschuld am Jahresanfang, Zinsen, Tilgung sowie Rate pro Jahr f�r beide Rechnungen.
 * 
 * ToDo: Beseitigung des doppelten Abstandes zwischen der Spalte Zinsen und Tilgung.
 */

package mypack;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Aufgabe1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Variablen
		int tilgungsdauer = 5;
		double p = 0.07;

		// Variablen f�r Annuit�tentilgung
		double kreditsummeAnnuitaet = 25000.0;
		double zinsenAnnuitaet = 0;
		double proJahrAnnuitaet = 0;
		int jahrAnnuitaet = 1;
		double tilgungsrateAnnuitaet = 0;

		// Variablen f�r Ratentilgung
		double kreditsummeRatentilgung = 25000.0;
		double tilgungsrate = 0;
		double zinsenRatentilgung = 0;
		double proJahrRatentilgung = 0;
		int jahrRatentilgung = 1;

		// Variablen f�r Ausgabe
		double laengsteZahl = 0;

		// Ausgabe der Tabelle mit Abgleich des Jahres f�r Ratentilgung

		while (jahrRatentilgung <= tilgungsdauer) {

			// Berechnungen f�r Ratentilgung

			// Nur f�r das erste Jahr, Tilgungsrate bleibt gleich
			if (jahrRatentilgung == 1) {
				tabellenAusgeben();
				laengsteZahl = kreditsummeRatentilgung;
				tilgungsrate = ratentilgungBerechnen(kreditsummeRatentilgung, tilgungsdauer);
			}
			// F�r alle Jahre gr��er 1
			else {
				kreditsummeRatentilgung = kreditsummeRatentilgung - tilgungsrate;
				proJahrRatentilgung = zinsenRatentilgung + tilgungsrate;
			}

			// Zinsen berechnen
			zinsenRatentilgung = zinsenBerechnen(kreditsummeRatentilgung, p);

			// Jahresbetrag errechnen
			proJahrRatentilgung = zinsenRatentilgung + tilgungsrate;

			// Ausgabe Ratentilgung
			werteAusgeben(jahrRatentilgung, kreditsummeRatentilgung, zinsenRatentilgung, tilgungsrate,
					proJahrRatentilgung, laengsteZahl);
			// System.out.println(jahrratentilgung + "\t" +
			// a.format(kreditsummeratentilgung) + "\t" + a.format(zinsenratentilgung) +
			// "\t" + a.format(tilgungsrate) + "\t" + a.format(projahrratentilgung));

			// Jahr erh�hen um 1
			jahrRatentilgung = jahrRatentilgung + 1;

		}

		// Ausgabe der Tabelle mit Abgleich des Jahres f�r Annuit�tentilgung
		while (jahrAnnuitaet <= tilgungsdauer) {

			// Berechnungen f�r Annuit�tentilgung

			// Nur f�r das erste Jahr, j�hrliche Gesamtrate bleibt gleich
			if (jahrAnnuitaet == 1) {
				tabellenAusgeben();

				proJahrAnnuitaet = (annuitaetentilgungBerechnen(kreditsummeAnnuitaet, tilgungsdauer, p));
			}

			// F�r alle Jahre gr��er 1

			kreditsummeAnnuitaet = kreditsummeAnnuitaet - tilgungsrateAnnuitaet;
			zinsenAnnuitaet = zinsenBerechnen(kreditsummeAnnuitaet, p);
			tilgungsrateAnnuitaet = proJahrAnnuitaet - zinsenAnnuitaet;

			// Ausgabe Annuit�tentilgung
			werteAusgeben(jahrAnnuitaet, kreditsummeAnnuitaet, zinsenAnnuitaet, tilgungsrateAnnuitaet, proJahrAnnuitaet,
					laengsteZahl);

			jahrAnnuitaet = jahrAnnuitaet + 1;

		}

	}

	/**
	 * Diese Methode berechnet die Ratentilgung
	 * 
	 * @param k: Kreditsumme
	 * @param n: Tilgungsdauer in Jahren
	 * @return: Ergebnis der ermittelten Gesamtrate
	 */
	public static double ratentilgungBerechnen(double k, double n) {

		double tilgungsrate = k / n;
		return tilgungsrate;
	}

	// Berechne Annuit�ten-Tilgung - j�hrliche Gesamtrate
	public static double annuitaetentilgungBerechnen(double kreditsumme, int jahre, double zinssatz) {

		double gesamtrate = (kreditsumme * Math.pow(1 + zinssatz, jahre) * zinssatz)
				/ (Math.pow(1 + zinssatz, jahre) - 1);

		return gesamtrate;
	}

	/**
	 * Diese Methode berechnet die Zinsen pro Jahr.
	 * 
	 * @param kreditsumme: Kreditsumme
	 * @param p: Prozentsatz
	 * @return: Ergebnis der Zinsberechnung
	 */

	public static double zinsenBerechnen(double kreditsumme, double p) {

		double zinsen = kreditsumme * p;

		return Math.round(100.0 * zinsen) / 100.0;
	}

	public static void tabellenAusgeben() {

		System.out.println("\nJahr\tRestschuld am\tZinsen\t\t\tTilgung\t\tRate pro Jahr");
		System.out.println("\tJahresanfang");

	}

	/**
	 * Diese Methode gibt die formatierte Tabelle mit den vorher berechneten Werten
	 * aus.
	 * 
	 * @param jahr: Jahr
	 * @param restschuld: Restschuld
	 * @param zinsen: Zinsen
	 * @param tilgung: Tilgung
	 * @param rate: Rate
	 * @param laengsteZahl: gr��te / l�ngste Zahl
	 */

	public static void werteAusgeben(int jahr, double restschuld, double zinsen, double tilgung, double rate,
			double laengsteZahl) {
		DecimalFormat a = new DecimalFormat("###,##0.00 �");

		String b = laengeAnpassen(a.format(restschuld), a.format(laengsteZahl).length());
		String c = laengeAnpassen(a.format(zinsen), a.format(laengsteZahl).length());
		String d = laengeAnpassen(a.format(tilgung), a.format(laengsteZahl).length());
		String e = laengeAnpassen(a.format(rate), a.format(laengsteZahl).length());

		String jahrStr = "";
		if (jahr < 10) {
			jahrStr = " " + jahr;
		} else {
			jahrStr = "" + jahr;
		}

		System.out.println(jahrStr + "\t" + b + "\t" + c + "\t" + "\t" + d + "\t" + e);

	}

	/**
	 * Diese Methode passt die L�nge der Spalten so an, dass keine Verschiebung
	 * auftritt und jede Spalte nur so gro� ist, wie ihre gr��te / l�ngste Zahl.
	 * 
	 * @param x: Zahl bzw. String, die / der anzupassen ist
	 * @param laengsteZahl: Erste Zahl zur Anpassung der Tabelle 
	 * 						(die Zahlen werden nur kleiner, 
	 * 						nicht gr��er)
	 * @return: Gibt den angepassten String zur�ck. 
	 * 			(Achtung: Diese Funktion ist noch nicht ganz funktional,
	 * 			s. doppelte Abstand)
	 */

	public static String laengeAnpassen(String x, int laengsteZahl) {

		while (x.length() < laengsteZahl) {
			x = " " + x;
		}

		return x;
	}

}