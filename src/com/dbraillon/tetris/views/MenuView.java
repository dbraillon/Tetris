package com.dbraillon.tetris.views;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.dbraillon.tetris.Game;

public class MenuView {

	private final int PLAY_ITEM = 0;
	private final int QUIT_ITEM = 1;
	
	private final int GO_UP = -1;
	private final int GO_DOWN = 1;
	
	private final Color WHITE_COLOR = new Color(255, 255, 255);
	
	
	private int _width, _height;
	private int _itemSelected;
	
	public MenuView(int width, int height) {
		
		_width = width;
		_height = height;
		_itemSelected = PLAY_ITEM;
	}
	
	public void render(Graphics graphics) {
		
		graphics.setColor(WHITE_COLOR);
		graphics.drawString("Tétris2d", percent(_width, 10), percent(_height, 10));
		graphics.drawString(get_ItemSelected(PLAY_ITEM) + " Jouer", percent(_width, 10), percent(_height, 10) + 20);
		graphics.drawString(get_ItemSelected(QUIT_ITEM) + " Quitter", percent(_width, 10), percent(_height, 10) + 35);
	}
	
	public int update(GameContainer gameContainer) {
		
		Input input = gameContainer.getInput();
		
		if(input.isKeyPressed(Keyboard.KEY_DOWN)) {
			
			change_ItemSelected(GO_DOWN);
		}
		else if(input.isKeyPressed(Keyboard.KEY_UP)) {
			
			change_ItemSelected(GO_UP);
		}
		else if(input.isKeyPressed(Keyboard.KEY_RETURN)) {
			
			return select_ItemSelected(gameContainer);
		}
		
		return Game.MENU_VIEW;
	}
	
	private int percent(int value, int percent) {
		
		return value * percent / 100;
	}
	
	private String get_ItemSelected(int value) {
		
		return (_itemSelected == value) ? ">" : " ";
	}
	 
	private void change_ItemSelected(int value) {
		
		_itemSelected += value;
		_itemSelected = (_itemSelected == 2) ? PLAY_ITEM : _itemSelected;
		_itemSelected = (_itemSelected == -1) ? QUIT_ITEM : _itemSelected;
	}
	
	private int select_ItemSelected(GameContainer gameContainer) {
		
		switch(_itemSelected) {
		case PLAY_ITEM:
			return Game.PLAY_VIEW;
		case QUIT_ITEM:
			gameContainer.exit();
			break;
		}
		
		return Game.MENU_VIEW;
	}
}
