package com.crm.comcast.GenericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
/**
 * This class provides property file specific common functionality
 * @author Murali Krishna
 *
 */
public class FileUtitlty {
	/**
	 * This method is used to read data from properties file based on key it will return the value of the key
	 * @param Key
	 * @return value
	 * @throws Throwable 
	 */
	public String getKeyValue(String Key) throws Throwable
	{
		FileInputStream fis=new FileInputStream(IConstants.PROPS_FILE_PATH);
		Properties p=new Properties();
		p.load(fis);
		return p.getProperty(Key);
	}
}
