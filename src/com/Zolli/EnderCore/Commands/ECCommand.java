package com.Zolli.EnderCore.Commands;

import org.bukkit.command.CommandSender;

public interface ECCommand {
	
	public boolean execute(CommandSender sender, String args[]);
	public boolean isAccessibleFromConsole();
	public boolean isEnabledForOPs();
	public String getPermission();
	public String getArgsLength();
	
}


