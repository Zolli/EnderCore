package com.Zolli.EnderCore.File;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileUtils {
	
	/**
	 * File object
	 */
	private File file;
	
	/**
	 * BufferedReader object
	 */
	private BufferedReader bReader;
	
	/**
	 * BufferedWriter object
	 */
	private BufferedWriter bWriter;
	
	/**
	 * Constructor of this object
	 * @param file The file to be parsed
	 */
	public fileUtils (File file) {
		this.file = file;
		this.bReader = this.read();
		this.bWriter = this.write();
	}
	
	/**
	 * Write the give string to end of the file and add a new line for future addition
	 * @param s The string to be written
	 */
	public void writeLineToEnd(String s) {
		try {
			this.bWriter.write(s);
			this.bWriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the file defined in constructor
	 * @return BufferedReader object if successful, null when open fails
	 */
	private BufferedReader read() {
		try {
			BufferedReader bReader = new BufferedReader(new FileReader(this.file));
			return bReader;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Open file defined in constructor for writing
	 * @return BufferedWriter object if successful, null when open fails
	 */
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
