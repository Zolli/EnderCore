package com.Zolli.EnderCore.Logger;

import java.io.File;

import org.bukkit.plugin.PluginDescriptionFile;

import com.Zolli.EnderCore.File.fileUtils;

public class simpleLogger {
	
	/**
	 * Defines the class write the log content to file
	 */
	private boolean logToFile;
	
	/**
	 * Definis the class send log messages to standard output (STDOUT)
	 */
	private boolean logToConsole;
	
	/**
	 * Store log prefix
	 */
	private String logPrefix;
	
	/**
	 * The log file name
	 */
	private String logName = "EnderCoreLog.log";
	
	/**
	 * The log file
	 */
	private File logFile;
	
	/**
	 * FileUtil object
	 */
	private fileUtils fileUtils;
	
	/**
	 * Constructor
	 * @param pdFile DescriptionFile object
	 */
	public simpleLogger(PluginDescriptionFile pdFile) {
		this.logToFile = false;
		this.logToConsole = true;
		this.logPrefix = "[" + pdFile.getName() + "]";
	}
	
	/**
	 * Constructor
	 * @param pdFile DescriptionFile object
	 * @param dataFolder Log file location
	 */
	public simpleLogger(PluginDescriptionFile pdFile, File dataFolder) {
		this.logToFile = true;
		this.logToConsole = true;
		this.logFile = new File(dataFolder, this.logName);
		this.fileUtils = new fileUtils(this.logFile);
		this.logPrefix = "[" + pdFile.getName() + "]";
	}
	
	/**
	 * Constructor
	 * @param pdFile DescriptionFile object
	 * @param logToConsole Definies the class send the output to console
	 * @param dataFolder Log file location
	 */
	public simpleLogger(PluginDescriptionFile pdFile, boolean logToConsole, File dataFolder) {
		this.logToFile = true;
		this.logToConsole = logToConsole;
		this.logFile = new File(dataFolder, this.logName);
		this.fileUtils = new fileUtils(this.logFile);
		this.logPrefix = "[" + pdFile.getName() + "]";
	}
	
	/**
	 * Constructor
	 * @param pdFile DescriptionFile object
	 * @param dataFolder Log file location
	 * @param logName Log file name
	 */
	public simpleLogger(PluginDescriptionFile pdFile, File dataFolder, String logName) {
		this.logToFile = true;
		this.logToConsole = true;
		this.logFile = new File(dataFolder, logName);
		this.fileUtils = new fileUtils(this.logFile);
		this.logPrefix = "[" + pdFile.getName() + "]";
	}
	
	/**
	 * Constructor
	 * @param pdFile DescriptionFile object
	 * @param logToConsole Definies the class sned output to console
	 * @param dataFolder Log file location
	 * @param logNameLog file Name
	 */
	public simpleLogger(PluginDescriptionFile pdFile, boolean logToConsole, File dataFolder, String logName) {
		this.logToFile = true;
		this.logToConsole = logToConsole;
		this.logFile = new File(dataFolder, logName);
		this.fileUtils = new fileUtils(this.logFile);
		this.logPrefix = "[" + pdFile.getName() + "]";
	}
	
	/**
	 * Log a message with given level, and write the log file to console, or file, or both
	 * @param l
	 * @param message
	 */
	public void log(Level l, String message) {
		String output = this.logPrefix + "[" + l.getName() + "] " + message;
		
		if(this.logToConsole) {
			System.out.println(output);
		}
		
		if(this.logToFile) {
			this.fileUtils.writeLineToEnd(output);
		}
	}
	
	/**
	 * Available log levels
	 */
	public enum Level {
		SERVER("Server"), WARNING("Warning"), INFO("Info"), CONFIG("Config"), FINE("Fine"), FINER("Finer"), FINEST("Finest");
		private String name;
		
		/**
		 * The Level constructor
		 * @param s Value
		 */
		private Level(String s) {
			this.name = s;
		}
		
		/**
		 * Get the definied Level normal name
		 * @return String The level name
		 */
		public String getName() {
			return this.name;
		}
	}
	
	
}
