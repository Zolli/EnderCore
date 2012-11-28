package com.Zolli.EnderCore;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Zolli.EnderCore.Configuration.Configuration;
import com.Zolli.EnderCore.Logger.simpleLogger;

public class EnderCore extends JavaPlugin {
	
	private PluginManager pluginManager;
	public simpleLogger logger;
	Configuration configuration;
	public String logPrefix;
	private PluginDescriptionFile pluginDescription;
	private File dataFolder;
	
	public void onLoad() {
		this.dataFolder = this.getDataFolder();
		this.logger = new simpleLogger(this.dataFolder);
		this.configuration = new Configuration();
	}
	
	public void onEnable() {
		configuration.loadYamls();
		FileConfiguration config = configuration.config;
		FileConfiguration storage = configuration.storage;
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
