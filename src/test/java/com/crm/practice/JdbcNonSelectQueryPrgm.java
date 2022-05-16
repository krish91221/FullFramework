package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class JdbcNonSelectQueryPrgm {

	public static void main(String[] args) throws SQLException {
		Connection connect=null;
		//load mysql driver
		Driver dref;
		try {
			dref = new Driver();
			//connect to DB
			DriverManager.registerDriver(dref);
			connect=DriverManager.getConnection("jdbc:mysql://localhost:3306/Students", "root", "root");
			System.out.println("Connection established successfully\n");
			//create query statement
			Statement stmt = connect.createStatement();
			//execute non select query
			int result = stmt.executeUpdate("insert into students_info values(5,'R','Keshava Rao')");
			System.out.println(result);
		}
		catch(Exception e)
		{
			System.out.println("Exception handled and prevented to reach DB");
		}
		finally
		{
			//close connection
			connect.close();
			System.out.println("\nConnection terminated successfully");
		}
	}
}
