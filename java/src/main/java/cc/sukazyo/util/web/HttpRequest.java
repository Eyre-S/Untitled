package cc.sukazyo.util.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
	
	public static HttpResponse sendGetRequest (String url, WebCommonUserAgent userAgent) throws IOException {
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		con.setRequestMethod("GET");
		
		con.setRequestProperty("User-Agent", userAgent.toString());
		
		HttpResponse response = new HttpResponse();
		response.responseCode = con.getResponseCode();
		response.message = con.getResponseMessage();
		response.date = con.getDate();
		response.contentType = con.getContentType();
		response.contentEncoding = con.getContentEncoding();
		response.cookies = HttpCookies.matchCookiesFromHeader(con.getHeaderFields());
		response.header = con.getHeaderFields();
		
		{
			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuilder tmp = new StringBuilder();
			while ((inputLine = in.readLine()) != null) {
				tmp.append(inputLine);
			}
			in.close();
			response.body = tmp.toString();
		}
		
		return response;
		
	}
	
}
