package com.dbraillon.tetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Square {

	public static final int SIZE_CLIP = 32;
	
	private int x, y;
	private Color color;
	private boolean isWall, isEmpty;
	
	public Square(int x, int y, Color color, boolean isWall, boolean isEmpty) {
		
		this.x = x;
		this.y = y;
		this.color = color;
		this.isWall = isWall;
		this.isEmpty = isEmpty;
	}
	
	public void fall() {
		
		this.y++;
	}

	public void up() {
		
		this.y--;
	}
	
	public void move(int move) {
		
		this.x += move;
	}
	
	public void draw(Graphics graphics) {
		
		if(!isEmpty) {
		
			graphics.setColor(color);
			graphics.fillRect(10 + x * SIZE_CLIP + 2, 32 + y * SIZE_CLIP + 2, SIZE_CLIP - 4, SIZE_CLIP - 4);
			
			graphics.setColor(color.darker());
			graphics.drawRect(10 + x * SIZE_CLIP + 1, 32 + y * SIZE_CLIP + 1, SIZE_CLIP - 3, SIZE_CLIP - 3);
			graphics.drawRect(10 + x * SIZE_CLIP, 32 + y * SIZE_CLIP, SIZE_CLIP - 1, SIZE_CLIP - 1);
		}
	}
	
	public int getX() {
		
		return x;
	}
	
	public void addX(int value) {
		
		x += value;
	}
	
	public int getY() {
		
		return y;
	}
	
	public void addY(int value) {
		
		y += value;
	}
	
	public boolean isWall() {
		
		return isWall;
	}
	
	public boolean isEmpty() {
		
		return isEmpty;
	}
	
	public void isEmpty(boolean isEmpty) {
		
		this.isEmpty = isEmpty;
	}
}
