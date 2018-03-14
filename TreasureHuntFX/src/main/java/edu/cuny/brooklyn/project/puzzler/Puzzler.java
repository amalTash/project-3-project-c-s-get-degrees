package edu.cuny.brooklyn.project.puzzler;

public abstract class Puzzler {
	private String message;
	private String answer;
	private int type;
	
	public Puzzler() {
		this.message = null;
		this.answer = null;
		this.type = PuzzlerSettings.UNSUPPORTED_PUZZLER;
	}
	
	public Puzzler(String message, String answer, int type) {
		this.message = message;
		this.answer = answer;
		this.type = type;
	}
	
	public abstract boolean isCorrect(String enteredAnswer);
	
	public String getAnswer() {
		return answer;
	}
	
	void setAnswer(String answer) {
		this.answer = answer;
	}
	
	public String getMessage() {
		return message;
	}

	void setMessage(String message) {
		this.message = message;
	}	
	
	public int getType() {
		return type;
	}
}
