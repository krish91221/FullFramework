package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	WebDriver driver=null;
	@FindAll({@FindBy(xpath="//img[@src='themes/softed/images/btnL3Add.gif']"),@FindBy(xpath="//img[@title='Create Organization...']"),@FindBy(xpath="//img[@alt='Create Organization...']")})
	private WebElement create_Organiszation_Img;
	
	@FindAll({@FindBy(xpath="//input[@value='Delete']"),@FindBy(xpath="//tr/td/a[.='Organization No']/ancestor::tr/td//input[@class='crmbutton small delete']")})
	private WebElement deleteButtonTop;
	
	@FindAll({@FindBy(xpath="//input[@value='Mass Edit']"),@FindBy(xpath="//tr/td/a[.='Organization No']/ancestor::tr/td//input[@class='crmbutton small edit' and @value='Mass Edit']")})
	private WebElement mass_EditButtonTop;
	
	@FindAll({@FindBy(xpath="//input[@value='Send Mail']"),@FindBy(xpath="//tr/td/a[.='Organization No']/ancestor::tr/td//input[@class='crmbutton small edit' and @value='Send Mail']")})
	private WebElement send_MailButtonTop;
	
	@FindBy(xpath="//input[@class='txtBox' and @name='search_text']")
	private WebElement search_Text_field;

	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getCreate_Organiszation_Img() {
		return create_Organiszation_Img;
	}

	public WebElement getDeleteButtonTop() {
		return deleteButtonTop;
	}

	public WebElement getMass_EditButtonTop() {
		return mass_EditButtonTop;
	}

	public WebElement getSend_MailButtonTop() {
		return send_MailButtonTop;
	}

	public WebElement getSearch_Text_field() {
		return search_Text_field;
	}
}
