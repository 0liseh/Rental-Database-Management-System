import DataClasses.Property;

import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager; //uses static method getConnection(database URL, username, password)
import java.sql.SQLException;
import java.util.*;
import java.lang.*;

public class DatabaseController{
  
	private Vector<Property> properties = new Vector<Property>();
	
	private static final String url = "jdbc:mysql://localhost:3306/propertymanagement";
	private static final String username = "root";
	private static final String password = "JesusistheOGM1!";
	
	public static Connection mysql_con;
	private Statement stmt; //object of type statement from JDBC class that enables the creation "Query statements"
	private ResultSet rs;//object of type ResultSet from the JDBC class that stores the result of the query
	
	public void DatabaseController() {
		try {
            //Register JDBC driver
			Driver driver = new com.mysql.cj.jdbc.Driver();
			DriverManager.registerDriver(driver);
            //Open a connection
			mysql_con = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			System.out.println("Problem");
			e.printStackTrace();
		}

	}
	
	public void getUsers() {
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			stmt = mysql_con.createStatement();  
			rs = stmt.executeQuery("select * from user");  
			
			while(rs.next())  
				System.out.println(rs.getString("username")+"\t\t\t\t\t"+rs.getInt("id")+"\t\t\t"+rs.getInt("password1"));
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
	}
	
	public void addUser() {}
	public void removeUser() {}
	public void addProperty() {}
	public void removeProperty() {}
	
	public void getProperties() {
		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");
			stmt = mysql_con.createStatement();  
			rs = stmt.executeQuery("select * from property");  
			
			while(rs.next()) {
				Property temp;
				int propID = rs.getInt("propertyId");
				String propType = rs.getString("propertyType");
				int numOfBed = rs.getInt("numberOfBed");
				int numOfBath = rs.getInt("numberOfBath");
				boolean furn = rs.getBoolean("furnished");
				String area = rs.getString("area");
				String status = rs.getString("status1");
				int llID = rs.getInt("landlordID");
				
				temp = new Property(propID, propType, numOfBed, numOfBath, furn, area, status, llID);
				properties.add(temp);
			}
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
	}
}
