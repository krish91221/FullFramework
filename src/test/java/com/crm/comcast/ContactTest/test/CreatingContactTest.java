package com.crm.comcast.ContactTest.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.GenericUtility.ExcelUtitlity;
import com.crm.comcast.Pom_Repo.ContactInfoPage;
import com.crm.comcast.Pom_Repo.ContactsPage;
import com.crm.comcast.Pom_Repo.CreateContactPage;
import com.crm.comcast.Pom_Repo.HomePage;
@Listeners(com.crm.comcast.GenericUtility.IlisternsImplimentationClass.class)
public class CreatingContactTest extends BaseAnnotaionConfigClass {
	@Test(groups ="regressionTesting")//,retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
	public  void createContactInSupportGroupTest() throws Throwable {
		/* Random Number Generation */
		int ran=jLib.getRandomNumber();

		/*Read Data from Excel*/
		
		String contact=eLib.getData("contact", 1, 2)+ran;	
	
		/*click on contacts link*/
		HomePage hp=new HomePage(driver);
		hp.getContacts_Link().click();
		ContactsPage cp= new ContactsPage(driver);
		wLib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
		
		/*create contact*/
		cp.getCreate_Contact_Img().click();
		
		CreateContactPage ccp=new CreateContactPage(driver);
		wLib.explicitlyWaitForElement(driver, ccp.getLastName_TextField());
		ccp.getLastName_TextField().sendKeys(contact);
		ccp.getGroup_Radio_Btn().click();
		wLib.selectElementByVisibleText(ccp.getGroup_Dropdown(),"Support Group");
		
		ccp.getSave_Btn().click();
	
		/*validation*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		wLib.explicitlyWaitForElement(driver, cip.getContact_Info_Header());
		String actualResult=cip.getContact_Info_Header().getText();
		if(actualResult.contains(contact))
		{
			System.out.println(contact+" ==> is verified : Pass");
		}
		else
		{
			System.out.println(contact+" ==> is verified : Pass");
		}
	}
	
	@Test(groups = "regressionTesting",retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
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
	
	@Test(groups="regressionTesting",retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
	public  void crateContactAndDeleteSameContactTest() throws Throwable {

		/* Random Number Generation */
	
		int randomNum=jLib.getRandomNumber();
		
		/*Read Data from Excel*/
		String contact=eLib.getData("contact", 1, 2)+randomNum;
		
		/*click on contacts link*/
		HomePage hp=new HomePage(driver);
		hp.getContacts_Link().click();
		/*click on add contact*/
		CreateContactPage ccp=new CreateContactPage(driver);
		ccp.createContact(contact);
			
		/*validation*/
		ContactInfoPage cip=new ContactInfoPage(driver);
		wLib.explicitlyWaitForElement(driver, cip.getContact_Info_Header());
		String actualResult=cip.getContact_Info_Header().getText();
		if(actualResult.equals(contact))
		{
			System.out.println(contact+" ==> is verification : Pass");
		}
		else
		{
			System.out.println(contact+" ==> is verification : fail");
		}
		
		/*click on contacts link*/
		
		hp.getContacts_Link().click();
		
		WebElement createdContact = driver.findElement(By.xpath("//a[.='"+contact+"']"));
		wLib.explicitlyWaitForElement(driver, createdContact);
		String condet=createdContact.getText();
		/*Validation of created contact*/
		if(condet.equals(contact))
		{
			System.out.println(contact+" is created successfully and displayed as "+condet);
			/*Select the created contact and delete the contact*/
			WebElement selectedcontact= driver.findElement(By.xpath("//a[.='"+contact+"']/../../td[1]"));
			wLib.explicitlyWaitForElement(driver, selectedcontact);
			selectedcontact.click();
			wLib.explicitlyWaitForElement(driver, cip.getDelete_Btn());
			cip.getDelete_Btn().click();
			wLib.acceptAlert(driver);
			System.out.println("Deleted the contact "+contact);
			/*Validating deletion*/
			cip.getSearch_TextField().sendKeys(contact);
			WebElement dd = cip.getSearch_By_DropDown();
			wLib.selectElementByVisibleText(dd, "Last Name");
			cip.getSearch_Btn().click();
			String message=cip.getWarning_Msg().getText();
			
			System.out.println(message);
			if(message.contains("No Contact Found !"))
			{
			
				System.out.println(contact+" Contact is deleted successfully");
			}
			else
			{
				System.out.println(contact+" Deletion is not successful");
			}
		}
		else
		{
			System.out.println(contact+" is not created "+condet);
		}
	}
	
		@Test(groups= {"adhocTesting","regressionTesting"},retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
		public  void crateContactWithoutMandatoryFieldTest() throws Throwable {
			/* Random Number Generation */
			int randomNumber=jLib.getRandomNumber();

			/*Read Data from Excel*/
			String contact = eLib.getData("contact",1, 2)+randomNumber;
			
			//click on contacts link	
			
			HomePage hp=new HomePage(driver);
			wLib.explicitlyWaitForElement(driver, hp.getCalendar_Link());
			hp.getContacts_Link().click();
			
			ContactsPage cp=new ContactsPage(driver);
			//click on add contact
			wLib.explicitlyWaitForElement(driver, cp.getCreate_Contact_Img());
			cp.getCreate_Contact_Img().click();
			
			CreateContactPage ccp=new CreateContactPage(driver);
			wLib.explicitlyWaitForElement(driver, ccp.getLastName_TextField());
			ccp.getLastName_TextField().sendKeys("");
			ccp.getSave_Btn().click();
		
			wLib.acceptAlert(driver);
		}
		
		@Test(groups={"smokeTesting","regressionTesting"},retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
		public  void deleteContactTest() throws Throwable {
			/* Random Number Generation */
			int randomNum=jLib.getRandomNumber();
			
			/*Read Data from Excel*/
			ExcelUtitlity eutil=new ExcelUtitlity();
			String contact=eutil.getData("contact", 1, 2)+randomNum;

			HomePage hp=new HomePage(driver);
			wLib.explicitlyWaitForElement(driver, hp.getContacts_Link());
			hp.getContacts_Link().click();
			
			//delete all contacts
			ContactsPage cp=new ContactsPage(driver);
			wLib.explicitlyWaitForElement(driver, cp.getSelectAll_ChkBox());
			cp.getSelectAll_ChkBox().click();
			cp.getDelete_Btn().click();
			
			wLib.acceptAlert(driver);		
		}
}
