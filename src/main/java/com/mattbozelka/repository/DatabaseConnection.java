package com.mattbozelka.repository;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mattbozelka.model.*;


public class DatabaseConnection {
	public static boolean driverLoaded = false;
	public static boolean databaseConnected = false;
	public static Connection conn;
	
	public DatabaseConnection(){
		if (!driverIsLoaded()){loadDriver();}
		if (!databaseIsConnected()){connectToDatabase();}
	}
	
	 public static void loadDriver() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
        	//com.mysql.jdbc.Driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            driverLoaded=true;
        } catch (Exception ex) {
            // handle the error
        	
        	//TODO: delete after testing
        	System.out.println("Error loading driver.");
        	System.out.println(ex.toString());
        	
        	driverLoaded=false;
        }
    }
	 
	public void connectToDatabase() {
		
			try {

				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cleanupstars","root","password1");
				
				databaseConnected=true;
	        } catch (Exception ex) {
	            // handle the error
	        	
	        	//TODO: delete after testing
	        	System.out.println("Error connecting to database.");
	        	System.out.println(ex.toString());
	        	
	        	databaseConnected=false;
	        }
	}
	

	public boolean recordExists(String query){

		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = conn.createStatement();
		    rs=stmt.executeQuery(query);
		    if (!rs.next()) {
		    	return false;
		    }
		    return true;
		} catch (Exception ex) {
			//System.out.println(ex.getMessage());
			return false;
		} finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }
		    
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}
	
	public boolean updateTable(String query){

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		    stmt.executeUpdate(query);
		    return true;
		} catch (Exception ex) {
			return false;
		} finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}
	

	public String[] getRecord(String query) {

		Statement stmt = null;
		ResultSet rs = null;
		String[] rows = {};
		try {

		    stmt = conn.createStatement();
		    String sql = query; 
		    rs = stmt.executeQuery(sql);
		    
		    ResultSetMetaData metadata = rs.getMetaData();
		    int numberOfColumns = metadata.getColumnCount();
		    rows = new String[numberOfColumns]; 
		    //TODO: add validation of only 1 record returned
		    while (rs.next()) {              
		        int i = 1;
		        while(i <= numberOfColumns) {
		        	rows[i-1] = rs.getString(i);
		        	
			        //TODO: delete after testing
			        //System.out.print(rs.getString(i) + " | ");
		        	
			        i++;
		        	
		        }
		        
		        //TODO: delete after testing
		        //System.out.println();
		    }
		    
		    return rows;
		} catch (Exception ex) {
            // handle the error
			
			//System.out.println(ex.toString());
			
			return rows;
        } finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
		    
	}
	
	
	
	
	
	public ArrayList<String[]> getQueryResults(String query) {

		Statement stmt = null;
		ResultSet rs = null;
		try {

		    ArrayList<String[]> data = new ArrayList();
		    stmt = conn.createStatement();
		    String sql = query; 
		    rs = stmt.executeQuery(sql);
		    
		    ResultSetMetaData metadata = rs.getMetaData();
		    int numberOfColumns = metadata.getColumnCount();
		    //System.out.println(numberOfColumns);
		    while (rs.next()) {              
		        int i = 1;
		        String[] rows = new String[numberOfColumns]; 
		        while(i <= numberOfColumns) {
		        	rows[i-1] = rs.getString(i);
		        	
			        //TODO: delete after testing
			        //System.out.print(rs.getString(i) + " | ");
		        	
			        i++;
		        	
		        }
		        data.add(rows);
		        
		        //TODO: delete after testing
		        //System.out.println();
		    }
		    
		    return data;
		} catch (Exception ex) {
            // handle the error
			
			//System.out.println(ex.toString());
			
			return new ArrayList<String[]>();
        } finally {
		    // it is a good idea to release
		    // resources in a finally{} block
		    // in reverse-order of their creation
		    // if they are no-longer needed

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } // ignore

		        rs = null;
		    }

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
		    
	}
	
	public Long handleLongNulls(String str){
		if (str==null || str==""){
			return 0L;
		} else {
			return Long.parseLong(str);
		}
	}
	
	public int handleIntNulls(String str){
		if (str==null || str==""){
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}
	
	public String handleStrNulls(String str){
		if (str==null){
			return "";
		} else {
			return str;
		}
	}
	
	 private boolean driverIsLoaded(){
		 return driverLoaded;
	 }
	 private boolean databaseIsConnected(){
		 return databaseConnected;
	 }
	 public void closeConnection(){
		 try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	 }
	
}
   
	