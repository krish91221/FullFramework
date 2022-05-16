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
import com.crm.comcast.Pom_Repo.CreateProductPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;
import com.crm.comcast.Pom_Repo.ProductPage;

public class CreateCapaignWithProduct {
	private static WebElement campaign_Name;
	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;
		WebDriverUtility wlib = new WebDriverUtility();
		/* Random number generation */
		JavaUtility jutil = new JavaUtility();
		int ran = jutil.getRandomNumber();

		/* Read Credentials from Properties file */
		FileUtitlty futil = new FileUtitlty();
		
		String BROWSER = futil.getKeyValue("browser");
		
		/* Read Test Data from Excel file */
		ExcelUtitlity eutil = new ExcelUtitlity();
		String product = eutil.getData("products", 1, 2) + ran;
		String org = eutil.getData("org", 1, 2) + ran;
		String OpportunityName = eutil.getData("opportunities", 1, 2) + ran;
		String campaign = eutil.getData("campaign", 5, 2) + ran;
		try {
			
			/* Launch Browser*/
			 if(BROWSER.equals("firefox")) 
			 {
					driver = new FirefoxDriver();
			} 
			else if(BROWSER.equals("chrome"))
			{ 
				driver=new ChromeDriver();
				driver.manage().window().maximize();
			} 
			else
			{ 
				driver=new ChromeDriver(); 
				driver.manage().window().maximize();
			}
			wlib.implicitWait(driver);
			/* Launch the Vtiger application */
			/* Login to application */
			LogInPage lp=new LogInPage(driver);
			lp.logInToApp();
			String loginPage = driver.getTitle();
			
			HomePage hp=new HomePage(driver);
			wlib.explicitlyWaitForElement(driver, hp.getProducts_Link());
			hp.getProducts_Link().click();
			
			/* Create product */
			ProductPage pp=new ProductPage(driver);
			wlib.explicitlyWaitForElement(driver, pp.getCreate_Product_Icon());
			pp.getCreate_Product_Icon().click();
			
			CreateProductPage cpp=new CreateProductPage(driver);
			wlib.explicitlyWaitForElement(driver, cpp.getProductName_TextField());
			cpp.getProductName_TextField().sendKeys(product);
			wlib.explicitlyWaitForElement(driver, cpp.getSave_Btn());
			cpp.getSave_Btn().click();
			wlib.explicitlyWaitForElement(driver, hp.getMore());
			hp.gotocampaignsPage();
			
				CampaignPage cp=new CampaignPage(driver);
				wlib.explicitlyWaitForElement(driver, cp.getCreate_Campaign_Icon());
				cp.getCreate_Campaign_Icon().click();
				CreateCampaignPage ccp=new CreateCampaignPage(driver);
				wlib.explicitlyWaitForElement(driver, ccp.getCampaign_Name_TextFiel());
				ccp.getCampaign_Name_TextFiel().sendKeys(campaign);
				wlib.explicitlyWaitForElement(driver, ccp.getProduct_LookUp_Img());
				ccp.getProduct_LookUp_Img().click();
				wlib.SwitchToWindowWithUrl(driver,pp.getProduct_lookup_Url());	
				wlib.explicitlyWaitForElement(driver, pp.getProduct_Lookup_search_TextField());
				pp.getProduct_Lookup_search_TextField().sendKeys(product);
				pp.getProduct_Lookup_Search_Btn().click();
				driver.findElement(By.xpath("//a[.='"+product+"']")).click();
				wlib.SwitchToWindowWithUrl(driver, cp.getCampaign_page_url());
		System.out.println(ccp.getProduct_From_LookUp().getText()+"\t"+product);
			if(ccp.getProduct_From_LookUp().getText().equals(product))
			{
				System.out.println(product+" is selected from product look up to create campaign -- verification-->pass");
			}
			else
			{
				System.out.println(product+" is not selected from product look up to create campaign -- verification-->fail");
			}
			
			

			/* Navigate to Campaigns Page */
			
			wlib.explicitlyWaitForElement(driver, ccp.getSave_Btn());
			ccp.getSave_Btn().click();
			Campaign_Info_Page cip=new Campaign_Info_Page(driver);
			wlib.explicitlyWaitForElement(driver, cip.getCampaign_Info_Header());
			String cnfMsg = cip.getCampaign_Info_Header().getText();
			if (cnfMsg.contains(campaign)) {
				System.out.println(campaign + " is created and verification --> pass");
			}
			else
			{
				System.out.println(campaign + " is created and verification --> pass");
			}

			// Logout
			hp.signout();
			String actualResult2 = driver.getTitle();
			if (loginPage.contains(actualResult2)) {
				System.out.println("Login Page is Displayed Verified");
			}
		} finally {
			/* Close the application */
			driver.close();
		}
	}

}
