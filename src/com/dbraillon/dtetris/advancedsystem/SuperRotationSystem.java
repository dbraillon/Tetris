package com.dbraillon.dtetris.advancedsystem;

import com.dbraillon.dtetris.entities.Playfield;
import com.dbraillon.dtetris.entities.Tetromino;


public class SuperRotationSystem {

	public SuperRotationSystem() {
		
		System.out.println("  SuperRotationSystem: Initializing");
	}
	
	public void rotate(int rotation, Tetromino tetromino, Playfield playfield) {
		
		System.out.println("-SuperRotationSystem: " + tetromino.toString() + " wants to turn " + toString(rotation));
		
		// first, rotate the Tetrominoes
		tetromino.rotate(rotation);
		
		// then watch if it overlaps a Square
		if(tetromino.isOverlaps(playfield)) {
			
			// if it does, try to wall kicks
			tetromino.rotate(-rotation);
			//wallKicks(tetromino);
		}
	}
	
	private void wallKicks(Tetromino tetromino) {
		
		int obstruction;
		if((obstruction = tetromino.boundingSquare.isObstructed) != BoundingSquare.BOTH) {
			
			System.out.println("-SuperRotationSystem: " + tetromino.toString() + " wall kicks " + BoundingSquare.toString(obstruction));
			
			switch(obstruction) {
			
				case BoundingSquare.LEFT:
				
					tetromino.move(Tetromino.MOVE_RIGHT);
					break;
					
				case BoundingSquare.RIGHT:
					
					tetromino.move(Tetromino.MOVE_LEFT);
					break;
			}
		}
	}

	public String toString(int rotation) {
		
		return (rotation == 1) ? "RIGHT" : "LEFT";
	}
}
