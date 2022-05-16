package com.crm.practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm.comcast.GenericUtility.FileUtitlty;
import com.crm.comcast.Pom_Repo.LogInPage;

public class LoginTest {
public static void main(String[] args)   {
	try {
		FileUtitlty flib=new FileUtitlty();
		String url=flib.getKeyValue("url");
	WebDriver driver=new FirefoxDriver();
	LogInPage login=new LogInPage(driver);
	login.logInToApp();
	}
	 catch (Throwable e) {
		
		e.printStackTrace();
	}
}
}
