package edu.cuny.brooklyn.project.frame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.message.I18n;
import edu.cuny.brooklyn.project.score.Scorer;
import edu.cuny.brooklyn.project.treasure.TreasureField;
import javafx.beans.InvalidationListener;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.shape.Circle;

public class TreasureFrame extends Frame {
	private final static Logger LOGGER = LoggerFactory.getLogger(TreasureFrame.class);
	
	private final static String MSG_TOTAL_SCORE_KEY = "totalScore";
	private final static String MSG_ROUND_SCORE_KEY = "roundScore";
	private final static String MSG_LOCATE_TREASURE_KEY = "locateTreasure";
	private final static String MSG_APP_TITLE_TREASURE_HUNT_KEY = "appTitleTreasureHunt";
	private final static String MSG_NO_LABEL_AT_LOCATION_KEY = "noLabelAtLocation";
	private final static String MSG_QUIT_GAME = "quitGame";
	private final static String MSG_NEXT_LEVEL_GAME = "nextLevel";
	
	BorderPane borderPane;
	
	private Scorer scorer;
	private int puzzlerAttempts;
	private TreasureField treasureField;
	
	private Scene scene;
	private Label totalScoreLabel;
	private Label roundScoreLabel;
	
	private Canvas canvas;
	private Label clueLabel;
	private Label responseLabel;
	
	private Pane xyInputPane;
	private TextField xPosTreasure;
	private TextField yPosTreasure;
	private Button buttonTreasure;
	
	//New Pane to overlay over xyInputPane
	private Pane winPane;
	private Button quitButton;
	private Button nextLevelButton;
	
	protected double mouseX;
	protected double mouseY;
	
	// for resizing
	private InvalidationListener resizeListener = o -> redrawTreasure();
	
	FlashEndFrame nextFrameEnd;
	PuzzlerFrame nextFrameReset;
	Stage nextStage;
	
	public TreasureFrame() {
		scorer = new Scorer();
		puzzlerAttempts = 0;
		treasureField = new TreasureField();
		
		borderPane = new BorderPane();
		
		borderPane.setPadding(GameSettings.PADDING_X);
		
		Pane pane = buildScorePane();
		borderPane.setTop(pane);
		
		pane = buildTreasureFieldPane();
		borderPane.setCenter(pane);
		
		xyInputPane = buildXYInputPane();
		borderPane.setBottom(xyInputPane);
		
		scene = new Scene(borderPane);
	}
	
	public TreasureField getTreasureField() {
		return treasureField;
	}
	

	public void setAttempts(int answeringAttempts) {
		puzzlerAttempts = answeringAttempts;
	}

	public void startLocatingTreasure(String clue) {
		clueLabel.setText(clue);
		clueLabel.setVisible(true);
		responseLabel.setVisible(false);
		xyInputPane.setDisable(false);
		canvas.widthProperty().removeListener(resizeListener);
		canvas.heightProperty().removeListener(resizeListener);
	}

	@Override
	public void show(Stage stage) {
		clearCanvas();
		borderPane.setBottom(xyInputPane);
		scene.setRoot(borderPane);
		xyInputPane.setDisable(false);
		xyInputPane.setVisible(true);
		new TreasureFrame();
		stage.setScene(scene);
		stage.setTitle(I18n.getBundle().getString(MSG_APP_TITLE_TREASURE_HUNT_KEY));
		stage.show();
	}
	
	private Pane buildScorePane() {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(GameSettings.H_SPACING);
		hbox.setPadding(GameSettings.PADDING);
		
		totalScoreLabel = new Label(String.format(GameSettings.SCORE_FORMAT, 0));
		roundScoreLabel = new Label(String.format(GameSettings.SCORE_FORMAT, 0));
		hbox.getChildren().addAll(new Label(I18n.getBundle().getString(MSG_TOTAL_SCORE_KEY)),
				totalScoreLabel,
				new Label(I18n.getBundle().getString(MSG_ROUND_SCORE_KEY)),
				roundScoreLabel);
		return hbox;
	}
	
	private Pane buildTreasureFieldPane() {
		Circle circle = new Circle();
		circle.setCenterX(TreasureClue.getCircleX());
		circle.setCenterY(TreasureClue.getCircleY());
		circle.setRadius(TreasureClue.getMarginError());
		
		StackPane canvasHolder = new StackPane();
		canvasHolder.setMinSize(GameSettings.CANVAS_HOLDER_MIN_WIDTH, GameSettings.CANVAS_HOLDER_MIN_HEIGHT);
		canvasHolder.setStyle(GameSettings.DEFAULT_CANVAS_HOLDER_STYLE);
		canvas = new Canvas(GameSettings.CANVAS_WIDTH, GameSettings.CANVAS_HEIGHT);
		canvasHolder.getChildren().add(canvas);
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(GameSettings.V_SPACING);
		clueLabel = new Label();
		clueLabel.setWrapText(true);
		clueLabel.setVisible(false);
		
		responseLabel = new Label();
		responseLabel.setWrapText(true);
		responseLabel.setVisible(false);
		vbox.getChildren().addAll(clueLabel, responseLabel);
		
		canvasHolder.getChildren().add(vbox);
		
		canvas.widthProperty().bind(canvasHolder.widthProperty().subtract(20));
		canvas.heightProperty().bind(canvasHolder.heightProperty().subtract(20));
		
		canvasHolder.setOnMouseClicked(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		
		return canvasHolder;
	}
	
	private Pane buildXYInputPane() {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(GameSettings.H_SPACING);
		hbox.setPadding(GameSettings.PADDING);
		
		xPosTreasure = new TextField();
		yPosTreasure = new TextField();
		buttonTreasure = new Button(I18n.getBundle().getString(MSG_LOCATE_TREASURE_KEY));
		buttonTreasure.setOnAction(e -> doTreasureLocationAction());
		
		hbox.getChildren().addAll(xPosTreasure, yPosTreasure, buttonTreasure);

		return hbox;
	}

	public void setNextFrameToShow(FlashEndFrame flashEndFrame, PuzzlerFrame puzzlerFrame, Stage stage) {
		nextFrameEnd = flashEndFrame;
		nextFrameReset = puzzlerFrame;
		nextStage = stage;
	}
	
	private Pane buildWinPane() {
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(GameSettings.H_SPACING);
		hbox.setPadding(GameSettings.PADDING);
		
		quitButton = new Button(I18n.getBundle().getString(MSG_QUIT_GAME));
		nextLevelButton = new Button(I18n.getBundle().getString(MSG_NEXT_LEVEL_GAME));
		hbox.getChildren().removeAll(xPosTreasure, yPosTreasure, buttonTreasure);
		hbox.getChildren().addAll(quitButton, nextLevelButton);
		return hbox;
	}
	
	private void clearCanvas() {
		canvas.getGraphicsContext2D().clearRect(0,  0,  canvas.getWidth(), canvas.getHeight());
	}

	private void doTreasureLocationAction() {
		int xInput = -1;
		int yInput = -1;
		if(mouseX > 0 && mouseY > 0) {
			xInput = (int)mouseX;
			yInput = (int)mouseY;
		}
		String xInputText = xPosTreasure.getText();
		String yInputText = yPosTreasure.getText();
			if (xInputText.isEmpty()) {
				LOGGER.debug("User hasn't guessed X position of the treasure.");
		}

			if (yInputText.isEmpty()) {
				LOGGER.debug("User hasn't guessed Y position of the treasure.");
		}
			
		if(!xInputText.isEmpty() && !yInputText.isEmpty()) {
			xInput = Integer.parseInt(xInputText);
			yInput = Integer.parseInt(yInputText);
		}
		
		if (treasureField.foundTreasure(xInput, yInput)) {
			LOGGER.debug("Found treasure at location (" + xInput + "," + yInput + ")");
			doneGuessing();
			showTreasure();
			updateScore();
			winPane = buildWinPane();
			borderPane.setBottom(winPane);
			scene.setRoot(borderPane);
			//TODO: Add a summary of how many rounds played and total score
			quitButton.setOnAction(e -> nextFrameEnd.show(nextStage));
			//TODO: Treasure Frame needs to be redrawn
			nextLevelButton.setOnAction(e -> nextFrameReset.show(nextStage));
		
		} else {
			LOGGER.debug("No treasure at location (" + xInput + "," + yInput + ")");
			responseLabel.setVisible(true);
			responseLabel.setText(I18n.getBundle().getString(MSG_NO_LABEL_AT_LOCATION_KEY) + " (" + xInput + "," + yInput + ")");
		}

	}
	
	private void doneGuessing() {
		clueLabel.setVisible(false);
		responseLabel.setVisible(false);
		xyInputPane.setDisable(true);
		xyInputPane.setVisible(false);
	}
	
	private void drawTreasure() {
		LOGGER.debug("redraw");
		double canvasWidth = canvas.getWidth();
		double canvasHeight = canvas.getHeight();
		double y = (double)treasureField.getTreasureYTop()/(double)treasureField.getFieldHeight()*canvasHeight;
		double x = (double)treasureField.getTreasureXLeft()/(double)treasureField.getFieldWidth()*canvasWidth;
		
		Image image = treasureField.getTreasureImage();
		double w = image.getWidth()/(double)treasureField.getFieldWidth()*canvasWidth;
		double h = image.getHeight()/(double)treasureField.getFieldHeight()*canvasHeight;

		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		gc.drawImage(image, x, y, w, h);
	}
	
	private void redrawTreasure() {
		clearCanvas();
		drawTreasure();
	}
	
	private void showTreasure() {
		drawTreasure();
		
		canvas.widthProperty().addListener(resizeListener);
		canvas.heightProperty().addListener(resizeListener);
	}
	
	private void updateScore() {
		scorer.updateScore(puzzlerAttempts);
		totalScoreLabel.setText(String.format(GameSettings.SCORE_FORMAT, scorer.getTotalScore()));
		roundScoreLabel.setText(String.format(GameSettings.SCORE_FORMAT, scorer.getRoundScore()));
	}

	@Override
	public void setState(int puzzlerSetter, int puzzlerDiffc, Stage stage) {}
	
}
