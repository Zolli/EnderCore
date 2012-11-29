package com.Zolli.EnderCore.Storage;

import com.Zolli.EnderCore.Utils.networkUtils;

public class Storage {
	
	public void Storage(storageEngine engine) {
		if(!engine.getDownloadUrl().equalsIgnoreCase("none")) {
			this.downloadDriver(engine.getDownloadUrl(), engine.getFileName());
		}
	}
	
	private void downloadDriver(String Url, String savedFileName) {
		networkUtils.downloadAndSave(Url, "./" + savedFileName);
	}
	
	public enum storageEngine {
		SQLITE("http://", "sqlite.jar"),
		MySQL("http://", "mysql.jar"),
		H2DB("http://", "h2.jar"),
		FLATFILE("none"),
		NBT("none");
		
		private String driverUrl;
		private String fileName;
		
		private storageEngine(String s) {
			this.driverUrl = s;
		}
		
		private storageEngine(String s, String f) {
			this.driverUrl = s;
		}
		
		public String getDownloadUrl() {
			return this.driverUrl;
		}
		
		public String getFileName() {
			return this.fileName;
		}
		
	}
	
}
