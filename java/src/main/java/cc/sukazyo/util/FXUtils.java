package cc.sukazyo.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class FXUtils {
	
	/**
	 * 获取一个fxml实例
	 *
	 * @param loc fxml文件在/fxml/目录下的相对位置
	 * @return 作为AnchorPane返回的root元素
	 */
	public static AnchorPane getFxml(Class<?> source, String loc) {
		
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(source.getResource("/fxml/" + loc));
			AnchorPane pane = loader.load();
			return pane;
		} catch (Exception e) {
			return new AnchorPane();
		}
		
	}
	
}
