package edu.cuny.brooklyn.project.frame;

import javafx.stage.Stage;

public abstract class Frame {
	public abstract void show(Stage stage);
	public abstract void setState(int puzzlerSetter, int puzzlerDiffc, Stage stage);
}
