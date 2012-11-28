package com.Zolli.EnderCore.Logger;

public class simpleLogger {
	
	private boolean logToFile;
	private String logPath;
	private String logName = "EnderCoreLog.log";
	
	public simpleLogger() {
		this.logToFile = false;
	}
	
	public simpleLogger(boolean fileLogging, String logPath) {
		this.logToFile = true;
		this.logPath = logPath;
	}
	
	public simpleLogger(boolean fileLogging, String logPath, String logName) {
		this.logToFile = true;
		this.logPath = logPath;
		this.logName = logName;
	}
	
}
