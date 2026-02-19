package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	 private static Workbook workbook;
	    private DataFormatter formatter = new DataFormatter();

	    public ExcelReader(String filename) {
	        try {
	            InputStream fileResourceInputStream = getClass().getResourceAsStream(filename);
	            workbook = WorkbookFactory.create(fileResourceInputStream);
	        } catch (Exception e) {
	            LoggerFactory.getLogger().error("Error while reading data {} file - {}", filename, e.getMessage());
	        }
	    }

		public Map<String, String> getDataByScenarioName(String sheetName, String scenarioName) {
			Sheet sheet = workbook.getSheet(sheetName);
	        Row headerRow = sheet.getRow(0);
	        int scenarioColumnIndex = -1;

			for (Cell cell : headerRow) {
				if ("ScenarioName".equalsIgnoreCase(formatter.formatCellValue(cell))) {
					scenarioColumnIndex = cell.getColumnIndex();
					break;
				}
			}
			if (scenarioColumnIndex == -1) {
				throw new IllegalStateException("ScenarioName column not found");
			}

			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				if (row == null)
					continue;
				String scenarioCellValue = formatter.formatCellValue(row.getCell(scenarioColumnIndex)).trim();
				if (scenarioCellValue.equalsIgnoreCase(scenarioName)) {
					return extractRowData(headerRow, row);
				}
			}
			throw new IllegalArgumentException("Scenario not found in Excel: " + scenarioName);
		}
		public static String[] getDataByScenarioName(String scenarioName) throws IOException {
	        Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
			    if (row.getRowNum() == 0) continue; // skip header
			    Cell scenarioCell = row.getCell(0);
			    if (scenarioCell.getStringCellValue().equalsIgnoreCase(scenarioName)) {
			        String[] data = new String[5]; // Email, Password, ConfirmPassword, AcceptTerms, ExpectedError
			        for (int i = 1; i <= 5; i++) {
			            Cell cell = row.getCell(i);
			            data[i - 1] = (cell != null) ? cell.getStringCellValue() : null;
			        }
			        return data;
			    }
			}
			throw new IllegalArgumentException("Scenario not found in Excel: " + scenarioName);
	    }
	    private Map<String, String> extractRowData(Row headerRow, Row dataRow) {
	        Map<String, String> data = new HashMap<>();
	        for (int i = 0; i < headerRow.getLastCellNum(); i++) {
	            String header = formatter.formatCellValue(headerRow.getCell(i)).trim();
	            String value = formatter.formatCellValue(dataRow.getCell(i)).trim();
	            if ("empty".equalsIgnoreCase(value)) {
	                value = "";
	            }
	            data.put(header, value);
	        }
	        return data;
	    }

}