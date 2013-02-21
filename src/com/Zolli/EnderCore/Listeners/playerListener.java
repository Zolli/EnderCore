package com.Zolli.EnderCore.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import com.Zolli.EnderCore.EnderCore;
import com.Zolli.EnderCore.Utils.ECPlayer;

public class playerListener implements Listener {
	
	/**
	 * The main class off the plugin
	 */
	EnderCore plugin;
	
	/**
	 * Main world name
	 */
	String mainWorld = null;
	
	/**
	 * End world name
	 */
	String endWorld = null;
	
	/**
	 * Constructor
	 * @param instance Plugin main class
	 */
	public playerListener(EnderCore instance) {
		this.plugin = instance;
		this.mainWorld = this.plugin.config.getString("worlds.mainWorld");
		this.endWorld = this.plugin.config.getString("worlds.endWorld");
	}
	
	/**
	 * Handle player first join based on player info file exist
	 * If this player joined first time insert a unique row to database
	 * @param e Event
	 */
	@EventHandler
	public void playerJoin(PlayerJoinEvent e) {
		Player pl = e.getPlayer();
		boolean played = pl.hasPlayedBefore();

		if(!played) {
			plugin.dbAction.addPlayer(pl);
		}
	}
	
	/**
	 * Handle Player world changing
	 * @param e Event
	 */
	@EventHandler
	public void goNether(PlayerChangedWorldEvent e) {
		Player pl = e.getPlayer();
		ECPlayer epl = new ECPlayer(pl, plugin);
		String fromWorld = e.getFrom().getName();
		String toWorld = pl.getWorld().getName();
		
		/* If player traveling to the nether world */
		if(toWorld.equalsIgnoreCase(this.endWorld)) {
			if(epl.isDragonDefeted()) {
				System.out.println("Igen");
			} else {
				System.out.println("Nem");
			}
		}
	}
	
}