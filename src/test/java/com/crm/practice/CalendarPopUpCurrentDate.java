package com.crm.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopUpCurrentDate 
{
	public static void main(String[] args) throws InterruptedException, AWTException {
	
	Date date=new Date();
	String[] d1=date.toString().split(" ");
	String currentdate=d1[0]+" "+d1[1]+" "+d1[2]+" "+d1[5];
	System.out.println(currentdate);
	//set wedriver manger to launch chrome browser
	WebDriverManager.chromedriver().setup();
	//launch chrome browser
     WebDriver driver = new ChromeDriver(); 
     driver.manage().window().maximize();
     //launch the application
     driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
     driver.get("https://www.makemytrip.com");
     
     Actions act=new Actions(driver);
     act.moveByOffset(10, 10).click().perform();
   //Navigate to From and To Elements
		WebElement src = driver.findElement(By.xpath("//input[@id='fromCity']"));
		src.sendKeys("mumbai");
		driver.findElement(By.xpath("//p[text()='Mumbai, India']")).click();
		
		WebElement dst = driver.findElement(By.xpath("//span[.='To']"));
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("chennai maa");	
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_UP);
		r.keyPress(KeyEvent.VK_UP);
		r.keyRelease(KeyEvent.VK_UP);
		driver.findElement(By.xpath("//p[.='Chennai, India']")).click();
     //select current date
     Thread.sleep(2000);
     driver.findElement(By.xpath("//span[contains(.,'DEPARTURE')]")).click();
		
     Thread.sleep(2000);
     WebElement cuda = driver.findElement(By.xpath("//div[@aria-label='"+currentdate+"']"));
      Point loc = cuda.getLocation();
      Thread.sleep(2000);
      act.moveByOffset(loc.x, loc.y).click().perform();
     System.out.println(cuda.getLocation());
     Thread.sleep(2000);
     cuda.click();
     Thread.sleep(2000);
     //close the browser
     driver.close();
	}
}
