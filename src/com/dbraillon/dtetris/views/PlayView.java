package com.dbraillon.dtetris.views;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.dbraillon.dtetris.SuperMoveSystem;
import com.dbraillon.dtetris.Playfield;
import com.dbraillon.dtetris.RandomGenerator;
import com.dbraillon.dtetris.SuperRotationSystem;
import com.dbraillon.dtetris.Tetromino;
import com.dbraillon.tetris.Square;
import com.dbraillon.tetris.Field;
import com.dbraillon.tetris.Game;
import com.dbraillon.tetris.ItemController;

public class PlayView {

	private final Color WHITE_COLOR = new Color(255, 255, 255);
	
	private int heightScreen, widthScreen;
	
	private RandomGenerator randomGenerator;
	private SuperMoveSystem superMoveSystem;
	private SuperRotationSystem superRotationSystem;
	
	private Playfield playfield;
	private Tetromino tetromino;
	
	private long score;
	
	private int moveTimer;
	private int fallTimer;
	
	private int moveSpeed;
	private int fallSpeed;
	
	
	private final int X_END_MAP;
	private final int Y_END_MAP;
	
	/*
	
	private Tetromino _piece;
	private ItemController _itemController;
	
	
	private int _level;
	
	private int _landTimer;*/
	
	public PlayView(int width, int height) {
		
		heightScreen = width;
		widthScreen = height;
		
		randomGenerator = new RandomGenerator();
		superMoveSystem = new SuperMoveSystem();
		superRotationSystem = new SuperRotationSystem();
		
		playfield = new Playfield(22, 12);
		tetromino = randomGenerator.nextPiece();
		
		score = 0;
		fallSpeed = 10;
		moveSpeed = 2;
		
		X_END_MAP = 10 + playfield.getWidth() * Square.SIZE_CLIP + 10;
		Y_END_MAP = 10 + playfield.getHeight() * Square.SIZE_CLIP + 10;
		
		/*
		_piece = _itemController.create();
		
		_moveTimer = 0;
		_fallTimer = 0;
		_landTimer = 0;
		
		
		
		*/
	}
	
	
	public void render(Graphics graphics) {
		
		graphics.setColor(WHITE_COLOR);
		graphics.drawString("Score : " + score, X_END_MAP, 10);
		
		playfield.draw(graphics);
		tetromino.draw(graphics);
	}
	
	public int update(GameContainer gameContainer) {
		
		Input input = gameContainer.getInput();
		
		if(input.isKeyDown(Keyboard.KEY_Q) && moveTimer >= moveSpeed) {
			
			superMoveSystem.move(playfield, tetromino, Tetromino.MOVE_LEFT);
			moveTimer = 0;
		}
		else if(input.isKeyDown(Keyboard.KEY_D) && moveTimer >= moveSpeed) {
			
			superMoveSystem.move(playfield, tetromino, Tetromino.MOVE_RIGHT);
			moveTimer = 0;
		}
		else if(input.isKeyPressed(Keyboard.KEY_K)) {
			
			superRotationSystem.rotate(Tetromino.TURN_LEFT, tetromino, playfield);
		}
		else if(input.isKeyPressed(Keyboard.KEY_L)) {
			
			superRotationSystem.rotate(Tetromino.TURN_RIGHT, tetromino, playfield);
		}
		else if(input.isKeyDown(Keyboard.KEY_S) && moveTimer >= moveSpeed) {
			
			fallTimer = fallSpeed;
		}
		
		if(fallTimer >= fallSpeed) {
			
			if(!superMoveSystem.fall(playfield, tetromino, randomGenerator)) {
				
				// add the Tetromino in the Playfield
				playfield.setTetromino(tetromino);
				playfield.checkLines(tetromino);
				
				// create a new Tetromino
				tetromino = randomGenerator.nextPiece();
			}
			
			fallTimer = 0;
			moveTimer = 0;
		}
		else {
			
			fallTimer++;
		}
		
		if(moveTimer < moveSpeed) {
			
			moveTimer++;
		}
		
		return Game.PLAY_VIEW;
	}
}
