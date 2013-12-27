package com.dbraillon.dtetris.advancedsystem;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Input;

import com.dbraillon.dtetris.entities.Playfield;
import com.dbraillon.dtetris.entities.Tetromino;

public class DelayedAutoShift {

	/**
	 * What we have :
	 * 	- we know when a key is pressed (if not release, this will not tick again)
	 *  - we know when a key is down (tick each loop if key is down)
	 *  
	 * What we want :
	 * 	- when we shift a tetrominos, if we press and hold LEFT or RIGHT the tetrominos
	 * 	has to move 1 time then multiple time after a short periode of time 
	 */
	
	private final int LOOP_BEFORE_AUTO_SHIFT = 10; 
	
	private boolean isLeftKeyDown, isRightKeyDown;
	private int leftTimer, rightTimer;
	
	private SuperMoveSystem superMoveSystem;
	
	
	public DelayedAutoShift() {
		
		isLeftKeyDown = isRightKeyDown = false;
		leftTimer = rightTimer = 0;
		
		superMoveSystem = new SuperMoveSystem();
	}
	
	public void tryToShift(Input input, Playfield playfield, Tetromino tetromino) {
		
		if(input.isKeyDown(Keyboard.KEY_LEFT)) {
			
			if(isLeftKeyDown) {
				
				if(leftTimer >= LOOP_BEFORE_AUTO_SHIFT) {
					
					superMoveSystem.shift(playfield, tetromino, Tetromino.MOVE_LEFT);
				}
				else {
				
					leftTimer++;
				}
			}
			else {
				
				superMoveSystem.shift(playfield, tetromino, Tetromino.MOVE_LEFT);
			}
			
			isLeftKeyDown = true;
		} 
		else {
			
			leftTimer = 0;
			isLeftKeyDown = false;
		}
		
		if(input.isKeyDown(Keyboard.KEY_RIGHT)) {
			
			if(isRightKeyDown) {
				
				if(rightTimer >= LOOP_BEFORE_AUTO_SHIFT) {
					
					superMoveSystem.shift(playfield, tetromino, Tetromino.MOVE_RIGHT);
				}
				else {
					
					rightTimer++;
				}
			}
			else
			{
				superMoveSystem.shift(playfield, tetromino, Tetromino.MOVE_RIGHT);
			}
			
			isRightKeyDown = true;
		}
		else {
			
			rightTimer = 0;
			isRightKeyDown = false;
		}
	}
}
