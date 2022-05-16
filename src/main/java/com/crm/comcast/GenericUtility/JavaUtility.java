package com.crm.comcast.GenericUtility;

import java.util.Date;
import java.util.Random;

/**
 * This class contains Java specific common Functionalities
 * @author Murali Krishna
 *
 */
public class JavaUtility {
	/**
	 * This method is used to generate and return random numbers 
	 * @return  int randomNumber
	 */
	public int getRandomNumber()
	{
		Random random=new Random();
		return random.nextInt(9999);
	}
	/**
	 * This method is used to get current date in Indian Standard Time
	 * @return DD-MM-YYYY
	 */
	public String getSysDateInISD()
	{
		Date date=new Date();
		String d=date.toString();
		String[] sarr = d.split(" ");
		String day=sarr[2];
		String year=sarr[5];
		int month=date.getMonth()+1;
		return day+"-"+month+"-"+year;
	}
	/**
	 * This method is used to get current date in required format for  current application
	 * @return YYYY-MM-DD
	 */
	public String getSysDate()
	{
		Date date=new Date();
		String d=date.toString();
		String[] sarr = d.split(" ");
		String day=sarr[2];
		String year=sarr[5];
		int month=date.getMonth()+1;
		return year+"-"+month+"-"+day;
	}
	public String getTimeStamp()
	{
		Date date=new Date();
		String d=date.toString();
		d=d.replace(" ", "_").replace(":", "_");
		System.out.println(d);
		return d;
	}
}
