package com.crm.comcast.OrgTest.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.Pom_Repo.CreateOrganizationsPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.OrganizationInfoPage;

public class CreateOrganisationTest extends BaseAnnotaionConfigClass {
	@Test
	public  void createOrganizationTest()
			throws Throwable {
		/* Random Number Generation */
		int randomNumber= jLib.getRandomNumber();

		/*Read Data from Excel*/
		String org=eLib.getData("org", 2, 0)+randomNumber;
		
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

}
