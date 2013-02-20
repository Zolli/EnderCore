package com.Zolli.EnderCore.Utils;

import org.bukkit.entity.Player;

public class ECPlayer {

	private Player player;
	
	public ECPlayer(Player pl) {
		this.player = pl;
	}
	
	public Player getPlayer() {
		return this.player;
	}

	public void isDragonDefeted() {
		System.out.println("Call");
		
	}

}
