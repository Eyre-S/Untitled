package cc.sukazyo.untitled;

import java.util.Scanner;

public class UntitledCLI {
	
	public static void main (String[] args) {
		
		UntitledModules.loadAllModule();
		
		Scanner input = new Scanner(System.in);
		
		IExitStatus lastStatus = null;
		IModule lastCalledModule;
		while (!(lastStatus instanceof IExitStatus.SystemEnd)) {
			System.out.print("> ");
			lastCalledModule = UntitledModules.modules.get(input.nextLine());
			if (lastCalledModule == null) {
				System.out.println(UntitledTexts.getText("core.unknown"));
				lastStatus = null;
			} else {
				lastStatus = lastCalledModule.execute();
			}
		}
		
	}
	
}
