package com.dbraillon.dtetris;

import org.newdawn.slick.SlickException;

import com.dbraillon.dbgraphics.Screen;
import com.dbraillon.dbgraphics.UiApplication;
import com.dbraillon.dtetris.views.MenuScreen;


public class Game extends UiApplication {

	// TODO: http://tetris.wikia.com/wiki/Tetris_Guideline
	public static void main(String[] args) throws SlickException {
        
        new Game(GameConfigs.windowsHeight, GameConfigs.windowsWidth, GameConfigs.windowsName,
                         new MenuScreen(), GameConfigs.windowsFrameRate);
	}
	
	public Game(int height, int width, String title, Screen firstScreen, int frameRate) throws SlickException {
	        super(height, width, title, firstScreen, frameRate);
	        
	}
}
