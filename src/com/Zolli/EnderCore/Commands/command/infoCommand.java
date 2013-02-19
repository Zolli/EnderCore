package com.Zolli.EnderCore.Commands.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.Zolli.EnderCore.Commands.ECCommand;

public class infoCommand implements ECCommand {
	
	private List<String> permissions = new ArrayList<String>();
	
	public infoCommand() {
		this.permissions.add("ec.basic.info");
		this.permissions.add("ec.info");
	}
	
	@Override
	public String getName() {
		return "info";
	}

	@Override
	public boolean isAccessibleFromConsole() {
		return true;
	}
	
	@Override
	public boolean isEnabledForOPs() {
		return true;
	}

	@Override
	public List<String> getPermission() {
		return this.permissions;
	}

	@Override
	public int getArgsLength() {
		return 0;
	}
	
	@Override
	public boolean execute(CommandSender sender, String[] args) {
		Player pl = (Player) sender;
		pl.sendMessage("Hello");
		return false;
	}
	
}