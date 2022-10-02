package com.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionClass {
public Connection getConnection() throws Exception
{
	Connection con =null;
	try {
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project", "root", "Root");
	}
	catch(Exception e) {
		e.printStackTrace();
		System.out.println(e.getMessage());
	}
	return con;
}
}
