package com.crm.practice;

import java.awt.AWTException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericFunctionalities.DataRead;
import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.ContactInfoPage;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactScript {
	@FindBy(xpath=("//img[@src='themes/softed/images/user.PNG']"))
	static WebElement acclogo;
	public static void main(String[] args) throws Throwable {
		
		WebDriver driver =new FirefoxDriver();
		WebDriverUtility wlib=new WebDriverUtility();
		//LAUNCHING THE VTIGER APPLICATION		
		//login to application
		LogInPage lp=new LogInPage(driver);
		lp.logInToApp();
		
		//click on contacts link
		HomePage hp=new HomePage(driver);
		wlib.explicitlyWaitForElement(driver, hp.getContacts_Link());
		hp.getContacts_Link().click();
		
		ContactsPage cp=new ContactsPage(driver);
		wlib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
		cp.getCreate_Contact_Img().click();
		
		//click on add contact
		ExcelUtitlity elib=new ExcelUtitlity();
		JavaUtility jlib=new JavaUtility();
		int randomNum=jlib.getRandomNumber();
		String lastName=elib.getData("contact", 3, 2)+randomNum;
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContact(lastName);
		ContactInfoPage cip=new ContactInfoPage(driver);
		wlib.explicitlyWaitForElement(driver, cip.getContact_Info_Header());
		String actualResult=cip.getContact_Info_Header().getText();
		if(actualResult.contains(lastName))
		{
			System.out.println(lastName+" contact is created and verified --> pass");
		}
		else
		{
			System.out.println(lastName+" contact is not created and verified --> fail");
		}
		
		//Logout
		hp.signout();
		
		//close application
		driver.close();
	}

}
