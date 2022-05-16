package com.crm.comcast.ContactTest.test;

import java.awt.AWTException;
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
import org.testng.annotations.Test;

import com.crm.comcast.GenericFunctionalities.DataRead;
import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.GenericUtility.FileUtitlty;
import com.crm.comcast.GenericUtility.JavaUtility;
import com.crm.comcast.GenericUtility.WebDriverUtility;
import com.crm.comcast.Pom_Repo.ContactInfoPage;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ContactEditScenario extends BaseAnnotaionConfigClass {
@Test
	public  void contactEditTest() throws Throwable {

		
		int randomNumber=jLib.getRandomNumber();
	
		/*Read Data from Excel*/
		
		String contact=eLib.getData("contact",2,2)+randomNumber;
	
		/*click on contacts link*/

		HomePage hp=new HomePage(driver);
		wLib.explicitlyWaitForElement(driver, hp.getContacts_Link());
		
		hp.getContacts_Link().click();
		
		ContactsPage cp=new ContactsPage(driver);
		
		wLib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
		cp.getCreate_Contact_Img().click();
		/*click on add contact*/
		CreateContactPage ccp=new CreateContactPage(driver);
		wLib.explicitlyWaitForElement(driver, ccp.getLastName_TextField()); 
		ccp.getLastName_TextField().sendKeys(contact);
		
		ccp.getSave_Btn().click();
		
		/*Edit the contact*/
		WebElement contact_Link=driver.findElement(By.xpath("//a[@class='hdrLink' and .='Contacts']"));
		wLib.explicitlyWaitForElement(driver, contact_Link);
		contact_Link.click();
		WebElement contact_Id=driver.findElement(By.xpath("//a[.='"+contact+"']/../..//td[2]"));
		wLib.explicitlyWaitForElement(driver, contact_Id);
		String cid=contact_Id.getText();
		WebElement edit_Link=driver.findElement(By.xpath("//a[.='"+contact+"']/../..//a[.='edit']"));
		wLib.explicitlyWaitForElement(driver, edit_Link);
		edit_Link.click();
		ccp.getLastName_TextField().clear();
		String new_Name="Lakshmi Devi"+randomNumber;
		ccp.getLastName_TextField().sendKeys(new_Name);
		ccp.getSave_Btn().click();
		
		/*validation*/
		
		String after_Edit=driver.findElement(By.xpath("//td[.='"+cid+" ']/following-sibling::td[2]")).getText();
		if(contact.equals(after_Edit))
		{
			System.out.println(contact+" Edit is not saved "+after_Edit+" verification -->failed ");
		}
		else
		{
			System.out.println(contact+" Edit is saved "+after_Edit+" verification -->passed ");
			
		}
	}
}
