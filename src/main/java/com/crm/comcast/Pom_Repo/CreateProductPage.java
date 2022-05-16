package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateProductPage {
	WebDriver driver;
	@FindBy(xpath="//input[@name='productname']")
	private WebElement ProductName_TextField;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement save_Btn;
	
	public WebElement getSave_Btn() {
		return save_Btn;
	}

	public CreateProductPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public WebElement getProductName_TextField() {
		return ProductName_TextField;
	}
}
