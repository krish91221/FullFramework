package com.crm.comcast.CampaignsTest.Test;

import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.Pom_Repo.CampaignPage;
import com.crm.comcast.Pom_Repo.Campaign_Info_Page;
import com.crm.comcast.Pom_Repo.CreateCampaignPage;
import com.crm.comcast.Pom_Repo.HomePage;

public class CreateCampaignTest extends BaseAnnotaionConfigClass {
	@Test
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
		if(campname.contains(campaign))
		{
			System.out.println(campaign+" is created and verification -->pass");
		}
		else
		{
			System.out.println(campaign+" is not created and verification -->fail");
		}
	}
}