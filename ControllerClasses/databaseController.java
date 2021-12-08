package ControllerClasses;

import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager; //uses static method getConnection(database URL, username, password)
import java.sql.SQLException;
import java.util.*;

public class DatabaseController{
  
	private static final String url = "jdbc:mysql://localhost:3306/propertymanagement";
	private static final String username = "root";
	private static final String password = "JesusistheOGM1!";
	public static Connection mysql_con;
	
	public DatabaseController(){}
	public void addUser() {}
	public void removeUser() {}
	
	
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			mysql_con = DriverManager.getConnection(url,username,password);
			
			//here propertyamanagement is database name, root is username and JesusistheOGM1! is password  
			Statement stmt=mysql_con.createStatement();  
			ResultSet rs=stmt.executeQuery("select * from user");  
			
			while(rs.next())  
				System.out.println(rs.getString("username")+"\t\t\t\t\t"+rs.getInt("id")+"\t\t\t\t"+rs.getString("email")+"\t\t\t\t"+rs.getString("type1"));  				
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
	}
}
