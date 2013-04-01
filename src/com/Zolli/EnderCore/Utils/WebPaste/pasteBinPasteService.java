package com.Zolli.EnderCore.Utils.WebPaste;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.Zolli.EnderCore.Exception.pasteFailedException;

public class pasteBinPasteService implements pasteService {
	
	private boolean isPrivate;
	
	public pasteBinPasteService(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	@Override
	public String encodeData(String data) {
		try {
			String encData = URLEncoder.encode("api_dev_key", "UTF-8") + "=" + URLEncoder.encode("1b78eb7585cf34930da9e0cd75bd1109", "UTF-8");
            encData += "&" + URLEncoder.encode("api_option", "UTF-8") + "=" + URLEncoder.encode("paste", "UTF-8");
            encData += "&" + URLEncoder.encode("api_paste_expire_date", "UTF-8") + "=" + URLEncoder.encode("1M", "UTF-8");
            encData += "&" + URLEncoder.encode("api_paste_code", "UTF-8") + "=" + URLEncoder.encode(data, "UTF-8");
            encData += "&" + URLEncoder.encode("api_paste_private", "UTF-8") + "=" + URLEncoder.encode(this.isPrivate ? "1" : "0", "UTF-8");
            encData += "&" + URLEncoder.encode("api_paste_format", "UTF-8") + "=" + URLEncoder.encode("yaml", "UTF-8");
            return encData;
		} catch(UnsupportedEncodingException e) {
			return "";
		}
	}

	@Override
	public URL getPostURL() {
		try {
			return new URL("http://pastebin.com/api/api_post.php");
		} catch(MalformedURLException e) {
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
			String pasteBinUrl = "";
			while ((line = rd.readLine()) != null) {
				pasteBinUrl = line;
			}
			wr.close();
			rd.close();
			return pasteBinUrl;
		} catch(Exception e) {
			throw new pasteFailedException(e);
		}
	}
	
	

}
