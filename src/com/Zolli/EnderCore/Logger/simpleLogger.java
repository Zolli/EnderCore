package com.Zolli.EnderCore.Logger;

import java.io.File;

import com.Zolli.EnderCore.File.fileUtils;

public class simpleLogger {
	
	private boolean logToFile;
	private boolean logToConsole;
	private String logName = "EnderCoreLog.log";
	private File logFile;
	private fileUtils fileUtils;
	
	public simpleLogger() {
		this.logToFile = false;
		this.logToConsole = true;
	}
	
	public simpleLogger(File dataFolder) {
		this.logToFile = true;
		this.logToConsole = true;
		this.logFile = new File(dataFolder, this.logName);
		this.fileUtils = new fileUtils(this.logFile);
	}
	
	public simpleLogger(boolean logToConsole, File dataFolder) {
		this.logToFile = true;
		this.logToConsole = logToConsole;
		this.logFile = new File(dataFolder, this.logName);
		this.fileUtils = new fileUtils(this.logFile);
	}
	
	public simpleLogger(File dataFolder, String logName) {
		this.logToFile = true;
		this.logToConsole = true;
		this.logFile = new File(dataFolder, logName);
		this.fileUtils = new fileUtils(this.logFile);
	}
	
	public simpleLogger(boolean logToConsole, File dataFolder, String logName) {
		this.logToFile = true;
		this.logToConsole = logToConsole;
		this.logFile = new File(dataFolder, logName);
		this.fileUtils = new fileUtils(this.logFile);
	}
	
	public void log(Level l, String message) {
		String output = "[" + l.getName() + "] " + message;
		
		if(this.logToConsole) {
			System.out.println(output);
		}
		
		if(this.logToFile) {
			this.fileUtils.writeLineToEnd(output);
		}
	}
	
	public enum Level {
		SERVER("Server"), WARNING("Warning"), INFO("Info"), CONFIG("Config"), FINE("Fine"), FINER("Finer"), FINEST("Finest");
		private String name;
		
		private Level(String s) {
			this.name = s;
		}
		
		public String getName() {
			return this.name;
		}
	}
	
	
}
