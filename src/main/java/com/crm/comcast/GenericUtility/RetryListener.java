package com.crm.comcast.GenericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListener implements IRetryAnalyzer{
	int counter=0;
	int retryLimit=4;
	public boolean retry(ITestResult result) {
		
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
