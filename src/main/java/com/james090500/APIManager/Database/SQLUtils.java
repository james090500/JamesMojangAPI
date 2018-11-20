package com.james090500.APIManager.Database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class SQLUtils {
	
	public static void updateSQL(String statement, String first, String second) throws Exception {
		PreparedStatement update = DBManager.getConnection().prepareStatement(statement);
     	update.setString(1, first);
     	update.setString(2, second);
     	update.execute();
	}
	
	public static void insertSQL(String statement, String first, String second, int expire) throws Exception {
		String time = Long.toString((System.currentTimeMillis() / 1000) + expire);
		
		PreparedStatement insert = DBManager.getConnection().prepareStatement(statement);
		insert.setString(1, first);
		insert.setString(2, second);
		insert.setString(3, time);
		insert.execute();
	}
	
	public static CachedRowSet getSQL(String statement, String value) throws Exception {
		PreparedStatement select = DBManager.getConnection().prepareStatement(statement);
     	select.setString(1, value);     	
     	
     	ResultSet result = select.executeQuery();	 	     	
     	CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
     	crs.populate(result);
     	result.close();
     	return crs;		
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
