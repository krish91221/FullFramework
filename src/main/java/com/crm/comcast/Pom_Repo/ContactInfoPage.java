package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPage {

	WebDriver driver;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement contact_Info_Header;
	
	@FindBy(xpath="//input[@name='search_text']")
	private WebElement search_TextField;
	
	@FindBy(xpath="//select[@id='bas_searchfield']")
	private WebElement search_By_DropDown;
	
	@FindBy(xpath="//input[@value=' Search Now ']")
	private WebElement search_Btn;
	
	@FindBy(xpath="//span[@class='genHeaderSmall']")
	private WebElement warning_Msg;
	
	/**
	 * @return the warning_Msg
	 */
	public WebElement getWarning_Msg() {
		return warning_Msg;
	}

	/**
	 * @return the search_TextField
	 */
	public WebElement getSearch_TextField() {
		return search_TextField;
	}

	/**
	 * @return the search_By_DropDown
	 */
	public WebElement getSearch_By_DropDown() {
		return search_By_DropDown;
	}

	/**
	 * @return the search_Btn
	 */
	public WebElement getSearch_Btn() {
		return search_Btn;
	}

	@FindBy(xpath="//input[@class='crmbutton small delete']")
	private WebElement delete_Btn;
	
	public ContactInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getContact_Info_Header() {
		return contact_Info_Header;
	}

	/**
	 * @return the delete_Btn
	 */
	public WebElement getDelete_Btn() {
		return delete_Btn;
	}
		
}
