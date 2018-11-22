package com.james090500.APIManager.API;

import javax.sql.rowset.CachedRowSet;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.james090500.APIManager.Database.SQLUtils;
import com.james090500.APIManager.Utils.Settings;
import com.james090500.APIManager.Utils.URLHandler;
import com.james090500.APIManager.Utils.WebRequest;

public class UUIDManager {

	private static final String INSERT = "INSERT INTO users VALUES (?, ?, ?)";
	private static final String SELECT = "SELECT * FROM users WHERE username=?";
	private static final String UPDATE = "UPDATE users SET uuid=? WHERE username=?";	
	
	public static String getUUID(String username) {
		try {
			return getWebUUID(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getWebUUID(String username) throws Exception {   
		CachedRowSet crs = SQLUtils.getSQL(SELECT, username);
		if(SQLUtils.needsUpdating(crs)) {
			String response = WebRequest.sendGet(URLHandler.formatAPI(URLHandler.UUID_GETTER, username));
			if(response.isEmpty()) {
				return null;
			}
			JsonElement uuidJsonEle = new JsonParser().parse(response);
			if(WebRequest.isRequestLimit(uuidJsonEle)) {
				return null;
			}
		    JsonObject  uuidJsonObj = uuidJsonEle.getAsJsonObject();
		    String uuid = uuidJsonObj.get("id").getAsString();
		    if(crs.size() > 1)
		    	SQLUtils.updateSQL(UPDATE, uuid, username, Settings.UUID_EXPIRE);
		    else
		    	SQLUtils.insertSQL(INSERT, uuid, username, Settings.UUID_EXPIRE);
		    return uuid;
		}
		return crs.getString("uuid");
	}
}
