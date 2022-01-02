package cc.sukazyo.untitled;

import java.util.HashMap;
import java.util.Map;

public class UntitledTexts {
	
	public static Map<String, String> texts = new HashMap<>();
	
	static { texts.put("core.unknown", "Request Module Not Found!"); }
	
	static { texts.put("module.things.cube.cycle-period.input-prompt", "input the law below\n::"); }
	static { texts.put("module.things.cube.cycle-period.running", "Calculating..."); }
	static { texts.put("module.things.cube.cycle-period.result", "Result: "); }
	
	static { texts.put("module.util.web.test.prompt", "url-|"); }
	static { texts.put("module.util.web.test.resp-code", "Response Code = "); }
	static { texts.put("module.util.web.test.message", "Message = "); }
	static { texts.put("module.util.web.test.date", "Date = "); }
	static { texts.put("module.util.web.test.header", "Header ==="); }
	static { texts.put("module.util.web.test.cookie", "Cookies ==="); }
	static { texts.put("module.util.web.test.content-type", "Content type = "); }
	static { texts.put("module.util.web.test.content-encoding", "Content encoding = "); }
	static { texts.put("module.util.web.test.body", "BODY ==="); }
	static { texts.put("module.util.web.test.end", "==="); }
	static { texts.put("module.util.web.test.failed", "===\nFAILED REQUEST\n==="); }
	
	public static String getText (String key) {
		return texts.get(key);
	}
	
}
