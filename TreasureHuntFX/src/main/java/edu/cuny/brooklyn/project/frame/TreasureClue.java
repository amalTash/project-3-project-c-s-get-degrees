package edu.cuny.brooklyn.project.frame;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.GameSettings;

public class TreasureClue {
	private final static Logger LOGGER = LoggerFactory.getLogger(TreasureClue.class);
	
	private static Random rng = new Random();
	
	public static int xOffset = 0;
	public static int yOffset = 0;
	public static int clueError = 0;
	
	
	/*
	 * In fact, we can have different types of clues. That can make the game a little more interesting. 
	 */
	public static String getClue(int xLeft, int yTop, int width, int length, int attempts) {
		clueError = (int) (attempts * GameSettings.DEFAULT_CLUE_RELATIVE_ERROR_INCREMENT * Math.max(width, length));
		xOffset = rng.nextInt(clueError) - clueError / 2;
		yOffset = rng.nextInt(clueError) - clueError / 2;
		
		// x and y may be out of bound, so are user's inputs
		int x = xLeft + width/2 + xOffset;
		int y = yTop + length/2 + yOffset;
		
		//marginError = clueError;
		
		LOGGER.debug(String.format("Treasure is at (xLeft, yTop) -- (xRight, yBottom) = (%d, %d) -- (%d, %d)",
				xLeft, yTop, xLeft + width, yTop + length));
		LOGGER.debug(String.format("Clue is given at (x, y) = (%d, %d)", x, y));
		
		
		
		return "A treasure of size " + Math.max(width, length) + " is near (" + x + "," + y + ").";
	}
	
	public static int getCircleX() {
		return xOffset;
	}
	
	public static int getCircleY() {
		return yOffset;
	}
	
	public static int getMarginError() {
		return clueError;
	}

}
