package com.Zolli.EnderCore.Commands;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.Zolli.EnderCore.EnderCore;


public class commandHandler implements CommandExecutor {

	private Map<String, ECCommand> handletCommands = new HashMap<String, ECCommand>();
	private EnderCore plugin;
	
	public commandHandler(EnderCore instance) {
		this.plugin = instance;
	}
	
	public void registerCommand(String name, ECCommand command) {
		this.handletCommands.put(name, command);
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		System.out.println(arg1.getName());
		System.out.println(arg3.length);
		System.out.println(arg2);
		ECCommand command = this.handletCommands.get(arg3[0]);
		command.execute(arg0, arg3);
		return false;
	}
	
	
	
}