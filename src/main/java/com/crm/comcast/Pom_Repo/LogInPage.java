package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtility.FileUtitlty;
/*Rule 1:create separate java class for every page*/
public class LogInPage {
	/*Rule 2: Identify all the web elements using @FindBy, @FindALL, @FindBys annotations */
	@FindBy(name="user_name")
	private WebElement userName_TextField;
	
	@FindBy(name="user_password")
	private WebElement password_TextField;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath="//input[@type='submit']"),@FindBy(xpath="//input[@value='Login']")})
	private WebElement login_Button;
	
	WebDriver driver;
	
	public LogInPage(WebDriver driver)
	{
		this.driver=driver;
		 PageFactory.initElements(driver, this);
		/*Rule 3: Provide a constructor to create the object of pom class and initialize the web elements using PageFactory.initElements()*/
	}

	/*Rule 4:Declare the WebElements as private and provide getter methods to provide read only access */
	public WebElement getUserName_TextField() {
		return userName_TextField;
	}

	public WebElement getPpassword_TextField() {
		return password_TextField;
	}

	public WebElement getLogin_Button() {
		return login_Button;
	}
	
	/*Rule 5: Provide implementation for repeated function in the same pom class Business Library*/
	public void logInToApp(String userName, String password)
	{
		
		userName_TextField.sendKeys(userName);
		password_TextField.sendKeys(password);
		login_Button.click();
	}
	public void logInToApp() throws Throwable
	{
		FileUtitlty flib=new FileUtitlty();
		driver.get(flib.getKeyValue("url"));
		userName_TextField.sendKeys(flib.getKeyValue("username"));
		password_TextField.sendKeys(flib.getKeyValue("password"));
		login_Button.click();
	}
}
