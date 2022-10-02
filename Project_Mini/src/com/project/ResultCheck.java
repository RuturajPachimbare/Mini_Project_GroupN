package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class ResultCheck {
	
  
	     public void getResult() throws SQLException{
		   Scanner sc=new Scanner(System.in);
           {
			   MainTest mt=new MainTest();
			   mt.getTable();//to get result table from database
			   
			   Connection con =null;
			   PreparedStatement ps =null;
			   ResultSet rs =null;
		try {
			   ConnectionClass cc=new ConnectionClass();
			   con=cc.getConnection();
		      System.out.println("Enter rollNumber to get student result:");
			   int i=sc.nextInt();
			//query for checking result from roll number
			 ps = con.prepareStatement("select * from result where rollNumber= ?; ");
			 ps.setInt(1, i);
			 
	      rs = ps.executeQuery();
			
			while(rs.next())
			{
				System.out.println("RollNumber="+rs.getInt(1));
				System.out.println("Name="+rs.getString(2));
				System.out.println("Grade="+rs.getString(3));
				System.out.println("score="+rs.getString(4));
				System.out.println();
			}
		}
		  catch(Exception e)
		    {   
			  e.printStackTrace();
			}
		finally {
			con.close();
			ps.close();
			rs.close();
		}
	
	}
	
	   
}
    }
