package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Campaign_Info_Page {

WebDriver driver;
	
	@FindBy(xpath="//span[@class='dvHeaderText']")
	private WebElement campaign_Info_Header;

	public Campaign_Info_Page(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCampaign_Info_Header() {
		return campaign_Info_Header;
	}
}
