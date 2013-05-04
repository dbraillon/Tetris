package com.dbraillon.dtetris.views;

import java.awt.Font;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.TrueTypeFont;

import com.dbraillon.dtetris.Game;


public class MenuView implements View {

	// constantes qui représente les items du menu
	private final int PLAY_ITEM 		= 0;
	private final int PREFERENCE_ITEM 	= 1;
	private final int QUIT_ITEM 		= 2;
	
	// commandes possibles
	private final int GO_UP 	= -1;
	private final int GO_DOWN 	=  1;
	
	// couleur utilisée dans la vue
	private final Color WHITE_COLOR;
	
	// polices utilisées dans la vue
	private final TrueTypeFont TITLE_FONT;
	private final TrueTypeFont MENU_ITEM_FONT;
	
	// propriétés de la vue
	private int _width, _height;
	
	private int _itemSelected;
	
	
	public MenuView(int width, int height) {
		
		System.out.println("+ MenuView(" + width + ", " + height + ")");
		
		_width 	= width;
		_height = height;
		_itemSelected = PLAY_ITEM;
		
		WHITE_COLOR 	= new Color(255, 255, 255);
		TITLE_FONT  	= new TrueTypeFont(new Font("Courier New", Font.BOLD, 24), true);
		MENU_ITEM_FONT 	= new TrueTypeFont(new Font("Courier New", Font.BOLD, 14), true);
	}
	
	@Override
	public void init(GameContainer gc) {
		
		System.out.println("  MenuView: init");
	
		// nothing
	}
	
	public void render(Graphics graphics) {
		
		graphics.setColor(WHITE_COLOR);
		
		// titre
		graphics.setFont(TITLE_FONT);
		graphics.drawString("Tétris2d", percent(_width, 10), percent(_height, 10));
		
		// menu items
		graphics.setFont(MENU_ITEM_FONT);
		graphics.drawString(get_ItemSelected(PLAY_ITEM) 	 + " Jouer", 	  percent(_width, 10), percent(_height, 90) - 60);
		graphics.drawString(get_ItemSelected(PREFERENCE_ITEM)+ " Préférence", percent(_width, 10), percent(_height, 90) - 30);
		graphics.drawString(get_ItemSelected(QUIT_ITEM) 	 + " Quitter",    percent(_width, 10), percent(_height, 90) -  0);
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
		_itemSelected = (_itemSelected ==  3) ? PLAY_ITEM : _itemSelected;
		_itemSelected = (_itemSelected == -1) ? QUIT_ITEM : _itemSelected;
		
		System.out.println("+ MenuView: Item selected: " + _itemSelected);
	}
	
	private int select_ItemSelected(GameContainer gameContainer) {
		
		System.out.println("+ MenuView: Item clicked: " + _itemSelected);
		
		switch(_itemSelected) {
			
			case PLAY_ITEM:
				
				return Game.PLAY_VIEW;
				
			case PREFERENCE_ITEM:
				
				break;
			
			case QUIT_ITEM:
				
				gameContainer.exit();
				break;
		}
		
		return Game.MENU_VIEW;
	}
}
