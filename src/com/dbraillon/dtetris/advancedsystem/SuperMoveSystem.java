package com.dbraillon.dtetris.advancedsystem;

import com.dbraillon.dtetris.entities.Playfield;
import com.dbraillon.dtetris.entities.Tetromino;

public class SuperMoveSystem {

	public SuperMoveSystem() {
		
		System.out.println("  SuperMoveSystem()");
	}
	
	public boolean fall(Playfield playfield, Tetromino tetromino) {
		
		System.out.println("+ SuperMoveSystem: " + tetromino.toString() + " falls");
		
		// first, make fall the Tetromino
		tetromino.fall();
		
		// then watch if it overlaps a Square
		if(tetromino.isOverlaps(playfield)) {
			
			// if it does, reverse fall
			tetromino.up();
			return false;
		}
		
		return true;
	}
	
	public void shift(Playfield playfield, Tetromino tetromino, int move) {
		
		System.out.println("-SuperMoveSystem: " + tetromino.toString() + " moves " + toString(move));
		
		// first, move the Tetromino
		tetromino.move(move);
		
		// then watch if it overlaps a Square
		if(tetromino.isOverlaps(playfield)) {
			
			// if it does, reverse move
			tetromino.move(-move);
		}
	}
	
	public String toString(int move) {
		
		return (move == 1) ? "RIGHT" : "LEFT";
	}
}
