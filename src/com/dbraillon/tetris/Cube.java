package com.dbraillon.tetris;

import org.newdawn.slick.Color;

public class Cube {

	public int x, y;
	public Color color;
	
	public Cube(int x, int y, Color color) {
		
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	
	public void fall() {
		
		this.y++;
	}

	public void move(int move) {
		
		this.x += move;
	}
}
