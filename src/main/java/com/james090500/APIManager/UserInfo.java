package com.james090500.APIManager;

import com.james090500.APIManager.API.UUIDManager;
import com.james090500.APIManager.API.UsernameManager;

public class UserInfo {
	
	public static String getName(String uuid) {
		return UsernameManager.getUsername(uuid);
	}
	
	public static String getUUID(String username) {
		return UUIDManager.getUUID(username);
		
	}
	
	public static String getParsedUUID(String username) {
		String uuid = UUIDManager.getUUID(username);
		return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
	}

}
