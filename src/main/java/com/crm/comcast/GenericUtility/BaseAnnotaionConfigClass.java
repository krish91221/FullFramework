package com.crm.comcast.GenericUtility;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.crm.comcast.Pom_Repo.HomePage;
import com.crm.comcast.Pom_Repo.LogInPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This is the Base class for Vtiger Application
 * @author Murali Krishna
 *
 */
public class BaseAnnotaionConfigClass {
	public JavaUtility jLib=new JavaUtility();
	public FileUtitlty fLib=new FileUtitlty();
	public ExcelUtitlity eLib=new ExcelUtitlity();
	public WebDriverUtility wLib=new WebDriverUtility();
	public JdbcUtility dLib=new JdbcUtility();
	public WebDriver driver;
	public static WebDriver sdriver;

	@BeforeSuite(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void openDataBaseConnection()
	{
		dLib.connectToDataBase();
		System.out.println("=====Data base connection is Established successfully=====");
	}
//	@Parameters("browser")
//	@BeforeTest(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void openBrowser(String BROWSER) throws Throwable
	{
		System.out.println("=====Used to launch the application in different Browsers=====");
			String URL=fLib.getKeyValue("url");
			if(BROWSER.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();
			}
			else if(BROWSER.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			else
			{
				WebDriverManager.chromedriver().setup();
			}
			wLib.implicitWait(driver);
			driver.manage().window().maximize();
			driver.get(URL);
			System.out.println("=====Application is launched successfully=====");
	}
	//@Parameters("browser")
	@BeforeClass(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void launchApplicationWindow() throws Throwable
	{
		String URL=fLib.getKeyValue("url");
		String BROWSER=fLib.getKeyValue("browser");
		if(BROWSER.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else
		{
			WebDriverManager.chromedriver().setup();
			driver=new FirefoxDriver();
		}
		wLib.implicitWait(driver);
		driver.manage().window().maximize();
		driver.get(URL);
		System.out.println("=====Application is launched successfully=====");
	}

	@BeforeMethod(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void logOInToApplication() throws Throwable
	{
		String USERNAME= fLib.getKeyValue("username");
		String PASSWORD=fLib.getKeyValue("password");
		LogInPage lp=new LogInPage(driver);
		lp.logInToApp(USERNAME,PASSWORD);
		System.out.println("=====Logged into Application successfully=====");
		sdriver=driver;
	}
	@AfterMethod(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void logOutOfApplication()
	{
		HomePage hp=new HomePage(driver);
		hp.signout();
		System.out.println("=====Logged out from Application successfully=====");
	}
	@AfterClass(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void closeApplicationWindow()
	{
		driver.quit();
		System.out.println("=====Application window is closed successfully=====");
	}
	@AfterTest(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void closeBrowser()
	{
		System.out.println("=====Opened browsers are closed successfully=====");
	}
	@AfterSuite(groups= {"smokeTesting","adhocTesting","integrationTesting","regressionTesting"})
	public void closeDataBaseConnection() throws Throwable
	{
		dLib.closeConnection();
		System.out.println("=====DataBase connection is terminated successfully=====");
	}

}
