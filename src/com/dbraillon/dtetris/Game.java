package com.dbraillon.dtetris;
import java.util.ArrayList;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.dbraillon.dtetris.views.MenuView;
import com.dbraillon.dtetris.views.PlayView;
import com.dbraillon.dtetris.views.PreferenceView;
import com.dbraillon.dtetris.views.View;


public class Game extends BasicGame {

	// TODO: http://tetris.wikia.com/wiki/Tetris_Guideline
	
	// constantes qui représentent les vues
	public static final int MENU_VIEW 			= 0;
	public static final int PLAY_VIEW 			= 1;
	public static final int PREFERENCES_VIEW	= 2;
	
	// gestion des vues
	private int  			_selectedView;
	private ArrayList<View> _views;

	
	public Game(String title) {
		
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		_selectedView = MENU_VIEW;
		_views = new ArrayList<View>();
		_views.add(new MenuView(gc.getWidth(), gc.getHeight()));
		_views.add(new PlayView(gc.getWidth(), gc.getHeight()));
		_views.add(new PreferenceView(gc.getWidth(), gc.getHeight()));
		
		for(View view : _views) {
		
			view.init(gc);
		}
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		_views.get(_selectedView).render(g);
	}
	
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		
		_selectedView = _views.get(_selectedView).update(gc);
	}
	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new Game("Tétris2d"));
		app.setDisplayMode(600, 800, false);
		app.setTargetFrameRate(30);
		
		app.start();
	}
}
