package com.Zolli.EnderCore.Commands;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import com.Zolli.EnderCore.EnderCore;


public class commandHandler implements CommandExecutor {

	private Map<String, ECCommand> handledCommands = new HashMap<String, ECCommand>();
	private EnderCore plugin;
	private List<String> commandPermissions;
	
	public commandHandler(EnderCore instance) {
		this.plugin = instance;
	}
	
	public void registerCommand(String name, ECCommand command) {
		this.handledCommands.put(name, command);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] arg3) {
		ECCommand command = this.handledCommands.get(arg3[0]);
		this.commandPermissions = command.getPermission();
		
		if((sender instanceof ConsoleCommandSender) && command.isAccessibleFromConsole()) {
			
		}
		
		
		return false;
	}
	
	
	
}