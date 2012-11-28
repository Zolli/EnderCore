package com.Zolli.EnderCore.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileUtils {
	
	private File file;
	private BufferedReader bReader;
	private BufferedWriter bWriter;
	
	public fileUtils (File file) {
		this.file = file;
		this.bReader = this.read();
		this.bWriter = this.write();
	}
	
	public void writeLineToEnd(String s) {
		try {
			this.bWriter.write(s);
			this.bWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private BufferedReader read() {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(this.file));
			return bReader;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private BufferedWriter write() {
		try {
			BufferedWriter bWriter = new BufferedWriter(new FileWriter(this.file));
			return bWriter;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
