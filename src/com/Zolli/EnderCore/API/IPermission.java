package com.Zolli.EnderCore.API;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public interface IPermission {
	
	public boolean has(CommandSender sender, String node);
	public boolean has(Player pl, String node);
	public String getPrimaryGroup(Player pl);
	public boolean playerRemoveGroup(Player pl, String groupName);
	public boolean playerAddGroup(Player pl, String groupName);
	public String getPrefix(Player pl);
	
}
