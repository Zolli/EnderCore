package com.Zolli.EnderCore.Events;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class playerTravelEnderEvent extends Event implements Cancellable {
	
	private boolean cancelled;
	private Player pl;
	private World endWorld;
	private World fromWorld;
	private boolean defeatStatus;
	private static final HandlerList handlers = new HandlerList();
	
	public playerTravelEnderEvent(Player pl, World end, World from, boolean status) {
		this.pl = pl;
		this.endWorld = end;
		this.fromWorld = from;
		this.defeatStatus = status;
	}
	
	public Player getPlayer() {
		return this.pl;
	}
	
	public World getTargetWorld() {
		return this.endWorld;
	}
	
	public World getMainWorld() {
		return this.fromWorld;
	}
	
	public ItemStack getPlayerHandItem() {
		return this.pl.getItemInHand();
	}
	
	public boolean getPlayerDefaeatStatus() {
		return this.defeatStatus;
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
