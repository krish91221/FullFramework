package com.crm.practice;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopUpAnyDate 
{
//	public static void main(String[] args) throws InterruptedException 
//	{
//		//set browser 
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver =new ChromeDriver();
//		driver.manage().window().maximize();
//		
//		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//		//launch the application
//		driver.get("https://www.makemytrip.com");
//		
//		Actions act=new Actions(driver);
//		act.moveByOffset(10, 10).click().perform();
//		//locate the from and to web elements
//		WebElement src = driver.findElement(By.xpath("//input[@id='fromCity']"));
//		Thread.sleep(2000);
//		WebElement dst = driver.findElement(By.xpath("//input[@id='toCity']"));
//		//send from and to data to the web elements
//		src.sendKeys("mumbai");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//p[.='Mumbai, India']")).click();
//		Thread.sleep(2000);
//
//		dst.sendKeys("chennai");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//p[.='Chennai, India']")).click();
//		//select the date
//		driver.findElement(By.xpath("//input[@data-cy='departure']")).click();
//		
//		driver.findElement(By.xpath("//div[@aria-label='Fri Apr 22 2022']")).click();
			public static void main(String[] args) throws Throwable {
		
			//Set the web driver manager to chrome
			WebDriverManager.chromedriver().setup();
			
			//Launch the browser
			WebDriver driver = new ChromeDriver();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			driver.get("https://www.makemytrip.com/");
			
			Actions act = new Actions(driver);
			act.moveByOffset(10, 10).click().perform();
			
			//Navigate to From and To Elements
			WebElement src = driver.findElement(By.xpath("//input[@id='fromCity']"));
			WebElement dst = driver.findElement(By.xpath("//input[@placeholder='To']"));
			
			
			src.sendKeys("mumbai");
			driver.findElement(By.xpath("//p[text()='Mumbai, India']")).click();
			
			Thread.sleep(2000);
			
			dst.sendKeys("chennai");
			Thread.sleep(2000);
			driver.findElement(By.xpath("//p[text()='Chennai, India']")).click();
			
			Thread.sleep(2000);
//			Robot r=new Robot();
//			r.keyPress(KeyEvent.VK_DOWN);
//			r.keyRelease(KeyEvent.VK_DOWN);
//			//navigate to departure
			driver.findElement(By.xpath("//label[@for='departure']")).click();
			
			//navigate to desired date in calender
			driver.findElement(By.xpath("//div[@aria-label='Fri Apr 22 2022']")).click();
			
			
			
			//close the browser
			//driver.quit();
			

			
		}

	}

