package com.james090500.APIManager;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.james090500.APIManager.API.CacheManager;
import com.james090500.APIManager.API.UUIDManager;
import com.james090500.APIManager.API.UsernameManager;

public class UserInfo {
	
	/*
	 * Will check if player is only before making api/cache call
	 * cache is update either way
	 */
	public static String getName(String uuid) {
		Player player = Bukkit.getPlayer(UUID.fromString(parseUUID(uuid)));
		if(player != null) {
			CacheManager.updateCache(uuid, player.getName());
			return player.getName();
		}
		return UsernameManager.getUsername(uuid);
	}
	
	/*
	 * Will check if player is only before making api/cache call
	 * cache is update either way
	 */
	public static String getUUID(String username) {
		Player player = Bukkit.getPlayer(username);
		if(player != null) {
			CacheManager.updateCache(player.getUniqueId().toString().replaceAll("-", ""), player.getName());
			return player.getUniqueId().toString().replace("-", "");
		}
		return UUIDManager.getUUID(username);	
	}
	
	/*
	 * Returns the uuid of the user with dashes
	 */
	public static String getParsedUUID(String username) {
		String uuid = getUUID(username);
		return parseUUID(uuid);
	}
		
	/*
	 * Adds dashes to uuid
	 */
	private static String parseUUID(String uuid) {
		return uuid.substring(0, 8) + "-" + uuid.substring(8, 12) + "-" + uuid.substring(12, 16) + "-" + uuid.substring(16, 20) + "-" + uuid.substring(20, 32);
	}

}
