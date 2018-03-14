package edu.cuny.brooklyn.project.puzzler;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SqrtMathPuzzler extends MathPuzzler {
	private final static Logger LOGGER = LoggerFactory.getLogger(SqrtMathPuzzler.class);
	
	private double toleratedAnswerRelativeError;
	private double answerValue;
	
	private Random rng;
	
	public SqrtMathPuzzler(int minNumber, int maxNumber, double toleratedAnswerRelativeError) {
		this.toleratedAnswerRelativeError = toleratedAnswerRelativeError;
		
		rng = new Random();
		if (minNumber <= 0) {
			LOGGER.warn("minNumber = " + minNumber + ", but expecting a number > 0. Use 2 instead ");
		}
		if (maxNumber <= 2) {
			LOGGER.warn("maxNumber = " + maxNumber + ", but expecting a number > 1. Use 2 instead ");			
		}
		int num = minNumber + rng.nextInt(maxNumber - minNumber + 1);
		String message = "Sqrt(" + num + ") = ?";
		answerValue = Math.sqrt(num);
		String answer = Double.toString(answerValue);
		setMessage(message);
		setAnswer(answer);
	}
	

	public boolean isCorrect(String enteredAnswer) {
		double entered = Double.parseDouble(enteredAnswer);
		if (Math.abs((entered - answerValue) / answerValue) < toleratedAnswerRelativeError) {
			LOGGER.debug("Correct answer");
			return true;
		} else {
			LOGGER.debug("Incorrect answer");
			return false;
		}
	}

}
