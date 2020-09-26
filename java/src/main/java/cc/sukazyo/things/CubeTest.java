package cc.sukazyo.things;

import cc.sukazyo.util.StringUtils;

public class CubeTest {
	
	public static void main (String[] args) throws InterruptedException {
		resolve("RU'");
		resolve("RU'RURURU'R'U'RR");
		resolve("RRDDR'U'RDDR'UR'");
		resolve("RU'R'U'");
		resolve("RU'R'");
		resolve("RRDDLLUU");
		resolve("RULD");
//		resolve("RU");
//		resolve("LD");
//		resolve("UF");
//		resolve("DB'");
//		resolve("FR'");
//		resolve("BU");
//		resolve("R'D");
//		resolve("L'U'");
//		resolve("U'B");
//		resolve("D'F");
//		resolve("F'R'");
//		resolve("B'R");
//		ThreeOrderRubikCube cube = ThreeOrderRubikCube.getDefault();
//		cube.simulate("R");
//		cube.echoState();
	}
	
	public static void resolve (String law) {
		ThreeOrderRubikCube cube = ThreeOrderRubikCube.getDefault();
		System.out.println("Simulating : " + law);
		int i = 0;
		System.out.print(i);
		do {
			cube.simulate(law);
			i++;
			StringUtils.deleteChars(String.valueOf(i).length());
			System.out.print(i);
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (!cube.isOK());
		System.out.println("\nDone!");
	}
	
}
