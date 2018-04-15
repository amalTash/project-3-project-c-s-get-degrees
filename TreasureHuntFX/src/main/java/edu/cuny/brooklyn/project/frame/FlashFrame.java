package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FlashFrame extends Frame {
	private final static String MSG_GAME_DESCRIPTION_KEY = "gameDescription";
	private final static String MSG_START_GAME_KEY = "startGame";
	private final static String MSG_SETTINGS_GAME_KEY = "settings";
	private final static String MSG_QUIT_GAME_KEY = "quitGame";
	private final static String MSG_APP_TITLE_FLASH_KEY = "appTitleFlash";
	
	private Scene scene;
	private int puzzlerState;
	private int diffcState;
	private Label flashLabel;
	private Button startButton;
	private Button settingsButton;
	private Button quitButton;
	
	public FlashFrame() {
		buildScene();
	}

	public void setNextFrameToShow(SettingsFrame settingsFrame, PuzzlerFrame puzzlerFrame, Stage stage) {
		startButton.setOnAction(e -> {puzzlerFrame.show(stage); puzzlerFrame.setState(puzzlerState, diffcState, stage);});
		settingsButton.setOnAction(e -> settingsFrame.show(stage));
		quitButton.setOnAction(e -> stage.close());
	}
	
	public void setState(int puzzlerSetter, int puzzlerDiffc, Stage stage) {
		this.puzzlerState = puzzlerSetter;
		this.diffcState = puzzlerDiffc;
	}
	
	@Override
	public void show(Stage stage) {
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_FLASH_KEY));
		stage.show();
	}

	private void buildScene() {
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		flashLabel = new Label(I18n.getBundle().getString(MSG_GAME_DESCRIPTION_KEY));
		startButton = new Button(I18n.getBundle().getString(MSG_START_GAME_KEY));
		settingsButton = new Button(I18n.getBundle().getString(MSG_SETTINGS_GAME_KEY));
		quitButton = new Button(I18n.getBundle().getString(MSG_QUIT_GAME_KEY));
		vbox.getChildren().addAll(flashLabel, startButton, settingsButton, quitButton);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
	}

}
