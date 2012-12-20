package com.Zolli.EnderCore.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class networkUtils {
	
	protected ReadableByteChannel rbc;
	
	/**
	 * Download The file at given url, and save to specified location
	 * @param Url The url from file is downloaded
	 * @param saveLocation The location on the file is saved
	 */
	public void downloadAndSave(String Url, String saveLocation) throws Exception {
		try {
			File file = new File(saveLocation);
			
			if(!file.exists()) {
				URL website = new URL(Url);
			    this.rbc = Channels.newChannel(website.openStream());
			}
		} catch (Exception e) {
			new Exception("Failed to retrieve file from definied location!");
		}
		
		try {
			File file = new File(saveLocation);
			
			if(!file.exists()) {
				file.getParentFile().mkdirs();
				FileOutputStream fos = new FileOutputStream(saveLocation);
			    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
			    fos.flush();
			    fos.close();
			}
		} catch (Exception e) {
			new Exception("Failed to save the downloaded file!");
		}
	}
	
	/**
	 * Get the contetn of specified url
	 * @param uri The url from contents get
	 * @return The url content
	 */
	public static String getContent(String uri) {
		try {
            URL url = new URL(uri);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            inputLine = in.readLine();
            in.close();
            return inputLine;
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * Compare given string and string form given location
	 * @param uri The location from string is given
	 * @param search The matched string
	 * @return True is two string is match (ignore case), false if not
	 */
	public static boolean compareResult(String uri, String search) {
		try {
            URL url = new URL(uri);
            URLConnection yc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;
            inputLine = in.readLine();
            in.close();

            if(inputLine.equalsIgnoreCase(search)) {
            	return true;
            } else {
            	return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
		return false;
	}
}
