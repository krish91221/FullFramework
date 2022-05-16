package com.crm.comcast.ContactTest;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericFunctionalities.DataRead;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DelectContactTest {
	public static void main(String[] args) throws Throwable {
		//setting to launch chrome browser
		DataRead d=new DataRead();
		d.getFile();
		//launching chrome browser
		WebDriver driver =d.driver;
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//LAUNCHING THE VTIGER APPLICATION
		driver.get(d.UrL);

		//login to application
		LogInPage lp=new LogInPage(driver);
		lp.logInToApp();
		WebDriverUtility wlib=new WebDriverUtility();
		HomePage hp=new HomePage(driver);
		wlib.explicitlyWaitForElement(driver, hp.getContacts_Link());
		hp.getContacts_Link().click();
		
		//delete all contacts
		ContactsPage cp=new ContactsPage(driver);
		wlib.explicitlyWaitForElement(driver, cp.getSelectAll_ChkBox());
		cp.getSelectAll_ChkBox().click();
		cp.getDelete_Btn().click();
		
		wlib.acceptAlert(driver);
		
		//Logout
		hp.signout();

		//close the application
		driver.close();
	}
}
