package com.crm.practice;

import static org.testng.Assert.fail;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.crm.comcast.GenericUtility.BaseAnnotaionConfigClass;
import com.crm.comcast.Pom_Repo.HomePage;
@Listeners(com.crm.comcast.GenericUtility.IlisternsImplimentationClass.class)
public class AssertSampleTest  extends BaseAnnotaionConfigClass{
	@Test
	public void sampleAssertTest()
	{
		SoftAssert sa=new SoftAssert();
		sa.assertEquals("a", "b", "a!=b");
		sa.assertAll();
		Assert.fail();
		System.out.println("hard Assert");
	}
	@Test
	public void verifyHomepageTest()
	{
		HomePage hp=new HomePage(driver);
		
		String hpTitle=driver.getTitle();
		Assert.assertEquals(hpTitle.trim()," Administrator - Home - vtiger Commercial Open Source CRM".trim());
	}

}
