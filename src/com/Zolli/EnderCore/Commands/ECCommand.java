package com.Zolli.EnderCore.Commands;

import java.util.List;

import org.bukkit.command.CommandSender;

public interface ECCommand {
	
	public boolean execute(CommandSender sender, String args[]);
	public String getName();
	public List<String> getPermission();
	public int getArgsLength();
	
}


