package mypack;

public class Aufgabe_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
	double kreditsumme = 2000;
	int tilgungsdauer = 1;
	double tilgungsrate;
	double p = 0.07;
	
		
	}
	
	
	// Berechne Ratentilgung - Tilgungsrate
	public static double ratentilgungBerechnen(double k,double n){
	
	double tilgungsrate = k/n;	
		
	return tilgungsrate;
	}
	
	// Berechne Annuitäten-Tilgung - jährliche Gesamtrate
	public static double annuitaetentilgungBerechnen(double k, double n, double p) {
	
	double gesamtrate = (k * Math.pow(1+p, n) * p) / (Math.pow(1+p, n) -1);
	
	return gesamtrate;
	}
	
	
	// Ausgabe Ratentilgung
	
	System.out.print("Jahr " + \t + "Bla")

}
