package com.Zolli.EnderCore.API;

import java.util.Map;

import org.bukkit.entity.Player;

public interface IPlayerAction {

	public boolean addPlayer(Player pl);
	public boolean setDefeated(String name, boolean b);
	public boolean setTag(String newTags);
	public Map<String, String> getTag(Player pl);
	
	
}
