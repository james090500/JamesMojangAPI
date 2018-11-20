package com.james090500.APIManager;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.james090500.APIManager.API.CacheManager;

public class Main extends JavaPlugin implements Listener {

	@Override
	public void onEnable() {
		getPluginLoader().createRegisteredListeners(this, this);
	}
	
	/*
	 * Updates player UUID on join with their username to avoid unneeded API calls.
	 */
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		CacheManager.updateCache(event.getPlayer().getUniqueId().toString().replace("-", ""), event.getPlayer().getName());		
	}
}
