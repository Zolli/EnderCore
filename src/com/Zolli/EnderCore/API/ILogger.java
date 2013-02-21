package com.Zolli.EnderCore.API;

import com.Zolli.EnderCore.Logger.simpleLogger.Level;

public interface ILogger {
	
	public void setDebug(String v);
	public void setFileLogging(boolean v);
	public boolean getFileLogging();
	public void setConsoleLogging(boolean v);
	public boolean getConsoleLogging();
	public void setPrefix(String v);
	public String getPrefix();
	public void log(Level l, String message);
	
}
