package com.Zolli.EnderCore.Commands.command;

import java.util.List;

public class ECCommandController {
	
	String commandName;
	int params;
	List<String> Permissions;
	boolean consoleAccess;
	String subCommand;
	
	public ECCommandController() {
		
	}
	
	public void addPermission(String node) {
		this.Permissions.add(node);
	}
	
	public void setParamLength(int length) {
		
	}
	
	public void setSubcommand(String sub) {
		this.subCommand = sub;
	}
	
}
