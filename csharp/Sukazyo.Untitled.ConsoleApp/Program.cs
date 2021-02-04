using System;

using Sukazyo.Untitled.ColorPattern;

namespace Sukazyo.Untitled.ConsoleApp {

	class Program {

		public static void Main (String[] args) {
			Console.WriteLine("Hello " + Untitled.ProjectName + "!");
			var a = RGB.FromHex("#0d0a6b");
			Console.Out.WriteLine(a.ToHex());
		}

	}

}