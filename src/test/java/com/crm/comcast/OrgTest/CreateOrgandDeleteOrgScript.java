package com.crm.comcast.OrgTest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericFunctionalities.DataRead;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrgandDeleteOrgScript {
	public static void main(String[] args) throws InterruptedException, IOException {
		DataRead d=new DataRead();
		d.getFile();
		//setting to launch chrome browser
		
		//launching chrome browser
		WebDriver driver=d.driver;
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//LAUNCHING THE VTIGER APPLICATION
		driver.get(d.UrL);
		
		//log in to apllication
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(d.UserName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(d.Password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,10);
		//click on organisations link
		d.getTestDataforOrg();
		WebElement orgs=driver.findElement(By.xpath("//a[.='Organizations']"));
		wait.until(ExpectedConditions.visibilityOf(orgs));
		orgs.click();
		
		//click on add organisation
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(d.Organisation);
		driver.findElement(By.xpath("//input[@value='  Save  ']")).click();
		
		//delete the organisation
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[.='Organizations']")).click();
		driver.findElement(By.xpath("//input[@id='selectCurrentPageRec']")).click();
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		
		Alert a=driver.switchTo().alert();
		a.accept();
		
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
