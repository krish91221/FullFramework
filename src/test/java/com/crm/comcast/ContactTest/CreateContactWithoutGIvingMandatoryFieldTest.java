package com.crm.comcast.ContactTest;

import java.awt.AWTException;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.GenericUtility.FileUtitlty;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

public class CreateContactWithoutGIvingMandatoryFieldTest {
	public static void main(String[] args) throws Throwable {

		WebDriver driver;

		/* Random Number Generation */
		JavaUtility jlib=new JavaUtility();
		int randomNumber=jlib.getRandomNumber();

		/*Read Common Data from Properties File*/
		FileUtitlty flib=new FileUtitlty();
		String browser=flib.getKeyValue("browser");
		/*Read Data from Excel*/
		ExcelUtitlity elib=new ExcelUtitlity();
		elib.getData("contact",1, 2);

		/*Launch browser*/
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver(); 
		}
		else if(browser.equals("safari"))
		{
			driver=new SafariDriver(); 
		}
		else if(browser.equals("ie"))
		{
			driver=new InternetExplorerDriver(); 
		}
		else
		{
			driver=new ChromeDriver();
		}
		/*LAUNCHING THE VTIGER APPLICATION*/
		

		/*login to application*/
		LogInPage lp=new LogInPage(driver);
		lp.logInToApp();

		
		//click on contacts link	
		WebDriverUtility wlib=new WebDriverUtility();
		HomePage hp=new HomePage(driver);
		wlib.explicitlyWaitForElement(driver, hp.getCalendar_Link());
		hp.getContacts_Link().click();
		
		ContactsPage cp=new ContactsPage(driver);
		wlib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
		cp.getCreate_Contact_Img().click();
		
		CreateContactPage ccp=new CreateContactPage(driver);
		wlib.explicitlyWaitForElement(driver, ccp.getLastName_TextField());
		ccp.getLastName_TextField().sendKeys("");
		ccp.getSave_Btn().click();
		
		//click on add contact
	
		wlib.acceptAlert(driver);
		
		//Logout
		hp.signout();


		//close application
		driver.close();
	}


}
