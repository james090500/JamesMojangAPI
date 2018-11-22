package com.james090500.APIManager;

import com.james090500.APIManager.API.CacheManager;

import net.md_5.bungee.api.event.PostLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;

public class BungeeMain extends Plugin implements Listener {
	
	@Override
	public void onEnable() {		
		getProxy().getPluginManager().registerListener(this, this);
	}
	
	/*
	 * Updates player UUID on join with their username to avoid unneeded API calls.
	 */
	@EventHandler
	public void onJoin(PostLoginEvent event) {		
		CacheManager.updateCache(event.getPlayer().getUniqueId().toString().replace("-", ""), event.getPlayer().getName());		
	}
}
