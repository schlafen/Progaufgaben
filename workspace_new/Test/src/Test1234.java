/**
  * Berechnung - enthält Methoden zur Berechnung von Tilgung
  *
  * Programm 			 : Tilgungspläne im Fenster
  * ----------------------------------------------------------------------------
  * Java Version		 : j2sdk1.4.2
  * Entwicklungsumgebung : JCreator LE Vers. 3.10.008
  * ----------------------------------------------------------------------------
  * @autor    		     : Mehmet-Ertan Cam
  * ----------------------------------------------------------------------------
  * @since   			 : 15.03.05 - Erstellung
  * @since				 : 22.03.05 - Kommentierung
  * ----------------------------------------------------------------------------
  * Dateiname			 : Main.java
  * @version  			 : 1.0
  * ----------------------------------------------------------------------------
  * Beschreibung		 : Methoden zur Berechnung von Tilgung
  *						   Erzeugt formatierte Tabelle
  * ----------------------------------------------------------------------------
  */
package mypack;
import java.text.*;			//wegen DecimalFormat
  
class Berechnung 
{
	//Variablendeklaration
	public double	kredit,
				 	zinssatz;
	public int    	laufzeit;
	public String 	name;
	
	private double  tilgungsrate,
					restschuld,
					zinsbetrag,
					zinsgesamt,
 					annuitaet,
					annuitaetgesamt;
	
	private int 	time;	// für aktuellen Zeitpunkt
	
  /**---------------------------------------------------------------------------
	* Konstruktor - Berechnungen
	* 
	* erhält folgende Werte von Klasse Fenster :
	*
	* @param kredit 	(int) 	 : Höhe des Kredits
	* @param zinssatz 	(int)	 : Höhe des Zinssatzes
	* @param laufzeit	(int) 	 : Dauer des Kredits
	* @param name		(String) : Art der Tilgung
	* --------------------------------------------------------------------------
    */	
	public Berechnung(double kredit,double zinssatz,int laufzeit,String name)
	{
		this.kredit = kredit;
		this.zinssatz = zinssatz;
		this.laufzeit = laufzeit;
		this.name = name;
		restschuld = kredit;
	}

  /** --------------------------------------------------------------------------
	* Methode - berechneTilgungsrate
	*
	* Art der Tilgung wird durch Bedingung ermittelt
	* Tilgunsrate wird berechnet
	* --------------------------------------------------------------------------
	*/
	private void berechneTilgungsrate()
	{
		if (name == "Ratentilgung")
		tilgungsrate = kredit / laufzeit;
		else 
		tilgungsrate = kredit *( zinssatz/100 * (Math.pow((1+zinssatz/100), time -1))/(Math.pow(1+zinssatz/100,laufzeit) -1));
	}


  /** --------------------------------------------------------------------------
	* Methode - berechneRestschuld
	*
	* Restschuld wird berechnet
	* Jahr(time) wird um 1 erhöht
	* --------------------------------------------------------------------------
	*/
	private void berechneRestschuld()
	{
		if (name == "Ratentilgung")
		restschuld = kredit - (time * tilgungsrate);
		else
		
		restschuld = restschuld - tilgungsrate; 

		time++;
	}
		
	
  /** --------------------------------------------------------------------------
	* Methode - berechneZinsbetrag
	*
	* Zinsbetrag wird berechnet
	* --------------------------------------------------------------------------
	*/
	private void berechneZinsbetrag()
	{
		zinsbetrag = zinssatz/100 * restschuld;
	}
	
		
  /** --------------------------------------------------------------------------
	* Methode - berechneAnnuitaet
	*
	* Annuitaet wird berechnet
	* --------------------------------------------------------------------------
	*/
	private void berechneAnnuitaet()
	{
		annuitaet = zinsbetrag + tilgungsrate;
	}
	
  /** --------------------------------------------------------------------------
	* Methode - addiereZinsbetrag
	*
	* Gesamtzinsen werden berechnet
	* --------------------------------------------------------------------------
	*/
	private void addiereZinsbetrag()
	{
		zinsgesamt = zinsgesamt + zinsbetrag;
	}
	
	
  /** --------------------------------------------------------------------------
	* Methode - addiereAnnuitaet
	*
	* Gesamtannuiaeten werden berechnet
	* --------------------------------------------------------------------------
	*/
	private void addiereAnnuitaet()
	{
		annuitaetgesamt = annuitaetgesamt + annuitaet;
	}
	
	
  /** --------------------------------------------------------------------------
	* Methode - formatNum
	*
	* Festlegung der Zahlendarstellung
	* Länge wird ermittelt, falls nötig Leerzeichen angehängt
	* Werte werden rechtsbündig zurückgegeben
	* 
	* @param zahl   (double) : zu formatierende Zahl
	* @param laenge (double) : gewünschte Gesamtlänge
	* @return zahlStr  (String)  -Breite der Spalten + Inhalt-
	* --------------------------------------------------------------------------
	*/
    public String formatNum(double zahl,int laenge) 
    {
        DecimalFormat form = new DecimalFormat("###,##0"); 
        StringBuffer zahlStr=new StringBuffer(form.format(zahl));
        for (int laufvar = zahlStr.length() ; laufvar < laenge ; laufvar++)
        	zahlStr.insert(0,' ');
        return zahlStr.toString();
	}
	
  /** --------------------------------------------------------------------------
	* Methode - tabellenAnfang
	*
	* @return	(String) -Inhalt des Tabellen Kopfes-
	* --------------------------------------------------------------------------
	*/
	public String tabellenAnfang()
	{
		return ("\n fuer einen Kredit von   " + kredit + " €   ueber " + laufzeit + " Jahre   bei " + zinssatz +" % Zins\n\n  Jahr   Schuld(Vorjahr)    Zinsbetrag    Tilgungsrate    Annuitaet\n  -----------------------------------------------------------------\n");
	}
	
  /** --------------------------------------------------------------------------
	* Methode - tabelleRechnung
	*  
	* ruft die einzelnen Berechnungsmethoden auf und gibt das Ergebniss
	* als String zurück
	* 
	* @return   (String)  -formatierter Ausgabe -
	* --------------------------------------------------------------------------
	*/
	public String tabelleRechnung()
	{
		berechneRestschuld();
		berechneZinsbetrag();
		berechneTilgungsrate();
		berechneAnnuitaet();
		addiereZinsbetrag();
		addiereAnnuitaet();
		
		return (formatNum(time,5)  + formatNum(restschuld,18) + formatNum(zinsbetrag,14) + formatNum(tilgungsrate,16) + formatNum(annuitaet,13) + "\n");
	}
	

  /** --------------------------------------------------------------------------
	* Methode - tabellenEnde
	*  
 	* Rückgabe der Restschuld im aktuellen Jahr,
 	* Gesamtsumme der Zinsen 
 	* und der geleisteten Annuitäten
	* 
	* @return   (String)  -formatierter Ausgabe -
	* --------------------------------------------------------------------------
	*/
	public String tabellenEnde()
	{
		berechneRestschuld();

		return (formatNum(time++,5) + formatNum(restschuld,18) + "\n ------------------------------------------------------------------\n Zinsen gesamt = " + formatNum(zinsgesamt,14) + "\n Annuitäten gesamt = " + formatNum(annuitaetgesamt,10) + "\n\n\n");
	}
}