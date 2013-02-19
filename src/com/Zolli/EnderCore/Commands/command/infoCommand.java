package com.Zolli.EnderCore.Commands.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;

import com.Zolli.EnderCore.Commands.ECCommand;
import com.Zolli.EnderCore.Commands.consoleMenuBuilder;

public class infoCommand implements ECCommand {
	
	private List<String> permissions = new ArrayList<String>();
	private List<String> examples = new ArrayList<String>();
	
	public infoCommand() {
		this.permissions.add("ec.basic.info");
		this.permissions.add("ec.info");
		
		this.examples.add("/ec info [param1] [param2]");
		this.examples.add("/ec info [param1] <param2>");
	}
	
	@Override
	public String getName() {
		return "info";
	}

	@Override
	public List<String> getPermission() {
		return this.permissions;
	}
	
	@Override
	public List<String> getExample() {
		return this.examples;
	}
	
	@Override
	public int getArgsLength() {
		return 2;
	}
	
	@Override
	public boolean isAccessibleFromConsole() {
		return false;
	}
	
	@Override
	public boolean execute(CommandSender sender, String[] allArgs, String chainedParams) {
		consoleMenuBuilder m = new consoleMenuBuilder("EnderCore - Info");
		m.addMenuItem("/ec info - Display this message");
		m.addMenuItem("/ec help - Display short help message");
		m.addMenuItem("/ec purge - Pureg all data in database");
		m.addMenuItem("/ec status - Display status info");
		
		m.build(sender);
		
		System.out.println(chainedParams);
		return false;
	}
	
}