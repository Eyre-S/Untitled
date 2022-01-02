package cc.sukazyo.untitled.util.web;

public enum WebCommonUserAgent {
	CHROME("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.111 Safari/537.36"),
	FIREFOX("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:81.0) Gecko/20100101 Firefox/81.0"),
	SAFARI("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_3) AppleWebKit/537.75.14 (KHTML, like Gecko) Version/7.0.3 Safari/7046A194A"),
	IE10("Mozilla/5.0 (MSIE 10.0; Windows NT 6.1; Trident/5.0)"),
	IE6("Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0; WOW64; Trident/4.0; SLCC1)"),
	KITKAT("Mozilla/5.0 (Linux; Android 4.4.2; Nexus 4 Build/KOT49H) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/34.0.1847.114 Mobile Safari/537.36"),
	OPERA("Opera/9.80 (Windows NT 6.0) Presto/2.12.388 Version/12.14"),
	NULL("")
	;
	
	private String agentCode;
	
	WebCommonUserAgent(String agentCode) {
		this.agentCode = agentCode;
	}
	
	private WebCommonUserAgent setAgentCode(String agentCode) {
		this.agentCode = agentCode;
		return this;
	}
	
	public static WebCommonUserAgent customUserAgent(String agentCode) {
		return NULL.setAgentCode(agentCode);
	}
	
	@Override
	public String toString() {
		return agentCode;
	}
	
}
