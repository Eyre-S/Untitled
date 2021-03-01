using Sukazyo.Untitled.ColorPattern;

using Xunit;

namespace Sukazyo.Untitled.Tests {

	public class ColorPatternTests {
		
		[Theory]
		[InlineData(11, 74, 118, "#0b4a76", 739958)]
		[InlineData(253, 246, 227, "#fdf6e3", 16643811)]
		[InlineData(15, 0, 15, "#0f000f", 983055)]
		[InlineData(0, 240, 0, "#00f000", 61440)]
		[InlineData(0, 0, 1, "#000001", 1)]
		[InlineData(255, 255, 255, "#ffffff", 16777215)]
        public void TestRgb (byte red, byte green, byte blue, string hexColor, int code) {
			
			// 测试值初始化
			var originRgb = new RGB(red, green, blue);
			var fromIntRgb = RGB.FromInt(code);
			var fromHexRgb = RGB.FromHex(hexColor);

			// 测试输出
			Assert.Equal(code, originRgb.ToInt());
			Assert.Equal(hexColor, originRgb.ToHex());
			
			// 测试 Get 方法
			Assert.Equal(red, originRgb.R);
			Assert.Equal(red, originRgb.Red);
			Assert.Equal(green, originRgb.G);
			Assert.Equal(green, originRgb.Green);
			Assert.Equal(blue, originRgb.B);
			Assert.Equal(blue, originRgb.Blue);

			// 测试输出字符串的规范
			Assert.Equal($"rgb({red}, {green}, {blue})", originRgb.ToString());
			
			// 测试初始化方式
			// 三个对象导出的数值内容应该相同
			Assert.Equal(originRgb.ToInt(), fromHexRgb.ToInt());
			Assert.Equal(originRgb.ToInt(), fromIntRgb.ToInt());
			
			// 测试相等运算
			// 自己和自己&同样的初始化方式应该相等
			Assert.True(originRgb.Equals(originRgb));
			var anotherOrigin = new RGB(red, green, blue);
			Assert.True(originRgb.Equals(anotherOrigin));
			Assert.Equal(originRgb, anotherOrigin);
			// 不同初始化方式的相同值对象应该相等
			Assert.True(fromIntRgb.Equals(originRgb));
			Assert.True(fromHexRgb.Equals(originRgb));
			// 同时双等号也应该返回 True
			Assert.True(originRgb == anotherOrigin);
			Assert.True(fromIntRgb == originRgb);
			Assert.True(fromHexRgb == originRgb);

		}

	}

}