package com.Zolli.EnderCore.Localization;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.bukkit.configuration.file.YamlConfiguration;

import com.Zolli.EnderCore.EnderCore;
import com.Zolli.EnderCore.API.ILocalization;

public class localizationManager implements ILocalization{
	
	/**
	 * Plugin main class
	 */
	private EnderCore plugin;
	
	/**
	 * The language file ne we want to load, without extension (e.g. en.yml => en)
	 */
	private String language;
	
	/**
	 * The configuration object which contains the language file
	 */
	private YamlConfiguration langConf = null;
	
	/**
	 * Constructor
	 * @param instance plugin main class
	 */
	public localizationManager(EnderCore instance) {
		this.plugin = instance;
		this.language = plugin.config.getString("localization");
		File languageFile = new File(plugin.getDataFolder() + File.separator + "lang" + File.separator + this.language + ".yml");
		
		//If language file is not exist, create directory, and copy the resource. If resource not found in the jar, set the default localization to English
		this.checkAndLoadFile(languageFile);
	}
	
	/**
	 * Get a string from currently loaded localization file
	 * @param path The string path
	 * @return Localized string
	 */
	public String getLocalizedString(String path) {
		return langConf.getString(path);
	}
	
	/**
	 * Get the language author name
	 * @return the author name
	 */
	public String getLanguageAuthor() {
		return langConf.getString("about.author");
	}
	
	/**
	 * Get the language real name defined in file
	 * @return Language name
	 */
	public String getLanguageRealName() {
		return langConf.getString("about.langName");
	}
	
	/**
	 * Get the language file name which currently in use
	 * @return the language filename
	 */
	public String getLanguageFilename() {
		return this.language + ".yml";
	}
	
	/**
	 * Check the localization file exist is given location, is not exist check in jar and copy it. If file
	 * not found in jar load the default configuration file
	 * @param languageFile The language file
	 */
	private void checkAndLoadFile(File languageFile) {
		if(!languageFile.exists()) {
			languageFile.getParentFile().mkdirs();

			if(this.isInJar()) {
				copy(plugin.getResource(this.language + ".yml"), languageFile);
				File lang = new File(plugin.getDataFolder() + File.separator + "lang" + File.separator + this.language + ".yml");
				this.loadLocalization(lang);
			} else {
				File defaultFile = new File(plugin.getDataFolder() + File.separator + "lang" + File.separator + "en.yml");
				this.loadLocalization(defaultFile);
			}
			
		} else {
			File lang = new File(plugin.getDataFolder() + File.separator + "lang" + File.separator + this.language + ".yml");
			this.loadLocalization(lang);
		}
	}
	
	/**
	 * load the language file into YamlConfiguration object
	 * @param file language file
	 */
	private void loadLocalization(File file) {
		this.langConf = new YamlConfiguration();
		
		try {
			this.langConf.load(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Return true is definied language file exist inside the jar file, false elsewhere
	 * @return boolean
	 */
	public boolean isInJar() {
		InputStream is = plugin.getResource(this.language + ".yml");
		
		if(is != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Copy file location to another location
	 * @param in InputStream object
	 * @param file File to be copied
	 */
	private void copy(InputStream in, File file) {
		try {
			OutputStream out = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
			out.write(buf, 0, len);
		}
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
