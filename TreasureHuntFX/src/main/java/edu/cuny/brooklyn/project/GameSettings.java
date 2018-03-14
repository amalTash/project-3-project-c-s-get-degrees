package edu.cuny.brooklyn.project;

import javafx.geometry.Insets;
import javafx.scene.paint.Color;

public class GameSettings {

	
	// default treasure field size
	public final static int FIELD_WIDTH = 70;
	public final static int FIELD_HEIGHT = 15;

	// field cell is empty
	public final static int FIELD_EMPTY = 0;
	
	// default treasure set up
	public final static int DEFAULT_TREASURE_SIZE = 10;
	public final static int DEFAULT_TREASURE_VALUE = 1;
	
	// number of types of puzzlers
	public final static int NUM_TYPES_OF_PUZZLERS = 1;
	
	// clue error
	public static final int DEFAULT_CLUE_ERROR_INCREMENT = 2;
	// clue error: to make it fun, error can be set proportional to the size of the treasure
	public static final double DEFAULT_CLUE_RELATIVE_ERROR_INCREMENT = 1.;
	
	// score computation
	public static final int MAX_SCORE = 100;
	public static final int SCORE_PENALTY = 10;

	// UI Settings
	public final static String APP_ICON_IMAGE = "icon/icon.png";
	public final static double SCENE_WIDTH = 900.;
	public final static double SCENE_HEIGHT = 600.;
	public final static double H_SPACING = 20.;
	public final static double V_SPACING = 20.;
	
	
	public final static double CANVAS_WIDTH = 600;
	public final static double CANVAS_HEIGHT = 400;
	public final static Color CANVAS_COLOR = Color.WHITE;
	public final static double CANVAS_HOLDER_MIN_WIDTH = CANVAS_WIDTH;
	public final static double CANVAS_HOLDER_MIN_HEIGHT = CANVAS_HEIGHT;
	
	public final static Insets PADDING = new Insets(20., 20., 20., 20.);
	public final static Insets PADDING_X = new Insets(0., 20., 0., 20.); 
	
	public final static String SCORE_FORMAT = "%05d";

	public static final Color DEFAULT_TREASURE_CELL_COLOR = Color.GOLD;
	
	public static final String DEFAULT_CANVAS_HOLDER_STYLE = "-fx-background-color:white";
}
