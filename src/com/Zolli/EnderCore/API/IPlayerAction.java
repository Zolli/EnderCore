package com.Zolli.EnderCore.API;

import java.util.Map;

import org.bukkit.entity.Player;

public interface IPlayerAction {

	public boolean addPlayer(Player pl);
	public boolean setTag(Player pl, String key, String value);
	public Map<String, String> getTag(Player pl);
	
	
}
