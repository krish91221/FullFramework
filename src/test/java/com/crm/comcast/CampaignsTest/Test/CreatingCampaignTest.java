package com.crm.comcast.CampaignsTest.Test;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.Pom_Repo.CampaignPage;
import com.crm.comcast.Pom_Repo.Campaign_Info_Page;
import com.crm.comcast.Pom_Repo.CreateCampaignPage;
import com.crm.comcast.Pom_Repo.CreateProductPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.ProductPage;
@Listeners(com.crm.comcast.GenericUtility.IlisternsImplimentationClass.class)
public class CreatingCampaignTest extends BaseAnnotaionConfigClass {

	@Test(groups="smoketesting")//,retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
	public  void createCampainTest() throws Throwable {

		/*Random number generation*/
		int randomNum=jLib.getRandomNumber();

		/*Read Test Data from Excel file */
		String org=eLib.getData("org", 1, 2)+randomNum;
		String OpportunityName=eLib.getData("opportunities",1, 2)+randomNum;
		String campaign=eLib.getData("campaign", 5, 2)+randomNum;
		String product=eLib.getData("products", 1, 2)+randomNum;

		/*Navigate to  Campaigns Page*/
		HomePage hp=new HomePage(driver);
		hp.gotocampaignsPage();

		CampaignPage cp=new CampaignPage(driver);
		cp.getCreate_Campaign_Icon().click();

		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		ccp.createCampaign(campaign);

		Campaign_Info_Page cip=new Campaign_Info_Page(driver);
		wLib.explicitlyWaitForElement(driver, cip.getCampaign_Info_Header());
		String campname=cip.getCampaign_Info_Header().getText();
		
		Assert.assertEquals(campname.trim(),campaign.trim()," is not created and verification -->fail");
		System.out.println(campaign+" is created and verified==Pass");
	}

	@Test(groups= {"integrationTesting","regressionTesting"},retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
	public  void createCampainWithProductTest() throws Throwable {

		/* Random number generation */
		int ran = jLib.getRandomNumber();

		/* Read Test Data from Excel file */
		ExcelUtitlity eutil = new ExcelUtitlity();
		String product = eutil.getData("products", 1, 2) + ran;
		String org = eutil.getData("org", 1, 2) + ran;
		String OpportunityName = eutil.getData("opportunities", 1, 2) + ran;
		String campaign = eutil.getData("campaign", 5, 2) + ran;

		HomePage hp=new HomePage(driver);
		wLib.explicitlyWaitForElement(driver, hp.getProducts_Link());
		hp.getProducts_Link().click();

		/* Create product */
		ProductPage pp=new ProductPage(driver);
		wLib.explicitlyWaitForElement(driver, pp.getCreate_Product_Icon());
		pp.getCreate_Product_Icon().click();

		CreateProductPage cpp=new CreateProductPage(driver);
		wLib.explicitlyWaitForElement(driver, cpp.getProductName_TextField());
		cpp.getProductName_TextField().sendKeys(product);
		wLib.explicitlyWaitForElement(driver, cpp.getSave_Btn());
		cpp.getSave_Btn().click();
		wLib.explicitlyWaitForElement(driver, hp.getMore());
		hp.gotocampaignsPage();

		CampaignPage cp=new CampaignPage(driver);
		wLib.explicitlyWaitForElement(driver, cp.getCreate_Campaign_Icon());
		cp.getCreate_Campaign_Icon().click();
		CreateCampaignPage ccp=new CreateCampaignPage(driver);
		wLib.explicitlyWaitForElement(driver, ccp.getCampaign_Name_TextFiel());
		ccp.getCampaign_Name_TextFiel().sendKeys(campaign);
		wLib.explicitlyWaitForElement(driver, ccp.getProduct_LookUp_Img());
		ccp.getProduct_LookUp_Img().click();
		wLib.SwitchToWindowWithUrl(driver,pp.getProduct_lookup_Url());	
		wLib.explicitlyWaitForElement(driver, pp.getProduct_Lookup_search_TextField());
		pp.getProduct_Lookup_search_TextField().sendKeys(product);
		pp.getProduct_Lookup_Search_Btn().click();
		driver.findElement(By.xpath("//a[.='"+product+"']")).click();
		wLib.SwitchToWindowWithUrl(driver, cp.getCampaign_page_url());
		String selectedProduct=ccp.getProduct_From_LookUp().getAttribute("value");
		System.out.println(ccp.getProduct_From_LookUp().getAttribute("value")+"\t"+product);
		AssertJUnit.assertEquals(selectedProduct,product,"Selected product is not verified ++.FAIL");
			System.out.println(product+" is selected from product look up to create campaign -- verification-->pass");
	
		/* Navigate to Campaigns Page */

		wLib.explicitlyWaitForElement(driver, ccp.getSave_Btn());
		ccp.getSave_Btn().click();
		Campaign_Info_Page cip=new Campaign_Info_Page(driver);
		wLib.explicitlyWaitForElement(driver, cip.getCampaign_Info_Header());
		String cnfMsg = cip.getCampaign_Info_Header().getText();
		boolean flag=cnfMsg.contains(campaign); 
		Assert.assertTrue(flag, "Campaign is not verified ==>FAIL");
		System.out.println(campaign + " is created and verification --> PASS");
	}
}
