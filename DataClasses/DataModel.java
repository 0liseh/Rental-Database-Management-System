import java.util.*;
import java.sql.*;


public class DataModel {
	private final String DBURL;
	private final String USERNAME;
	private final String PASSWORD;
	private Connection connection;
	private ResultSet results;
	private Statement statement;
	private PreparedStatement preparedStatment;
	private ArrayList<String> columns;
	private int rows = -1;
	private ArrayList<String> availableTables;
	private HashMap<String, ArrayList<HashMap<String, String>>> tables;

	public DataModel(String DBURL, String USERNAME, String PASSWORD) {
		this.DBURL = DBURL;
		this.USERNAME = USERNAME;
		this.PASSWORD = PASSWORD;
		try {
			initializeConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		availableTables = new ArrayList<String> ();
		populateAvailableTables();

		tables = new HashMap<String, ArrayList<HashMap<String, String>>> ();
		for (int i = 0; i<availableTables.size(); i++) {
			tables.put(availableTables.get(i), retrieveData(availableTables.get(i)));
		}
	}

	private void populateAvailableTables() {
		try {
			statement = connection.createStatement();
			results = statement.executeQuery("Show tables");

			while (results.next()) {
				availableTables.add(results.getString(1).toLowerCase());
			}
			statement.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public String getDburl() {
		return this.DBURL;
	}

	public String getUsername() {
		return this.USERNAME;
	}

	public String getPassword() {
		return this.PASSWORD;
	}

	public Connection getconnection() {
		return this.connection;
	}

	public int getRows() {
		return this.rows;
	}

	public ArrayList<String> getColumns() {
		return this.columns;
	}


	public ArrayList<String> getAvailableTables() {
		return this.availableTables;
	}

	private
	void initializeConnection() throws SQLException {

		connection = DriverManager.getConnection(this.DBURL, this.USERNAME, this.PASSWORD);
	}

	public ArrayList<HashMap<String, String>> retrieveData(String tableName) {

		ArrayList<HashMap<String, String>> table = null;
		try {

			ArrayList<String> columns = new ArrayList<String>();
			table = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> parts = new HashMap<String, String>();

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			results = statement.executeQuery("SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` " + "WHERE `TABLE_SCHEMA`= 'PROPERTYMANAGEMENT' AND `TABLE_NAME` = '" + tableName + "'");

			if (results.next() == false) {
				statement.close();
				throw new IllegalArgumentException("invalid table");
			}

			results.previous();

			while (results.next()) {
				columns.add(results.getString("COLUMN_NAME")); 
			}

			this.columns = columns;

			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			results = statement.executeQuery("SELECT * FROM " + tableName);

			int rows = 0;

			if (results.next() == false) {
				statement.close();
				throw new IllegalArgumentException("invalid table type");
			}

			results.previous(); 

			
			while (results.next()) {
				parts = new HashMap<String, String> ();

				for (String column: columns) {
					parts.put(column, results.getString(column));
				}

				rows += 1;
				table.add(parts);
			}

			this.rows = rows;

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return table;
	}


    public void addItems(ArrayList<String> attributes) {
        try {
            String query = "INSERT FROM " + table + " VALUES (";
            for(String attribute:attributes){
                query += attribute + ", ";
            }
            query += ")";
            preparedStatment = connection.prepareStatement(query);

            preparedStatment.setString(1, id); 

            preparedStatment.executeUpdate();
            preparedStatment.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}

	public void deleteItems(String id, String table) {
        try {
            String query = "DELETE FROM " + table + " WHERE id = ?";
            preparedStatment = connection.prepareStatement(query);

            preparedStatment.setString(1, id); 

            int rowCount = preparedStatment.executeUpdate();
            preparedStatment.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}


	public void close() {
		try {
			if (results != null) {
				results.close();
			}
			if (connection != null) {
				connection.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}