package com.crm.comcast.OrgTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateOrganisationTest {
	public static void main(String[] args) throws IOException, InterruptedException {

		WebDriver driver;

		/* Random Number Generation */
		Random r=new Random();
		int ran= r.nextInt(9999);

		/*Read Common Data from Properties File*/
		FileInputStream pfile=new FileInputStream("./data/commondata.properties");

		Properties p=new Properties();
		p.load(pfile);
		String URL=p.getProperty("url");
		String BROWSER=p.getProperty("browser");
		String USERNAME=p.getProperty("uname");
		String PASSWORD=p.getProperty("pass");

		/*Read Data from Excel*/
		FileInputStream efile=new FileInputStream("./data/testdata.xlsx");

		Workbook book=WorkbookFactory.create(efile);
		Sheet sh = book.getSheet("org");
		String org=sh.getRow(2).getCell(0).getStringCellValue()+ran;
		book.close();

		/*Launch browser*/
		if(BROWSER.equals("chrome"))
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(BROWSER.equals("firefox"))
		{
			driver=new FirefoxDriver(); 
		}
		else if(BROWSER.equals("safari"))
		{
			driver=new SafariDriver(); 
		}
		else if(BROWSER.equals("ie"))
		{
			driver=new InternetExplorerDriver(); 
		}
		else
		{
			driver=new ChromeDriver();
		}

		/*Launch Application*/
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(URL);

		//Login to application
		/*
		 * driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME)
		 * ; driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(
		 * PASSWORD);
		 * driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		 */
		WebDriverWait wait=new WebDriverWait(driver,10);
		//click on organizations link
		WebElement orgs=driver.findElement(By.xpath("//a[.='Organizations']"));
		wait.until(ExpectedConditions.visibilityOf(orgs));
		orgs.click();

		//click on add organization
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();

		//validate the organization name created
		String actualorg = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(actualorg.contains(org))
		{
			System.out.println(org+" ==> Organisation is verified : pass");
		}
		else
		{
			System.out.println("Organisation is not verified : fail");
		}
		//Logout
		Thread.sleep(2000);
		WebElement acc=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wait.until(ExpectedConditions.visibilityOf(acc));
		Actions act=new Actions(driver);
		act.moveToElement(acc).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();

		//close the application
		driver.close();
	}

}
