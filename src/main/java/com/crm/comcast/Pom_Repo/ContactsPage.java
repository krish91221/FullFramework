package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactsPage {
	WebDriver driver;
	@FindAll({@FindBy(xpath="//img[@title='Create Contact...']"),@FindBy(xpath="//img[@alt='Create Contact...']")})
	private WebElement create_Contact_Img;
	
	@FindBy(xpath="//input[@id='selectCurrentPageRec']")
	private WebElement selectAll_ChkBox;
	
	@FindBy(xpath="//input[@class='crmbutton small delete']")
	private WebElement delete_Btn;
	
	
	/**
	 * @return the delete_Btn
	 */
	public WebElement getDelete_Btn() {
		return delete_Btn;
	}

	/**
	 * @return the selectAll_ChkBox
	 */
	public WebElement getSelectAll_ChkBox() {
		return selectAll_ChkBox;
	}

	public ContactsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreate_Contact_Img() {
		return create_Contact_Img;
	}
}
