package com.crm.practice;

import java.sql.ResultSet;
import java.util.Date;

import com.crm.comcast.GenericUtility.JdbcUtility;

public class ExecuteQueryJdbcTest {
	public static void main(String[] args) throws Throwable {
		JdbcUtility dbutil=new JdbcUtility();
		dbutil.connectToDataBase();
		 ResultSet tdata = dbutil.executeQuery("select * from students_info");
		while(tdata.next()) 
		{
		  System.out.println(tdata.getInt(1)+"\t"+tdata.getString(2)+"\t"+tdata.getString(3)); 
		}
	}

}
