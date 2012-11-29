package com.Zolli.EnderCore.Storage;

public class Storage {
	
	public void Storage(storageEngine e) {
		
	}
	
	public enum storageEngine {
		SQLITE("http://"),
		MySQL("HTTP://"),
		FLATFILE("none"),
		NBT("none");
		
		private String driverUrl;
		
		private storageEngine(String s) {
			this.driverUrl = s;
		}
		
		public String getDownloadUrl() {
			return this.driverUrl;
		}
		
	}
	
}
