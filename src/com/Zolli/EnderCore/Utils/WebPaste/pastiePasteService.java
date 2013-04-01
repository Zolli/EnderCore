package com.Zolli.EnderCore.Utils.WebPaste;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Zolli.EnderCore.Exception.pasteFailedException;

public class pastiePasteService implements pasteService {

	private boolean isPrivate;

    public pastiePasteService(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }
	
	@Override
	public String encodeData(String data) {
		try {
			String encData = URLEncoder.encode("paste[authorization]", "UTF-8") + "=" + URLEncoder.encode("burger", "UTF-8"); // burger is magic
			encData += "&" + URLEncoder.encode("paste[restricted]", "UTF-8") + "=" + URLEncoder.encode(this.isPrivate ? "1" : "0", "UTF-8");
			encData += "&" + URLEncoder.encode("paste[parser_id]", "UTF-8") + "=" + URLEncoder.encode("6", "UTF-8"); // 6 is plain text
			encData += "&" + URLEncoder.encode("paste[body]", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8");
            return encData;
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

	@Override
	public URL getPostURL() {
		try {
			return new URL("http://pastie.org/pastes");
		} catch (MalformedURLException e) {
			return null;
		}
	}

	@Override
	public String postData(String data) throws pasteFailedException {
		try {
			URLConnection conn = this.getPostURL().openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(this.encodeData(data));
			wr.flush();
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			String pastieUrl = "";
			Pattern pastiePattern = this.getURLMatchingPattern();
			while ((line = rd.readLine()) != null) {
				Matcher m = pastiePattern.matcher(line);
				if (m.matches()) {
					String pastieID = m.group(1);
					pastieUrl = this.formatURL(pastieID);
				}
			}
			wr.close();
			rd.close();
			return pastieUrl;
		} catch (Exception e) {
			throw new pasteFailedException(e);
		}
	}
	
	private Pattern getURLMatchingPattern() {
		if (this.isPrivate) {
	    	return Pattern.compile(".*http://pastie.org/.*key=([0-9a-z]+).*");
	    } else {
	    	return Pattern.compile(".*http://pastie.org/([0-9]+).*");
	    }
	}

	private String formatURL(String pastieID) {
		return "http://pastie.org/" + (this.isPrivate ? "private/" : "") + pastieID;
	}

}
