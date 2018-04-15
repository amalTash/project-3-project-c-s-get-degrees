package edu.cuny.brooklyn.project.puzzler;

public abstract class GuessingPuzzler extends Puzzler {
	public GuessingPuzzler() {
		this(null, null);
	}
	
	public GuessingPuzzler(String message, String answer) {
		super(message, answer, PuzzlerSettings.GUESSING_PUZZLER);
	}
}
