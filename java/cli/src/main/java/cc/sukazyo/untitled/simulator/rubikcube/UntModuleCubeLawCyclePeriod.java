package cc.sukazyo.untitled.simulator.rubikcube;

import cc.sukazyo.untitled.util.string.StringR;
import cc.sukazyo.untitled.IExitStatus;
import cc.sukazyo.untitled.IModule;
import cc.sukazyo.untitled.UntitledTexts;

import java.util.Scanner;

public class UntModuleCubeLawCyclePeriod implements IModule {
	
	@Override
	public IExitStatus execute () {
		System.out.print(UntitledTexts.getText("module.things.cube.cycle-period.input-prompt"));
		System.out.print(UntitledTexts.getText("module.things.cube.cycle-period.running"));
		int result = ThreeOrderRubikCube.calcLawCyclePeriod(new Scanner(System.in).nextLine());
		StringR.deleteChars(UntitledTexts.getText("module.things.cube.cycle-period.running").length());
		System.out.println(
				UntitledTexts.getText("module.things.cube.cycle-period.result") +
				result
		);
		return new IExitStatus.Success(){};
	}
	
}
