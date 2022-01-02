package cc.sukazyo.untitled.util.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cookie 数据
 *
 * 可以储存复数条 Cookie 记录
 *
 * TODO hostOnly, secure, httpOnly 等属性待支持
 */
public class HttpCookies {
	
	public static class CookieValue {
		String data;
		String host;
		String path;
		long expireDate;
		boolean hostOnly = false;
//		boolean session = false;
		boolean secure = false;
		boolean httpOnly = false;
		
		public CookieValue (String data, String host, String path, long expireDate) {
			this.data = data;
			this.host = host;
			this.path = path;
			this.expireDate = expireDate;
		}
		
		@Override
		public String toString () {
			return String.format("{%s, %s%s, %d, %b %b %b}", data, host, path, expireDate, hostOnly, secure, httpOnly);
		}
		
	}
	
	private final Map<String, CookieValue> data;
	
	public static final Matcher requestMatcher = Pattern.compile("Set-Cookie=\\[(.*?)]").matcher("");
	public static final Matcher dateMatcher = Pattern.compile("expires=(.*?);").matcher("");
	
	/* 下列是用于解析 HTTP 编码的Cookie组时使用的正则匹配器 */
	public static final Matcher expiresMatcher = Pattern.compile("expiresLong=(.*?);").matcher("");
	public static final Matcher maxAgeMatcher = Pattern.compile("Max-Age=(.*?);").matcher("");
	public static final Matcher domainMatcher = Pattern.compile("domain=(.*?);").matcher("");
	public static final Matcher pathMatcher = Pattern.compile("path=(.*?);").matcher("");
	
	public HttpCookies () {
		data = new HashMap<>();
	}
	
	/**
	 * 添加一条 Cookie 记录
	 * 已存在的 Cookie 记录将会被覆写
	 *
	 * @param key Cookie键
	 * @param value Cookie值和附加数据
	 * @return 更新后的自身
	 */
	public HttpCookies append (String key, CookieValue value) {
		data.put(key, value);
		return this;
	}
	
	/**
	 * 合并 Cookie 组
	 * 已存在的 Cookie 条目会被覆写
	 *
	 * @return 更新后的自身
	 */
	public HttpCookies append (HttpCookies cookies) {
		data.putAll(cookies.data);
		return this;
	}
	
	@Override
	public String toString () {
		StringBuilder str = new StringBuilder("{{{");
		data.forEach( (k, v) -> str.append(k).append(v).append(",") );
		return str.append("}}}").toString();
	}
	
	public String toStringMultiLine() {
		StringBuilder str = new StringBuilder();
		data.forEach( (k, v) -> str.append(k).append(v).append("\n") );
		return str.deleteCharAt(str.length()-1).toString();
	}
	
	/**
	 * 从HTTP请求头中提取出发送的Cookie数据
	 *
	 * @param header HTTP请求头
	 *
	 * @return HttpCookies 从HTTP请求头中提取出的需要更新的Cookie组对象
	 */
	public static HttpCookies matchCookiesFromHeader (Map<String, List<String>> header) {
		String strData = header.toString();
		requestMatcher.reset(strData);
		if (requestMatcher.find()) {
			String strCookies = requestMatcher.group(1);
			return parseCookiesStr(strCookies);
		}
		return null;
	}
	
	/**
	 * 将以 HTTP 方案编码的Cookie组解析为Cookie组对象
	 *
	 * TODO 由于时区问题，过期时间可能出现不正确的现象
	 *
	 * @param strCookies 以 HTTP 方案编码的字符串Cookie组
	 *
	 * @return HttpCookies 解析出来的Cookie组
	 */
	public static HttpCookies parseCookiesStr (String strCookies) {
		do {
			dateMatcher.reset(strCookies);
			if (dateMatcher.find()) {
				SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd-MMM-yyyy HH:mm:ss 'GMT'", Locale.US);
				try {
					strCookies = dateMatcher.replaceFirst("expiresLong=" + sdf.parse(dateMatcher.group(1)).getTime() + ";");
				} catch (ParseException e) {
					e.printStackTrace();
				}
			} else break;
		} while (true);
		HttpCookies cookies = new HttpCookies();
		strCookies = strCookies.replaceAll(" ", "");
		for (String single : strCookies.split(",")) {
			String meta = single.substring(single.indexOf(';')+1) + ';';
			long expire;
			if (maxAgeMatcher.reset(meta).find()) {
				expire = Long.parseLong(maxAgeMatcher.group(1)) + System.currentTimeMillis();
			} else {
				expire = Long.parseLong(expiresMatcher.reset(meta).find()?expiresMatcher.group(1):"-1");
			}
			cookies.append(
					single.substring(0, single.indexOf('=')),
					new CookieValue(
							single.substring(single.indexOf('=')+1, single.indexOf(';')),
							domainMatcher.reset(meta).find()?domainMatcher.group(1):"",
							pathMatcher.reset(meta).find()?pathMatcher.group(1):"",
							expire
					)
			);
		}
		return cookies;
	}
	
}
