package com.crm.practice;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.comcast.GenericFunctionalities.DataRead;

public class TC_61TestScript {
public static void main(String[] args) throws IOException {
	//launch application
	DataRead d=new DataRead();
	d.getFile();
	WebDriver driver=d.driver;
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(d.UrL);
	//login to application
	WebDriverWait wait=new WebDriverWait(driver, 10);
	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(d.UserName);
	driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(d.Password);
	driver.findElement(By.xpath("//input[@id='submitButton']")).click();
	
	//open purchase orders
	WebElement more = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
	Actions act=new Actions(driver);
	act.moveToElement(more).perform();
   WebElement purchase = driver.findElement(By.xpath("//a[@name='Purchase Order']"));
	wait.until(ExpectedConditions.visibilityOf(purchase));
	purchase.click();
	
	
	//open Documents link
	driver.findElement(By.xpath("//a[@href='index.php?module=Documents&action=index']")).click();
	driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
	String fcreated="User Manual";
	driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(fcreated);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	driver.findElement(By.xpath("//a[@href='index.php?action=ListView&module=Documents&parenttab=Marketing']")).click();
	 List<WebElement> dlist = driver.findElements(By.xpath("//a[@title='Documents']"));
	 for (WebElement doc : dlist) {
		 if(doc.getText().equals(fcreated))
		 {
			 doc.findElement(By.xpath("//a[@title='Documents']/../../td[1]")).click();
		 }
	}
	//open purchase orders
	 WebElement more1 = driver.findElement(By.xpath("//img[@src='themes/softed/images/menuDnArrow.gif']"));
		act.moveToElement(more1).perform();
		driver.findElement(By.xpath("//a[@name='Purchase Order']")).click();
		
		//Logout
				WebElement acc=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				wait.until(ExpectedConditions.visibilityOf(acc));
				act.moveToElement(acc).perform();
				driver.findElement(By.xpath("//a[.='Sign Out']")).click();
}
}
