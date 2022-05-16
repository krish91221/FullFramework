package com.crm.practice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CalendarPopUpFutureDate {
	public static void main(String[] args) throws InterruptedException 
	{
		//set browser 
		WebDriverManager.chromedriver().setup();
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		//launch the application
		driver.get("https://www.makemytrip.com");
		
		Actions act=new Actions(driver);
		act.moveByOffset(10, 10).click().perform();
		//locate the from and to web elements
		WebElement src = driver.findElement(By.xpath("//input[@id='fromCity']"));
		Thread.sleep(2000);
		//send from and to data to the web elements
		src.sendKeys("mumbai");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[.='Mumbai, India']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='toCity']")).sendKeys("chennai");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//p[.='Chennai, India']")).click();
		//select the date
		driver.findElement(By.xpath("//input[@data-cy='departure']")).click();
		
		//infinite for loop
		//to select future dates not visible in current DOM
		for(;;)
		{
			
			//to select the required date if present
			try
			{
				driver.findElement(By.xpath("//div[@aria-label='Sat Jun 18 2022']")).click();
				break;
			}
			//to move to next month 
			catch(Exception e)
			{
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
				
			}
		}
		
		
	
}
}
