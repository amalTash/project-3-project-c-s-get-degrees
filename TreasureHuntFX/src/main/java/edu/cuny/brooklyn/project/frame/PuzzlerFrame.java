package edu.cuny.brooklyn.project.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import edu.cuny.brooklyn.project.puzzler.Puzzler;
import edu.cuny.brooklyn.project.puzzler.PuzzlerMaker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PuzzlerFrame extends Frame {
	private final static Logger LOGGER = LoggerFactory.getLogger(PuzzlerFrame.class);
	
	private final static String MSG_GAME_DESCRIPTION_KEY = "gameDescription";
	private final static String MSG_ANSWER_PUZZLER_KEY = "answerPuzzler";
	private final static String MSG_APP_TITLE_PUZZLER_KEY = "appTitlePuzzler";
	
	private PuzzlerMaker puzzlerMaker;
	private Puzzler puzzler;
	private int  answeringAttempts;
	private Scene scene;
	private Label puzzlerLabel;
	private TextField puzzlerAnswer;
	private Button answerButton;
	private TreasureFrame nextFrame;
	private Stage nextStage;
	private int puzzlerSetter;
	private int diffcSetter;
	
	public PuzzlerFrame() {
		puzzlerMaker = new PuzzlerMaker();
		
		buildScene();
		
		answerButton.setOnAction(e -> answerPuzzler());
	}
	
	private void answerPuzzler() {
		String answer = puzzlerAnswer.getText();
		if (answer.isEmpty()) {
			LOGGER.debug("User's answer to the puzzler is empty!");
			return;
		}
		
		answeringAttempts ++;
		
		if (!puzzler.isCorrect(answer, answeringAttempts)) {
			LOGGER.debug("User's answer to the puzzler is wrong! This is attempt #" + answeringAttempts);
			return;
		} else {
			LOGGER.debug("User's answer to the puzzler is correct, move on to locate the treasure." );
			String clue = TreasureClue.getClue(nextFrame.getTreasureField().getTreasureXLeft(),
					nextFrame.getTreasureField().getTreasureYTop(),
					nextFrame.getTreasureField().getTreasureBoundingBoxWidth(),
					nextFrame.getTreasureField().getTreasureBoundingBoxLength(),
					answeringAttempts);		
			nextFrame.startLocatingTreasure(clue);
			nextFrame.setAttempts(answeringAttempts);
			nextFrame.show(nextStage);
		}
	}
	
	public void setNextFrameToShow(TreasureFrame treasureFrame, Stage stage) {
		nextFrame = treasureFrame;
		nextStage = stage;
	}
	
	@Override
	public void setState(int puzzlerSetter, int puzzlerDiffc, Stage stage) {
		this.puzzlerSetter = puzzlerSetter;
		LOGGER.debug("PuzzlerSetter: " + puzzlerSetter);
		this.diffcSetter = puzzlerDiffc;
		show(stage);
	}
	
	@Override
	public void show(Stage stage) {
		new PuzzlerFrame();
		switch (puzzlerSetter) {
		case 0:
		puzzler = puzzlerMaker.makeRandom();
		break;
		case 1:
		puzzler = puzzlerMaker.makeSqrt();
		break;
		case 2:
		puzzler = puzzlerMaker.makeSlidingCups(diffcSetter);
		break;
		default:
		puzzler = puzzlerMaker.makeRandom();
		break;
		}
		puzzlerLabel.setText(puzzler.getMessage());
		answeringAttempts = 0;
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_PUZZLER_KEY));
		stage.show();
	}

	private void buildScene() {
		VBox vbox = new VBox();
		vbox.setPadding(GameSettings.PADDING);
		vbox.setSpacing(GameSettings.V_SPACING);
		puzzlerLabel = new Label(I18n.getBundle().getString(MSG_GAME_DESCRIPTION_KEY));
		puzzlerAnswer = new TextField();
		answerButton = new Button(I18n.getBundle().getString(MSG_ANSWER_PUZZLER_KEY));
		vbox.getChildren().addAll(puzzlerLabel, puzzlerAnswer, answerButton);
		scene = new Scene(vbox, GameSettings.SCENE_WIDTH, GameSettings.CANVAS_HEIGHT);
	}
}
