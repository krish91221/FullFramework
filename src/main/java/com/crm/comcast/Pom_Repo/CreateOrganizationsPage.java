package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateOrganizationsPage  {

	WebDriver driver;
	@FindBy(name="accountname")
	private WebElement organization_name_TextField;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement save_Btn;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement industry_DropDown;
	
	/**
	 * @return the industry_DropDown
	 */
	public WebElement getIndustry_DropDown() {
		return industry_DropDown;
	}

	public CreateOrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getOrganization_name_TextField() {
		return organization_name_TextField;
	}

	public WebElement getSave_Btn() {
		return save_Btn;
	}
	public void createOrganisation(String orgName)
	{
		organization_name_TextField.sendKeys(orgName);
		save_Btn.click();
	}
}
