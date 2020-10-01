package cc.sukazyo.untitled.ui;

import javafx.application.Application;
import javafx.stage.Stage;

public class FXApplication extends Application {
	
	public static HomeStage homeStage;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		homeStage = new HomeStage();
		homeStage.stage = primaryStage;
		homeStage.load();
	}
	
}
