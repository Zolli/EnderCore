package com.Zolli.EnderCore.Commands;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface ECCommand {
	
	public boolean execute(CommandSender sender, String args[], String chainedParams);
	public String getName();
	public boolean isAccessibleFromConsole();
	public List<String> getPermission();
	public int getArgsLength();
	public List<String> getExample();
	
}


