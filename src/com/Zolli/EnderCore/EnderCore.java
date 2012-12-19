package com.Zolli.EnderCore;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Zolli.EnderCore.Configuration.Configuration;
import com.Zolli.EnderCore.Logger.simpleLogger;
import com.Zolli.EnderCore.Logger.simpleLogger.Level;

public class EnderCore extends JavaPlugin {
	
	/**
	 * Bukkit PluginManager instance
	 */
	private PluginManager pluginManager;
	
	/**
	 * SimpleLogger object
	 */
	public simpleLogger logger;
	
	/**
	 * Configuration object
	 */
	Configuration mainConfig;
	
	/**
	 * PluginDescriptionFile object
	 */
	private PluginDescriptionFile pluginDescription;
	
	/**
	 * Location of plugin data folder
	 */
	private File dataFolder;
	
	/**
	 * Runs when plugin initialization started
	 */
	public void onLoad() {
		this.pluginDescription = getDescription();
		
		this.dataFolder = this.getDataFolder();
		this.logger = new simpleLogger(this.pluginDescription, this.dataFolder, "EnderCore.Log");
		this.mainConfig = new Configuration(this, "config.yml");
	}
	
	/**
	 * Runs when initialization end (loaded plugin.yml file) 
	 */
	public void onEnable() {
		YamlConfiguration config = mainConfig.config;
		
		this.logger.log(Level.INFO, "Sucessfully enabled!");
		this.logger.log(Level.SERVER, config.getString("test"));
	}
	
	/**
	 * Runs when disabling signal sent
	 */
	public void onDisable() {
		this.logger.log(Level.INFO, "Disabling...");
	}
	
}
