package com.Zolli.EnderCore.Utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class networkUtils {
	
	/**
	 * Download The file at given url, and save to specified location
	 * @param Url The url from file is downloaded
	 * @param saveLocation The location on the file is saved
	 */
	public static void downloadAndSave(String Url, String saveLocation) {
		try {
			URL website = new URL(Url);
		    ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		    FileOutputStream fos = new FileOutputStream(saveLocation);
		    fos.getChannel().transferFrom(rbc, 0, 1 << 24);
		} catch (Exception e) {
			e.printStackTrace();
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
