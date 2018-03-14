package edu.cuny.brooklyn.project.treasure;

import edu.cuny.brooklyn.project.GameSettings;
import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public abstract class Treasure {
	/*
	 * Treasure is bounded in a box
	 * +---------------------+------------------>X
	 * |333312111111111113444|     ^
	 * |        2222         |    length
	 * |          2          |     V
	 * +---------------------+------
	 * |                     |
	 * |<-----width--------->|
	 * |
	 * |
	 * V Y
	 */
	private int totalValue;
	
	public abstract int getBoundingBoxWidth();

	public abstract int getBoundingBoxLength();

	public abstract int getValueAt(int x, int y);
	
	public abstract boolean isTreasureCell(int x, int y);
	
	public int getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(int totalValue) {
		this.totalValue = totalValue;
	}
	
	public Image toImage() {
		WritableImage writableImage = new WritableImage(getBoundingBoxWidth(), getBoundingBoxLength());
		PixelWriter pixelWriter = writableImage.getPixelWriter();
		for (int i=0; i<getBoundingBoxLength(); i++) {
			for (int j=0; j<getBoundingBoxWidth(); j++) {
				pixelWriter.setColor(j, i, GameSettings.DEFAULT_TREASURE_CELL_COLOR);
			}
		}
		return writableImage;
	}
	
}
