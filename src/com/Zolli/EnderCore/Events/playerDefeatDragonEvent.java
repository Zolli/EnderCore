package com.Zolli.EnderCore.Events;

import org.bukkit.Location;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class playerDefeatDragonEvent extends Event implements Cancellable {
	
	private boolean cancelled;
	private Player pl;
	private EnderDragon dragon;
	private static final HandlerList handlers = new HandlerList();
	
	public playerDefeatDragonEvent(Player pl, EnderDragon dragon) {
		this.pl = pl;
		this.dragon = dragon;
	}
	
	public Player getPlayer() {
		return this.pl;
	}
	
	public Location getDragonLocation() {
		return this.dragon.getLocation();
	}
	
	public Location getPlayerLocation() {
		return this.pl.getLocation();
	}
	
	public ItemStack getWeapon() {
		return this.pl.getItemInHand();
	}
	
	@Override
	public boolean isCancelled() {
		return this.cancelled;
	}

	@Override
	public void setCancelled(boolean canceled) {
		this.cancelled = canceled;
	}

	 @Override
	 public HandlerList getHandlers() {
		 return handlers;
	 }
	
	 public static HandlerList getHandlerList() {
		 return handlers;
	 }	
}
