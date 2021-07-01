package com.excel_reader_utility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {
	
	public static int pageLoadTimeout=40; // Not necessary to initalize here 
	public static int implicitlyWait=30;
	
	static DataFormatter formatter = new DataFormatter();

	//Only sheet name should be updated in calling Class excelpath should be same as we are using same sheet frm excel
	
	public static Object[][] ExcelDataReader(String sheetName) throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\prate\\workspace\\DataDrivenUsingTestNG\\src\\main\\java\\com\\test_data\\Testdata.xlsx");
		//XSSFWorkbook wb1 = new XSSFWorkbook("C:\\Users\\prate\\workspace\\DataDrivenUsingTestNG\\src\\main\\java\\com\\test_data\\Testdata.xlsx"); 
		XSSFWorkbook wb1 = new XSSFWorkbook(fis);
		//XSSFSheet sheet = wb1.getSheet(sheetName);
		XSSFSheet sheet = wb1.getSheetAt(1);
		
		int rowCount = sheet.getPhysicalNumberOfRows(); // method for calculating row count in sheet
		//int colCount = sheet.getRow(0).getPhysicalNumberOfCells();//
		XSSFRow row = sheet.getRow(1);
		int colCount = row.getLastCellNum();
	
		Object[][] obj = new Object[rowCount-1][colCount];
		
		for (int i=0;i<rowCount-1;i++) // this will traverse all the rows in FreeCrmData
		{
			row  = sheet.getRow(i+1);
			for(int j=0;j<colCount;j++) // this will traverse the all cols in freeCrmData
			{
				
			XSSFCell cell = row.getCell(j);
			obj[i][j] = formatter.formatCellValue(cell);
			
			
				/*String cellData = sheet.getRow(row).getCell(col).getStringCellValue();
				obj[row-1][col]=cellData;*/
			}
		}
		 return obj;
	}
}
