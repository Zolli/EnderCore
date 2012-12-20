package com.Zolli.EnderCore.Listeners;

import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.Zolli.EnderCore.EnderCore;
import com.Zolli.EnderCore.Events.playerDefeatDragonEvent;

public class entityListener implements Listener {
	
	/**
	 * The main class off the plugin
	 */
	EnderCore plugin;
	
	/**
	 * Constructor
	 * @param instance The plagin main class
	 */
	public entityListener(EnderCore instance) {
		this.plugin = instance;
	}
	
	/**
	 * Called when entity dies
	 * @param e EntityDeathEvent
	 */
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		EntityDamageEvent event = e.getEntity().getLastDamageCause();
		Entity entity = e.getEntity();
		
		if((entity != null) && (event != null) && (entity instanceof EnderDragon) && (event instanceof EntityDamageByEntityEvent)) {
			EntityDamageByEntityEvent EDBEEvent = (EntityDamageByEntityEvent) event;
			Player pl = (Player) EDBEEvent.getDamager();
			EnderDragon enderDragon = (EnderDragon) entity;
			
			plugin.storage.setDefeated(pl.getName(), true);
			pl.sendMessage(plugin.local.getLocalizedString("defeat.successSingle"));
			
			//Trigger the playerDefeatDragonEvent()
			playerDefeatDragonEvent defeatEvent = new playerDefeatDragonEvent(pl, enderDragon);
			plugin.pluginManager.callEvent(defeatEvent);
		}
	}
}
