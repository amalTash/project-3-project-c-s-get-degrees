package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.GameSettings;

import edu.cuny.brooklyn.project.message.I18n;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashEndFrame extends Frame{
	private final static String MSG_APP_TITLE_FLASH_END_KEY = "appTitleFinalScore";
	private final static String MSG_END_GAME_KEY = "closeGame";
	
	private Scene scene;
	private Button closeButton;
	
	public FlashEndFrame() {
		buildScene();
	}
	
	@Override
	public void show(Stage stage) {
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_FLASH_END_KEY));
		stage.show();
		closeButton.setOnAction(e -> stage.close());
	}
	
	private void buildScene() {
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		closeButton = new Button(I18n.getBundle().getString(MSG_END_GAME_KEY));
		vbox.getChildren().addAll(closeButton);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
	}

	@Override
	public void setState(int puzzlerSetter, int puzzlerDiffc, Stage stage) {}
}
