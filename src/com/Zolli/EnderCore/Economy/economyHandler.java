package com.Zolli.EnderCore.Economy;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import com.Zolli.EnderCore.EnderCore;
import com.Zolli.EnderCore.API.IEconomy;
import com.Zolli.EnderCore.Logger.simpleLogger.Level;
import com.earth2me.essentials.Essentials;
import com.iCo6.iConomy;
import com.iCo6.system.Accounts;

import cosine.boseconomy.BOSEconomy;

public class economyHandler implements IEconomy {
	
	EnderCore plugin;
	Essentials essEco;
	iConomy iCon;
	BOSEconomy bosEcon;
	public static net.milkbowl.vault.economy.Economy vaultEconomy;
	
	private Accounts iConAccounts;
	Econs handler = null;
	
	/**
	 * Constuctor of Economy handler. This class scan all available plugin
	 * and get the most valuable plugin. When found this class return
	 * 
	 * @param plugin One object of the plugin main class
	 */
	
	public economyHandler(EnderCore plugin) {
		this.plugin = plugin;
		PluginManager pm = Bukkit.getServer().getPluginManager();
		
		if(pm.isPluginEnabled("iConomy")) {
			Plugin iConPlugin = pm.getPlugin("iConomy");
			
			if(iConPlugin.isEnabled() && iConPlugin.getClass().getName().equals("com.iCo6.iConomy")) {
				handler = Econs.iCon;
				iCon = (iConomy) iConPlugin;
				iConAccounts = new Accounts();
				this.plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("economy.detected").replace("#ECONNAME#", "iConomy6"));
				return;
			}
		} else if(pm.isPluginEnabled("BOSEconomy")) {
			Plugin bosEconPlugin = pm.getPlugin("BOSEconomy");
			
			if(bosEconPlugin.isEnabled() && bosEconPlugin.getDescription().getVersion().startsWith("0.7")) {
				handler = Econs.boseCon;
				bosEcon = (BOSEconomy) bosEconPlugin;
				this.plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("economy.detected").replace("#ECONNAME#", "BOSEconomy"));
				return;
			}
		} else if(pm.isPluginEnabled("Essentials")) {
			Plugin essEcoPlugin = pm.getPlugin("Essentials");
			
			if(essEcoPlugin.isEnabled()) {
				handler = Econs.eEco;
				essEco = (Essentials) essEcoPlugin;
				this.plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("economy.detected").replace("#ECONNAME#", "Essentials"));
				return;
			}
		} else if(pm.isPluginEnabled("Vault")) {
			RegisteredServiceProvider<net.milkbowl.vault.economy.Economy> economyProvider = plugin.getServer().getServicesManager().getRegistration(Economy.class);
			
			if(economyProvider != null) {
				handler = Econs.Vault;
				vaultEconomy = economyProvider.getProvider();
				this.plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("economy.detected").replace("#VAULTECONNAME#", vaultEconomy.getName()));
				return;
			}
		}
	}
	
	/**
	 * Add specific amount to given player balance
	 * 
	 * @param pl Player object
	 * @param amount The money they are added to the player balance
	 */
	
	public void deposit(Player pl, int amount) {
		String playerName = pl.getName();
		
		switch(handler) {
			case iCon:
				iConAccounts.get(playerName).getHoldings().add(amount);
				break;
			case boseCon:
				double currentMoney = bosEcon.getPlayerMoneyDouble(playerName);
				double newAmount = currentMoney + amount;
				bosEcon.setPlayerMoney(playerName, newAmount, true);
				break;
			case eEco:
				try {
					com.earth2me.essentials.api.Economy.add(playerName, amount);
				} catch(Exception e) {
					this.plugin.reporter.pushReport(e);
				}
				break;
			case Vault:
				vaultEconomy.depositPlayer(playerName, amount);
				break;
		}
	}
	
	/**
	 * Remove specified amount from given player balance
	 * 
	 * @param pl Player object
	 * @param amount The money they are removed from the player balance
	 */
	
	public void withdraw(Player pl, int amount) {
		String playerName = pl.getName();
		
		switch(handler) {
			case iCon:
				iConAccounts.get(playerName).getHoldings().subtract(amount);
				break;
			case boseCon:
				double currentMoney = bosEcon.getPlayerMoneyDouble(playerName);
				double newAmount = currentMoney - amount;
				bosEcon.setPlayerMoney(playerName, newAmount, true);
				break;
			case eEco:
				try {
					com.earth2me.essentials.api.Economy.subtract(playerName, amount);
				} catch(Exception e) {
					this.plugin.reporter.pushReport(e);
				}
				break;
			case Vault:
				vaultEconomy.withdrawPlayer(playerName, amount);
				break;
		}
	}
	
	/**
	 * Check the given player has the specified amount
	 * 
	 * @param pl Player object
	 * @param amount The amount the player checked for
	 * @return true at least as much money than the specified, elswhere false
	 */
	
	public boolean hasEnought(Player pl, int amount) {
		String playerName = pl.getName();
		
		switch(handler) {
			case iCon:
				return iConAccounts.get(playerName).getHoldings().hasEnough(amount);
			case boseCon:
				if((bosEcon.getPlayerMoneyDouble(playerName) - amount) >= 0) {
					return true;
				} else {
					return false;
				}
			case eEco:
				try {
					return com.earth2me.essentials.api.Economy.hasEnough(playerName, amount);
				} catch(Exception e) {
					this.plugin.reporter.pushReport(e);
				}
			case Vault:
				
		}
		return false; 
	}
	
	/**
	 * Format the given amount. Attache the specified currency
	 * 
	 * @param amount Format amount
	 * @return The formatted string
	 */
	
	public String format(int amount) {
		switch(handler) {
			case Vault:
				return vaultEconomy.format(amount);
			case iCon:
				return iConomy.format(amount);
			case boseCon:
				return bosEcon.getMoneyFormatted(amount);
			case eEco:
				return com.earth2me.essentials.api.Economy.format(amount);
		}
		return null;
	}
	
	enum Econs {
		eEco,
		iCon,
		boseCon,
		Vault;
	}
	
}
