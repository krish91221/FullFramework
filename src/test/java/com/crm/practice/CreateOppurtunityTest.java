package com.crm.practice;

import java.awt.dnd.peer.DropTargetPeer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateOppurtunityTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		WebDriver driver=null;
		/*Random number generation*/
		Random r=new Random();
		int ran=r.nextInt(9999);

		/*Read Credentials from Properties file*/
		FileInputStream fis= new FileInputStream("./data/commondata.properties");
		Properties p=new Properties() ;
		p.load(fis);
		String URL=p.getProperty("url");
		String BROWSER=p.getProperty("browser");
		String USERNAME=p.getProperty("uname");
		String PASSWORD=p.getProperty("pass");

		/*Read Test Data from Excel file */
		FileInputStream tis=new FileInputStream("./data/testdata.xlsx");
		Workbook book=WorkbookFactory.create(tis);

		Sheet sh = book.getSheet("org");
		String org=sh.getRow(2).getCell(0).getStringCellValue()+ran;

		sh = book.getSheet("opportunities");
		String OpportunityName=sh.getRow(0).getCell(1).getStringCellValue()+ran;

		book.close();

		/*Launch Browser*/
		if(BROWSER=="chrome")
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
			
		}
		else if(BROWSER=="firefox")
		{
			driver= new FirefoxDriver();
		}
		else
		{
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
try
{
		/*Launch the Vtiger application*/
		driver.get(URL);

		/*Login to application*/
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(USERNAME);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(PASSWORD);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		WebDriverWait wait=new WebDriverWait(driver,10);

		//click on organizations link
		WebElement orgs=driver.findElement(By.xpath("//a[.='Organizations']"));
		wait.until(ExpectedConditions.visibilityOf(orgs));
		orgs.click();

		//click on add organization
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(org);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();


		/*Open Opportunities*/
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@href='index.php?module=Potentials&action=index']")).click();
		/*Create Opportunity*/
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		driver.findElement(By.xpath("//input[@name='potentialname']")).sendKeys(OpportunityName);
		WebElement orgorcon=driver.findElement(By.xpath("//select[@id='related_to_type']"));
		Select sel =new Select(orgorcon);
		sel.selectByVisibleText("Organizations");
		driver.findElement(By.xpath("//select[@id='related_to_type']"));
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();

		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> it= tabs.iterator();
		while(it.hasNext())
		{
			String CurTab=it.next();
			driver.switchTo().window(CurTab);
			if(driver.getCurrentUrl().contains("Accounts&action"))
			{
				break;
			}
		}

		/*search for organization and select the organization*/
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(org);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[.='"+org+"']")).click();

		tabs = driver.getWindowHandles();
		it= tabs.iterator();
		while(it.hasNext())
		{
			String CurTab=it.next();
			driver.switchTo().window(CurTab);
			if(driver.getTitle().contains("Opportunitues"))
			{
				break;
			}
		}

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		//Logout
		Thread.sleep(2000);
		WebElement acc=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wait.until(ExpectedConditions.visibilityOf(acc));
		Actions act=new Actions(driver);
		act.moveToElement(acc).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
}
finally
{
		//close the application
	Set<String> tabs = driver.getWindowHandles();
	Iterator<String> it = tabs.iterator();
	while(it.hasNext())
	{
		String CurTab=it.next();
		driver.switchTo().window(CurTab);
		driver.close();
	}
}
	}
}
