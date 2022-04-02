package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {

	public static void main(String[] args) throws Exception {
		getConnection();
		createTable();
		insert();	
		fetch();
	}
	
	//This is our method of establishing a connection to the database. I am using a local connection, my local connection is password protected.
		public static Connection getConnection() throws Exception{
			try {
				String driver = "com.mysql.cj.jdbc.Driver";
				String url = "jdbc:mysql://localhost:3306/java-database";
				String username = "root";
				String password = "ENTER PASSWORD HERE";
				Class.forName(driver);
				
				Connection conn = DriverManager.getConnection(url,username,password);
				
				//This will print if the connection is working!
				System.out.println("Connected");
				return conn;
			} catch(Exception e) {System.out.println(e);}
			
			
			return null;
		}
		
		//Method for creating a table in our database.
		public static void createTable() throws Exception {
			try {
				Connection con = getConnection();
				
				//If there isn't a table, create one. In this case a table will be created called tablename, rows called, first, last and the primary key being id.
				PreparedStatement create = con.prepareStatement("CREATE TABLE IF NOT EXISTS tablename(id int NOT NULL AUTO_INCREMENT, first varchar(255), last varchar(255), PRIMARY KEY(id))");
				create.executeUpdate();
				
			} catch(Exception e) {System.out.println(e);}	
				finally {
				
				//Giving us an update on the function
				System.out.println("Data Created.");
			};
		}
		
		
		//Creating variables to add to our table, first and last rows.
		public static void insert() throws Exception {
			final String varfirst = "John";
			final String varlast = "Miller";
			
			try {
				Connection con = getConnection();
				
				//This adds what we just typed above into our table in MySQL.
				PreparedStatement insert = con.prepareStatement("INSERT INTO tablename(first, last) VALUES('"+varfirst+"','"+varlast+"')");
				
				//Adds the information
				insert.executeUpdate();
				
			} catch(Exception e) {System.out.println(e);}
				finally {
				
				//Giving us an update on the function
				System.out.println("Insert Completed.");
			}
			
		}	
	
	//Method of pulling the data over to us.
	public static ArrayList<String> fetch() throws Exception {
		try {
			Connection con = getConnection();
			
			//MySQL mumbo jumbo, we are taking all the data from tablename. LIMIT 1 stops the data from being printed several times.
			PreparedStatement statement = con.prepareStatement("SELECT * FROM tablename LIMIT 1");
			
			//We are taking that data and bringing it to us to read, rather then changing anything
			ResultSet fetch = statement.executeQuery();
			
			//We need something to store the data in, this will be that
			ArrayList<String> array = new ArrayList<String>();
			while(fetch.next()) {
				System.out.print(fetch.getString("first"));
				System.out.print(" ");
				System.out.print(fetch.getString("last"));
				
				array.add(fetch.getString("last"));
			}
			System.out.println("\nAll records have been selected.");
			return array;
		} catch(Exception e) {System.out.println(e);}
			finally {
			
			//Giving us an update on the function
			System.out.println("Data retrieved.");
	}
		return null;
	}
	
}
