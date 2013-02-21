package com.Zolli.EnderCore;

import java.io.File;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.Zolli.EnderCore.Commands.commandHandler;
import com.Zolli.EnderCore.Commands.command.infoCommand;
import com.Zolli.EnderCore.Configuration.Configuration;
import com.Zolli.EnderCore.Economy.economyHandler;
import com.Zolli.EnderCore.Listeners.blockListener;
import com.Zolli.EnderCore.Listeners.entityListener;
import com.Zolli.EnderCore.Listeners.inventoryListener;
import com.Zolli.EnderCore.Listeners.playerListener;
import com.Zolli.EnderCore.Listeners.serverListeners;
import com.Zolli.EnderCore.Listeners.worldListener;
import com.Zolli.EnderCore.Localization.localizationManager;
import com.Zolli.EnderCore.Logger.simpleLogger;
import com.Zolli.EnderCore.Logger.simpleLogger.Level;
import com.Zolli.EnderCore.Permission.permissionHandler;
import com.Zolli.EnderCore.Storage.Storage;
import com.Zolli.EnderCore.Storage.storageActions;

public class EnderCore extends JavaPlugin {
	
	/**
	 * Bukkit PluginManager instance
	 */
	public PluginManager pluginManager;
	
	/**
	 * SimpleLogger object
	 */
	public simpleLogger logger;
	
	/**
	 * Configuration object for main config
	 */
	private Configuration mainConfig;
	
	/**
	 * Configuration object for flatfile storage engine
	 */
	private Configuration storageConfig;
	
	/**
	 * PluginDescriptionFile object
	 */
	private PluginDescriptionFile pluginDescription;
	
	/**
	 * Location of plugin data folder
	 */
	private File dataFolder;
	
	/**
	 * Main configuration object
	 */
	public YamlConfiguration config;
	
	/**
	 * Storage engine class
	 */
	public Storage storage;
	
	/**
	 * StorageActions class
	 */
	public storageActions dbAction;
	
	/**
	 * LocalizationManager class
	 */
	public localizationManager local;
	
	/**
	 * commandHandler class
	 */
	public commandHandler command;
	
	/**
	 * Permission handler object
	 */
	public permissionHandler permission;
	
	/**
	 * Economy handler object
	 */
	public economyHandler economy;
	
	/**
	 * Runs when plugin initialization started
	 */
	public void onLoad() {
		
		/* Fill some variables with objects */
		this.pluginDescription = getDescription();
		this.pluginManager = this.getServer().getPluginManager();
		this.dataFolder = this.getDataFolder();
		this.logger = new simpleLogger(this.pluginDescription, this.dataFolder, "EnderCore.Log");
		this.mainConfig = new Configuration(this, "config.yml");
	}
	
	/**
	 * Runs when initialization end (loaded plugin.yml file) 
	 */
	public void onEnable() {
		
		/* Fill configuration and get some values for future class initialization */
		config = mainConfig.config;
		String driver = config.getString("database.type");
		String debugMode = config.getString("debug.debugStatus");
		
		/* Initializing some class depended on previous class initialization */
		this.logger.setDebug(debugMode);
		this.detectWords();
		this.permission = new permissionHandler(this);
		this.economy = new economyHandler(this);
		this.storage = new Storage(this, driver, this.dataFolder, this.config, this.logger);
		this.dbAction = new storageActions(this.storage);
		this.local = new localizationManager(this);
		this.command = new commandHandler(this);
		
		/* Registering listeners and commands */
		this.registerListeners();
		this.registerCommands();
		
		/* Log the successfully initialization */
		this.logger.log(Level.INFO, "Sucessfully enabled!");
	}
	
	/**
	 * Runs when disabling signal sent
	 */
	public void onDisable() {
		this.mainConfig.saveConfig();
		this.logger.log(Level.INFO, "Disabling...");
	}
	
	/**
	 * Register all listeners into EventHandler
	 */
	private void registerListeners() {
		this.pluginManager.registerEvents(new blockListener(this), this);
		this.pluginManager.registerEvents(new entityListener(this), this);
		this.pluginManager.registerEvents(new inventoryListener(this), this);
		this.pluginManager.registerEvents(new playerListener(this), this);
		this.pluginManager.registerEvents(new serverListeners(this), this);
		this.pluginManager.registerEvents(new worldListener(this), this);
	}
	
	/**
	 * Register all the commands
	 */
	private void registerCommands() {
		this.getCommand("ec").setExecutor(this.command);
		this.command.registerCommand("info", new infoCommand(this));
	}
	
	/**
	 * Initialize flatfile storage engine, if needed. Called in storageEngine
	 * @return 
	 */
	public Configuration initializeFlatfile() {
		this.storageConfig = new Configuration(this, "storage.yml");
		return this.storageConfig;
	}
	
	public EnderCore getCore() {
		return this;
	}
	
	/**
	 * Get all worlds and get the main world name and nether name
	 * Save the config when main world name contains the default value
	 */
	public void detectWords() {
		List<World> worlds = Bukkit.getServer().getWorlds();
		String mainWorld = worlds.get(0).getName();
		String endWorld = null;
		
		for(World w : worlds) {
			if(w.getEnvironment().equals(Environment.THE_END)) {
				endWorld = w.getName();
			}
		}
		
		if(this.config.getString("worlds.mainWorld").equalsIgnoreCase("defaultWorld")) {
			this.logger.log(Level.WARNING, "World names does not set properly. Detecting worlds automatically for You");
			this.logger.log(Level.CONFIG, "Detected main world, with the following name: " + mainWorld);
			this.logger.log(Level.CONFIG, "Detected nether world, with the following name: " + endWorld);
			
			this.config.set("worlds.mainWorld", mainWorld);
			this.config.set("worlds.endWorld", endWorld);
		}
	}
	
}
