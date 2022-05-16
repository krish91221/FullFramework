package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateContactPage 
{
	WebDriver driver;
	@FindBy(name="lastname")
	private WebElement lastName_TextField;
	
	@FindBy(xpath="//input[@type='radio' and @value='T']")
	private WebElement group_Radio_Btn;
	
	@FindBy(xpath="//select[@name='assigned_group_id']")
	private WebElement group_Dropdown;
	/**
	 * @return the lastName_TextField
	 */
	public WebElement getLastName_TextField() {
		return lastName_TextField;
	}

	/**
	 * @return the save_Btn
	 */
	public WebElement getSave_Btn() {
		return save_Btn;
	}

	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement save_Btn;

	public CreateContactPage(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void createContact(String lastName)
	{
		ContactsPage cp=new ContactsPage(driver);
		cp.getCreate_Contact_Img().click();
		lastName_TextField.sendKeys(lastName);
		save_Btn.click();
		
	}

	/**
	 * @return the group_Radio_Btn
	 */
	public WebElement getGroup_Radio_Btn() {
		return group_Radio_Btn;
	}

	/**
	 * @return the group_Dropdown
	 */
	public WebElement getGroup_Dropdown() {
		return group_Dropdown;
	}
}
