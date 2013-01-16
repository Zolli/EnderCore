package com.Zolli.EnderCore.Commands.command;

public class infoCommand extends ECCommandController {
	
	public infoCommand() {
		this.addPermission("ec.info");
		this.setSubcommand("info");
	}
	
}
