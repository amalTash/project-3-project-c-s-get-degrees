/**
 * The start-up code for the 1st class project of a sequence 5 projects in CISC 3120 
 * Sections MW2 and MW8 CUNY Brooklyn College. The project should result a simple 
 * text-based game application. 
 * 
 * Spring 2018 
 */

package edu.cuny.brooklyn.project;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.controller.GameController;
import javafx.application.Application;
import javafx.stage.Stage;

public class TreasureHuntFXApp extends Application {
	private final static Logger LOGGER = LoggerFactory.getLogger(TreasureHuntFXApp.class);
	
	public static void main(String[] args) {
		launch(args); 
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		LOGGER.info("TreasureHuntFXApp started.");
		
		GameController controller = new GameController(primaryStage);
		controller.runTheGame();
		
		LOGGER.info("TreasureHuntFXApp exits.");
	}
}
