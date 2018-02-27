package pack;

import java.util.Arrays;
import java.util.TreeMap;
import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Empfehlung {

	// Key Kunde, Value Artikellliste, erstmal mit Double und Artikel
	private TreeMap<Double, Artikel[]> einkauf = new TreeMap<>();
	private Artikel[] artikelliste = new Artikel[6];

	public Empfehlung() throws Exception {
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

					// Artikel in Liste hinzufügen
					if (cell.getColumnIndex() <= 6 && cell.getColumnIndex() > 1) {
						if (cell.getNumericCellValue() == 1) {
							artikelliste[i] = new Artikel((i+2) * 111);
						}
					i++;
					}
						
					einkauf.put(kundenNr, artikelliste);
				}
				}
			}
		}
	}
	
	

	@Override
	public String toString() {
		return "Empfehlung [Key=" + einkauf + ", artikelliste=" + Arrays.toString(artikelliste) + "]";
	}



	public void berechneEmpfehlung() {

	}
	
	public static void main(String[] args) throws Exception {
		Empfehlung test = new Empfehlung();
		System.out.println(test.toString());
	}
	
}
