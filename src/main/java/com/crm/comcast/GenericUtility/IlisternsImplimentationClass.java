package com.crm.comcast.GenericUtility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestNGListener;
import org.testng.ITestResult;
import org.testng.internal.annotations.IListeners;

public class IlisternsImplimentationClass implements ITestListener,IRetryAnalyzer{

	public void onTestFailure(ITestResult result)
	{
		JavaUtility jLib=new JavaUtility();
		String testName=result.getMethod().getMethodName();
		EventFiringWebDriver edriver=new EventFiringWebDriver(BaseAnnotaionConfigClass.sdriver);
		File src=edriver.getScreenshotAs(OutputType.FILE);
		File dst=new File("./screenshot/"+testName+"_"+jLib.getTimeStamp()+".png");
		System.out.println(dst);
		try 
		{
			FileUtils.copyFile(src, dst);
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	}
	
	public boolean retry(ITestResult result) 
	{
		int counter=0;
		int retryLimit=4;
		boolean flag=false;
		if(counter<retryLimit)
		{
			counter++;
			flag=true;
			return flag;
		}
		flag=false;
		return flag;
	}

}
