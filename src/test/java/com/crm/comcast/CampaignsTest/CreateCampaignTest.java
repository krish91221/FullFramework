package com.crm.comcast.CampaignsTest;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.GenericUtility.FileUtitlty;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.CampaignPage;
import com.crm.comcast.Pom_Repo.Campaign_Info_Page;
import com.crm.comcast.Pom_Repo.CreateCampaignPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

public class CreateCampaignTest {
	public static void main(String[] args) throws Throwable {
		WebDriver driver=null;
		/*Random number generation*/
		JavaUtility jutil=new JavaUtility();
		int randomNum=jutil.getRandomNumber();

		/*Read Credentials from Properties file*/
		FileUtitlty futil=new FileUtitlty();
		String BROWSER=futil.getKeyValue("browser");

		/*Read Test Data from Excel file */
		ExcelUtitlity eutil=new ExcelUtitlity();
		String org=eutil.getData("org", 1, 2)+randomNum;
		String OpportunityName=eutil.getData("opportunities",1, 2)+randomNum;
		String campaign=eutil.getData("campaign", 5, 2)+randomNum;
		String product=eutil.getData("products", 1, 2)+randomNum;

		try {
		/*Launch Browser*/
			WebDriverUtility wutil=new WebDriverUtility();
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();	
		}
		else if(BROWSER.equals("firefox"))
		{
			driver= new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wutil.implicitWait(driver);
		/*Launch the Vtiger application*/
		/*Login to application*/
		LogInPage lp=new LogInPage(driver);
		lp.logInToApp();
		
		String loginPage=driver.getTitle();

		/*Navigate to  Campaigns Page*/
		HomePage hp=new HomePage(driver);
		hp.gotocampaignsPage();
		
		CampaignPage cp=new CampaignPage(driver);
		cp.getCreate_Campaign_Icon().click();
		
		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.createCampaign(campaign);
		
		Campaign_Info_Page cip=new Campaign_Info_Page(driver);
		wutil.explicitlyWaitForElement(driver, cip.getCampaign_Info_Header());
		String campname=cip.getCampaign_Info_Header().getText();
		if(campname.contains(campaign))
		{
			System.out.println(campaign+" is created and verification -->pass");
		}else
		{
		System.out.println(campaign+" is created and verification -->pass");
		}
		
		//Logout
				hp.signout();
		}
		
				finally
				{
				/*Close the application*/
				driver.close();
				}
	}
}