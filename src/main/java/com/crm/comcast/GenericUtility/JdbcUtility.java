package com.crm.comcast.GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;



/**
 * This class consists of Database specific libraries
 * @author Murali Krishna
 *
 */
public class JdbcUtility {

	static Connection conn=null;
	static Driver driver=null;
	/**
	 * This method is used to establish connection with MySql specified database
	 * @param dbName
	 * @throws Throwable
	 */
	public void connectToDataBase()
	{
		try
		{
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			conn=DriverManager.getConnection(IConstants.JDBC_DB_URL,IConstants.DB_USERNAME,IConstants.DB_PASSWORD);
		}
		catch(Exception e)
		{
			e.printStackTrace();

		}
	}
	/**
	 * This method is used to execute Select Query by taking the query parameter
	 * @param query
	 * @return ResultSet reference
	 * @throws Throwable
	 */
	public ResultSet executeQuery(String query) 
	{
		ResultSet tdata=null;
		try
		{
			Statement stmt=conn.createStatement();
			tdata= stmt.executeQuery(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return tdata;
	}

	/**
	 * This method is used to execute Non select query by taking the query parameter
	 * @param query
	 * @return int result 
	 * @throws Throwable
	 */
	public int executeUpdate(String query)
	{
		int result=0;
		try
		{
			Statement stmt=conn.createStatement();
			stmt.executeUpdate(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * This method is used to verify single data from database and expected data
	 * @param query
	 * @param columnIndex
	 * @param expectedData
	 * @return boolean flag
	 */
	public boolean verifyData(String query,int columnIndex,String expectedData)
	{
		boolean flag=false;
		try
		{
			ResultSet tdata = executeQuery(query);
			if(tdata.getString(columnIndex).trim().equals(expectedData.trim()))
			{
				System.out.println("verification is successful -->passed");
				flag=true;
			}
			else
			{
				System.out.println("verification is not successful -->failed");
				flag=false;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * This method is used to close the connection with the Data base
	 * @throws Throwable 
	 * @throws Throwable
	 */
	public void closeConnection() throws Throwable
	{
		try {
			conn.close();
		}
		finally
		{
			try
			{}
			catch(Exception e)
			{
				e.printStackTrace();
				conn.close();
			}
		}
	}
}

