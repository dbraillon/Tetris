package com.dbraillon.dtetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import com.dbraillon.dbgraphics.Depth;
import com.dbraillon.dbgraphics.Point;
import com.dbraillon.dbgraphics.Renderable;

public class ScoringSystem extends Renderable {

	private int score;
	
	public ScoringSystem() {
		super(new Point(25, 50), Depth.Middle, new Color(255, 255, 255), 10, 10);
		
		score = 0;
	}
	
	public void scoreLines(int numberLines) {
		
		score += numberLines;
	}
	
	@Override
	protected void render(GameContainer gameContainer) {
		
		gameContainer.getGraphics().setColor(new Color(255, 255, 255));
		gameContainer.getGraphics().drawString(String.format("Score: %d", score), getPosition().getX(), getPosition().getY());
	}
}
