package cc.sukazyo.util.web;

import cc.sukazyo.untitled.IExitStatus;
import cc.sukazyo.untitled.IModule;
import cc.sukazyo.untitled.UntitledTexts;

import java.util.Scanner;

public class UntModuleWebReqTest implements IModule {
	
	@Override
	public IExitStatus execute () {
		
		System.out.print(UntitledTexts.getText("module.util.web.test.prompt"));
		String input = new Scanner(System.in).nextLine();
		try {
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
			System.out.println();
			return new IExitStatus.Failed(){};
		}
		
		return new IExitStatus.Success(){};
		
	}
	
}
