package com.james090500.APIManager;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		getLogger().info("Enabling James090500's API Manager. This plugin is a dependency and performs no sole action");
	}
	
	@Override
	public void onDisable() {
		getLogger().info("Disabling James090500's API Manager.");
	}
	
}
