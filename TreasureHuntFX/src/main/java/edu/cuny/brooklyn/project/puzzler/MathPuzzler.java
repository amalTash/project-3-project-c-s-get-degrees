package edu.cuny.brooklyn.project.puzzler;

public abstract class MathPuzzler extends Puzzler {
	public MathPuzzler() {
		this(null, null);
	}
	
	public MathPuzzler(String message, String answer) {
		super(message, answer, PuzzlerSettings.MATH_PUZZLER);
	}
}
