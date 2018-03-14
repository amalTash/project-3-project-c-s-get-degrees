package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashFrame extends Frame {
	private Scene scene;
	private Label flashLabel;
	private Button startButton;
	
	public FlashFrame() {
		buildScene();
	}

	public void setNextFrameToShow(PuzzlerFrame puzzlerFrame, Stage stage) {
		startButton.setOnAction(e -> puzzlerFrame.show(stage));
	}
	
	@Override
	public void show(Stage stage) {
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString("appTitleFlash"));
		stage.show();
	}

	private void buildScene() {
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		flashLabel = new Label(I18n.getBundle().getString("gameDescription"));
		startButton = new Button(I18n.getBundle().getString("startGame"));
		vbox.getChildren().addAll(flashLabel, startButton);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
	}

}
