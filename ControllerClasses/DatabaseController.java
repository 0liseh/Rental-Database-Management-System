import java.sql.*;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager; //uses static method getConnection(database URL, username, password)
import java.sql.SQLException;
import java.util.*;
import java.lang.*;

public class DatabaseController{
  
	private Vector<Property> properties = new Vector<Property>();
	private Vector<Landlord> landlords = new Vector<Landlord>();
	
	
	private static final String url = "jdbc:mysql://localhost:3306/propertymanagement";
	private static final String username = "root";
	private static final String password = "JesusistheOGM1!";
	
	public static Connection mysql_con;
	private Statement stmt; //object of type statement from JDBC class that enables the creation "Query statements"
	private ResultSet rs;//object of type ResultSet from the JDBC class that stores the result of the query
	
	//Database Controller cstr
	public DatabaseController() {
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
	
	//Gets the list of users 
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

	//Gets the list of all the properties
	public Vector<Property> getProperties(){
		try {
			stmt = mysql_con.createStatement();  
			rs = stmt.executeQuery("select * from property");  
			
			while(rs.next()) {
				
				int propID = rs.getInt("propertyId");
				String propType = rs.getString("propertyType");
				int numOfBed = rs.getInt("numberOfBed");
				int numOfBath = rs.getInt("numberOfBath");
				boolean furn = rs.getBoolean("furnished");
				String area = rs.getString("area");
				String status = rs.getString("status1");
				int llID = rs.getInt("landlordID");
				
				Property temp = new Property(propID, propType, numOfBed, numOfBath, furn, area, status, llID);
				properties.add(temp);
			}
			
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
		return properties;
	}

	//Gets the properties based on the landlord ID
	public Vector<Property> getMyProperties(int lID){
		try {
			stmt = mysql_con.createStatement();  
			rs = stmt.executeQuery("select * from property");  
			
			while(rs.next()) {

				if(lID == rs.getInt("landlordID")){
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
			
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
		return properties;
	}

	//sends a list of landlords
	public Vector<Landlord> getLandlords(){
		return landlords;
	}
	
	public Vector<Property> getStatusProperties(String stat){
		Vector<Property> prop = new Vector<Property>();
		try {
			stmt = mysql_con.createStatement();  
			rs = stmt.executeQuery("select * from property");  
			
			while(rs.next()) {

				if(stat.equals(rs.getString("status1"))){
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
			
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
		return prop;
		
	}
	
	public Vector<Property> getSearchedProperties(String type, String numBed, String numBath , String furnished, String location){
        Vector<Property> searched = new Vector<Property>();
        int bedNum = Integer.parseInt(numBed);
        int bathNum = Integer.parseInt(numBath);
        boolean f = Boolean.parseBoolean(furnished);

        try {
            stmt = mysql_con.createStatement();  
            rs = stmt.executeQuery("select * from property");  
            
            while(rs.next()) {

                int propID = rs.getInt("propertyId");
                String propType = rs.getString("propertyType");
                int numOfBed = rs.getInt("numberOfBed");
                int numOfBath = rs.getInt("numberOfBath");
                boolean furn = rs.getBoolean("furnished");
                String area = rs.getString("area");
                String status = rs.getString("status1");
                int llID = rs.getInt("landlordID");


                if((type.equals(propType) || type.equals("-------")) && (numBed.equals("-------") || bedNum == numOfBed) && (numBath.equals("-------") || bathNum == numOfBath) &&
                        (furnished.equals("-------") || Boolean.compare(f , furn) == 0) && (location.equals("-------") || location.equals(area)) ){
                    Property temp = new Property(propID, propType, numOfBed, numOfBath, furn, area, status, llID);
                    searched.add(temp);
                }
                
                
                
                
            }
            
        }
        catch(Exception e){ 
            System.out.println(e);
        }  

        return searched;
    }
	
	
    public void addItems(String table, ArrayList<String> attributes) {
        try {
            String query = "INSERT INTO " + table + " VALUES (";
            for(int i = 0; i < attributes.size(); i++){
                if(i != attributes.size()-1){
                    query += attributes.get(i) + ", ";
                }else{
                    query += attributes.get(i) + ")";
                }
            }
            PreparedStatement preparedStatment = mysql_con.prepareStatement(query);

            preparedStatment.executeUpdate();
            preparedStatment.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
	
	
	
	
	
	public int TotalPropertyPostedInPeriod(String start_date, String end_date)  // date must be of form: dd-mm-yyyy
    {
        int counter=0;
        try {
            stmt = mysql_con.createStatement();
            rs = stmt.executeQuery("select * from property");
            var startDate = start_date.split("-");
            var newStartDate = (new Date(Integer.parseInt(startDate[2]),Integer.parseInt(startDate[1])-1, Integer.parseInt(startDate[0]))).getTime();
            var endDate = end_date.split("-");
            var newEndDate = (new Date(Integer.parseInt(endDate[2]),Integer.parseInt(endDate[1])-1, Integer.parseInt(endDate[0]))).getTime();
            while(rs.next())
            {

                String date_posted = rs.getString("datePosted");
                var datePosted = date_posted.split("-");
                var newDatePosted = (new Date(Integer.parseInt(datePosted[2]),Integer.parseInt(datePosted[1])-1, Integer.parseInt(datePosted[0]))).getTime();
                if(newStartDate<newDatePosted && newEndDate>newDatePosted)
                {
                    counter+=1;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return counter;
    }

    public int TotalPropertyRentedInPeriod(String start_date, String end_date)  // date must be of form: dd-mm-yyyy and status should be rented
    {
        int counter=0;
        try {
            stmt = mysql_con.createStatement();
            rs = stmt.executeQuery("select * from property");
            var startDate = start_date.split("-");
            var newStartDate = (new Date(Integer.parseInt(startDate[2]),Integer.parseInt(startDate[1])-1, Integer.parseInt(startDate[0]))).getTime();
            var endDate = end_date.split("-");
            var newEndDate = (new Date(Integer.parseInt(endDate[2]),Integer.parseInt(endDate[1])-1, Integer.parseInt(endDate[0]))).getTime();
            while(rs.next())
            {

                String date_posted = rs.getString("dateRented");
                var dateRented = date_posted.split("-");
                var newDateRented = (new Date(Integer.parseInt(dateRented[2]),Integer.parseInt(dateRented[1])-1, Integer.parseInt(dateRented[0]))).getTime();
                if(newStartDate<newDateRented && newEndDate>newDateRented)
                {
                    counter+=1;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return counter;
    }
	
	public void sendMailToLandlord(String message, int propertyId)  // adds a message in the messages table for the landlord of that property
    {
        try {
            stmt = mysql_con.createStatement();
            rs = stmt.executeQuery("select * from property");

            while(rs.next())
            {
                if(rs.getInt("propertyId") == propertyId)
                {
                    String sql = "INSERT INTO messages (landlordID, message)" +
                            "VALUES (?, ?)";
                    PreparedStatement preparedStatement = mysql_con.prepareStatement(sql);
                    preparedStatement.setInt(1, rs.getInt("landlordID"));
                    preparedStatement.setString(2, message);
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public Vector<String> getAllMails(int landlord_id)  // adds a message in the messages table for the landlord of that property
    {
        Vector<String> result = new Vector<>();
        try {
            stmt = mysql_con.createStatement();
            rs = stmt.executeQuery("select * from messages");

            while(rs.next())
            {
                if(rs.getInt("landlordID") == landlord_id)
                {
                    result.add(rs.getString("message"));
                }
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        return result;
    }
	
}
