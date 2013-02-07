package com.dbraillon.tetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Cube {

	public static final int SIZE_CLIP = 24;
	
	
	public int x, y;
	public Color color;
	public boolean isWall;
	
	public Cube(int x, int y, Color color, boolean isWall) {
		
		this.x = x;
		this.y = y;
		this.color = color;
		this.isWall = isWall;
	}
	
	
	public void fall() {
		
		this.y++;
	}

	public void move(int move) {
		
		this.x += move;
	}
	
	public void drawCube(Graphics graphics) {
		
		graphics.setColor(color);
		graphics.fillRect(10 + x * SIZE_CLIP + 2, 32 + y * SIZE_CLIP + 2, SIZE_CLIP - 4, SIZE_CLIP - 4);
		
		graphics.setColor(color.darker());
		graphics.drawRect(10 + x * SIZE_CLIP + 1, 32 + y * SIZE_CLIP + 1, SIZE_CLIP - 3, SIZE_CLIP - 3);
		graphics.drawRect(10 + x * SIZE_CLIP, 32 + y * SIZE_CLIP, SIZE_CLIP - 1, SIZE_CLIP - 1);
	}
}
