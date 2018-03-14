package edu.cuny.brooklyn.project.frame;

public class GameFrame {
	private FlashFrame flashFrame;
	private PuzzlerFrame puzzlerFrame;
	private TreasureFrame treasureFrame;
	
	public GameFrame() {
		flashFrame = new FlashFrame();
		puzzlerFrame = new PuzzlerFrame();
		treasureFrame = new TreasureFrame();
	}
	
	public FlashFrame getFlashFrame() {
		return flashFrame;
	}
	
	public PuzzlerFrame getPuzzlerFrame() {
		return puzzlerFrame;
	}

	public TreasureFrame getTreasureFrame() {
		return treasureFrame;
	}
}
