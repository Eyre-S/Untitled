using System;
using System.Text.RegularExpressions;

using Sukazyo.Untitled.ColorPattern;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Media;

namespace Sukazyo.Untitled.ColorPatternGUI {

	/// <summary>
	/// Interaction logic for MainWindow.xaml
	/// </summary>
	public partial class MainWindow : Window {
		
		private RGB _color = RGB.FromInt(0);

		private bool _redSliderAvailable;
		private bool _redInputAvailable;
		private bool _greenSliderAvailable;
		private bool _greenInputAvailable;
		private bool _blueSliderAvailable;
		private bool _blueInputAvailable;
		private bool _hexInputAvailable;
		private bool _intCodeSliderAvailable;
		private bool _intCodeInputAvailable;
		private readonly bool _colorPreviewAvailable;

		private readonly Brush _brushInputOk = new SolidColorBrush(Colors.LightGray);
		private readonly Brush _brushInputError = new SolidColorBrush(Colors.LightSalmon);
		
		public MainWindow () {
			InitializeComponent();
			_redSliderAvailable = true;
			_redInputAvailable = true;
			_greenSliderAvailable = true;
			_greenInputAvailable = true;
			_blueSliderAvailable = true;
			_blueInputAvailable = true;
			_hexInputAvailable = true;
			_intCodeSliderAvailable = true;
			_intCodeInputAvailable = true;
			_colorPreviewAvailable = true;
			_color = RGB.FromHex("#c3eb8d");
			Update();
		}
		
		private void RedSliderValueChanged (object sender, RoutedPropertyChangedEventArgs<double> e) {
			_color = new RGB((byte)RedSlider.Value, _color.G, _color.B);
			_redSliderAvailable = false;
			Update();
			_redSliderAvailable = true;
		}
		
		private void RedInputTextChanged (object sender, TextChangedEventArgs e) {
			if (Regex.IsMatch(RedInput.Text, @"^[0-9]+$")) {
				int tmp = int.Parse(RedInput.Text);
				if (!(tmp > RGB.SingleMax || tmp < RGB.SingleMin)) {
					_color = new RGB((byte)tmp, _color.G, _color.B);
					_redInputAvailable = false;
					Update();
					_redInputAvailable = true;
					RedInputBorder.Background = _brushInputOk;
					return;
				}
			}
			RedInputBorder.Background = _brushInputError;
		}
		
		private void GreenSliderValueChanged (object sender, RoutedPropertyChangedEventArgs<double> e) {
			_color = new RGB(_color.R, (byte)GreenSlider.Value, _color.B);
			_greenSliderAvailable = false;
			Update();
			_greenSliderAvailable = true;
		}
		
		private void GreenInputTextChanged (object sender, TextChangedEventArgs e) {
			if (Regex.IsMatch(GreenInput.Text, @"^[0-9]+$")) {
				int tmp = int.Parse(GreenInput.Text);
				if (!(tmp > RGB.SingleMax || tmp < RGB.SingleMin)) {
					_color = new RGB(_color.R, (byte)tmp, _color.B);
					_greenInputAvailable = false;
					Update();
					_greenInputAvailable = true;
					GreenInputBorder.Background = _brushInputOk;
					return;
				}
			}
			GreenInputBorder.Background = _brushInputError;
		}
		
		private void BlueSliderValueChanged (object sender, RoutedPropertyChangedEventArgs<double> e) {
			_color = new RGB(_color.R, _color.G, (byte)BlueSlider.Value);
			_blueSliderAvailable = false;
			Update();
			_blueSliderAvailable = true;
		}
		
		private void BlueInputTextChanged (object sender, TextChangedEventArgs e) {
			if (Regex.IsMatch(BlueInput.Text, @"^[0-9]+$")) {
				int tmp = int.Parse(BlueInput.Text);
				if (!(tmp > RGB.SingleMax || tmp < RGB.SingleMin)) {
					_color = new RGB(_color.R, _color.G, (byte)tmp);
					_blueInputAvailable = false;
					Update();
					_blueInputAvailable = true;
					BlueInputBorder.Background = _brushInputOk;
					return;
				}
			}
			BlueInputBorder.Background = _brushInputError;
		}
		
		private void HexInputTextChanged (object sender, TextChangedEventArgs e) {
			if (ColorPatternHelper.IsHexColor(HexInput.Text)) {
				_color = RGB.FromHex(HexInput.Text);
				_hexInputAvailable = false;
				Update();
				_hexInputAvailable = true;
				HexInputBorder.Background = _brushInputOk;
			} else {
				HexInputBorder.Background = _brushInputError;
			}
		}
		
		private void IntCodeSliderValueChanged (object sender, RoutedPropertyChangedEventArgs<double> e) {
			_color = RGB.FromInt((int)IntCodeSlider.Value);
			_intCodeSliderAvailable = false;
			Update();
			_intCodeSliderAvailable = true;
		}

		private void IntCodeInputTextChanged (object sender, TextChangedEventArgs e) {
			if (Regex.IsMatch(IntCodeInput.Text, @"^[0-9]+$")) {
				int tmp = int.Parse(IntCodeInput.Text);
				if (!(tmp > RGB.IntMax || tmp < RGB.IntMin)) {
					_color = RGB.FromInt(tmp);
					_intCodeInputAvailable = false;
					Update();
					_intCodeInputAvailable = true;
					IntCodeInputBorder.Background = _brushInputOk;
					return;
				}
			}
			IntCodeInputBorder.Background = _brushInputError;
		}

		private void Update () {
			
			// 输入框与滑条数据更新
			RgbStr.Content = _color.ToString();
			if (_redSliderAvailable)
				RedSlider.Value = _color.Red;
			if (_redInputAvailable)
				RedInput.Text = _color.Red.ToString();
			if (_greenSliderAvailable)
				GreenSlider.Value = _color.Green;
			if (_greenInputAvailable)
				GreenInput.Text = _color.Green.ToString();
			if (_blueSliderAvailable)
				BlueSlider.Value = _color.Blue;
			if (_blueInputAvailable)
				BlueInput.Text = _color.Blue.ToString();
			if (_hexInputAvailable)
				HexInput.Text = _color.ToHex();
			if (_intCodeSliderAvailable)
				IntCodeSlider.Value = _color.ToInt();
			if (_intCodeInputAvailable)
				IntCodeInput.Text = _color.ToInt().ToString();
			
			// 更新预览面板
			if (_colorPreviewAvailable)
				ColorPreview.Background = new SolidColorBrush(Color.FromRgb(_color.R, _color.G, _color.B));
			
		}

	}

}