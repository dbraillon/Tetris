package com.dbraillon.tetris.views;

import java.util.ArrayList;
import java.util.Vector;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.dbraillon.tetris.Cube;
import com.dbraillon.tetris.Game;
import com.dbraillon.tetris.ItemController;
import com.dbraillon.tetris.Piece;

public class PlayView {

	private final int HEIGHT_MAP = 22;
	private final int WIDTH_MAP = 10;
	private final int SIZE_CLIP = 32;
	private final int X_END_MAP;
	private final int Y_END_MAP;
	
	private final Color WHITE_COLOR = new Color(255, 255, 255);
	private final Color DARK_GREY_COLOR = new Color(120, 120, 120);
	
	
	private int _heightScreen, _widthScreen;
	private ArrayList<Cube> _cubes;
	private Piece _piece;
	private ItemController _itemController;
	
	private long _score;
	private int _level;
	private int _speedFall;
	private int _speedMove;
	
	private int _moveTimer;
	private int _fallTimer;
	private int _landTimer;
	
	public PlayView(int width, int height) {
		
		_heightScreen = width;
		_widthScreen = height;
		
		_cubes = new ArrayList<Cube>();
		_piece = new Piece();
		_itemController = new ItemController(WIDTH_MAP, HEIGHT_MAP);
		
		_moveTimer = 0;
		_fallTimer = 0;
		_landTimer = 0;
		
		_speedFall = 5;
		_speedMove = 2;
		
		X_END_MAP = 10 + WIDTH_MAP * SIZE_CLIP + 10;
		Y_END_MAP = 10 + HEIGHT_MAP * SIZE_CLIP + 10;
	}
	
	
	public void render(Graphics graphics) {
		
		graphics.setColor(WHITE_COLOR);
		graphics.drawString("Score : " + _score, X_END_MAP, Y_END_MAP);
		
		for(int x = -1; x <= WIDTH_MAP; x++) {
			
			for(int y = -1; y <= HEIGHT_MAP; y++) {
				
				if(x == -1 || x == WIDTH_MAP || y == -1 || y == HEIGHT_MAP) {
					
					drawCube(graphics, DARK_GREY_COLOR, x, y);
				}
				
				for(Cube cube : _piece.pieces) {
					
					drawCube(graphics, cube.color, cube.x, cube.y);
				}
				
				for(Cube cube : _cubes) {
					
					drawCube(graphics, cube.color, cube.x, cube.y);
				}
			}
		}
	}
	
	private void drawCube(Graphics graphics, Color color, int x, int y) {
		
		graphics.setColor(color);
		graphics.fillRect(10 + (x + 1) * SIZE_CLIP + 2, 10 + (y + 1) * SIZE_CLIP + 2, SIZE_CLIP - 4, SIZE_CLIP - 4);
		
		graphics.setColor(color.darker());
		graphics.drawRect(10 + (x + 1) * SIZE_CLIP + 1, 10 + (y + 1) * SIZE_CLIP + 1, SIZE_CLIP - 3, SIZE_CLIP - 3);
		graphics.drawRect(10 + (x + 1) * SIZE_CLIP, 10 + (y + 1) * SIZE_CLIP, SIZE_CLIP - 1, SIZE_CLIP - 1);
	}
	
	public int update(GameContainer gameContainer) {
		
		Input input = gameContainer.getInput();
		
		if(input.isKeyDown(Keyboard.KEY_LEFT) && _moveTimer >= _speedMove) {
			
			_itemController.move(-1, _piece, _cubes);
			_moveTimer = 0;
		}
		else if(input.isKeyDown(Keyboard.KEY_RIGHT) && _moveTimer >= _speedMove) {
			
			_itemController.move(1, _piece, _cubes);
			_moveTimer = 0;
		}
		else if(input.isKeyPressed(Keyboard.KEY_UP)) {
			
			_itemController.turn(true, _piece, _cubes);
		}
		else if(input.isKeyDown(Keyboard.KEY_DOWN) && _moveTimer >= _speedMove) {
			
			_fallTimer = _speedFall;
		}
		else if(input.isKeyPressed(Keyboard.KEY_RETURN)) {
			
			_itemController.hardFall(_piece, _cubes);
			
			_fallTimer = 0;
			_moveTimer = 0;
		}
		
		if(_fallTimer >= _speedFall) {
			
			if(!_itemController.fall(_piece, _cubes)) {

				for(Cube cItem : _piece.pieces) {
					_cubes.add(cItem);
				}
				
				_score += _itemController.removeLine(_piece, _cubes);
				_piece = _itemController.create(_cubes);
				if(!_itemController.verify(_cubes, _piece)) {
					
					return Game.MENU_VIEW;
				}
			}
			
			_fallTimer = 0;
			_moveTimer = 0;
		}
		else {
			
			_fallTimer++;
		}
		
		if(_moveTimer < _speedMove) {
			
			_moveTimer++;
		}
		
		return Game.PLAY_VIEW;
	}
}
