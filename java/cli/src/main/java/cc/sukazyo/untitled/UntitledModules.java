package cc.sukazyo.untitled;

import cc.sukazyo.untitled.simulator.rubikcube.UntModuleCubeLawCyclePeriod;
import cc.sukazyo.untitled.util.web.UntModuleWebReqTest;

import java.util.HashMap;
import java.util.Map;

public class UntitledModules {
	
	public static Map<String, IModule> modules = new HashMap<>();
	
	private static class Exitor implements IModule {
		
		@Override
		public IExitStatus execute () {
			return new IExitStatus.SystemEnd(){};
		}
		
	}
	
	public static void loadAllModule () {
		modules.put("exit", new Exitor());
		modules.put("cube", new UntModuleCubeLawCyclePeriod());
		modules.put("webreq", new UntModuleWebReqTest());
	}
	
}
