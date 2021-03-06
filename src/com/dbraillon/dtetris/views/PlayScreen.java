package com.dbraillon.dtetris.views;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import com.dbraillon.dbgraphics.Screen;

import com.dbraillon.dtetris.GameConfigs;
import com.dbraillon.dtetris.advancedsystem.DelayedAutoShift;
import com.dbraillon.dtetris.advancedsystem.LevelSystem;
import com.dbraillon.dtetris.advancedsystem.RandomGenerator;
import com.dbraillon.dtetris.advancedsystem.ScoringSystem;
import com.dbraillon.dtetris.advancedsystem.SuperMoveSystem;
import com.dbraillon.dtetris.advancedsystem.SuperRotationSystem;
import com.dbraillon.dtetris.entities.Playfield;
import com.dbraillon.dtetris.entities.Tetromino;

public class PlayScreen extends Screen {

	private Playfield playfield;
	
	private RandomGenerator randomGenerator;
	private SuperMoveSystem superMoveSystem;
	private SuperRotationSystem superRotationSystem;
	private DelayedAutoShift delayedAutoShift;
	private ScoringSystem scoringSystem;
	private LevelSystem levelSystem;
	
	private Tetromino tetromino;
	
	private int moveSpeed;
	private int fallSpeed;
	
	private int fallTimer;
	private int moveTimer;
	
	
	public PlayScreen() {
		
		playfield = new Playfield(GameConfigs.playfieldHeight, GameConfigs.playfieldWidth);
		
		randomGenerator = new RandomGenerator(playfield.getPosition());
		superMoveSystem = new SuperMoveSystem();
		superRotationSystem = new SuperRotationSystem();
		delayedAutoShift = new DelayedAutoShift();
		scoringSystem = new ScoringSystem();
		levelSystem = new LevelSystem(1);
		
		tetromino = randomGenerator.nextPiece();
		
		fallSpeed = 20;
		moveSpeed = 2;
		
		addItem(scoringSystem);
		addItem(playfield);
		addItem(tetromino);
	}
	
	@Override
	public void update(GameContainer gameContainer, double frameTimeModifier) {
		
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

				// check if it's the end
				if(tetromino.isOverlapsTopBar())
					getNavigator().popScreen(this);
				
				// add the Tetromino in the Playfield
				playfield.setTetromino(tetromino);
				scoringSystem.scoreLines(playfield.checkLines(tetromino));
				
				// create a new Tetromino
				removeItem(tetromino);
				tetromino = randomGenerator.nextPiece();
				addItem(tetromino);
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
	}
}
