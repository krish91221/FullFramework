package com.crm.comcast.Pom_Repo;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignPage {
	WebDriver driver;
	@FindBy(xpath="//img[@title='Create Campaign...']")
	private WebElement create_Campaign_Icon;
	
	private String Campaign_page_url="Campaigns&action";	
	
	public CampaignPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreate_Campaign_Icon()
	{
		return create_Campaign_Icon;
	}

	public String getCampaign_page_url() {
		return Campaign_page_url;
	}

}
