package com.Zolli.EnderCore.Utils;

import org.bukkit.entity.Player;

import com.Zolli.EnderCore.EnderCore;

public class ECPlayer  {

	private Player player;
	EnderCore plugin;
	
	public ECPlayer(Player pl, EnderCore instance) {
		this.player = pl;
		this.plugin = instance;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public boolean isDragonDefeted() {
		return this.plugin.dbAction.getDefeatStatus(this.player);
	}

}
