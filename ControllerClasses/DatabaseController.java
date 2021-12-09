//uses static method getConnection(database URL, username, password)
import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.lang.*;

public class DatabaseController{
  
	private Vector<Property> properties = new Vector<Property>();
	private Vector<Landlord> landlords = new Vector<Landlord>();
	private Vector<RegisteredRenter> renters = new Vector<RegisteredRenter>();
	private Vector<Property> dud = new Vector<Property>();
	
	private static final String url = "jdbc:mysql://localhost:3306/propertymanagement";
	private static final String username = "root";
	private static final String password = "kaboomy";
	
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
	public Vector<Integer> getMyProperties(int lID){
		Vector<Integer> landlordProperties = new Vector<Integer>();
		try {
			stmt = mysql_con.createStatement();  
			rs = stmt.executeQuery("select * from property");  
			while(rs.next()) {
				System.out.println("In getMyproperties while loop");
				if(lID == rs.getInt("landlordID")){
					//System.out.println("Found landlord properties");
					int propID = rs.getInt("propertyId");
					/*String propType = rs.getString("propertyType");
					int numOfBed = rs.getInt("numberOfBed");
					int numOfBath = rs.getInt("numberOfBath");
					boolean furn = rs.getBoolean("furnished");
					String area = rs.getString("area");
					String status = rs.getString("status1");
					int llID = rs.getInt("landlordID");
					
					Property temp = new Property(propID, propType, numOfBed, numOfBath, furn, area, status, llID);
					landlordProperties.add(temp);*/
					landlordProperties.add(propID);
				}
				
			}
			
		}
		catch(Exception e){ 
			System.out.println(e);
		}  
		return landlordProperties;
	}

	//sends a list of landlords
	public Vector<Landlord> getLandlords(){
		try {
			String type = "Landlord";
			stmt = mysql_con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from user");
			landlords.clear(); 
			while(rs.next()) {
				System.out.println("In getLandlords while loop");
				if(type.equals(rs.getString("type1"))){
					Landlord temp = new Landlord(rs.getString("username"), rs.getInt("id"), rs.getString("email"), rs.getString("phoneNumber"), rs.getString("password1"), dud);//getMyProperties(rs.getInt("id")));
					landlords.add(temp);
				}
			}
			System.out.println("Outside getLandlords while loop");
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		return landlords;
	}
	
	public int checkUser(String email, String password, String type) {
		System.out.println("System is in checkUser");
		try {
			stmt = mysql_con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from user");
			//renters.clear(); 
			while(rs.next()) {
				System.out.println("System is checking for user in database");
				if(type.equals(rs.getString("type1")) && email.equals(rs.getString("email")) && password.equals(rs.getString("password1"))){
					return rs.getInt("id"));
				}
			}
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		System.out.println("System is done with checkUser");
		return -1;
	}
	
	public Vector<RegisteredRenter> getRegisteredRenters(){
		try {
			String type = "Renter";
			stmt = mysql_con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("select * from user");
			renters.clear(); 
			while(rs.next()) {
				if(type.equals(rs.getString("type1"))){
					RegisteredRenter temp;
					temp = new RegisteredRenter(rs.getString("username"), rs.getInt("id"), rs.getString("email"), rs.getString("phoneNumber"), rs.getString("password1"), 0); //getNotification(rs.getInt("id")));
					renters.add(temp);
				}
			}
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		return renters;
	}
	
	public int getNotification(int id){
		try {
			stmt = mysql_con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs = stmt.executeQuery("SELECT * FROM NOTIFICATION");
			while(rs.next()) {
				if(id == rs.getInt("id")){
					return rs.getInt("notification");
				}
        }
		} catch (SQLException ex) {
            ex.printStackTrace();
        }
		System.out.println("Registered Renter was not found");
		return -1;
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
					prop.add(temp);
		
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
            System.out.println(query);
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
    
    //Manager can set a fee
    public void setFee(double feeAmount, int feeDuration) {

    	 try {
 			String query = "INSERT INTO FEE (fee, duration) values (?,?)";
 			PreparedStatement pStat = mysql_con.prepareStatement(query);
 			pStat.setDouble(1, feeAmount);
 			pStat.setInt(2, feeDuration);
 			pStat.executeUpdate();
 			pStat.close();
 		} catch (SQLException e) {
 			e.printStackTrace();
 		}
    	
    }
    
  //Landlords can get a fee
    public Vector<String> getFee() {
    	Vector<String> feeStr = new Vector<String>();
    	 try {
             stmt = mysql_con.createStatement();
             rs = stmt.executeQuery("select * from FEE");
             
             while(rs.next()) {
            	 feeStr.add( "Fee amount: " + rs.getDouble("fee") + " for " + rs.getInt("duration") + " days");
             }
    	 }catch(Exception e) {
             System.out.println(e);
    	 }
    	 System.out.println(feeStr);
		return feeStr;
    	
    }
	
}
