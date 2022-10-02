package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class MainTest {
	
		public  void storeTable() throws SQLException {
			
			  Scanner sc=new Scanner(System.in);
			  
        	   System.out.println("Enter first name >>"); 
  			String Name = sc.next(); 
  			System.out.println("Enter roll no  >>"); 
  			String RollNo = sc.next();
  			
  			//calling method and correct answer count will be stored in count variable
  			RetriveQuestions obj1=new RetriveQuestions();
  			int count = obj1.startQuiz();
  			
  			try
  			{    ConnectionClass cc=new ConnectionClass();
  				 Connection con = cc.getConnection();
  				PreparedStatement stmt = con.prepareStatement("insert into result(studentName,rollNumber,score,Grade)values(?,?,?,?)");
  			//inserting parameter in query
  				stmt.setString(1,Name);
  				stmt.setString(2,RollNo);
  				stmt.setLong(3,count);
  			//condition for checking grade
  				if(count>=8 && count<=10)
  				{
  					stmt.setString(4,"A");
  				}
  				else if(count>=6 && count<=8)
  				{
  					stmt.setString(4,"B");
  				}
  				else if(count==5)
  				{
  					stmt.setString(4,"C");
  				}
  				  else 
  				{
  					stmt.setString(4,"fail");
  				}
  				
  				System.out.println();
  				int i = stmt.executeUpdate();
  				System.out.println("Record inserted  "+i);
  				System.out.println();
  				con.close();
  				stmt.close();
  				
  			}
  			catch(Exception e)
  			{
  				System.out.println(e);
  			}
  			}

		public  void getTable()//get table from database 
		{
			try {//loading driver class
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Root");
				PreparedStatement ps = con.prepareStatement("select* from result order by score Desc limit 0,1000 ");
				ResultSet rs = ps.executeQuery();
				System.out.println("CLASS RESULT TABLE>>");
				System.out.println();
				
				while(rs.next())
				{
					System.out.println("RollNumber="+rs.getInt(1));
					System.out.println("StudentName="+rs.getString(2));
					System.out.println("Grade="+rs.getString(3));
					System.out.println("score="+rs.getString(4));
					System.out.println();
				}
				con.close();
				ps.close();
				rs.close();
				
			}catch(Exception e)
			{    e.printStackTrace();
				
			}
		}

        public static void main(String[] args) throws SQLException {
        	  MainTest mt=new MainTest();
        	  Scanner sc=new Scanner(System.in);
        	  System.out.println("Enter number of student");
        	  int a=sc.nextInt();
        	 for(int i=a;i>0;i--)
        	  
              {//method for start quiz for student and storing data in result table
        	      mt.storeTable();
        	  }
  			
  		  //method for to check the Class Result and also check perticular student result from roll number
        	        ResultCheck obj=new ResultCheck();
        	        obj.getResult();
  			     
        	  
}
}