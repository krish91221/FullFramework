package com.crm.comcast.ContactTest.test;

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
import org.testng.annotations.Test;

import com.crm.comcast.GenericFunctionalities.DataRead;
import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.GenericUtility.FileUtitlty;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

public class CreateContactWithoutGIvingMandatoryFieldTest extends BaseAnnotaionConfigClass {
	@Test
	public  void crateContactWithoutMandatoryFieldTest() throws Throwable {
		/* Random Number Generation */
		int randomNumber=jLib.getRandomNumber();

		/*Read Data from Excel*/
		String contact = eLib.getData("contact",1, 2)+randomNumber;
		
		//click on contacts link	
		
		HomePage hp=new HomePage(driver);
		wLib.explicitlyWaitForElement(driver, hp.getCalendar_Link());
		hp.getContacts_Link().click();
		
		ContactsPage cp=new ContactsPage(driver);
		//click on add contact
		wLib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
		cp.getCreate_Contact_Img().click();
		
		CreateContactPage ccp=new CreateContactPage(driver);
		wLib.explicitlyWaitForElement(driver, ccp.getLastName_TextField());
		ccp.getLastName_TextField().sendKeys("");
		ccp.getSave_Btn().click();
	
		wLib.acceptAlert(driver);
	}
}
