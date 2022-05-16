package com.crm.practice;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericFunctionalities.DataRead;

public class OppurtunitiesScenarioChildBrowserHandling {
	public static void main(String[] args) throws IOException, InterruptedException {
		DataRead d=new DataRead();
		d.getFile();
		//setting to launch chrome browser
		
		//launching chrome browser
		WebDriver driver=d.driver;
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//LAUNCHING THE VTIGER APPLICATION
		driver.get(d.UrL);
		
		//log in to application
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(d.UserName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(d.Password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		//Opportunities module
		WebDriverWait wait=new WebDriverWait(driver,10);
		driver.findElement(By.xpath("//a[@href='index.php?module=Potentials&action=index']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='related_to_display']/../img[@title='Select']")).click();
		
		//switch to child browser
        String pid = driver.getWindowHandle();
        System.out.println(pid);
        Set<String> wids = driver.getWindowHandles();
        String cburl="http://localhost:8888/index.php?module=Accounts&action=Popup&html=Popup_picker&form=vtlibPopupView&forfield=related_to&srcmodule=Potentials&forrecord=";
        for (String ids : wids) {
			driver.switchTo().window(ids);
			if(driver.getCurrentUrl().equals(cburl))
			{
				Thread.sleep(2000);
				driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(d.Organisation);
				
				Thread.sleep(2000);
				driver.findElement(By.xpath("input[@name='search']")).click();
				
				Thread.sleep(2000);
				driver.switchTo().window(pid);	
			}
			break;
		}
      //Logout
        Actions act=new Actions(driver);
		WebElement acc=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wait.until(ExpectedConditions.visibilityOf(acc));
		act.moveToElement(acc).perform();
		driver.findElement(By.xpath("//a[.='Sign Out']")).click();
	}
}
