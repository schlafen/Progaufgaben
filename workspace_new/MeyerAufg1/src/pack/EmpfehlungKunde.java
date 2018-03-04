package pack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmpfehlungKunde extends Empfehlung{

	private List<Kunde> kundenListe = new ArrayList<Kunde>();
		
	public EmpfehlungKunde() {
		//dynamisch erstellen
		super(50);
		
		try {
			readData();
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private double berechneLaenderwert(String land) {
		double laenderwert = 0;
		
		if(land == "DE") {
			laenderwert = 1.0;
		}
		
		if(land == "CH") {
			laenderwert = 0.8;
		}
		
		if(land == "US") {
			laenderwert = 0.7;
		}
		
		return laenderwert;
	}
	
	private void readData()  throws FileNotFoundException, IOException{
		try (Workbook wb = new XSSFWorkbook(new FileInputStream(".//src//pack//Daten.xlsx"))) {
			Sheet sheet = wb.getSheetAt(1);
			for (Row row : sheet) {

				int kundenNr = 0;
				String kundenName = "";
				String land = "";
				int alter = 0;

				for (Cell cell : row) {
					// System.out.println(cell);

					if (cell.getColumnIndex() == 0) {
						kundenNr = (int) cell.getNumericCellValue();
					}

					if (cell.getColumnIndex() == 1) {
						kundenName = cell.getStringCellValue();
					}

					if (cell.getColumnIndex() == 2) {
						land = cell.getStringCellValue();
					}

					if (cell.getColumnIndex() == 3) {
						alter = (int) cell.getNumericCellValue();
					}
				}

				kundenListe.add(new Kunde(kundenNr, kundenName, land, alter));
			}
		}
	}
	
	@Override
	protected void zusatzFiltern(List<Kunde> kundenMitDemGleichenArtikel, Kunde k) {
		int kundenNr = k.getKundenNr();
		double landZuVergleichen = berechneLaenderwert(k.getLand());
		int alterKundeZuVergleichen = k.getAlter();
		double aehnlichkeit;
		double bestMatch=0;
		double landVergleich = 0;
		int alterVergleich = 0;
		
		for (Kunde l: kundenMitDemGleichenArtikel) {
			for (Kunde n: kundenListe) {
				
				
				
				aehnlichkeit = (landVergleich - landZuVergleichen) / landVergleich + (alterKundeZuVergleichen - alterVergleich) / 100.0;
				
				if(aehnlichkeit<bestMatch) {
					bestMatch = aehnlichkeit;
				}
			}
				
		}
		
		
		
		System.out.println("Best-Match-Kunde: ");
		System.out.println("Empfehlungen fuer KundeNr " );
		
		
	}
	

}
