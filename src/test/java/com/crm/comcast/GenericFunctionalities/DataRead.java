package com.crm.comcast.GenericFunctionalities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.WorkbookUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DataRead {
	public String UrL;
	public String UserName;
	public  String Password;
	public String Browser;
	public WebDriver driver;
	public String Organisation;
	public String Contact;
	int random=(int)Math.random()*10000;
	//generic method to get properties file 
	public  void getFile() throws IOException
	{
		//to read common data from properties file
		FileInputStream fis=new FileInputStream("./data/commondata.properties");
		Properties props=new Properties();
		props.load(fis);
		UrL=props.getProperty("url");
		UserName=props.getProperty("uname");
		Password=props.getProperty("pass");
		Browser=props.getProperty("browser");
		if(Browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Browser.equals("ie"))
		{
			driver=new InternetExplorerDriver();
		}
	}
	public void getTestDataforOrg() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./data/testdata.xlsx");
		Workbook book=WorkbookFactory.create(fis);
		Sheet sh=book.getSheet("Sheet1");
		Row r=sh.getRow(1);
		Cell c=r.getCell(0);
		Organisation=c.getStringCellValue()+random;
	}
	public void getTestDataforcontact() throws EncryptedDocumentException, IOException
	{
		FileInputStream fis=new FileInputStream("./data/testdata.xlsx");
		Workbook book=WorkbookFactory.create(fis);
		Sheet sh=book.getSheet("Sheet1");
		Row r=sh.getRow(4);
		Cell c=r.getCell(0);
		Contact=c.getStringCellValue()+random;
	}
}
