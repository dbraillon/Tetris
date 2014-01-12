package com.dbraillon.dtetris;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.dbraillon.dbgraphics.Point;


public class Square {

	public static final int SIZE_CLIP = 32;
	
	private int x, y;
	private Color color;
	private boolean isEmpty;
	
	public Square(int x, int y, Color color, boolean isEmpty) {
		
		this.x = x;
		this.y = y;
		this.color = color;
		this.setEmpty(isEmpty);
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
		graphics.fillRect(position.getX() + x * SIZE_CLIP + 2, position.getY() + y * SIZE_CLIP + 2, SIZE_CLIP - 4, SIZE_CLIP - 4);
		
		graphics.setColor(color.darker());
		graphics.drawRect(position.getX() + x * SIZE_CLIP + 1, position.getY() + y * SIZE_CLIP + 1, SIZE_CLIP - 3, SIZE_CLIP - 3);
		graphics.drawRect(position.getX() + x * SIZE_CLIP, position.getY() + y * SIZE_CLIP, SIZE_CLIP - 1, SIZE_CLIP - 1);
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}
}
