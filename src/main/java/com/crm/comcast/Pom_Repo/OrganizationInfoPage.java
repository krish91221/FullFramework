package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInfoPage {
	WebDriver driver;

	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement Organization_Header_Info;
	
	public OrganizationInfoPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public WebElement getOrganization_Header_Info()
	{
		return Organization_Header_Info;
	}

}
