package com.crm.comcast.OrgTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

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
import com.crm.comcast.Pom_Repo.CreateOrganizationsPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;
import com.crm.comcast.Pom_Repo.OrganizationInfoPage;
import com.crm.comcast.Pom_Repo.OrganizationsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganisationScript {
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
String orgName=	elib.getData("org",1, 2)+randomNumber;

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
	LogInPage lp=new LogInPage(driver);
	lp.logInToApp();
	/*click on organisations link*/
	HomePage hp=new HomePage(driver);
	WebDriverUtility wlib=new WebDriverUtility();
	
	/*click on add organisation*/
	wlib.explicitlyWaitForElement(driver, hp.getOrganizatoins_Link());
	hp.getOrganizatoins_Link().click();
	
	OrganizationsPage orgPage=new OrganizationsPage(driver);
	
	wlib.explicitlyWaitForElement(driver, orgPage.getCreate_Organiszation_Img());
	orgPage.getCreate_Organiszation_Img().click();
	
	CreateOrganizationsPage cop=new CreateOrganizationsPage(driver);
	cop.createOrganisation(orgName );

	//validate the organisation name created
	OrganizationInfoPage oip=new OrganizationInfoPage(driver);
	wlib.explicitlyWaitForElement(driver, oip.getOrganization_Header_Info());
	
	String actualorg = oip.getOrganization_Header_Info().getText();
	if(actualorg.contains(orgName))
	{
		System.out.println("Organisation is created successfully pass");
	}
	else
	{
		System.out.println("Organisation is not created as required fail");
	}
	//Logout
	hp.signout();
	//close the application
	driver.close();
}
}
