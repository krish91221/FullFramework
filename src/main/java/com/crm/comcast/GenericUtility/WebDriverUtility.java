package com.crm.comcast.GenericUtility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.input.WindowsLineEndingInputStream;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;


/**
 * This class contains WebDriver specific generic methods
 * @author Murali Krishna
 *
 */
public class WebDriverUtility {
	/**
	 * This method is used for obtaining Implicit wait functionality by passing WebDriver reference as parameter
	 * It will hold the control until a webElement is present in WebEleent DOM
	 */
	public void implicitWait(WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(IConstants.IMPLICIT_WAIT_TIME_IN_SECONDS,TimeUnit.SECONDS);
	}
	/**
	 * This method is used for explicit wait functionality by providing WebDriver reference and WebElement reference
	 * @param driver
	 * @param element
	 */
	public void explicitlyWaitForElement(WebDriver driver,WebElement element)
	{
		WebDriverWait wait=new WebDriverWait(driver,IConstants.EXPLIXIT_WAIT_TIME_IN_SECONDS);
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	/**
	 * This method is used for explicit wait until a specified url is loaded
	 * @param driver
	 * @param partialUrl
	 */
	public void explicitlyWaitForUrl(WebDriver driver,String partialUrl)
	{
		WebDriverWait wait=new WebDriverWait(driver,IConstants.EXPLIXIT_WAIT_TIME_IN_SECONDS);	
		wait.until(ExpectedConditions.urlContains(partialUrl));
	}
	/**
	 * This method is used for explicit wait until a specified page is loaded
	 * @param driver
	 * @param partialUrl
	 */
	public void explicitlyWaitForPage(WebDriver driver,String title)
	{
		WebDriverWait wait=new WebDriverWait(driver,IConstants.EXPLIXIT_WAIT_TIME_IN_SECONDS);	
		wait.until(ExpectedConditions.titleContains(title));
	}
	/**
	 * This method is used to move the control to particular Web element 
	 * @param driver
	 * @param element
	 */
	public void mouseHoveOnElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();;
	}
	/**
	 * This method is used to click on specific element 
	 * @param driver
	 * @param element
	 * @throws Throwable 
	 */
	public void waitAndClick(WebDriver driver, WebElement element) throws Throwable
	{
		int count=0;
		while(count<20)
		{
			try
			{
				element.click();
				break;
			}
			catch(Exception e)
			{
				Thread.sleep(100);
			}
		}
	}
	/**
	 * This method is used to select an option based on index from drop down  
	 * @param element
	 * @param i
	 */
	public void selectElementByIndex(WebElement element,int i)
	{
		Select sel=new Select(element);
		sel.selectByIndex(i);
	}
	/**
	 * This method is used to select an option based on value from drop down  
	 * @param element
	 * @param val
	 */
	public void selectElementByValue(WebElement element,String val)
	{
		Select sel=new Select(element);
		sel.selectByValue(val);
	}
	/**
	 * This method is used to select an option based on Visible text from drop down  
	 * @param DropDown
	 * @param option
	 */
	public void selectElementByVisibleText(WebElement element,String option)
	{
		Select sel=new Select(element);
		sel.selectByVisibleText(option);
	}
	/**
	 * This method is used to perform mouse hover action to particular element  
	 * @param WebDriver reference
	 * @param Target Element reference
	 */
	public void mouseHoverElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
	}
	public void mouseHoverElement(WebDriver driver,WebElement element,int x,int y)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element,x,y).perform();
	}
	/**
	 * This method is used to perform double click action on particular Web element  
	 * @param WebDriver reference
	 * @param Target Element reference	
	 */
	public void doubleClickElement(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.doubleClick(element).perform();
	}
	/**
	 * This method is used to right click upon a particular Web Element 
	 * @param WebDriver reference
	 * @param Target Element reference	
	 */
	public void rightClick(WebDriver driver,WebElement element)
	{
		Actions act=new Actions(driver);
		act.moveToElement(element).perform();
		act.contextClick(element).perform();
	}
	/**
	 * This method is used to perform drag and drop operation based on source web element to target web element locations
	 * @param driver
	 * @param sourceLocation
	 * @param destinationLocation
	 */
	public void dragAndDrop(WebDriver driver,WebElement sourceLocation,WebElement destinationLocation)
	{
		Actions act=new Actions(driver);
		act.dragAndDrop(sourceLocation, destinationLocation).perform();;
	}
	/**
	 * This method is used to switch to window based on url 
	 * @param driver
	 * @param partialUrl
	 */
	public void SwitchToWindowWithUrl(WebDriver driver,String partialUrl)
	{
		Set<String> wtabs = driver.getWindowHandles();
		Iterator<String> it=wtabs.iterator();
		while(it.hasNext())
		{
			String currTab=it.next();
			driver.switchTo().window(currTab);
			if(driver.getCurrentUrl().contains(partialUrl))
			{
				break;
			}
		}
	}
	public void SwitchToWindowWithTitle(WebDriver driver,String partialTitle)
	{
		Set<String> wtabs = driver.getWindowHandles();
		Iterator<String> it=wtabs.iterator();
		while(it.hasNext())
		{
			String currTab=it.next();
			driver.switchTo().window(currTab);
			if(driver.getCurrentUrl().contains(partialTitle))
			{
				break;
			}
		}
	}
	public void javaScriptExecutorMoveAction(WebDriver driver,WebElement element)
	{
		JavascriptExecutor jse=(JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	/**
	 * Accept alert 
	 * @param driver
	 */

	public void acceptAlert(WebDriver driver)
	{
		driver.switchTo().alert().accept();
	}

	/**
	 * Cancel Alert
	 * @param driver
	 */
	public void cancelAlert(WebDriver driver)
	{
		driver.switchTo().alert().dismiss();
	}
	/**
	 * This method used for scrolling action in a web page
	 * @param driver
	 * @param element
	 */
	public void scrollToWebElement(WebDriver driver, WebElement element) {
		JavascriptExecutor js=(JavascriptExecutor)driver;
		int y= element.getLocation().getY();
		js.executeScript("window.scrollBy(0,"+y+")", element);
	}

	/**
	 * This method is used to Switch to frame based on index
	 * @param driver
	 * @param index
	 */
	public void switchFrame(WebDriver driver,int index) {
		driver.switchTo().frame(index);
	}   
	/**
	 * This method is used to switch to frame based on web element
	 * @param driver
	 * @param element
	 * @throws AWTException 
	 */
	public Robot getRobotRef(
			) throws AWTException
	{
		Robot robot=new Robot();
		return robot;
	}
	public void switchFrame(WebDriver driver,WebElement element) {
		driver.switchTo().frame(element);
	} 
	/**
	 * This method is used to switch to frame based on id or name
	 * @param driver
	 * @param idOrName
	 */
	public void switchFrame(WebDriver driver,String idOrName) {
		driver.switchTo().frame(idOrName);
	} 
	/**
	 * This method is used to take screen shot and save it
	 * @param driver
	 * @param screenshotName
	 * @throws Throwable
	 */
	public void takeScreenshot(WebDriver driver, String screenshotName) throws Throwable {
		Date date=new Date();
		
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File dest=new File("./screenshot/"+screenshotName+".PNG");
		Files.copy(src, dest);
	}

	/**
	 * This method is used to perform press Enter key Action 
	 * @param driver
	 */
	public void passEnterKey(WebDriver driver) {
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).perform();
	} 


}
