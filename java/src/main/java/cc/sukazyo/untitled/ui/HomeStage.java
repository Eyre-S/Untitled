package cc.sukazyo.untitled.ui;

import cc.sukazyo.things.ThreeOrderRubikCube;
import cc.sukazyo.util.FXUtils;
import cc.sukazyo.util.StringUtils;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeStage {
	
	public Stage stage;
	private AnchorPane root;
	
	private Thread simulationThread;
	
	@FXML
	private TextField inputLaw;
	
	@FXML
	private Button buttonStart;
	
	@FXML
	private Label labelNumber;
	
	@FXML
	@SuppressWarnings("all")
	private void buttonStartActive () {
		if (simulationThread != null && simulationThread.isAlive()) {
			simulationThread.stop();
			inputLaw.setDisable(false);
			labelNumber.setStyle("-fx-text-fill: lightpink");
			buttonStart.setText("Start Simulation");
		} else {
			buttonStart.setText("Stop");
			inputLaw.setDisable(true);
			labelNumber.setStyle("-fx-text-fill: darkslategray");
			simulationThread = new Thread(() -> {
				ThreeOrderRubikCube cube = ThreeOrderRubikCube.getDefault();
				int i = 0;
				do {
					cube.simulate(inputLaw.getText());
					i++;
					int finalI = i;
					Platform.runLater(() -> labelNumber.setText(String.valueOf(finalI)));
					StringUtils.deleteChars(String.valueOf(i).length());
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} while (!cube.isOK());
				Platform.runLater(() -> {
					buttonStart.setDisable(false);
					inputLaw.setDisable(false);
					labelNumber.setStyle("-fx-text-fill: mediumaquamarine");
					buttonStart.setText("Start Simulation");
				});
			});
			simulationThread.start();
		}
	}
	
	public void load () {
		root = FXUtils.getFxml(FXApplication.class, "home.fxml");
		stage.setScene(new Scene(root));
		stage.setTitle("Rubik Cube Simulation");
		stage.show();
	}
	
}
