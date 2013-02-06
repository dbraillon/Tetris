package com.dbraillon.tetris;

import java.util.Vector;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.dbraillon.tetris.views.Menu;

public class Game extends BasicGame {

	// TODO: http://tetris.wikia.com/wiki/Tetris_Guideline
	
	private Menu menu;
	
	private int xMap = 10;
	private int yMap = 22;
	private int clipSize = 32;
	
	private int xBegin = 37;
	private int yBegin = 37;
	
	private ItemController ic;
	private Piece cPiece;
	private Vector<Item> items;
	private int fallingTimer;
	private int movingTimer;
	
	private int score;
	
	public Game(String title) {
		super(title);
	}

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		menu.render(g);
		
		g.setColor(new Color(255, 255, 255));
		g.drawString("Score : " + score, (xMap+2) * (clipSize+1) + xBegin, yBegin-32);
		
		for(int x = -1; x <= xMap; x++) {
			
			for(int y = -1; y <= yMap; y++) {
				
				if(x == -1 || x == xMap || y == -1 || y == yMap) {
					
					Color c = new Color(102, 102, 102);
					g.setColor(c);
					g.fillRect(x * (clipSize+1) + xBegin, y * (clipSize+1) + yBegin, 
							   clipSize, clipSize);

					g.setColor(c.darker());
					g.drawRect(x * (clipSize+1) + xBegin, y * (clipSize+1) + yBegin,
							   clipSize, clipSize);
					g.drawRect(x * (clipSize+1) + xBegin + 1, y * (clipSize+1) + yBegin + 1,
							   clipSize - 2, clipSize - 2);
				}
			}
		}
		
		for(Item oItem : cPiece.pieces) {
			
			g.setColor(oItem.color);
			g.fillRect(oItem.x * (clipSize+1) + xBegin, oItem.y * (clipSize+1) + yBegin,
					   clipSize, clipSize);
			
			g.setColor(oItem.color.darker());
			g.drawRect(oItem.x * (clipSize+1) + xBegin, oItem.y * (clipSize+1) + yBegin,
					   clipSize, clipSize);
			g.drawRect(oItem.x * (clipSize+1) + xBegin + 1, oItem.y * (clipSize+1) + yBegin + 1,
					   clipSize - 2, clipSize - 2);
		}
		
		for(Item oItem : items) {
			
			g.setColor(oItem.color);
			g.fillRect(oItem.x * (clipSize+1) + xBegin, oItem.y * (clipSize+1) + yBegin,
					   clipSize, clipSize);
			
			g.setColor(oItem.color.darker());
			g.drawRect(oItem.x * (clipSize+1) + xBegin, oItem.y * (clipSize+1) + yBegin,
					   clipSize, clipSize);
			g.drawRect(oItem.x * (clipSize+1) + xBegin + 1, oItem.y * (clipSize+1) + yBegin + 1,
					   clipSize - 2, clipSize - 2);
		}
	}
	
	@Override
	public void init(GameContainer gc) throws SlickException {
		
		menu = new Menu(600, 800);
		
		ic = new ItemController(xMap, yMap);
		cPiece = new Piece();
		items = new Vector<Item>();
		fallingTimer = 0;
		movingTimer = 0;
	}
	
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		
		Input input = gc.getInput();
		
		if(input.isKeyDown(Keyboard.KEY_LEFT) && movingTimer == 0) {
			
			ic.move(-1, cPiece, items);
			movingTimer = 2;
		}
		else if(input.isKeyDown(Keyboard.KEY_RIGHT) && movingTimer == 0) {
			
			ic.move(1, cPiece, items);
			movingTimer = 2;
		}
		else if(input.isKeyPressed(Keyboard.KEY_UP)) {
			
			ic.turn(true, cPiece, items);
		}
		else if(input.isKeyDown(Keyboard.KEY_DOWN) && movingTimer == 0) {
			
			if(!ic.fall(cPiece, items)) {

				for(Item cItem : cPiece.pieces) {
					items.add(cItem);
				}
				
				score += ic.removeLine(cPiece, items);
				cPiece = ic.create(items);
				if(cPiece == null) score = 99999;
			}
			
			fallingTimer = 0;
			movingTimer = 2;
		}
		else if(input.isKeyPressed(Keyboard.KEY_RETURN)) {
			
			ic.hardFall(cPiece, items);
		}
		
		if(fallingTimer == 15) {
			
			if(!ic.fall(cPiece, items)) {

				for(Item cItem : cPiece.pieces) {
					items.add(cItem);
				}
				
				score += ic.removeLine(cPiece, items);
				cPiece = ic.create(items);
				if(cPiece == null) score = 99999;
			}
			
			fallingTimer = 0;
		}
		
		if(movingTimer != 0) {
			
			movingTimer--;
		}
		
		fallingTimer++;
	}
	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new Game("Tétris"));
		app.setDisplayMode(600, 800, false);
		app.setTargetFrameRate(30);
		app.start();
	}
}
