package com.dbraillon.dtetris.advancedsystem;

import com.dbraillon.dtetris.entities.Playfield;
import com.dbraillon.dtetris.entities.Tetromino;


public class BoundingSquare {

	public static final int RIGHT =  1;
	public static final int BOTH  =  0;
	public static final int LEFT  = -1;
	
	public static String toString(int obstruction) {
		
		if(obstruction ==  1) return "RIGHT";
		if(obstruction ==  0) return "BOTH";
		if(obstruction == -1) return "LEFT";
		
		return "NOWHERE";
	}
	
	private Tetromino tetromino;
	private int[][] boundingSquare;
	public int isObstructed;
	
	public BoundingSquare(Tetromino tetromino) {
		
		this.tetromino = tetromino;
		
		if(tetromino.type == 'I') {
			
			boundingSquare = new int[4][4];
		}
		else {
			
			boundingSquare = new int[3][3];
		}
	}
	
	public void changePosition(int position, Tetromino tetromino, Playfield playfield) {
		
		this.tetromino = tetromino;
		
		switch(tetromino.type) {
			
			case 'O':
				
				// O can't change position
				
				break;
				
			case 'L':
				
				switch(position) {
				
					case Tetromino.POSITION_TOP:
						
						
						break;
						
					case Tetromino.POSITION_RIGHT:
						
						
						break;
					
					case Tetromino.POSITION_BOTTOM:
						
						
						break;
						
					case Tetromino.POSITION_LEFT:
						
						
						break;
				}
				
				break;
			
			case 'J':
				
				switch(position) {
				
					case Tetromino.POSITION_TOP:
						
						
						break;
						
					case Tetromino.POSITION_RIGHT:
						
						
						break;
					
					case Tetromino.POSITION_BOTTOM:
						
						
						break;
						
					case Tetromino.POSITION_LEFT:
						
						
						break;
				}
				
				break;
				
			case 'I':
				
				switch(position) {
					
					case Tetromino.POSITION_TOP:
						
						
						break;
						
					case Tetromino.POSITION_RIGHT:
						
						
						break;
					
					case Tetromino.POSITION_BOTTOM:
						
						
						break;
						
					case Tetromino.POSITION_LEFT:
						
						
						break;
				}
				
				break;
	
			case 'T':
				
				switch(position) {
				
					case Tetromino.POSITION_TOP:
						
						
						break;
						
					case Tetromino.POSITION_RIGHT:
						
						
						break;
					
					case Tetromino.POSITION_BOTTOM:
						
						
						break;
						
					case Tetromino.POSITION_LEFT:
						
						
						break;
				}
				
				break;
	
			case 'S':
				
				switch(position) {
				
					case Tetromino.POSITION_TOP:
						
						
						break;
						
					case Tetromino.POSITION_RIGHT:
						
						
						break;
					
					case Tetromino.POSITION_BOTTOM:
						
						
						break;
						
					case Tetromino.POSITION_LEFT:
						
						
						break;
				}
				
				break;
				
			case 'Z':
				
				switch(position) {
				
					case Tetromino.POSITION_TOP:
						
						
						break;
						
					case Tetromino.POSITION_RIGHT:
						
						
						break;
					
					case Tetromino.POSITION_BOTTOM:
						
						
						break;
						
					case Tetromino.POSITION_LEFT:
						
						
						break;
				}
				
				break;
		}
	}
}
