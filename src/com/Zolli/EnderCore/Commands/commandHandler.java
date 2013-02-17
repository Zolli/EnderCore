package com.Zolli.EnderCore.Commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Zolli.EnderCore.EnderCore;
import com.Zolli.EnderCore.Commands.command.ECCommandController;

public class commandHandler implements CommandExecutor {

	EnderCore plugin;
	List<ECCommandController> handledCommands;
	List<String> handletCommandsName;
	
	public commandHandler(EnderCore instance) {
		this.plugin = instance;
	}
	
	public void registerCommand(ECCommandController command) {
		this.handledCommands.add(command);
		this.handletCommandsName.add(command.getSubCommand());
	}
	
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		if(this.handletCommandsName.contains(arg0.toString())) {
			
		}
		
		return false;
	}
	
	

}
