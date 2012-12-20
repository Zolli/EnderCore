package com.Zolli.EnderCore.Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.bukkit.craftbukkit.libs.jline.internal.InputStreamReader;

public class fileUtils {

	public static String streamToString(InputStream in) {
		if (in != null) {
		    Writer writer = new StringWriter();

		    char[] buffer = new char[1024];
		    try {
		        Reader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		        int n;
		        while ((n = reader.read(buffer)) != -1)
		            writer.write(buffer, 0, n);
		    } catch (IOException e) {
				e.printStackTrace();
			} finally {
		        try {
					in.close();
				} catch (IOException e) {}
		    }
		    return writer.toString();
		} else     
		    return "";
	}
	
}
