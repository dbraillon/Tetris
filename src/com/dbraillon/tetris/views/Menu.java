package com.dbraillon.tetris.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

public class Menu {

	private int _width, _height;
	
	public Menu(int width, int height) {
		
		_width = width;
		_height = height;
	}
	
	public void render(Graphics graphics) {
		
		graphics.drawString("Tétris2d", percent(_width, 10), percent(_height, 10));
	}
	
	public void update(GameContainer gameContainer) {
		
	}
	
	private int percent(int value, int percent) {
		
		return value * percent / 100;
	}
}
