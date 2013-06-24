package com.dbraillon.dtetris.views;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import com.dbraillon.dtetris.DelayedAutoShift;
import com.dbraillon.dtetris.Playfield;
import com.dbraillon.dtetris.RandomGenerator;
import com.dbraillon.dtetris.Square;
import com.dbraillon.dtetris.SuperMoveSystem;
import com.dbraillon.dtetris.SuperRotationSystem;
import com.dbraillon.dtetris.Tetromino;

public class PlayView implements View {

	private final Color WHITE_COLOR = new Color(255, 255, 255);
	private final int X_END_MAP;
	
	
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
	
	private DelayedAutoShift delayedAutoShift;
	
	
	public PlayView(int width, int height) {
		
		randomGenerator = new RandomGenerator();
		superMoveSystem = new SuperMoveSystem();
		superRotationSystem = new SuperRotationSystem();
		delayedAutoShift = new DelayedAutoShift();
		
		playfield = new Playfield(22, 12);
		tetromino = randomGenerator.nextPiece();
		
		score = 0;
		fallSpeed = 20;
		moveSpeed = 2;
		
		X_END_MAP = 10 + playfield.getWidth() * Square.SIZE_CLIP + 10;
	}
	
	@Override
	public void init(GameContainer gc) {
		
		// nothing here
	}
	
	@Override
	public void render(Graphics graphics) {
		
		graphics.setColor(WHITE_COLOR);
		graphics.drawString("Score : " + score, X_END_MAP, 10);
		
		playfield.draw(graphics);
		tetromino.draw(graphics);
	}
	
	@Override
	public int update(GameContainer gameContainer) {
		
		Input input = gameContainer.getInput();
		
		// shift with the delayAutoShift method
		delayedAutoShift.tryToShift(input, playfield, tetromino);
		
		if(input.isKeyPressed(Keyboard.KEY_W)) {
			
			superRotationSystem.rotate(Tetromino.TURN_LEFT, tetromino, playfield);
		}
		else if(input.isKeyPressed(Keyboard.KEY_X)) {
			
			superRotationSystem.rotate(Tetromino.TURN_RIGHT, tetromino, playfield);
		}
		else if(input.isKeyDown(Keyboard.KEY_DOWN) && moveTimer >= moveSpeed) {
			
			fallTimer = fallSpeed;
		}
		
		if(fallTimer >= fallSpeed) {
			
			
			if(!superMoveSystem.fall(playfield, tetromino)) {
				
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
		
		return com.dbraillon.dtetris.Game.PLAY_VIEW;
	}
}
