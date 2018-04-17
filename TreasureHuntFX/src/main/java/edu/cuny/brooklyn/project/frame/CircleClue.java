package edu.cuny.brooklyn.project.frame;

import edu.cuny.brooklyn.project.treasure.Treasure;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class CircleClue {
	//this radius is the margin of error the user can stray from the treasure location
	int radius  = TreasureClue.getMarginError();
	Treasure treasure;
	private Canvas canvas;

	double canvasWidth = canvas.getWidth();
	double canvasHeight = canvas.getHeight();
	
	public void drawCircle(Treasure treasure) {
		//the x and y coordinate for circle center
		double x = treasure.getBoundingBoxWidth()/2;
		double y = treasure.getBoundingBoxLength()/2;
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		
		//fills an oval with the treasure's center point, using a width and height of the radius*2
		gc.fillOval(x, y, radius*2, radius*2);
	}
	
}