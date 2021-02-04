using System;
using System.Globalization;

namespace Sukazyo.Untitled.ColorPattern {

	public class RGB {

		public byte Red { get; }
		public byte R => Red;
		public byte Green { get; }
		public byte G => Green;
		public byte Blue { get; }
		public byte B => Blue;
		
		public RGB (byte red, byte green, byte blue) {
			Red = red;
			Green = green;
			Blue = blue;
		}

		public static RGB FromInt (int code) {
			return new(
				(byte)((code&16711680)>>16),
				(byte)((code&65280)>>8),
				(byte)(code&255)
			);
		}

		public static RGB FromHex (string hexStr) {
			if (hexStr.StartsWith('#')) {
				hexStr = hexStr.Substring(1);
			}
			return FromInt(int.Parse(hexStr, NumberStyles.HexNumber));
		}

		public int ToInt () {
			return (Red<<16)|(Green<<8)|Blue;
		}

		public String ToHex () {
			return $"#{ToInt().ToString("x6")}";
		}

		public override String ToString () {
			return $"rgb({Red}, {Green}, {Blue})";
		}

	}

}