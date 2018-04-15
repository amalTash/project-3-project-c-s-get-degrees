package edu.cuny.brooklyn.project.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SettingsFrame extends Frame{
	private final static Logger LOGGER = LoggerFactory.getLogger(SettingsFrame.class);
	
	private final static String MSG_APP_TITLE_SETTINGS_KEY = "appTitleSettings";
	private final static String MSG_SAVE_AND_RETURN_GAME_KEY = "saveAndQuit";
	
	BorderPane borderPane;
	
	private Scene scene;
	
	private Button saveAndReturn;
	
	private ToggleGroup puzzlerGroup;
	private Label puzzlerLabel;
	private ToggleButton randomPuzzlers;
	private ToggleButton sqrtPuzzler;
	private ToggleButton slidingCupsPuzzler;
	public int puzzlerSetter;
	public int puzzlerDiffc;
	
	private ToggleGroup diffcGroup;
	private Label diffcLabel;
	private ToggleButton easyDiffc;
	private ToggleButton normalDiffc;
	private ToggleButton hardDiffc;
	
	public SettingsFrame() {
		borderPane = new BorderPane();
		borderPane.setPadding(GameSettings.PADDING_X);
		
		Pane pane = buildSaveRuturnPane();
		borderPane.setTop(pane);
		
		pane = buildPuzzlerSettings();
		borderPane.setLeft(pane);
		
		pane = buildDiffcSettings();
		borderPane.setRight(pane);
		
		scene = new Scene(borderPane);
	}
	
	@Override
	public void setState(int puzzlerSetter, int puzzlerDiffc, Stage stage) {
		this.puzzlerSetter = puzzlerSetter;
		this.puzzlerDiffc = puzzlerDiffc;
	}
	
	@Override
	public void show(Stage stage) {
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_SETTINGS_KEY));
		stage.show();
	}
	
	public void setNextFrameToShow(SettingsFrame settingsFrame, FlashFrame flashFrame, Stage stage) {
		saveAndReturn.setOnAction(e -> flashFrame.show(stage));
		slidingCupsPuzzler.setOnAction(e -> {puzzlerSetter = 2; flashFrame.setState(puzzlerSetter, puzzlerDiffc, stage);});
		sqrtPuzzler.setOnAction(e -> {puzzlerSetter = 1; flashFrame.setState(puzzlerSetter, puzzlerDiffc, stage);});
		randomPuzzlers.setOnAction(e -> {puzzlerSetter = 0; flashFrame.setState(puzzlerSetter, puzzlerDiffc, stage);});
		easyDiffc.setOnAction(e -> {puzzlerDiffc = 0; flashFrame.setState(puzzlerSetter, puzzlerDiffc, stage);});
		normalDiffc.setOnAction(e -> {puzzlerDiffc = 1; flashFrame.setState(puzzlerSetter, puzzlerDiffc, stage);});
		hardDiffc.setOnAction(e -> {puzzlerDiffc = 2; flashFrame.setState(puzzlerSetter, puzzlerDiffc, stage);});
		}
	
	private Pane buildSaveRuturnPane() {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(GameSettings.H_SPACING);
		hbox.setPadding(GameSettings.PADDING);
		
		saveAndReturn = new Button(I18n.getBundle().getString(MSG_SAVE_AND_RETURN_GAME_KEY));
		hbox.getChildren().addAll(saveAndReturn);
		scene = new Scene(hbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
		
		return hbox;
	}	
	
	public int getPuzzlerSetter() {
		return puzzlerSetter;
	}
	
	private Pane buildPuzzlerSettings() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(GameSettings.H_SPACING);
		vbox.setPadding(GameSettings.PADDING);
		
		puzzlerLabel = new Label("Default Puzzler");
		puzzlerGroup = new ToggleGroup();
		randomPuzzlers = new ToggleButton("Random");
		randomPuzzlers.setToggleGroup(puzzlerGroup);
		sqrtPuzzler = new ToggleButton("Sqrt");
		sqrtPuzzler.setToggleGroup(puzzlerGroup);
		slidingCupsPuzzler = new ToggleButton("SlidingCups");	
		slidingCupsPuzzler.setToggleGroup(puzzlerGroup);

		
		vbox.getChildren().addAll(puzzlerLabel, sqrtPuzzler, slidingCupsPuzzler, randomPuzzlers);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);

		return vbox;
	}
	
	private Pane buildDiffcSettings() {
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(GameSettings.H_SPACING);
		vbox.setPadding(GameSettings.PADDING);
		
		diffcLabel = new Label("Default Difficulty");
		
		diffcGroup = new ToggleGroup();
		easyDiffc = new ToggleButton("Easy");
		easyDiffc.setToggleGroup(diffcGroup);
		normalDiffc = new ToggleButton("Normal");	
		normalDiffc.setToggleGroup(diffcGroup);
		hardDiffc = new ToggleButton("Hard");
		hardDiffc.setToggleGroup(diffcGroup);
		vbox.getChildren().addAll(diffcLabel, easyDiffc, normalDiffc, hardDiffc);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);

		return vbox;
	}	
}
