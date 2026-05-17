package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	XSSFWorkbook workbook;

	XSSFSheet sheet;

	public ExcelUtils(String excelPath, String sheetName) {

		try {

			FileInputStream file = new FileInputStream(excelPath);

			workbook = new XSSFWorkbook(file);

			sheet = workbook.getSheet(sheetName);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public int getRowCount() {

		return sheet.getPhysicalNumberOfRows();
	}

	public String getCellData(int row, int col) {

		return sheet.getRow(row).getCell(col).getStringCellValue();
	}
}