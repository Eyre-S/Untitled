using System.Collections.Generic;
using System.Linq;

namespace Sukazyo.Untitled.ColorPattern {

	public class ColorPatternHelper {

		public static readonly HashSet<char> hexAvaliableChars = new char[]{
			'0',
			'1',
			'2',
			'3',
			'4',
			'5',
			'6',
			'7',
			'8',
			'9',
			'A',
			'a',
			'B',
			'b',
			'C',
			'c',
			'D',
			'd',
			'E',
			'e',
			'F',
			'f'
		}.ToHashSet();


		public static bool IsHexColor (string hexStr) {
			hexStr = hexStr.StartsWith('#') ? hexStr = hexStr[1..] : hexStr;
			return (
				hexStr.Length == 3 &&
				hexAvaliableChars.Contains(hexStr[0]) &&
				hexAvaliableChars.Contains(hexStr[1]) &&
				hexAvaliableChars.Contains(hexStr[2])
			) || (
				hexStr.Length == 6 &&
				hexAvaliableChars.Contains(hexStr[0]) &&
				hexAvaliableChars.Contains(hexStr[1]) &&
				hexAvaliableChars.Contains(hexStr[2]) &&
				hexAvaliableChars.Contains(hexStr[3]) &&
				hexAvaliableChars.Contains(hexStr[4]) &&
				hexAvaliableChars.Contains(hexStr[5])
			);
		}

	}

}
