package com.crm.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;



public class JdbcSelectQueryPrgm {
	public static void main(String[] args) throws SQLException {
		 Connection connect=null;
		//load mysql driver
		 try {
			Driver dref = new Driver();
			DriverManager.registerDriver(dref);
			//connect to database
			connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/students", "root", "root");
			System.out.println("connection established with DB successfully\n"); 
			//create query statement
			Statement stmt = connect.createStatement();
			//execute query
			ResultSet restab = stmt.executeQuery("select * from students_info");
			while(restab.next())
			{
				System.out.println(restab.getInt(1)+"\t"+restab.getString(2)+"\t"+restab.getString(3));
			}
		}
		catch (SQLException e) {
			System.out.println("Exception handled and restricted to reach DB");
			e.printStackTrace();
		}
		finally
		{
			//close the connection
			connect.close();
			System.out.println("\nConnection is terminated successfully");

		}
	}
}