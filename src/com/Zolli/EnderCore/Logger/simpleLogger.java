package com.Zolli.EnderCore.Logger;

import java.io.File;
import java.util.Date;

import org.bukkit.plugin.PluginDescriptionFile;

import com.Zolli.EnderCore.File.fileUtils;

public class simpleLogger {
	
	/**
	 * Defines the class write the log content to file
	 */
	private boolean logToFile;
	
	/**
	 * Defines the class send log messages to standard output (STDOUT)
	 */
	private boolean logToConsole;
	
	/**
	 * Store log prefix
	 */
	private String logPrefix;
	
	/**
	 * The log file name
	 */
	private String logName;
	
	/**
	 * The log file
	 */
	private File logFile;
	
	/**
	 * FileUtil object
	 */
	private fileUtils fileUtils;
	
	public simpleLogger(PluginDescriptionFile pdFile, File dataFolder, String logName) {
		this.logToConsole = true;
		this.logToFile = true;
		this.logName = logName;
		this.logFile = new File(dataFolder, this.logName);
		
		if(!this.logFile.exists()) {
			this.logFile.getParentFile().mkdirs();
		}
		
		this.fileUtils = new fileUtils(this.logFile);
		this.logPrefix = "[" + pdFile.getName() + "]";
	}
	
	/**
	 * Enable or disable file logging 
	 * @param v Value of file logging
	 */
	public void setFileLogging(boolean v) {
		this.logToFile = v;
	}
	
	/**
	 * Gets file logging status
	 * @return File logging status
	 */
	public boolean getFileLogging() {
		return this.logToFile;
	}
	
	/**
	 * Enable or disable console logging
	 * @param v Value of console logging
	 */
	public void setConsoleLogging(boolean v) {
		this.logToConsole = v;
	}
	
	/**
	 * Get console logging status
	 * @return Value of console logging
	 */
	public boolean getConsoleLogging() {
		return this.logToConsole;
	}
	
	/**
	 * Set another prefix to log
	 * @param v The new prefix value
	 */
	public void setPrefix(String v) {
		this.logPrefix = v;
	}
	
	/**
	 * Return the current prefix as string
	 * @return Current prefix
	 */
	public String getPrefix() {
		return this.logPrefix;
	}
	
	/**
	 * Log a message with given level, and write the log file to console, or file, or both (Definied by constructor)
	 * @param l
	 * @param message
	 */
	@SuppressWarnings("deprecation")
	public void log(Level l, String message) {
		String output = this.logPrefix + "[" + l.getName() + "] " + message;
		
		if(this.logToConsole) {
			System.out.println(output);
		}
		
		if(this.logToFile) {
			Date dateObject = new Date();
			String date = "[" + dateObject.toLocaleString() + "]";
			this.fileUtils.writeLineToEnd(date + output);
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
