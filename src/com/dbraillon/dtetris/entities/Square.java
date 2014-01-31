package com.dbraillon.dtetris.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;

import com.dbraillon.dbgraphics.Depth;
import com.dbraillon.dbgraphics.Point;
import com.dbraillon.dbgraphics.Renderable;
import com.dbraillon.dtetris.GameConfigs;

public class Square extends Renderable {

	private int x, y;
	private Image image;
	private boolean isEmpty;
	
	public Square(int x, int y, Image image, Point position) {
		super(position, Depth.Middle);
		
		this.x = x;
		this.y = y;
		this.image = image;
		this.isEmpty = false;
	}
	
	public Square(int x, int y, Image image, Point position, boolean isEmpty) {
		super(position, Depth.Middle);
		
		this.x = x;
		this.y = y;
		this.image = image;
		this.isEmpty = isEmpty;
	}
	
	@Override
	protected void render(GameContainer gameContainer) {
		
		if(y > 1 || isEmpty) {
		
			gameContainer.getGraphics().drawImage(image, 
					x * GameConfigs.gameSquareSize + getPosition().getX(), 
					y * GameConfigs.gameSquareSize + getPosition().getY());
		}
	}
	
	public void fall(int fall) {
		
		this.y += fall;
	}
	
	public void move(int move) {
		
		this.x += move;
	}
	
	public int getX() {
		
		return x;
	}
	
	public int getY() {
		
		return y;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
}
