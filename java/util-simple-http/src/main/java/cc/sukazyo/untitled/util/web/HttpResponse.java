package cc.sukazyo.untitled.util.web;

import java.util.List;
import java.util.Map;

public class HttpResponse {
	
	public int responseCode;
	public String message;
	public long date;
	public String contentType;
	public String contentEncoding;
	
	public Map<String, List<String>> header;
	
	public HttpCookies cookies;
	public String body;
	
	protected HttpResponse() {}
	
}
