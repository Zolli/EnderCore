package com.Zolli.EnderCore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.Zolli.EnderCore.EnderCore;

public class playerListener implements Listener {
	
	/**
	 * The main class off the plugin
	 */
	EnderCore plugin;
	
	public playerListener(EnderCore instance) {
		this.plugin = instance;
	}

	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player pl = e.getPlayer();
		boolean played = pl.hasPlayedBefore();
	}
	
}