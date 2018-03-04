package pack;

import java.util.List;
import java.util.TreeMap;

public class EmpfehlungArtikel extends Empfehlung{

	public EmpfehlungArtikel() {
		super(50);
	}
	
	protected void zusatzFiltern(List<Kunde> kundenMitDemGleichenArtikel, Kunde k) {	
		int kundennummer = k.getKundenNr();
		//Key Artikelnummer, Value Häufigkeit
		TreeMap<Integer, Integer> empfehlungKombiArtikel = new TreeMap<>();
		
		
		System.out.println("Haeufigkeit Kombi-Artikel fuer ArtikelNr " + "" + ":" + empfehlungKombiArtikel);
		
	}


}
