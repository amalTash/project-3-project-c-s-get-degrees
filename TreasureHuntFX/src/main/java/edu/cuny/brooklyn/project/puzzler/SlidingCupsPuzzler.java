package edu.cuny.brooklyn.project.puzzler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Random;

public class SlidingCupsPuzzler extends GuessingPuzzler{
	private final static Logger LOGGER = LoggerFactory.getLogger(SlidingCupsPuzzler.class);
	private Random rnd;
	private ArrayList<String> cupAndBallMaterials;
	private int attempts;
	static int numberOfCups = 3;
	int diffc;
	boolean diffcSwitch;
	
	public SlidingCupsPuzzler() {	
		cupAndBallMaterials =  new ArrayList<String>();
		rnd = new Random();
		String noClue = "Empty";
		String clue = "Clue";
		int arraySize;
		int cluePlacement;
		
		setMessage(numberOfCups + " Cups, choose 1: ");
		
		cupAndBallMaterials.removeAll(cupAndBallMaterials);
		while (cupAndBallMaterials.size() != numberOfCups + 1) {
			cupAndBallMaterials.add(noClue);
			}
		LOGGER.debug("Array size: " + cupAndBallMaterials.size());
		arraySize = cupAndBallMaterials.size();
		cluePlacement = rnd.nextInt(arraySize - 1) + 1;
		cupAndBallMaterials.add(cluePlacement - 1, clue);
		String answer = Integer.toString(cluePlacement);
		setAnswer(answer);
		LOGGER.debug("Answer set: " + answer + ", number of cups with clues: ");
	}

	public SlidingCupsPuzzler(int diffcSetter) {
		this.diffc = diffcSetter;
		puzzlerSetUp(diffc);
	}
	
	public void puzzlerSetUp(int diffc) {
		
		if (diffcSwitch == false) {
			switch(diffc) {
			case 0:
				numberOfCups = 3;
				break;
			case 1:
				numberOfCups = 5;
				break;
			case 2:
				numberOfCups = 10;
				break;
			default:
				numberOfCups = 3;
			}
		diffcSwitch = true;
		}
		
		cupAndBallMaterials =  new ArrayList<String>();
		rnd = new Random();
		String noClue = "Empty";
		String clue = "Clue";
		int arraySize;
		int cluePlacement;
		
		setMessage(numberOfCups + " Cups, choose 1: ");
		
		cupAndBallMaterials.removeAll(cupAndBallMaterials);
		while (cupAndBallMaterials.size() != numberOfCups + 1) {
			cupAndBallMaterials.add(noClue);
			}
		LOGGER.debug("Array size: " + cupAndBallMaterials.size());
		arraySize = cupAndBallMaterials.size();
		cluePlacement = rnd.nextInt(arraySize - 1) + 1;
		cupAndBallMaterials.add(cluePlacement - 1, clue);
		String answer = Integer.toString(cluePlacement);
		setAnswer(answer);
		LOGGER.debug("Answer set: " + answer + ", number of cups with clues: ");
	}
	
	public boolean isCorrect(String enteredAnswer, int answeringAttempts) {
		String entered = enteredAnswer;
		this.attempts = answeringAttempts;
		if (entered.equalsIgnoreCase(getAnswer())) {
			LOGGER.debug("Correct answer");
			setDiffc(attempts);
			return true;
		} else {
			LOGGER.debug("Incorrect answer");
			setDiffc(attempts);
			return false;
		}
	}
	
	public void setDiffc(int attempts) {
		if(attempts <= 2) {
			numberOfCups++;
		} else if(attempts > 2 && numberOfCups > 3) {
			numberOfCups--;
		} else 
			numberOfCups = 3;
	}
}
