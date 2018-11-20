package com.james090500.APIManager.API;

import javax.sql.rowset.CachedRowSet;

import com.james090500.APIManager.Database.SQLUtils;
import com.james090500.APIManager.Utils.Settings;

public class CacheManager {
	
	private static final String INSERT = "INSERT INTO users VALUES (?, ?, ?)";
	private static final String SELECT = "SELECT * FROM users WHERE uuid=?";
	private static final String UPDATE = "UPDATE users SET username=? WHERE uuid=?";
	
	public static void updateCache(String uuid, String username) {
		try {
			updateCacheCheck(uuid, username);
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	private static void updateCacheCheck(String uuid, String username) throws Exception {
		CachedRowSet crs = SQLUtils.getSQL(SELECT, uuid);
		if(SQLUtils.needsUpdating(crs)) {
		    if(crs.size() > 1)
		    	SQLUtils.updateSQL(UPDATE, username, uuid, Settings.UUID_EXPIRE);
		    else
		    	SQLUtils.insertSQL(INSERT, uuid, username, Settings.UUID_EXPIRE);		    
		}		
	}
	
}
