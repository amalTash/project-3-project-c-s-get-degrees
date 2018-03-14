package edu.cuny.brooklyn.project.treasure;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.cuny.brooklyn.project.GameSettings;
import javafx.scene.image.Image;

public class TreasureField {
	private final static Logger LOGGER = LoggerFactory.getLogger(TreasureField.class);
	
	private TreasureGenerator treasureGenerator;
	
	private int fieldWidth;
	private int fieldHeight;
	private int[][] field;
	
	private Treasure treasure;
	private int xLeft; /* position of the top left corder of the treasure bounding box */ 
	private int yTop;  /* position of the top left corner of the treasure bounding box */ 
	
	private Random rng;
	
	public TreasureField() {
		fieldWidth = GameSettings.FIELD_WIDTH;
		fieldHeight = GameSettings.FIELD_HEIGHT;
		field = new int[fieldHeight][fieldWidth];
		
		clear();	
		
		treasure = null;
		rng = new Random();
	}
	

	public boolean foundTreasure(int x, int y) {
		if (x >= fieldWidth || x < 0 || y >= fieldHeight || y < 0) {
			LOGGER.debug(String.format("(x, y, fieldWidth, fieldHeight) = (%d, %d, %d, %d)", x, y, fieldWidth, fieldHeight));
			LOGGER.debug("User enter a location out of bound, not a treasure obviously.");
			return false;
		}
		
		LOGGER.debug("User's guess (x, y) = (" + x + ", " + y + ")");
		int xRight = xLeft + treasure.getBoundingBoxWidth();
		int yBottom = yTop + treasure.getBoundingBoxLength();
		LOGGER.debug("Treasure is bounded at (xLeft, yTop) -- (xRight, yBottom) = "
				+ "(" + xLeft + ", " + yTop + ") -- " 
				+ "(" + xRight + ", " + yBottom + ")");
		int xOffset = x - xLeft;
		int yOffset = y - yTop;
		LOGGER.debug("Treasure cell at TreasureField(x, y) = (" + x + "," + y + ")");
		LOGGER.debug("Treasure cell at Treasure(x, y) = (" + xOffset + "," + yOffset + ")");
		return treasure.isTreasureCell(xOffset, yOffset);
	}



	public int getValueAt(int x, int y) {
		return field[y][x];
	}
	
	public int getFieldWidth() {
		return fieldWidth;
	}
	
	public int getFieldHeight() {
		return fieldHeight;
	}
	
	public int getTreasureXLeft() {
		return xLeft;
	}

	public int getTreasureYTop() {
		return yTop;
	}
	
	public int getTreasureBoundingBoxLength() {
		return treasure.getBoundingBoxLength();
	}
	
	public int getTreasureBoundingBoxWidth() {
		return treasure.getBoundingBoxWidth();
	}
	


	public Image getTreasureImage() {
		return treasure.toImage();
	}
	
	public void placeTreasure() {
		clear();
		Treasure treasure = treasureGenerator.generate();
		placeTreasure(treasure);
	}
	
	public void placeTreasure(Treasure treasure) {
		this.treasure = treasure;
		int availableWidth = fieldWidth - treasure.getBoundingBoxLength();
		int availableHeight = fieldHeight - treasure.getBoundingBoxWidth();
		xLeft = rng.nextInt(availableWidth);
		yTop = rng.nextInt(availableHeight);
		int xRight = xLeft + treasure.getBoundingBoxWidth();
		int yBottom = yTop + treasure.getBoundingBoxLength();
		LOGGER.debug("Treasure is placed and bounded at (xLeft, yTop) -- (xRight, yBottom) = "
				+ "(" + xLeft + ", " + yTop + ") -- " 
				+ "(" + xRight + ", " + yBottom + ")");
	}
	
	public void setTreasureGenerator(TreasureGenerator generator) {
		treasureGenerator = generator;
	}

	private void clear() {
		for (int i=0; i<field.length; i++) {
			for (int j=0; j<field[i].length; j++) {
				field[i][j] = GameSettings.FIELD_EMPTY;
			}
		}
	}
}
