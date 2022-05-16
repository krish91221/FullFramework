package com.crm.comcast.Pom_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.comcast.GenericUtility.WebDriverUtility;

public class HomePage {
	WebDriver driver=null;
	@FindBy(linkText="themes/softed/images/Home.PNG")
	private WebElement home_Btn;
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement account_Icon;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Calendar&action=index']"),@FindBy(xpath="//a[.='Calendar']")})
	private WebElement calendar_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Leads&action=index']"),@FindBy(xpath="//a[.='Leads']")})
	private WebElement leads_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Accounts&action=index']"),@FindBy(xpath="//a[.='Organizations']")})
	private WebElement organizatoins_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Contacts&action=index']"),@FindBy(xpath="//a[.='Contacts']")})
	private WebElement contacts_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Potentials&action=index']"),@FindBy(xpath="//a[.='Opportunities']")})
	private WebElement opportunities_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Products&action=index']"),@FindBy(xpath="//a[.='Products']")})
	private WebElement products_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Documents&action=index']"),@FindBy(xpath="//a[.='Documents']")})
	private WebElement documents_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Emails&action=index']"),@FindBy(xpath="//a[.='Email']")})
	private WebElement email_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=HelpDesk&action=index']"),@FindBy(xpath="//a[.='Trouble Tickets']")})
	private WebElement trouble_Tickets_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Dashboard&action=index']"),@FindBy(xpath="//a[.='Dashboard']")})
	private WebElement dashboard_Link;
	@FindAll({@FindBy(xpath="//img[@src='themes/softed/images/menuDnArrow.gif']"),@FindBy(xpath="//a[.='More']")})
	private WebElement more_Dropdown_Menu;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=PurchaseOrder&action=index']"),@FindBy(xpath="//a[.='Purchase Order']"),@FindBy(name="Purchase Order")})
	private WebElement purchase_Order_Link;
	@FindAll({@FindBy(xpath="//a[@href='index.php?module=Vendors&action=index']"),@FindBy(xpath="//a[.='Vendors']"),@FindBy(name="Vendors")})
	private WebElement vendors_Link;
	@FindAll({@FindBy(xpath="//a[.='Campaigns']"),@FindBy(name="Campaigns")})
	private WebElement campaigns_Link;
	@FindAll({@FindBy(xpath="//a[.='Sign Out']"),@FindBy(linkText ="Sign Out" )})
	private WebElement sign_Out_Link;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getHOME_BUTTON() {
		return home_Btn;
	}

	public WebElement getAccount_Icon() {
		return account_Icon;
	}

	public WebElement getCalendar_Link() {
		return calendar_Link;
	}

	public WebElement getLeads_Link() {
		return leads_Link;
	}

	public WebElement getOrganizatoins_Link() {
		return organizatoins_Link;
	}

	public WebElement getContacts_Link() {
		return contacts_Link;
	}

	public WebElement getOpportunities_Link() {
		return opportunities_Link;
	}

	public WebElement getProducts_Link() {
		return products_Link;
	}

	public WebElement getDocuments_Link() {
		return documents_Link;
	}

	public WebElement getEmail_Link() {
		return email_Link;
	}

	public WebElement getTrouble_Tickets_Link() {
		return trouble_Tickets_Link;
	}

	public WebElement getDashboard_Link() {
		return dashboard_Link;
	}

	public WebElement getMore() {
		return more_Dropdown_Menu;
	}

	public WebElement getPurchase_Order_Link() {
		return purchase_Order_Link;
	}

	public WebElement getVendors_Link() {
		return vendors_Link;
	}

	public WebElement getCampaigns_Link() {
		return campaigns_Link;
	}

	public WebElement getSign_Out_Link() {
		return sign_Out_Link;
	}
	
	public void signout()
	{
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.explicitlyWaitForElement(driver, account_Icon);
		wlib.mouseHoveOnElement(driver, account_Icon);
		wlib.explicitlyWaitForElement(driver, sign_Out_Link);
		sign_Out_Link.click();
	}
	
	public void gotocampaignsPage()
	{
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.mouseHoveOnElement(driver,more_Dropdown_Menu);
		campaigns_Link.click();
	}
	public void gotoPurchaseOrderPage()
	{
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.mouseHoveOnElement(driver,more_Dropdown_Menu);
		purchase_Order_Link.click();
	}
	public void gotoVendorsPage()
	{
		WebDriverUtility wlib=new WebDriverUtility();
		wlib.mouseHoveOnElement(driver,more_Dropdown_Menu);
		vendors_Link.click();
	}
}
