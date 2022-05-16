package com.crm.comcast.ContactTest.test;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DelectContactTest extends BaseAnnotaionConfigClass{
	@Test
	public  void deleteContactTest() throws Throwable {
		/* Random Number Generation */
		int randomNum=jLib.getRandomNumber();
		
		/*Read Data from Excel*/
		ExcelUtitlity eutil=new ExcelUtitlity();
		String contact=eutil.getData("contact", 1, 2)+randomNum;

		HomePage hp=new HomePage(driver);
		wLib.explicitlyWaitForElement(driver, hp.getContacts_Link());
		hp.getContacts_Link().click();
		
		//delete all contacts
		ContactsPage cp=new ContactsPage(driver);
		wLib.explicitlyWaitForElement(driver, cp.getSelectAll_ChkBox());
		cp.getSelectAll_ChkBox().click();
		cp.getDelete_Btn().click();
		
		wLib.acceptAlert(driver);		
	}
}
