package com.Zolli.EnderCore.Utils;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class networkUtils {
	
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
