package com.Zolli.EnderCore.Configuration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
 
/**
* Yaml saving/loading API
* Code author: DomovoiButler
* @author beastman3226
* @version 1.0
*/
 
public class Configuration {
 
	public JavaPlugin plugin;
	File configFile;
	//label [1]
	public FileConfiguration config;
	//label [2]
 
	public void loadConfig() {
		configFile = new File(plugin.getDataFolder(), "config.yml");
		//label [3]
	}
 
	public void firstRun() throws Exception {
		if (!configFile.exists()) {
			configFile.getParentFile().mkdirs();
			copy(plugin.getResource("config.yml"), configFile);
		}
		//label [4]
	}
 
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
 
	public void loadYamls() {
		try {
			config.load(configFile);
			} catch (Exception e) {
			e.printStackTrace();
		}
		//label [5]
	}
 
	public void saveYamls() {
		try {
			config.save(configFile);
			} catch (IOException e) {
			e.printStackTrace();
			}
			//label [6]
		}
	}