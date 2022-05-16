package com.crm.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericFunctionalities.DataRead;

public class TC_49TestScript {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver;
		String VendorName = null;
		/* Random Number Generation */
		Random r=new Random();
		int ran= r.nextInt(9999);

		/*Read Common Data from Properties File*/
		FileInputStream pfile=new FileInputStream("./data/commondata.properties");

		Properties p=new Properties();
		p.load(pfile);
		String URL=p.getProperty("url");
		String BROWSER=p.getProperty("browser");
		String USERNAME=p.getProperty("uname");
		String PASSWORD=p.getProperty("pass");

		/*Read Data from Excel*/
		FileInputStream efile=new FileInputStream("./data/testdata.xlsx");

		Workbook book=WorkbookFactory.create(efile);
		Sheet sh = book.getSheet("purchaseorder");
		String expectedResult=sh.getRow(7).getCell(3).getStringCellValue();
		String expectedResult2=sh.getRow(8).getCell(3).getStringCellValue();
		for(int i=0;i<=sh.getLastRowNum();i++)
		{
			if(sh.getRow(i).equals("Vendor Name"))
			{
				VendorName=sh.getRow(i).getCell(1).getStringCellValue();
				break;
			}
		}
			
		book.close();

		/*Launch browser*/
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver(); 
		}
		else if(BROWSER.equals("safari"))
		{
			driver=new SafariDriver(); 
		}
		else if(BROWSER.equals("ie"))
		{
			driver=new InternetExplorerDriver(); 
		}
		else
		{
			driver=new ChromeDriver();
		}
		try
		{
		/*LAUNCHING THE VTIGER APPLICATION*/
		driver.get(URL);

		/*login to application*/
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		WebDriverWait wait=new WebDriverWait(driver,10);
	
		
		/*create vendor*/
		driver.findElement(By.xpath("//img[@title='Create Vendor...']"));
		driver.findElement(By.xpath("//input[@name='vendorname']")).sendKeys(VendorName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//open purchase order
		WebElement more = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		Actions act=new Actions(driver);
		act.moveToElement(more).perform();
	   WebElement purchase = driver.findElement(By.xpath("//a[@name='Purchase Order']"));
		wait.until(ExpectedConditions.visibilityOf(purchase));
		purchase.click();
		Thread.sleep(2000);
		
		/*Create Purchase Order*/
		
		
		WebElement porderrec= driver.findElement(By.xpath("//a[.='Purchase order for IAF']"));
		wait.until(ExpectedConditions.visibilityOf(porderrec));
		porderrec.click();
		driver.findElement(By.xpath("//input[@title='Delete [Alt+D]']")).click();
		Alert alert=driver.switchTo().alert();
		String actualResult=alert.getText();
		if(expectedResult.contains(actualResult))
		{
			System.out.println("Alert and confirmation popup is Verified");
		}
		/*Alert is handled and delete is cancelled*/
		alert.dismiss();
		
		//Logout
		WebElement acc=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wait.until(ExpectedConditions.visibilityOf(acc));
		act.moveToElement(acc).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
		String actualResult2=driver.getTitle();
		if(expectedResult2.contains(actualResult2))
		{
			System.out.println("Login Page is Displayed Verified");
		}
		}
		finally
		{
		/*Close the application*/
		driver.close();
		}
	}
}
