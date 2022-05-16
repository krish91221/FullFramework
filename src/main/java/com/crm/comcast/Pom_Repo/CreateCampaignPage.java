package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtility.WebDriverUtility;

public class CreateCampaignPage  {
	WebDriver driver;
	
	@FindBy(xpath="//input[@name='campaignname']")
	private WebElement campaign_Name_TextField;
	
	@FindBy(xpath="//input[@name='product_name']/following-sibling::img")
	private WebElement product_LookUp_Img;
	
	@FindBy(name="product_name")
	private WebElement product_From_LookUp;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement save_Btn;
	
	public CreateCampaignPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCampaign_Name_TextFiel() {
		return campaign_Name_TextField;
	}

	public WebElement getProduct_LookUp_Img() {
		return product_LookUp_Img;
	}
	
	public void createCampaign(String campaignName)
	{
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.explicitlyWaitForElement(driver, campaign_Name_TextField);
		campaign_Name_TextField.sendKeys(campaignName);
		wlib.explicitlyWaitForElement(driver, getSave_Btn());
		getSave_Btn().click();
	}


	public WebElement getProduct_From_LookUp() {
		return product_From_LookUp;
	}

	public WebElement getSave_Btn() {
		return save_Btn;
	}

}
