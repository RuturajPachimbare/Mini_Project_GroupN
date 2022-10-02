package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RetriveQuestions {
//method for retriving questions from quiz table and we get return how many questions correctly solved by student.
	public int startQuiz() throws SQLException
	{    int  countCorrectAns=0;
	
	 Connection con =null;
	   PreparedStatement ps =null;
	   ResultSet rs =null;
	try {   ConnectionClass cc=new ConnectionClass();
		
		  con=cc.getConnection();
		 ps = con.prepareStatement("select questions,optionA,optionB,optionC,optionD,answer  from quiz ORDER BY RAND() LIMIT 10");
	    rs = ps.executeQuery();
	    
		 int i=1;
		while(rs.next()&&i<=10)
		{  
			System.out.println("question::"+i+":"+rs.getString(1));
			System.out.println("A."+rs.getString(2));
			System.out.println("B."+rs.getString(3));
			System.out.println("C."+rs.getString(4));
			System.out.println("D."+rs.getString(5));
			
			System.out.println();
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Ans(lowerCaseOnly)>>");
			
			
			String ans = sc.next();
			String y = rs.getString(6);
			System.out.println(y);
			if(ans.equals(y))
			{   
				countCorrectAns++;
			}
			
			i++;
		}
		 
	   System.out.println("correct ans>>"+countCorrectAns);
	
	    }catch(Exception e)
	{
		e.printStackTrace();
	}
	finally {

		con.close();
		ps.close();
		rs.close();
		
	}
	return  countCorrectAns;
	

}
}