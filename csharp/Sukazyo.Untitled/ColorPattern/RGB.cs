using System.Globalization;

namespace Sukazyo.Untitled.ColorPattern {

	public class RGB {
		
		public static readonly byte SingleMin = 0;
		public static readonly byte SingleMax = 255;
		public static readonly int IntMin = new RGB(SingleMin, SingleMin, SingleMin).ToInt();
		public static readonly int IntMax = new RGB(SingleMax, SingleMax, SingleMax).ToInt();

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
				hexStr = hexStr[1..];
			}
			return FromInt(int.Parse(hexStr, NumberStyles.HexNumber));
		}

		public int ToInt () {
			return (Red<<16)|(Green<<8)|Blue;
		}

		public string ToHex () {
			return "#"+ToInt().ToString("x6");
		}

		public override string ToString () {
			return $"rgb({Red}, {Green}, {Blue})";
		}

		public override bool Equals (object obj) {
			return obj != null && obj.GetType() == typeof(RGB) && ((RGB) obj).R == R && ((RGB) obj).G == G && ((RGB) obj).B == B;
		}

		public override int GetHashCode () {
			return ("Sukazyo.Untitled.ColorPattern.RGB"+ToHex()).GetHashCode();
		}

	}
	
}