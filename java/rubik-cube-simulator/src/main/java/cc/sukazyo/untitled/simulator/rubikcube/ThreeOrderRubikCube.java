package cc.sukazyo.untitled.simulator.rubikcube;

@SuppressWarnings("unused")
public class ThreeOrderRubikCube {
	
	Face front;
	Face up;
	Face down;
	Face back;
	Face right;
	Face left;
	
	static class Face {
		
		private final CubeColor[] face;
		
		Face (CubeColor filler) {
			face = new CubeColor[]{filler, filler, filler, filler, filler, filler, filler, filler, filler};
		}
		
		void rotate () {
			CubeColor[] now = face.clone();
			face[1] = now[3];
			face[2] = now[4];
			face[3] = now[5];
			face[4] = now[6];
			face[5] = now[7];
			face[6] = now[8];
			face[7] = now[1];
			face[8] = now[2];
		}
		
		boolean isOk () {
			for (int i = 1; i < 9; i++) {
				if (!face[i].equals(face[0])) {
					return false;
				}
			}
			return true;
		}
		
	}
	
	private ThreeOrderRubikCube () {}
	
	public static ThreeOrderRubikCube getDefault () {
		ThreeOrderRubikCube cube = new ThreeOrderRubikCube();
		cube.front = new Face(CubeColor.BLUE);
		cube.up = new Face(CubeColor.YELLOW);
		cube.down = new Face(CubeColor.WHITE);
		cube.back = new Face(CubeColor.GREEN);
		cube.right = new Face(CubeColor.PINK);
		cube.left = new Face(CubeColor.RED);
		return cube;
	}
	
	public static int calcLawCyclePeriod (String law) {
		ThreeOrderRubikCube cube = ThreeOrderRubikCube.getDefault();
		int i = 0;
		do {
			cube.simulate(law);
			i++;
		} while (!cube.isOK());
		return i;
	}
	
	public boolean isOK() {
		return front.isOk() && up.isOk() && down.isOk() && back.isOk() && right.isOk() && left.isOk();
	}
	
	public void turnRightOpp () {
		right.rotate();
		CubeColor[] turned = turnThree(new CubeColor[]{front.face[5], front.face[4], front.face[3], up.face[5], up.face[4], up.face[3], back.face[1], back.face[8], back.face[7], down.face[5], down.face[4], down.face[3],});
		front.face[5] = turned[0];
		front.face[4] = turned[1];
		front.face[3] = turned[2];
		up.face[5] = turned[3];
		up.face[4] = turned[4];
		up.face[3] = turned[5];
		back.face[1] = turned[6];
		back.face[8] = turned[7];
		back.face[7] = turned[8];
		down.face[5] = turned[9];
		down.face[4] = turned[10];
		down.face[3] = turned[11];
	}
	
	public void turnRight () { turnRightOpp();turnRightOpp();turnRightOpp(); }
	
	public void turnLeftOpp () {
		left.rotate();
		CubeColor[] turned = turnThree(new CubeColor[]{front.face[1], front.face[8], front.face[7], down.face[1], down.face[8], down.face[7], back.face[5], back.face[4], back.face[3], up.face[1], up.face[8], up.face[7]});
		front.face[1] = turned[0];
		front.face[8] = turned[1];
		front.face[7] = turned[2];
		down.face[1] = turned[3];
		down.face[8] = turned[4];
		down.face[7] = turned[5];
		back.face[5] = turned[6];
		back.face[4] = turned[7];
		back.face[3] = turned[8];
		up.face[1] = turned[9];
		up.face[8] = turned[10];
		up.face[7] = turned[11];
	}
	
	public void turnLeft () { turnLeftOpp();turnLeftOpp();turnLeftOpp(); }
	
	public void turnUpOpp () {
		up.rotate();
		CubeColor[] turned = turnThree(new CubeColor[]{front.face[1], front.face[2], front.face[3], left.face[1], left.face[2], left.face[3], back.face[1], back.face[2], back.face[3], right.face[1], right.face[2], right.face[3]});
		front.face[1] = turned[0];
		front.face[2] = turned[1];
		front.face[3] = turned[2];
		left.face[1] = turned[3];
		left.face[2] = turned[4];
		left.face[3] = turned[5];
		back.face[1] = turned[6];
		back.face[2] = turned[7];
		back.face[3] = turned[8];
		right.face[1] = turned[9];
		right.face[2] = turned[10];
		right.face[3] = turned[11];
	}
	
	public void turnUp () { turnUpOpp();turnUpOpp();turnUpOpp(); }
	
	public void turnDownOpp () {
		down.rotate();
		CubeColor[] turned = turnThree(new CubeColor[]{front.face[7], front.face[6], front.face[5], right.face[7], right.face[6], right.face[5], back.face[7], back.face[6], back.face[5], left.face[7], left.face[6], left.face[5]});
		front.face[7] = turned[0];
		front.face[6] = turned[1];
		front.face[5] = turned[2];
		right.face[7] = turned[3];
		right.face[6] = turned[4];
		right.face[5] = turned[5];
		back.face[7] = turned[6];
		back.face[6] = turned[7];
		back.face[5] = turned[8];
		left.face[7] = turned[9];
		left.face[6] = turned[10];
		left.face[5] = turned[11];
	}
	
	public void turnDown () { turnDownOpp();turnDownOpp();turnDownOpp(); }
	
	public void turnFrontOpp () {
		front.rotate();
		CubeColor[] turned = turnThree(new CubeColor[]{up.face[7], up.face[6], up.face[5], right.face[1], right.face[8], right.face[7], down.face[3], down.face[2], down.face[1], left.face[5], left.face[4], left.face[3]});
		up.face[7] = turned[0];
		up.face[6] = turned[1];
		up.face[5] = turned[2];
		right.face[1] = turned[3];
		right.face[8] = turned[4];
		right.face[7] = turned[5];
		down.face[3] = turned[6];
		down.face[2] = turned[7];
		down.face[1] = turned[8];
		left.face[5] = turned[9];
		left.face[4] = turned[10];
		left.face[3] = turned[11];
	}
	
	public void turnFront() { turnFrontOpp();turnFrontOpp();turnFrontOpp(); }

	public void turnBackOpp () {
		back.rotate();
		CubeColor[] turned = turnThree(new CubeColor[]{up.face[3], up.face[2], up.face[1], left.face[1], left.face[8], left.face[7], down.face[7], down.face[6], down.face[5], right.face[5], right.face[4], right.face[3]});
		up.face[3] = turned[0];
		up.face[2] = turned[1];
		up.face[1] = turned[2];
		left.face[1] = turned[3];
		left.face[8] = turned[4];
		left.face[7] = turned[5];
		down.face[7] = turned[6];
		down.face[6] = turned[7];
		down.face[5] = turned[8];
		right.face[5] = turned[9];
		right.face[4] = turned[10];
		right.face[3] = turned[11];
	}
	
	public void turnBack() { turnBackOpp();turnBackOpp();turnBackOpp(); }
	
	public void turnCentralHorizontalOpp() {
		CubeColor[] turned = turnThree(new CubeColor[]{front.face[8], front.face[0], front.face[4], right.face[8], right.face[0], right.face[4], back.face[8], back.face[0], back.face[4], left.face[8], left.face[0], left.face[4]});
		front.face[8] = turned[0];
		front.face[0] = turned[1];
		front.face[4] = turned[2];
		right.face[8] = turned[3];
		right.face[0] = turned[4];
		right.face[4] = turned[5];
		back .face[8] = turned[6];
		back .face[0] = turned[7];
		back .face[4] = turned[8];
		left .face[8] = turned[9];
		left .face[0] = turned[10];
		left .face[4] = turned[11];
	}
	
	public void turnCentralHorizontal() { turnCentralHorizontalOpp();turnCentralHorizontalOpp();turnCentralHorizontalOpp(); }
	
	public void turnCentralVerticalOpp() {
		CubeColor[] turned = turnThree(new CubeColor[]{front.face[2], front.face[0], front.face[6], down.face[2], down.face[0], down.face[6], back.face[6], back.face[0], back.face[2], up.face[2], up.face[0], up.face[6]});
		front.face[2] = turned[0];
		front.face[0] = turned[1];
		front.face[6] = turned[2];
		down.face[2] = turned[3];
		down.face[0] = turned[4];
		down.face[6] = turned[5];
		back.face[6] = turned[6];
		back.face[0] = turned[7];
		back.face[2] = turned[8];
		up.face[2] = turned[9];
		up.face[0] = turned[10];
		up.face[6] = turned[11];
	}
	
	public void turnCentralVertical() { turnCentralVerticalOpp();turnCentralVerticalOpp();turnCentralVerticalOpp(); }
	
	public void turnCentralInsiderOpp() {
		CubeColor[] turned = turnThree(new CubeColor[]{up.face[8], up.face[0], up.face[4], right.face[2], right.face[0], right.face[6], down.face[4], down.face[0], down.face[8], left.face[6], left.face[0], left.face[2]});
		up.face[8] = turned[0];
		up.face[0] = turned[1];
		up.face[4] = turned[2];
		right.face[2] = turned[3];
		right.face[0] = turned[4];
		right.face[6] = turned[5];
		down.face[4] = turned[6];
		down.face[0] = turned[7];
		down.face[8] = turned[8];
		left.face[6] = turned[9];
		left.face[0] = turned[10];
		left.face[2] = turned[11];
	}
	
	public void turnCentralInsider() { turnCentralInsiderOpp();turnCentralInsiderOpp();turnCentralInsiderOpp(); }
	
	public void simulate (String law) {
		char last = '-';
		for (int u = 0; u < law.length() + 1; u++) {
			char curr = '-';
			if (u < law.length()) curr = law.toCharArray()[u];
			if (u > 0) {
				if (curr == '\'') {
					switch (last) {
						case 'R':
							turnRightOpp();
							break;
						case 'L':
							turnLeftOpp();
							break;
						case 'U':
							turnUpOpp();
							break;
						case 'D':
							turnDownOpp();
							break;
						case 'F':
							turnFrontOpp();
							break;
						case 'B':
							turnBackOpp();
							break;
						case 'H':
							turnCentralHorizontalOpp();
							break;
						case 'V':
							turnCentralVerticalOpp();
							break;
						case 'I':
							turnCentralInsiderOpp();
							break;
					}
				} else {
					switch (last) {
						case 'R':
							turnRight();
							break;
						case 'L':
							turnLeft();
							break;
						case 'U':
							turnUp();
							break;
						case 'D':
							turnDown();
							break;
						case 'F':
							turnFront();
							break;
						case 'B':
							turnBack();
							break;
						case 'H':
							turnCentralHorizontal();
							break;
						case 'V':
							turnCentralVertical();
							break;
						case 'I':
							turnCentralInsider();
							break;
					}
				}
			}
			last = curr;
		}
	}
	
	public void echoState () {
		System.out.println(
				"        " + up.face[1] + " " + up.face[2] + " " + up.face[3] + " " + "\n" +
				"        " + up.face[8] + " " + up.face[0] + " " + up.face[4] + " " + "\n" +
				"        " + up.face[7] + " " + up.face[6] + " " + up.face[5] + " " + "\n" +
				" " + left.face[1] + " " + left.face[2] + " " + left.face[3] + "  " + front.face[1] + " " + front.face[2] + " " + front.face[3] + "  " + right.face[1] + " " + right.face[2] + " " + right.face[3] + "  " + back.face[1] + " " + back.face[2] + " " + back.face[3] + "" + "\n" +
				" " + left.face[8] + " " + left.face[0] + " " + left.face[4] + "  " + front.face[8] + " " + front.face[0] + " " + front.face[4] + "  " + right.face[8] + " " + right.face[0] + " " + right.face[4] + "  " + back.face[8] + " " + back.face[0] + " " + back.face[4] + "" + "\n" +
				" " + left.face[7] + " " + left.face[6] + " " + left.face[5] + "  " + front.face[7] + " " + front.face[6] + " " + front.face[5] + "  " + right.face[7] + " " + right.face[6] + " " + right.face[5] + "  " + back.face[7] + " " + back.face[6] + " " + back.face[5] + "" + "\n" +
				"        " + down.face[1] + " " + down.face[2] + " " + down.face[3] + "" + "\n" +
				"        " + down.face[8] + " " + down.face[0] + " " + down.face[4] + "" + "\n" +
				"        " + down.face[7] + " " + down.face[6] + " " + down.face[5] + "" + "\n"
		);
	}
	
	private static CubeColor[] turnThree (CubeColor[] data) {
		CubeColor[] now = data.clone();
		for (int i = 0; i < data.length; i++) {
			data[i] = now[addThree(i, data.length)];
		}
		return data;
	}
	
	private static int addThree (int num, int max) {
		num += 3;
		return num>=max ? num-max : num;
	}
	
}