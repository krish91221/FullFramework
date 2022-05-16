package com.crm.comcast.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtitlity {
	Workbook book=null;
	/**
	 * This method is used to read data from Excel based on below parameters
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return String
	 * @throws Throwable
	 */
	public String getData(String sheetName,int rowNum,int cellNum) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IConstants.EXCEL_FILE_PATH);
		book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		return sheet.getRow(rowNum).getCell(cellNum).getStringCellValue();
	}
	/**
	 * This method is used to get count of rows in a particular Sheet
	 * @param sheetName
	 * @return int
	 * @throws Throwable
	 */
	public int getRowCount(String sheetName) throws Throwable
	{
		
		FileInputStream fis=new FileInputStream(IConstants.EXCEL_FILE_PATH);
		book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		return sheet.getLastRowNum();
	}
	/**
	 * This method is used to get count of cells in a particular Sheet
	 * @param sheetName
	 * @return int
	 * @throws Throwable
	 */
	public int getCellCount(String sheetName) throws Throwable
	{
		
		FileInputStream fis=new FileInputStream(IConstants.EXCEL_FILE_PATH);
		book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		return sheet.getRow(0).getLastCellNum();
	}
	/**
	 * This method is used to write data to excel file
	 * @param sheetName
	 * @param row
	 * @param cell
	 * @param data
	 * @throws Throwable
	 */
	public void setDataToExcel(String sheetName,int row,int cell,String data) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IConstants.EXCEL_FILE_PATH);
		book=WorkbookFactory.create(fis);
		Sheet sheet=book.getSheet(sheetName);
		sheet.getRow(row).getCell(cell).setCellValue(data);
		FileOutputStream fos=new FileOutputStream(IConstants.EXCEL_FILE_PATH);
		book.write(fos);
	}
	/**
	 * This method is used to read data from excel file and provide data for DataProvider
	 * @param sheetName
	 * @return Object[][]
	 * @throws Throwable
	 * @throws Throwable
	 */
	public Object[][] getDataUsingDataProvider(String sheetName) throws Throwable, Throwable
	{
		FileInputStream fis=new FileInputStream(IConstants.EXCEL_FILE_PATH);
		Workbook book=WorkbookFactory.create(fis);
		 Sheet sheet = book.getSheet(sheetName);
		 int rowCount=sheet.getLastRowNum();
		 int cellCount=sheet.getRow(0).getLastCellNum();
		 Object[][] data=new Object[rowCount][cellCount];
		 for(int i=1;i<rowCount;i++) {
			 for(int j=0;j<cellCount;j++)
			 {
				 data[i][j]=sheet.getRow(i).getCell(j).getStringCellValue();
			 }
		 }
		return data;
	}
}
