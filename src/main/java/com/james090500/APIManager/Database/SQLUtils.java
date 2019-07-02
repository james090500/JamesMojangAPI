package com.james090500.APIManager.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class SQLUtils {
	
	public static void updateSQL(String statement, String first, String second, int expire) throws Exception {
		String time = Long.toString((System.currentTimeMillis() / 1000) + expire);
		
		try(Connection connection = DBManager.getHikari().getConnection()) {
			PreparedStatement update = connection.prepareStatement(statement);
	     	update.setString(1, first);
	     	update.setString(2, second);
	     	update.setString(3, time);
	     	update.execute();   
		} catch (SQLException e) {
			e.printStackTrace();			
		}     	
	}
	
	public static void insertSQL(String statement, String first, String second, int expire) throws Exception {
		String time = Long.toString((System.currentTimeMillis() / 1000) + expire);
		
		try(Connection connection = DBManager.getHikari().getConnection()) {
			PreparedStatement insert = connection.prepareStatement(statement);
			insert.setString(1, first);
			insert.setString(2, second);
			insert.setString(3, time);
			insert.execute();
			insert.close();
		} catch (SQLException e) {
			e.printStackTrace();			
		}			
	}
	
	public static CachedRowSet getSQL(String statement, String value) throws Exception {
		try(Connection connection = DBManager.getHikari().getConnection()) {
			PreparedStatement select = connection.prepareStatement(statement);
	     	select.setString(1, value);     	
	     	
	     	ResultSet result = select.executeQuery();	 	     	
	     	CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
	     	crs.populate(result);
	     	result.close();
	     	select.close();
	     	return crs;		
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public static boolean needsUpdating(CachedRowSet crs) throws Exception {		
		if(crs.size() == 1) {		
			while (crs.next()) {
				if(Long.parseLong(crs.getString("time")) < (System.currentTimeMillis() / 1000)) {
					return true;
				} else {
					return false;
				}
			}
		}
		return true;
	}
}
