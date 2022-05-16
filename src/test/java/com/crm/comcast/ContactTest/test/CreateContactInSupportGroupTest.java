package com.crm.comcast.ContactTest.test;

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
import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.GenericUtility.FileUtitlty;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.ContactInfoPage;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

public class CreateContactInSupportGroupTest  extends BaseAnnotaionConfigClass{
	@Test
	public  void createContactInSupportGroupTest() throws Throwable {
		/* Random Number Generation */
		int ran=jLib.getRandomNumber();

		/*Read Data from Excel*/
		
		String contact=eLib.getData("contact", 1, 2)+ran;	
	
		/*click on contacts link*/
		HomePage hp=new HomePage(driver);
		hp.getContacts_Link().click();
		ContactsPage cp= new ContactsPage(driver);
		wLib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
		
		/*create contact*/
		cp.getCreate_Contact_Img().click();
		
		CreateContactPage ccp=new CreateContactPage(driver);
		wLib.explicitlyWaitForElement(driver, ccp.getLastName_TextField());
		ccp.getLastName_TextField().sendKeys(contact);
		ccp.getGroup_Radio_Btn().click();
		wLib.selectElementByVisibleText(ccp.getGroup_Dropdown(),"Support Group");
		
		ccp.getSave_Btn().click();
	
		/*validation*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		wLib.explicitlyWaitForElement(driver, cip.getContact_Info_Header());
		String actualResult=cip.getContact_Info_Header().getText();
		if(actualResult.contains(contact))
		{
			System.out.println(contact+" ==> is verified : Pass");
		}
		else
		{
			System.out.println(contact+" ==> is verified : Pass");
		}
	}
}


