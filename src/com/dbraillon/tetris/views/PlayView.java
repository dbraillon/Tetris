package com.dbraillon.tetris.views;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.dbraillon.tetris.Cube;
import com.dbraillon.tetris.Field;
import com.dbraillon.tetris.Game;
import com.dbraillon.tetris.ItemController;
import com.dbraillon.tetris.Piece;

public class PlayView {

	
	private final int X_END_MAP;
	private final int Y_END_MAP;
	
	private final Color WHITE_COLOR = new Color(255, 255, 255);
	
	
	private int _heightScreen, _widthScreen;
	private Field _field;
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
		
		_field = new Field();
		_itemController = new ItemController(Field.WIDTH_FIELD, Field.HEIGHT_FIELD);
		_piece = _itemController.create();
		
		_moveTimer = 0;
		_fallTimer = 0;
		_landTimer = 0;
		
		_speedFall = 5;
		_speedMove = 2;
		
		X_END_MAP = 10 + (Field.WIDTH_TOTAL) * Cube.SIZE_CLIP + 10;
		Y_END_MAP = 10 + (Field.HEIGHT_TOTAL) * Cube.SIZE_CLIP + 10;
	}
	
	
	public void render(Graphics graphics) {
		
		graphics.setColor(WHITE_COLOR);
		graphics.drawString("Score : " + _score, X_END_MAP, 10);
		
		_piece.drawPiece(graphics);
		_field.drawField(graphics);
	}
	
	public int update(GameContainer gameContainer) {
		
		Input input = gameContainer.getInput();
		
		if(input.isKeyDown(Keyboard.KEY_Q) && _moveTimer >= _speedMove) {
			
			_itemController.move(-1, _field, _piece);
			_moveTimer = 0;
		}
		else if(input.isKeyDown(Keyboard.KEY_D) && _moveTimer >= _speedMove) {
			
			_itemController.move(1, _field, _piece);
			_moveTimer = 0;
		}
		else if(input.isKeyPressed(Keyboard.KEY_K)) {
			
			_itemController.turn(Piece.TURN_LEFT, _field, _piece);
		}
		else if(input.isKeyPressed(Keyboard.KEY_L)) {
			
			_itemController.turn(Piece.TURN_RIGHT, _field, _piece);
		}
		else if(input.isKeyDown(Keyboard.KEY_S) && _moveTimer >= _speedMove) {
			
			_fallTimer = _speedFall;
		}
		else if(input.isKeyPressed(Keyboard.KEY_RETURN)) {
			
			_itemController.hardFall(_field, _piece);
			
			_fallTimer = 0;
			_moveTimer = 0;
		}
		
		if(_fallTimer >= _speedFall) {
			
			if(!_itemController.fall(_field, _piece)) {

				for(Cube cItem : _piece.cubes) {
					_field.setCube(cItem, cItem.getX(), cItem.getY());
				}
				
				_score += _itemController.removeLine(_field, _piece);
				_piece = _itemController.create();
				if(!_itemController.verify(_field, _piece)) {
					
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
