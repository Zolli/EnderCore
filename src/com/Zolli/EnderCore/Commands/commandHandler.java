package com.Zolli.EnderCore.Commands;

import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;


public class commandHandler implements CommandExecutor {

	private Map<String, ECCommand> handletCommands;
	
	public void registerCommand(String name, ECCommand command) {
		this.handletCommands.put(name, command);
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		ECCommand command = this.handletCommands.get(arg1.toString());
		return false;
	}
	
	
	
}