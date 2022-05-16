package com.crm.comcast.OrgTest.Test;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.Pom_Repo.CreateOrganizationsPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.OrganizationInfoPage;
import com.crm.comcast.Pom_Repo.OrganizationsPage;
@Listeners(com.crm.comcast.GenericUtility.IlisternsImplimentationClass.class)
public class CreatingOrganizationTest extends BaseAnnotaionConfigClass {

	@Test(groups="smokeTesting")//,retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
	public  void createOrganizationTest()
			throws Throwable {
		/* Random Number Generation */
		int randomNumber= jLib.getRandomNumber();

		/*Read Data from Excel*/
		String org=eLib.getData("org", 1, 2)+randomNumber;
		
		/*Click on organizations link*/
		HomePage hp=new HomePage(driver);
		hp.getOrganizatoins_Link().click();

		//click on add organization
		CreateOrganizationsPage cop=new CreateOrganizationsPage(driver);
		cop.getOrganization_name_TextField().sendKeys(org);
		cop.getSave_Btn().click();

		//validate the organization name created
		OrganizationInfoPage oip=new OrganizationInfoPage(driver);
		
		String actualorg = oip.getOrganization_Header_Info().getText();
		if(actualorg.contains(org))
		{
			System.out.println(org+" ==> Organisation is verified : pass");
		}
		else
		{
			System.out.println("Organisation is not verified : fail");
		}
	}
	
	@Test(dataProvider = "data",groups="regressionTesting")//,retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
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
