package cc.sukazyo.untitled.simulator.rubikcube;

public enum CubeColor {
	WHITE, RED, GREEN, BLUE, PINK, YELLOW,
	 a1,  a2,  a3,
	 b1,  b2,  b3,
	 c1,  c2,  c3,
	 d1,  d2,  d3;
	
	public String toString() {
		switch (this) {
			case WHITE:
				return "1";
			case RED:
				return "2";
			case GREEN:
				return "3";
			case BLUE:
				return "4";
			case PINK:
				return "5";
			case YELLOW:
				return "6";
			default:
				return super.toString();
		}
	}
}
