package edu.cuny.brooklyn.project.treasure;

import edu.cuny.brooklyn.project.GameSettings;

public class TreasureGenerator {
	public Treasure generate() {
		return new SquareTreasure(GameSettings.DEFAULT_TREASURE_SIZE);
	}
}
