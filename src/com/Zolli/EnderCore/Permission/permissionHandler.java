package com.Zolli.EnderCore.Permission;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.data.Group;
import org.anjocaido.groupmanager.data.User;
import org.anjocaido.groupmanager.dataholder.OverloadedWorldHolder;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;

import ru.tehkode.permissions.PermissionGroup;
import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import com.Zolli.EnderCore.EnderCore;
import com.Zolli.EnderCore.API.IPermission;
import com.Zolli.EnderCore.Logger.simpleLogger.Level;

public class permissionHandler implements IPermission {
	
	EnderCore plugin;
	PermissionManager pexPlugin;
	GroupManager gmPlugin;
	public static net.milkbowl.vault.permission.Permission vaultPermission;
	Perms handler = null;

	/**
	 * Main function of Permission handler. This class scan all available plugin
	 * and get the most valuable plugin. When found this class return
	 * 
	 * @param plugin One object of the plugin main class
	 */

	public permissionHandler(EnderCore plugin) {
		this.plugin = plugin;
		PluginManager pm = Bukkit.getServer().getPluginManager();

		if(pm.isPluginEnabled("PermissionsEx")) {
			handler = Perms.PEX;
			pexPlugin = PermissionsEx.getPermissionManager();
			plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("permission.detected").replace("#PERMNAME#", "PermissionsEx"));
			return;
		} else if(pm.isPluginEnabled("GroupManager")) {
			handler = Perms.GM;
			gmPlugin = (GroupManager) pm.getPlugin("GroupManager");
			plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("permission.detected").replace("#PERMNAME#", "GroupManager"));
			return;
		} else if(pm.isPluginEnabled("Vault")) {
			RegisteredServiceProvider<net.milkbowl.vault.permission.Permission> permissionProvider = plugin.getServer().getServicesManager().getRegistration(net.milkbowl.vault.permission.Permission.class);

			if(permissionProvider != null) {
				vaultPermission = permissionProvider.getProvider();
				handler = Perms.VAULT;
				plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("permission.detectVault").replace("#VAULTPERMNAME#", vaultPermission.getName()));
				return;
			}
		} else {
			handler = Perms.BUKKIT;
			plugin.logger.log(Level.INFO, this.plugin.local.getLocalizedString("permission.standartdAPI"));
		}
	}

	/**
	 * Check the specified player for given permission node.
	 * Return true when command sent form console or given player
	 * is OP on server, or player have given permission node, else false
	 * 
	 * @param sender The command sender
	 * @param node Permission node
	 * @return true when player has node, false if not
	 */

	public boolean has(CommandSender sender, String node) {
		Player pl = (Player) sender;

		if((sender instanceof ConsoleCommandSender) || pl.isOp()) {
			return true;
		}

		switch(handler) {
			case PEX:
				return pexPlugin.has(pl, node);
			case GM:
				AnjoPermissionsHandler gmHandler = gmPlugin.getWorldsHolder().getWorldPermissions(pl);
				return gmHandler.has(pl, node);
			case VAULT:
				return vaultPermission.has(pl, node);
			case BUKKIT:
				return pl.hasPermission(node);
		}
		return false;
	}

	/**
	 * Check the specified player for given permission node.
	 * Return true when player is OP on server, or player
	 *  have given permission node, else false
	 * 
	 * @param pl The player
	 * @param node Permission node
	 * @return true when player has node, false if not
	 */

	public boolean has(Player pl, String node) {

		if(pl.isOp()) {
			return true;
		}

		switch(handler) {
			case PEX:
				return pexPlugin.has(pl, node);
			case GM:
				AnjoPermissionsHandler gmHandler = gmPlugin.getWorldsHolder().getWorldPermissions(pl);
				return gmHandler.has(pl, node);
			case VAULT:
				return vaultPermission.has(pl, node);
			case BUKKIT:
				return pl.hasPermission(node);
		}
		return false;
	}

	/**
	 * Get the primary group of the specified player
	 * 
	 * @param pl The checked player
	 * @return The primary group name
	 */

	public String getPrimaryGroup(Player pl) {
		String word = pl.getLocation().getWorld().getName();

		switch(handler) {
			case PEX:
				PermissionUser user = pexPlugin.getUser(pl);

				if(user.getGroupsNames(word).length > 0) {
					return user.getGroupsNames(word)[0];
				} else {
					return null;
				}
			case GM:
				if(word != null) {
					AnjoPermissionsHandler gmHandler = gmPlugin.getWorldsHolder().getWorldPermissions(word);
					return gmHandler.getGroup(pl.getName());
				} else {
					return null;
				}
			case VAULT:
				return vaultPermission.getPrimaryGroup(pl);
			case BUKKIT:
				new UnsupportedOperationException("BukkitPerms does not support getPrimaryGroup() function!");
		}
		return null;
	}

	/**
	 * Remove the specified group from the given player
	 * 
	 * @param pl Player object
	 * @param groupName The group removed from player
	 * @return true when successfully removed, else false
	 */

	public boolean playerRemoveGroup(Player pl, String groupName) {
		String word = pl.getLocation().getWorld().getName();

		switch(handler) {
			case PEX:
				pexPlugin.getUser(pl).removeGroup(groupName);
				return true;
			case GM:
				OverloadedWorldHolder owh = gmPlugin.getWorldsHolder().getWorldData(word);
				User user = owh.getUser(pl.getName());

				if(user == null) {
					return false;
				}

				if(user.getGroup().getName().equalsIgnoreCase(groupName)) {
					user.setGroup(owh.getDefaultGroup());
					return true;
				} else {
					Group group = owh.getGroup(groupName);

					if(group == null) {
						return false;
					}

					user.removeSubGroup(group);
				}
			case VAULT:
				vaultPermission.playerRemoveGroup(pl, groupName);
				return true;
			case BUKKIT:
				new UnsupportedOperationException("BukkitPerms does not support playerRemoveGroup() function!");
		}
		return false;
	}

	/**
	 * Add specified group to the given player
	 * 
	 * @param pl Player object
	 * @param groupName The group name of the added to player
	 * @return true when successfully added, else false
	 */

	public boolean playerAddGroup(Player pl, String groupName) {
		String world = pl.getLocation().getWorld().getName();

		switch(handler) {
			case PEX:
				PermissionGroup pexGroup = pexPlugin.getGroup(groupName);
				PermissionUser pexUser = pexPlugin.getUser(pl);

				if((pexGroup == null) || (pexUser == null)) {
					return false;
				} else {
					pexUser.addGroup(pexGroup);
					return true;
				}
			case GM:
				OverloadedWorldHolder owh = gmPlugin.getWorldsHolder().getWorldData(world);

				if(owh == null) {
					return false;
				}

				User gmUser = owh.getUser(pl.getName());
				if(gmUser == null) {
					return false;
				}

				Group gmGroup = owh.getGroup(groupName);
				if(gmGroup == null) {
					return false;
				}

				if(gmUser.getGroup().equals(owh.getDefaultGroup())) {
					gmUser.setGroup(gmGroup);
				} else if(gmGroup.getInherits().contains(gmUser.getGroup().getName().toLowerCase())) {
					gmUser.setGroup(gmGroup);
				} else {
					gmUser.addSubGroup(gmGroup);
				}
				return true;
			case VAULT:
				vaultPermission.playerAddGroup(pl, groupName);
				return true;
			case BUKKIT:
				new UnsupportedOperationException("BukkitPerms does not support playerAddGroup() function!");
		}

		return false;
	}

	public String getPrefix(Player pl) {
		switch(handler) {
			case PEX:
				return pexPlugin.getUser(pl.getName()).getPrefix();
			case GM:
				AnjoPermissionsHandler gmHandler = gmPlugin.getWorldsHolder().getWorldPermissions(pl);
				return gmHandler.getUserPrefix(pl.getName());
			case VAULT:
				throw new UnsupportedOperationException("getPrefix() method not supported by Vault!");
			case BUKKIT:
				throw new UnsupportedOperationException("getPrefix() method not supported by BukkitPerms!");
		}

		return null;
	}

	enum Perms {
		GM,
		PEX,
		VAULT,
		BUKKIT;
	}
	
}
