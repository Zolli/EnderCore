package com.Zolli.EnderCore;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderCore extends JavaPlugin {
	
	private PluginManager pluginManager;
	public Logger log;
	public String logPrefix;
	private PluginDescriptionFile pluginDescription;
	
	public void onLoad() {
		
	}
	
	public void onEnable() {
		
	}
	
	public void onDisable() {
		
	}
	
	public EnderCore getCore() {
		return this;
	}
	
	public PluginManager getPluginManager() {
		return this.pluginManager;
	}
	
	public PluginDescriptionFile getDescriptionFile() {
		return this.pluginDescription;
	}
}
