package com.Zolli.EnderCore.Commands.command;

import java.util.List;

public class ECCommandController {
	
	private List<String> permissions;
	private List<String> subCommand;
	private int commandCoast = 0;
	private String description;
	private int argsLength = 0;
	
	public ECCommandController() {
		
	}
	
	public void addPermission(String node) {
		this.permissions.add(node);
	}
	
	public void setParamLength(int length) {
		
	}
	
	public void setSubcommand(String sub) {
		this.subCommand.add(sub);
	}
	
	public void setCoast(int coast) {
		this.commandCoast = coast;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setArgsLength(int argsLength) {
		this.argsLength = argsLength;
	}
	
	public String getSubCommand() {
		return this.subCommand.get(0);
	}
	
}
