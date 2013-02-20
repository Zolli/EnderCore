package com.Zolli.EnderCore.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.YamlConfiguration;

import com.Zolli.EnderCore.EnderCore;
 
/**
* Yaml saving/loading API
* Code author: DomovoiButler
* @author beastman3226
* @version 1.0
*/
 
public class Configuration {
	
	/**
	 * JavaPlugin object
	 */
	public EnderCore plugin;
	
	/**
	 * Configuration file
	 */
	File configFile;
	
	/**
	 * Config file name
	 */
	String configFileName;
	
	/**
	 * Config file FileConfiguration object
	 */
	public YamlConfiguration config;
	
	/**
	 * Constructor
	 * @param instance EnderCore instance
	 */
	public Configuration(EnderCore instance, String fileName) {
		this.plugin = instance;
		this.configFileName = fileName;
		this.initialize(this.configFileName);
		
		try {
			this.firstRun(this.configFileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.load();
		
	}
	
	/**
	 * Check the configuration file ar exist in desired location
	 * @throws Exception when file copy, or directory making is failed
	 */
	public void firstRun(String fileName) throws Exception {
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			copy(plugin.getResource(fileName), configFile);
		}
	}
	
	/**
	 * Copy file location to another location
	 * @param in InputStream object
	 * @param file File to be copied
	 */
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Create a new File in plugin folder.
	 * @param FileName
	 */
	private void initialize(String FileName) {
		configFile = new File(plugin.getDataFolder(), FileName);
	}
	
	/**
	 * Load File content into YamlConfiguration object
	 */
	private void load() {
		config = new YamlConfiguration();
		
		try {
			config.load(this.configFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void saveConfig() {
		try {
			this.config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}