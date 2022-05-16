package com.crm.comcast.OrgTest.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.Pom_Repo.CreateOrganizationsPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.OrganizationInfoPage;
import com.crm.comcast.Pom_Repo.OrganizationsPage;

public class DataProviderPracticeOrganizationTest extends BaseAnnotaionConfigClass {
	
	@Test(dataProvider = "data")
	public void createOrganizationUsingDataProviderTest(String orgName,String industry)
	{
		HomePage hp=new HomePage(driver);
		wLib.explicitlyWaitForElement(driver, hp.getOrganizatoins_Link());
		hp.getOrganizatoins_Link().click();
		OrganizationsPage op=new OrganizationsPage(driver);
		op.getCreate_Organiszation_Img().click();
		CreateOrganizationsPage cop=new CreateOrganizationsPage(driver);
		cop.getOrganization_name_TextField().sendKeys(orgName+jLib.getRandomNumber());
		wLib.selectElementByVisibleText(cop.getIndustry_DropDown(), industry);
		cop.getSave_Btn().click();
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		wLib.explicitlyWaitForElement(driver, oip.getOrganization_Header_Info());
		String createdOrgName = oip.getOrganization_Header_Info().getText();
		if(createdOrgName.contains(orgName))
		{
			System.out.println(orgName+" organization is created in "+industry+" sector");
		}
		else
		{
			System.out.println(orgName+" organization is  not created in "+industry+" sector");
		}
	}
	
	@DataProvider(name="data")
	public Object[][] getData() throws Throwable
	{
		
		return eLib.getDataUsingDataProvider("organizations");
		
	}
}
