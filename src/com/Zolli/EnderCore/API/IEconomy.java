package com.Zolli.EnderCore.API;

import org.bukkit.entity.Player;

public interface IEconomy {
	
	public void deposit(Player pl, int amount);
	public void withdraw(Player pl, int amount);
	public boolean hasEnought(Player pl, int amount);
	public String format(int amount);
	
}
