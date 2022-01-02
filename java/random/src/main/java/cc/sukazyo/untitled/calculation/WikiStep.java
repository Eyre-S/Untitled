package cc.sukazyo.untitled.calculation;

import cc.sukazyo.untitled.util.web.HttpRequest;
import cc.sukazyo.untitled.util.web.WebCommonUserAgent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class WikiStep {
	
	public final String siteRoot;
	public final String linkRoot;
	public final String pageRoot;
	
	public final String htmlContentId;
	public final String htmlTitleId;
	
	private final Map<String, Integer> minToPage = new HashMap<>();
	private List<String> pages;
	private int location = 0;
	
	public WikiStep (String wikiSiteRoot, String wikiInsideLinkRoot, String rootPageName) throws UnsupportedEncodingException {
		siteRoot = wikiSiteRoot;
		linkRoot = wikiInsideLinkRoot;
		pageRoot = rootPageName;
		htmlContentId = "mw-content-text";
		htmlTitleId = "firstHeading";
		pages = Collections.singletonList(rootPageName);
		minToPage.put(rootPageName, location);
	}
	
	public void Calculate (int depth) {
		while (location < depth) {
			try {
				final List<String> nextPages = new ArrayList<>();
				location++;
				System.out.printf("[INFO]===->=== depth %d ===<-===%n", location);
				for (String next : pages) {
					System.out.printf("[INFO]>>>::page<%s>%n", next);
					final String requested = siteRoot + linkRoot + URLEncoder.encode(next, "UTF-8");
					System.out.printf("[INFO]>>>::request %s%n", requested);
					Document doc = Jsoup.parse(HttpRequest.sendGetRequest(
							requested,
							WebCommonUserAgent.CHROME,
							false
					).body);
					for (Element tag : doc.getElementById(htmlContentId).getElementsByTag("a")) {
						if (tag.hasAttr("href")) {
							String tmp = tag.attr("href");
							if (tmp.startsWith(linkRoot) && tmp.indexOf(':')==-1) {
								tmp = tmp.substring(linkRoot.length());
								if (tmp.indexOf('#')!=-1) tmp = tmp.substring(0, tmp.indexOf('#'));
								String linkTo = URLDecoder.decode(tmp, "UTF-8");
								System.out.printf("[INFO]get>%s(%s)%n", linkTo, tag.attr("href"));
								if (!minToPage.containsKey(linkTo)) {
									minToPage.put(linkTo, location);
									nextPages.add(linkTo);
								}
							} else {
								System.out.println("[INFO]pass>" + tmp);
							}
						}
					}
					pages = nextPages;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public String write () {
		return minToPage.toString();
	}
	
}
