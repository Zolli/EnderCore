package com.Zolli.EnderCore.Commands.command;

import org.bukkit.command.CommandSender;

import com.Zolli.EnderCore.Commands.ECCommand;

public class infoCommand implements ECCommand {

	@Override
	public boolean execute(CommandSender sender, String[] args) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccessibleFromConsole() {
		return true;
	}

	@Override
	public String getPermission() {
		return null;
	}

	@Override
	public String getArgsLength() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isEnabledForOPs() {
		// TODO Auto-generated method stub
		return false;
	}
	
}