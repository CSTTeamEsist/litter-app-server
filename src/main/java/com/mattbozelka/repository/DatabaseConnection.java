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
//REQ13
public class DatabaseConnection {
	
	public static Connection conn;
	
	public DatabaseConnection(){
		loadDriver();
		connectToDatabase();
		
	}
	
	 public static void loadDriver() {
        try {
            // The newInstance() call is a work around for some
            // broken Java implementations
        	// Load driver if not yet loaded
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
        	//return false if driver cannot be loaded
        }
    }
	
	public void connectToDatabase() {
		
			try {
				//Set database connection
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cleanupstars","root","password1");
				//return true if connection successful
	        } catch (Exception ex) {
	        	//return false if cannot connect to database
	        }
	}
	
	//query database to see if record exists
	//return true if exists false if not or on error.
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
			return false;
		} finally {

		    if (rs != null) {
		        try {
		            rs.close();
		        } catch (SQLException sqlEx) { } 

		        rs = null;
		    }
		    
		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } 

		        stmt = null;
		    }
		}
	}
	
	//Execute an update or insert query
	//if successful return true
	//false if not or on error.
	public boolean updateTable(String query){

		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		    stmt.executeUpdate(query);
		    return true;
		} catch (Exception ex) {
			return false;
		} finally {

		    if (stmt != null) {
		        try {
		            stmt.close();
		        } catch (SQLException sqlEx) { } // ignore

		        stmt = null;
		    }
		}
	}
	
	//return a string array with values of one record
	//from passed query.
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
			        i++;
		        }
		    }
		    return rows;
		} catch (Exception ex) {
			return rows;
        } finally {
        	
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
	
	//return all query results as an arraylist of string arrays.
	//each string array represents one record.
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
		    
		    while (rs.next()) {              
		        int i = 1;
		        String[] rows = new String[numberOfColumns]; 
		        while(i <= numberOfColumns) {
		        	rows[i-1] = rs.getString(i);
			        i++;
		        }
		        data.add(rows);
		    }
		    
		    return data;
		} catch (Exception ex) {
			return new ArrayList<String[]>();
        } finally {

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
	
	//return zero if passed string is null or empty
	public Long handleLongNulls(String str){
		if (str==null || str==""){
			return 0L;
		} else {
			return Long.parseLong(str);
		}
	}

	//return zero if passed string is null or empty
	public int handleIntNulls(String str){
		if (str==null || str==""){
			return 0;
		} else {
			return Integer.parseInt(str);
		}
	}

	//return empty string if null
	public String handleStrNulls(String str){
		if (str==null){
			return "";
		} else {
			return str;
		}
	}
	
	 public void closeConnection(){
		 try {
			conn.close();
		} catch (SQLException e) {
			//ignore
		}
	 }
	
}
   
	