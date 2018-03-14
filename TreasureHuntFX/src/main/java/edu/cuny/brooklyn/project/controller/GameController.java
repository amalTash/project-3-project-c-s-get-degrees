package edu.cuny.brooklyn.project.controller;

import edu.cuny.brooklyn.project.GameSettings;
import edu.cuny.brooklyn.project.frame.FlashFrame;
import edu.cuny.brooklyn.project.frame.GameFrame;
import edu.cuny.brooklyn.project.frame.PuzzlerFrame;
import edu.cuny.brooklyn.project.frame.TreasureFrame;
import edu.cuny.brooklyn.project.treasure.TreasureGenerator;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GameController {
	private GameFrame gameFrame;
	private Stage stage;
	
	public GameController(Stage stage) {
		this.gameFrame = new GameFrame();
		this.stage = stage;
		this.stage.getIcons().add(new Image(GameSettings.APP_ICON_IMAGE));
	}
	
	public void runTheGame() {
		FlashFrame flashFrame = gameFrame.getFlashFrame();
		PuzzlerFrame puzzlerFrame = gameFrame.getPuzzlerFrame();
		TreasureFrame treasureFrame = gameFrame.getTreasureFrame();
		
		TreasureGenerator treasureGenerator = new TreasureGenerator();
		treasureFrame.getTreasureField().setTreasureGenerator(treasureGenerator);
		treasureFrame.getTreasureField().placeTreasure();
		
		flashFrame.setNextFrameToShow(puzzlerFrame, stage);
		puzzlerFrame.setNextFrameToShow(treasureFrame, stage);

		flashFrame.show(stage);
	}
}
