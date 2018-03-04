package pack;

import java.util.Arrays;
import java.util.List;
import java.util.Map.Entry;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class Empfehlung {

	// Key Kunde, Value Artikellliste, erstmal mit Double und Artikel
	private TreeMap<Double, List<Artikel>> einkauf = new TreeMap<>();
	private List<Artikel> artikelliste = new ArrayList<Artikel>();
	public List<Kunde> kundenMitDemGleichenArtikel = new ArrayList<Kunde>();

	public Empfehlung(int kundenNr){
		try {
			leseDaten();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Ausgabe
		for(Entry<Double, List<Artikel>> entry : einkauf.entrySet()) {
			Double kundennummer = entry.getKey();
			List<Artikel> artikelliste = entry.getValue();
			System.out.println("Key: Kunde " + "[nr=" + kundennummer + "] - Value: " + artikelliste );
		}
		
	}
	
	
	public TreeMap<Double, List<Artikel>> getEinkauf() {
		return einkauf;
	}


	public List<Artikel> getArtikelliste() {
		return artikelliste;
	}


	public void setArtikelliste(List<Artikel> artikelliste) {
		this.artikelliste = artikelliste;
	}


	private void leseDaten()  throws FileNotFoundException, IOException{
		// Daten einlesen
				try (Workbook wb = new XSSFWorkbook(new FileInputStream(".//src//pack//Daten.xlsx"))) {
					Sheet sheet = wb.getSheetAt(0);
					for (Row row : sheet) {
						if(row.getRowNum()==0) {}
						else {
						//System.out.println("rownum: " + row.getRowNum());
						int i=0;
						double kundenNr = 0;
						for (Cell cell : row) {
							//System.out.println(cell);
							
							if (cell.getColumnIndex() == 0) {
								kundenNr = cell.getNumericCellValue();
							}

							// Artikel in Liste hinzufügen (dynamisch zu definieren)
							if (cell.getColumnIndex() <= 6 && cell.getColumnIndex() > 1) {
								if (cell.getNumericCellValue() == 1) {
									//artikelliste[i] = new Artikel((i+2) * 111);
									artikelliste.add(new Artikel((i+2)*111));
								}
							i++;
							}
								
							einkauf.put(kundenNr, artikelliste);
						}
						}
					}
				}
	}

	public List<Kunde> berechneEmpfehlung(int artikelNr, int kundenNr) {
		int i = 0;
		for(Entry<Double, List<Artikel>> entry : einkauf.entrySet()) {
			Double kundennummer = entry.getKey();
			List<Artikel> artikelliste = entry.getValue();
			//iterate over array list
			for(Artikel data : artikelliste) {
				
			}
			
		}
		
		return kundenMitDemGleichenArtikel;
	}
	
	abstract protected void zusatzFiltern(List<Kunde>kundenMitDemGleichenArtikel, Kunde k); //Einschubmethode
	
}
