package com.crm.comcast.ContactTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
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
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;
import com.mysql.cj.jdbc.Driver;

public class CreateContactandDeletetheSameContactTest {
	public static void main(String[] args) throws Throwable {

		WebDriver driver;

		/* Random Number Generation */
		JavaUtility jutil=new JavaUtility();
		int randomNum=jutil.getRandomNumber();

		/*Read Common Data from Properties File*/
		FileUtitlty futil=new FileUtitlty();
		
		String BROWSER=futil.getKeyValue("browser");
		

		/*Read Data from Excel*/
		ExcelUtitlity eutil=new ExcelUtitlity();
		String contact=eutil.getData("contact", 1, 2)+randomNum;

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
		/*LAUNCHING THE VTIGER APPLICATION*/
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.implicitWait(driver);
		/*login to application*/
		LogInPage lp=new LogInPage(driver);
		lp.logInToApp();
		/*click on contacts link*/
		HomePage hp=new HomePage(driver);
		hp.getContacts_Link().click();
		/*click on add contact*/
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContact(contact);
			
		/*validation*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		wutil.explicitlyWaitForElement(driver, cip.getContact_Info_Header());
		String actualResult=cip.getContact_Info_Header().getText();
		if(actualResult.equals(contact))
		{
			System.out.println(contact+" ==> is verification : Pass");
		}
		else
		{
			System.out.println(contact+" ==> is verification : fail");
		}
		
		/*click on contacts link*/
		
		hp.getContacts_Link().click();
		
		WebElement createdContact = driver.findElement(By.xpath("//a[.='"+contact+"']"));
		wutil.explicitlyWaitForElement(driver, createdContact);
		String condet=createdContact.getText();
		/*Validation of created contact*/
		if(condet.equals(contact))
		{
			System.out.println(contact+" is created successfully and displayed as "+condet);
			/*Select the created contact and delete the contact*/
			WebElement selectedcontact= driver.findElement(By.xpath("//a[.='"+contact+"']/../../td[1]"));
			wutil.explicitlyWaitForElement(driver, selectedcontact);
			selectedcontact.click();
			wutil.explicitlyWaitForElement(driver, cip.getDelete_Btn());
			cip.getDelete_Btn().click();
			wutil.acceptAlert(driver);
			System.out.println("Deleted the contact "+contact);
			/*Validating deletion*/
			cip.getSearch_TextField().sendKeys(contact);
			WebElement dd = cip.getSearch_By_DropDown();
			wutil.selectElementByVisibleText(dd, "Last Name");
			cip.getSearch_Btn().click();
			String message=cip.getWarning_Msg().getText();
			
			System.out.println(message);
			if(message.contains("No Contact Found !"))
			{
			
				System.out.println(contact+" Contact is deleted successfully");
			}
			else
			{
				System.out.println(contact+" Deletion is not successful");
			}
		}
		else
		{
			System.out.println(contact+" is not created "+condet);
		}
		/*Logout*/
		hp.signout();
		/*close application*/
		driver.quit();
	}

}
