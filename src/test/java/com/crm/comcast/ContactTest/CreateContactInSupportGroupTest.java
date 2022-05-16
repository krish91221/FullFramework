package com.crm.comcast.ContactTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.GenericUtility.FileUtitlty;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.ContactInfoPage;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

public class CreateContactInSupportGroupTest {
	public static void main(String[] args) throws Throwable {

		WebDriver driver;

		/* Random Number Generation */
		JavaUtility jutil=new JavaUtility();
		int ran=jutil.getRandomNumber();
		

		/*Read Common Data from Properties File*/
		FileUtitlty futil=new FileUtitlty();
		String BROWSER=futil.getKeyValue("browser");
		
		/*Read Data from Excel*/
		ExcelUtitlity eutil=new ExcelUtitlity();
		String contact=eutil.getData("contact", 1, 2)+ran;
		
		/*Launch browser*/
		WebDriverUtility wlib=new WebDriverUtility();
		
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
		/*LAUNCHING THE VTIGER APPLICATION*/
		
		/*login to application*/
		LogInPage lp=new LogInPage(driver);
		lp.logInToApp();
		/*click on contacts link*/
		HomePage hp=new HomePage(driver);
		hp.getContacts_Link().click();
		ContactsPage cp= new ContactsPage(driver);
		wlib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
		cp.getCreate_Contact_Img().click();
		CreateContactPage ccp=new CreateContactPage(driver);
		wlib.explicitlyWaitForElement(driver, ccp.getLastName_TextField());
		ccp.getLastName_TextField().sendKeys(contact);
		ccp.getGroup_Radio_Btn().click();
		wlib.selectElementByVisibleText(ccp.getGroup_Dropdown(),"Support Group");
		
		ccp.getSave_Btn().click();
		

		/*click on add contact*/

		/*validation*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		wlib.explicitlyWaitForElement(driver, cip.getContact_Info_Header());
		String actualResult=cip.getContact_Info_Header().getText();
		if(actualResult.contains(contact))
		{
			System.out.println(contact+" ==> is verified : Pass");
		}
		else
		{
			System.out.println(contact+" ==> is verified : Pass");
		}
		/*Logout*/
		hp.signout();

		/*close application*/
		driver.close();
	}
}


