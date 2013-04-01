package com.Zolli.EnderCore.Utils.WebPaste;

import java.io.PrintWriter;
import java.io.StringWriter;

public class exceptionSerializer {
	
	Throwable exception;
	StringWriter sw;
	
	public exceptionSerializer(Throwable t) {
		this.exception = t;
		this.sw = new StringWriter();
	}
	
	public String serialize() {
		this.exception.printStackTrace(new PrintWriter(this.sw));
		return this.sw.toString();
	}
	
}
