package com.crm.practice;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
@Listeners(com.crm.comcast.GenericUtility.IlisternsImplimentationClass.class)
public class RtryAnalyserTest extends BaseAnnotaionConfigClass{
	@Test(retryAnalyzer=com.crm.comcast.GenericUtility.RetryListener.class)
	public void failedTestCase()
	{
		Assert.assertEquals("a", "B");
	}

}

