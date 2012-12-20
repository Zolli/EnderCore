package com.Zolli.EnderCore.Storage;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import com.Zolli.EnderCore.Logger.simpleLogger;
import com.Zolli.EnderCore.Logger.simpleLogger.Level;
import com.Zolli.EnderCore.Utils.networkUtils;
import com.Zolli.EnderCore.Utils.tagHelper;

public class Storage {
	
	/**
	 * Network utils object
	 */
	private networkUtils nu;
	
	/**
	 * Defines the selected storageEngine
	 */
	private storageEngine selectedEngine;
	
	/**
	 * Plugin root directory
	 */
	private File dataFolder;
	
	/**
	 * Main configuration object
	 */
	private FileConfiguration config;
	
	/**
	 * Logger object
	 */
	private simpleLogger logger;
	
	/**
	 * Tag helper object
	 */
	private tagHelper tagHelper;
	
	/**
	 * Connection object
	 */
	private Connection conn;
	
	/**
	 * Storage class constructor
	 * @param engine The storageEngine we want to use
	 * @param dataFolder Folder for file type databases
	 * @param config Configuration to get database details
	 * @param log simpleLogger object
	 */
	public Storage(String driver, File dataFolder, FileConfiguration config, simpleLogger log) {
		this.nu = new networkUtils();
		this.tagHelper = new tagHelper();
		storageEngine engine = null;
		
		if(driver.equalsIgnoreCase(storageEngine.MySQL.getName())) {
			engine = storageEngine.MySQL;
		} else if(driver.equalsIgnoreCase(storageEngine.SQLITE.getName())) {
			engine = storageEngine.SQLITE;
		} else if(driver.equalsIgnoreCase(storageEngine.H2DB.getName())) {
			engine = storageEngine.H2DB;
		} else if(driver.equalsIgnoreCase(storageEngine.FLATFILE.getName())) {
			engine = storageEngine.FLATFILE;
		} else if(driver.equalsIgnoreCase(storageEngine.NBT.getName())) {
			engine = storageEngine.NBT;
		} else {
			engine = storageEngine.SQLITE;
		}
		
		this.selectedEngine = engine;
		this.config = config;
		this.logger= log;
		this.dataFolder = dataFolder;
		
		if(!engine.getDownloadUrl().equalsIgnoreCase("none")) {
			this.downloadDriver(engine.getDownloadUrl(), engine.getFileName());
		}
		
		this.initializeDriver();
		this.conn = this.setConnection();
	}
	
	private void loadExternalDriver(File file) throws Exception {
	    Method method = URLClassLoader.class.getDeclaredMethod("addURL", new Class[]{URL.class});
	    method.setAccessible(true);
	    method.invoke(ClassLoader.getSystemClassLoader(), new Object[]{file.toURI().toURL()});
	}
	
	/**
	 * Initialize the selected driver, and log initialization result
	 */
	private void initializeDriver() {
		if(this.selectedEngine.equals(storageEngine.MySQL)) {
			try {
				File driver = new File("./lib" + File.separator + this.selectedEngine.getFileName());
				this.loadExternalDriver(driver);
				Class.forName("com.mysql.jdbc.Driver");
				logger.log(Level.INFO, "Initialized MySQL storage engine!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if(this.selectedEngine.equals(storageEngine.SQLITE)) {
			try {
				File driver = new File("./lib" + File.separator + this.selectedEngine.getFileName());
				this.loadExternalDriver(driver);
				Class.forName("org.sqlite.JDBC");
				logger.log(Level.INFO, "Initialized SQLite storage engine!");
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(this.selectedEngine.equals(storageEngine.H2DB)) {
			try {
				File driver = new File("./lib" + File.separator + this.selectedEngine.getFileName());
				this.loadExternalDriver(driver);
				Class.forName("org.h2.Driver");
				logger.log(Level.INFO, "Initialized H2 Database storage engine!");
			} catch (Exception e) {
				logger.log(Level.WARNING, "H2 Dtabase driver not found!");
				e.printStackTrace();
			} 
		} else {
			if(this.selectedEngine.equals(storageEngine.FLATFILE)) {
				logger.log(Level.INFO, "Initialized flatfile storage engine!");
			} else {
				logger.log(Level.INFO, "Initialized NBT storage engine!");
			}
		}
	}
	
	/**
	 * After initialization we want to connect specified engine, with data from configuration
	 * @return Connection object, if connecting successfully, otherwise null
	 */
	private Connection setConnection() {
		if(this.selectedEngine.equals(storageEngine.MySQL)) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:mysql://" + this.config.getString("database.host") + "/" + this.config.getString("database.name"), this.config.getString("database.username"), this.config.getString("database.password"));
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
		} else if(this.selectedEngine.equals(storageEngine.H2DB)) {
			try {
				Connection conn = DriverManager.getConnection("jdbc:h2:" + this.dataFolder + File.separator + "EnderCore");
				return conn;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * Download the database driver from url and place at given path
	 * @param Url The URL from file is downloaded
	 * @param savedFileName The saved file name
	 */
	private void downloadDriver(String Url, String savedFileName) {
		try {
			this.nu.downloadAndSave(Url, "./lib" + File.separator + savedFileName);
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
		}
	}
	
	/**
	 * Get the connection object from this class
	 * @return Connection object
	 */
	public Connection getConnection() {
		return this.conn;
	}
	
	public boolean setTag(String newTags) {
		switch (this.selectedEngine) {
		case MySQL:
		case SQLITE:
			try {
				Statement stmt = conn.createStatement();
				int rows = stmt.executeUpdate("UPDATE `users` SET uniqueTags = CONCAT(uniqueTags, '" + newTags + "')");
				if(rows == 1) {
					return true;
				} else {
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		default:
			break;
		}
		return false;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public Map<String, String> getTag(Player pl) {
		String object = null;
		
		switch (this.selectedEngine) {
		case MySQL:
		case SQLITE:
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT uniqueTags FROM `users` WHERE userName=" + pl.getName() + " LIMIT 1");
				object = rs.getString(0);
				stmt.close();
				return tagHelper.createMap(object);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		default:
			break;
		}
		return null;
	}
	
	public enum storageEngine {
		SQLITE("sqlite", "http://mc.coldline.hu/zolli/lib/sqliteConnector.jar", "sqlite.jar"),
		MySQL("mysql",  "http://mc.coldline.hu/zolli/lib/mysqlConnector.jar", "mysql.jar"),
		H2DB("h2", "http://mc.coldline.hu/zolli/lib/h2Connector.jar", "h2.jar"),
		FLATFILE("flatfile", "none", "none"),
		NBT("nbt", "none", "none");
		
		private String driverUrl;
		private String fileName;
		private String name;
		
		private storageEngine(String n, String s, String f) {
			this.driverUrl = s;
			this.fileName = f;
			this.name = n;
		}
		
		public String getDownloadUrl() {
			return this.driverUrl;
		}
		
		public String getFileName() {
			return this.fileName;
		}
		
		public String getName() {
			return this.name;
		}
		
	}
	
}
