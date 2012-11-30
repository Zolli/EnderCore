package com.Zolli.EnderCore.Storage;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.configuration.file.FileConfiguration;

import com.Zolli.EnderCore.Logger.simpleLogger;
import com.Zolli.EnderCore.Utils.networkUtils;

public class Storage {
	
	private networkUtils nu;
	private storageEngine selectedEngine;
	private String dataFolder;
	private FileConfiguration config;
	private simpleLogger logger;
	
	public void Storage(storageEngine engine, String dataFolder, FileConfiguration config, simpleLogger log) {
		this.nu = new networkUtils();
		this.selectedEngine = engine;
		this.config = config;
		this.logger= log;
		this.dataFolder = dataFolder;
		
		if(!engine.getDownloadUrl().equalsIgnoreCase("none")) {
			this.downloadDriver(engine.getDownloadUrl(), engine.getFileName());
		}
	}
	
	private void initializeDriver() {
		if(this.selectedEngine.equals(storageEngine.MySQL)) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		} else if(this.selectedEngine.equals(storageEngine.SQLITE)) {
			try {
				Class.forName("org.slite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	private Connection getConnection() {
		if(this.selectedEngine.equals(storageEngine.MySQL)) {
			try {
				Connection conn = DriverManager.getConnection(this.config.getString("database.host"), this.config.getString("database.username"), this.config.getString("database.password"));
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(this.selectedEngine.equals(storageEngine.SQLITE)) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:sqlite:" + this.dataFolder + File.separator + "EnderCore.db");
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	private void downloadDriver(String Url, String savedFileName) {
		try {
			this.nu.downloadAndSave(Url, "./" + savedFileName);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	public enum storageEngine {
		SQLITE("http://", "sqlite.jar"),
		MySQL("http://", "mysql.jar"),
		H2DB("http://", "h2.jar"),
		FLATFILE("none"),
		NBT("none");
		
		private String driverUrl;
		private String fileName;
		
		private storageEngine(String s) {
			this.driverUrl = s;
		}
		
		private storageEngine(String s, String f) {
			this.driverUrl = s;
		}
		
		public String getDownloadUrl() {
			return this.driverUrl;
		}
		
		public String getFileName() {
			return this.fileName;
		}
		
	}
	
}
