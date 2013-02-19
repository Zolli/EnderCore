package com.Zolli.EnderCore.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

/**
 * Make user friendly menu by arguments, makd menu headers dynamically
 * @author Zolli
 * @version 1.0
 */
public class consoleMenuBuilder {
	
	/**
	 * Store menu's header
	 */
	private String menuHeader = null;
	
	/**
	 * Store a line maximum length in in-game console
	 */
	private int lineLength = 53;
	
	/**
	 * Store menu items for building menu
	 */
	private List<String> menuItems = new ArrayList<String>();
	
	/**
	 * Store the final menu line-by-line
	 */
	private List<String> returnLines = new ArrayList<String>();
	
	/**
	 * Player object who receiving the menu
	 */
	private Player pl;
	
	/**
	 * Constructor
	 * @param player The message receiver
	 * @param header The menu header title
	 */
	public consoleMenuBuilder(Player player, String header) {
		this.pl = player;
		this.menuHeader = header;
	}
	
	/**
	 * Add item to building list
	 * @param item Menu item
	 */
	public void addMenuItem(String item) {
		this.menuItems.add(item);
	}
	
	/**
	 * Build the menu header.
	 * Calculate the borders dynamically and append lines to array
	 */
	private void buildHeader() {
		int singleTabulator = (this.lineLength - this.menuHeader.length()-2)/2;
		StringBuilder sb = new StringBuilder();
		
		sb.append("<");
		for( int i = 0; i < singleTabulator; i++ ) {
            sb.append("-");
        }
		sb.append(" " + this.menuHeader + " ");
		for( int i = 0; i < singleTabulator; i++ ) {
            sb.append("-");
        }
		sb.append(">");
		
		this.returnLines.add(sb.toString());
	}
	
	/**
	 * Building menu body by menuItems array
	 * Append lines to return array
	 */
	private void buildBody() {
		for(String s : this.menuItems) {
			this.returnLines.add("> " + s);
		}
	}
	
	/**
	 * Build footer line for menu's
	 * Append lines to return array
	 */
	private void buildFooter() {
		String footer;
		footer = "<";
		for( int i = 0; i < this.lineLength-2; i++ ) {
            footer = footer + "-";
        }
		footer = footer + ">";
		this.returnLines.add(footer.toString());
	}
	
	/**
	 * Send all menu lines to defined player in constructor
	 */
	public void build() {
		this.buildHeader();
		this.buildBody();
		this.buildFooter();
		String[] s = this.returnLines.toArray(new String[this.returnLines.size()]);
		pl.sendMessage(s);
	}
	
}
