package cc.sukazyo.untitled.util.web;

import cc.sukazyo.untitled.IExitStatus;
import cc.sukazyo.untitled.IModule;
import cc.sukazyo.untitled.UntitledTexts;

import java.io.IOException;
import java.util.Scanner;

public class UntModuleWebReqTest implements IModule {
	
	@Override
	public IExitStatus execute () {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print(UntitledTexts.getText("module.util.web.test.prompt"));
		String input = scanner.nextLine();
		while (!input.equals("webreq://exit")) {
			if (input.equals("webreq://ip")) {
				try {
					HttpResponse response = HttpRequest.sendGetRequest("http://23.80.5.90/ip.php", WebCommonUserAgent.CHROME);
					System.out.println(response.body.substring(297, 312));
				} catch (IOException e) {
					System.out.println(UntitledTexts.getText("module.util.web.test.failed"));
					e.printStackTrace(System.out);
				}
			} else if (input.startsWith("webreq://")) {
				System.out.println("Unknown webreq action.");
			} else try {
				System.out.println();
				HttpResponse response = HttpRequest.sendGetRequest(input, WebCommonUserAgent.CHROME);
				System.out.println(UntitledTexts.getText("module.util.web.test.resp-code") + response.responseCode);
				System.out.println(UntitledTexts.getText("module.util.web.test.message") + response.message);
				System.out.println();
				System.out.println(UntitledTexts.getText("module.util.web.test.date") + response.date);
				System.out.println();
				System.out.println(UntitledTexts.getText("module.util.web.test.header"));
				response.header.forEach((k, v) -> System.out.println(k + "=" + v));
				System.out.println(UntitledTexts.getText("module.util.web.test.end"));
				System.out.println();
				System.out.println(UntitledTexts.getText("module.util.web.test.cookie"));
				if (response.cookies == null) System.out.println("null");
				else System.out.println(response.cookies.toStringMultiLine());
				System.out.println(UntitledTexts.getText("module.util.web.test.end"));
				System.out.println();
				System.out.println(UntitledTexts.getText("module.util.web.test.content-type") + response.contentType);
				System.out.println();
				System.out.println(UntitledTexts.getText("module.util.web.test.content-encoding") + response.contentEncoding);
				System.out.println();
				System.out.println(UntitledTexts.getText("module.util.web.test.body"));
				System.out.println(response.body);
				System.out.println(UntitledTexts.getText("module.util.web.test.end"));
				System.out.println();
			} catch (Exception e) {
				System.out.println();
				System.out.println(UntitledTexts.getText("module.util.web.test.failed"));
				e.printStackTrace(System.out);
				System.out.println();
			}
			System.out.print(UntitledTexts.getText("module.util.web.test.prompt"));
			input = scanner.nextLine();
		}
		
		System.out.println();
		return new IExitStatus.Success(){};
		
	}
	
}
