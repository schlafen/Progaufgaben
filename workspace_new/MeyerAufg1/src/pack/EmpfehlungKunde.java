package pack;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmpfehlungKunde {

	private Kunde[] kundenliste = new Kunde[7];

	public EmpfehlungKunde() throws FileNotFoundException, IOException {
		try (Workbook wb = new XSSFWorkbook(new FileInputStream(".//src//pack//Daten.xlsx"))) {
			int i = 0;
			Sheet sheet = wb.getSheetAt(1);
			for (Row row : sheet) {

				
				int kundenNr = 0;
				String kundenName = "";
				String land = "";
				int alter = 0;

				for (Cell cell : row) {
					//System.out.println(cell);

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
				
				kundenliste[i] = new Kunde(kundenNr, kundenName, land, alter);
				i++;
			}
		}
	}
}
