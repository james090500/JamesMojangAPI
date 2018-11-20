package com.james090500.APIManager.API;

import javax.sql.rowset.CachedRowSet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.james090500.APIManager.Database.SQLUtils;
import com.james090500.APIManager.Utils.Settings;
import com.james090500.APIManager.Utils.URLHandler;
import com.james090500.APIManager.Utils.WebRequest;

public class UsernameManager {

	private static final String INSERT = "INSERT INTO users VALUES (?, ?, ?)";
	private static final String SELECT = "SELECT * FROM users WHERE uuid=?";
	private static final String UPDATE = "UPDATE users SET username=? WHERE uuid=?";	
	
	public static String getUsername(String uuid) {
		try {
			return getWebUsername(uuid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getWebUsername(String uuid) throws Exception {   
		CachedRowSet crs = SQLUtils.getSQL(SELECT, uuid);
		if(SQLUtils.needsUpdating(crs)) {
			String response = WebRequest.sendGet(URLHandler.formatAPI(URLHandler.USERNAME_GETTER, uuid));
			JsonElement usernameJsonEle = new JsonParser().parse(response);
		    JsonObject  usernameJsonObj = usernameJsonEle.getAsJsonObject();
		    //To Do handle array
		    String username = usernameJsonObj.get("id").getAsString();
		    if(crs.size() > 1)
		    	SQLUtils.updateSQL(UPDATE, username, uuid);
		    else
		    	SQLUtils.insertSQL(INSERT, username, uuid, Settings.USERNAME_EXPIRE);
		    return username;
		}
		return crs.getString("username");
	}
}
