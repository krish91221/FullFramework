package com.crm.practice;

import org.testng.annotations.Test;
import org.testng.internal.IInvocationStatus;

public class TaskTest {
	@Test(priority=0,dependsOnMethods="secondMethodTest")
	public void firstMethodTest()
	{
		int i=1/0;
		System.out.println("First Method Called");
	}
	@Test(priority=1,dependsOnMethods="thirdMethodTest",invocationCount = -1)
	public void secondMethodTest()
	{
		System.out.println("Second Method Called");
	}
	@Test(priority=2)
	public void thirdMethodTest()
	{
		System.out.println("Third Method Called");
	}
}
