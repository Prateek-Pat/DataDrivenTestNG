package com.excel_reader_utility;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static int pageLoadTimeout=30; // Not necessary to initalize here 
	public static int implicitlyWait=30;

	//Only sheet name should be updated in calling Class excelpath should be same as we are using same sheet frm excel
	
	public static Object[][] ExcelDataReader(String sheetName) throws IOException
	{
		XSSFWorkbook wb = new XSSFWorkbook("C:\\Users\\prate\\workspace\\DataDrivenUsingTestNG\\src\\main\\java\\com\\test_data\\Testdata.xlsx"); 
		XSSFSheet sheet = wb.getSheet(sheetName);
		
		int rowCount = sheet.getPhysicalNumberOfRows(); // method for calculating row count in sheet
		int colCount = sheet.getRow(0).getPhysicalNumberOfCells();//
	
		Object[][] obj = new Object[rowCount-1][colCount];
		
		for (int row=1;row<rowCount;row++) // this will traverse all the rows in FreeCrmData
		{
			for(int col=0;col<colCount;col++) // this will traverse the all cols in freeCrmData
			{
				String cellData = sheet.getRow(row).getCell(col).getStringCellValue();
				obj[row-1][col]=cellData;
			}
		}
		 return obj;
	}
}
