package com.dbraillon.dtetris.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.dbraillon.dbgraphics.Point;
import com.dbraillon.dtetris.GameConfigs;

public class Square {

	private int x, y;
	private Color color;
	
	public Square(int x, int y, Color color) {
		
		this.x = x;
		this.y = y;
		this.color = color;
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

	public void draw(Graphics graphics, Point position) {
		
		graphics.setColor(color);
		graphics.fillRect(position.getX() + x * GameConfigs.gameSquareSize + 2, position.getY() + y * GameConfigs.gameSquareSize + 2, GameConfigs.gameSquareSize - 4, GameConfigs.gameSquareSize - 4);
		
		graphics.setColor(color.darker());
		graphics.drawRect(position.getX() + x * GameConfigs.gameSquareSize + 1, position.getY() + y * GameConfigs.gameSquareSize + 1, GameConfigs.gameSquareSize - 3, GameConfigs.gameSquareSize - 3);
		graphics.drawRect(position.getX() + x * GameConfigs.gameSquareSize, position.getY() + y * GameConfigs.gameSquareSize, GameConfigs.gameSquareSize - 1, GameConfigs.gameSquareSize - 1);
	}
}
