package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	WebDriver driver;
	private String product_lookup_Url="Products&action";
	
	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement create_Product_Icon;
	
	@FindBy(xpath="//input[@id='search_txt']")
	private WebElement product_Lookup_search_TextField;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement product_Lookup_Search_Btn;
	
	@FindBy(xpath="//span[@class='genHeaderSmall']")
	private WebElement warning_Msg;
	

	/**
	 * @return the warning_Msg
	 */
	public WebElement getWarning_Msg() {
		return warning_Msg;
	}

	public WebElement getProduct_Lookup_search_TextField() {
		return product_Lookup_search_TextField;
	}

	public WebElement getProduct_Lookup_Search_Btn() {
		return product_Lookup_Search_Btn;
	}

	public ProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebElement getCreate_Product_Icon() {
		return create_Product_Icon;
	}

	public String getProduct_lookup_Url() {
		return product_lookup_Url;
	}
	
	
}
