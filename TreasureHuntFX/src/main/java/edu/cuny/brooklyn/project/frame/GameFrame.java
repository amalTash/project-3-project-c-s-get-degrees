package edu.cuny.brooklyn.project.frame;

public class GameFrame {
	private FlashFrame flashFrame;
	private SettingsFrame settingsFrame;
	private PuzzlerFrame puzzlerFrame;
	private TreasureFrame treasureFrame;
	private FlashEndFrame flashEndFrame;
	
	public GameFrame() {
		flashFrame = new FlashFrame();
		settingsFrame = new SettingsFrame();
		puzzlerFrame = new PuzzlerFrame();
		treasureFrame = new TreasureFrame();
		flashEndFrame = new FlashEndFrame();
	}
	
	public FlashFrame getFlashFrame() {
		return flashFrame;
	}
	
	public SettingsFrame getSettingsFrame() {
		return settingsFrame;
	}
	
	public PuzzlerFrame getPuzzlerFrame() {
		return puzzlerFrame;
	}

	public TreasureFrame getTreasureFrame() {
		return treasureFrame;
	}
	
	public FlashEndFrame getFlashEndFrame() {
		return flashEndFrame;
	}
}
