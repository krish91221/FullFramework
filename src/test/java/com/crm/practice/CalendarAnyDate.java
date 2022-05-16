package com.crm.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarAnyDate {
public static void main(String[] args) throws InterruptedException, AWTException {
			
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
			
			Thread.sleep(2000);
			
			//navigate to departure
			driver.findElement(By.xpath("//span[contains(.,'DEPARTURE')]")).click();
			
			//navigate to desired date in calender
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[@aria-label='Mon Apr 25 2022']")).click();
			Thread.sleep(2000);
			
			//close the browser
			driver.quit();
	
		}

	}