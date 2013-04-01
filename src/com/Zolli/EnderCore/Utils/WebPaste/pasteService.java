package com.Zolli.EnderCore.Utils.WebPaste;

import java.net.URL;

import com.Zolli.EnderCore.Exception.pasteFailedException;

public interface pasteService {

	String encodeData(String data);
	URL getPostURL();
	String postData(String encodeData)throws pasteFailedException;
	
}
