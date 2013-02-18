package com.Zolli.EnderCore.Commands.command;

import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Zolli.EnderCore.Commands.ECCommand;

public class infoCommand implements ECCommand {
	
	public infoCommand() {
		//this.Permissions.add("ec.basic.info");
	}
	
	@Override
	public boolean execute(CommandSender sender, String[] args) {
		Player pl = (Player) sender;
		pl.sendMessage("Hello");
		return false;
	}

	@Override
	public boolean isAccessibleFromConsole() {
		return true;
	}

	@Override
	public List<String> getPermission() {
		return null;
	}

	@Override
	public int getArgsLength() {
		return 1;
	}

	@Override
	public boolean isEnabledForOPs() {
		return true;
	}

	@Override
	public String getName() {
		return "info";
	}
	
}