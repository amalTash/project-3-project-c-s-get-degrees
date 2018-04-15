package edu.cuny.brooklyn.project.puzzler;

import java.util.Random;

public class PuzzlerSettings {
	public final static int UNSUPPORTED_PUZZLER = -1;
	public final static int MATH_PUZZLER = 100;
	public final static int MATH_PUZZLER_SQRT = 101;
	public final static int GUESSING_PUZZLER = 200;
	public final static int GUESSING_PUZZLER_SLIDING_CUPS = 201;
	
	private static int[] puzzlerTypes = {
			MATH_PUZZLER_SQRT, GUESSING_PUZZLER_SLIDING_CUPS 
	};
	
	private static Random rng = new Random();
	
	public static int getRandomPuzzlerType() {
		int typeIndex = rng.nextInt(puzzlerTypes.length);
		return puzzlerTypes[typeIndex]; 
	}
}
