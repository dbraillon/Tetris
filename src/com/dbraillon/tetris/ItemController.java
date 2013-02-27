package com.dbraillon.tetris;

import java.util.ArrayList;

public class ItemController {

	/*public int xMap, yMap;
	private RandomGenerator randomGenerator;
	
	public ItemController(int xMap, int yMap) {
		
		this.xMap = xMap;
		this.yMap = yMap;
		
		randomGenerator = new RandomGenerator();
	}
	
	public Tetromino create() {
		
		return randomGenerator.nextPiece();
	}
	
	/**
	 * Verify if a Piece is not overriding another Piece in Field
	 * 
	 * @param field
	 * @param piece
	 * @return
	 *
	public boolean verify(Field field, Tetromino piece) {
		
		for(Square cube : piece.squares) {
			
			if(!field.getCube(cube.getX(), cube.getY()).isEmpty()) return false;
		}
		
		return true;
	}
	
	/**
	 * Make a Piece fall
	 * 
	 * @param field
	 * @param piece
	 * @return
	 *
	public boolean fall(Field field, Tetromino piece) {
		
		for(Square cube : piece.squares) {
			
			if(cube.getY() + 1 >= Field.HEIGHT_TOTAL) {
				
				return false;
			}
			else {
				
				if(cube.getY() + 1 > 2) {
					
					if(field.getCube(cube.getX(), cube.getY() + 1).isEmpty() == false) {
						
						return false;
					}
				}
			}
		}
		
		piece.fall();
		
		return true;
	}
	
	/**
	 * Move a Piece
	 * 
	 * @param move
	 * @param field
	 * @param piece
	 * @return
	 *
	public boolean move(int move, Field field, Tetromino piece) {
		
		for(Square cube : piece.squares) {
			
			if(cube.getX() + move >= Field.WIDTH_TOTAL || cube.getX() + move < 0) {
				
				return false;
			}
			else {
				
				if(field.getCube(cube.getX() + move, cube.getY()).isEmpty() == false) {
				
					return false;
				}
			}
		}
		
		piece.move(move);
		
		return true;
	}
	
	/**
	 * Make a Piece hard fall
	 * 
	 * @param field
	 * @param piece
	 * @return
	 *
	public boolean hardFall(Field field, Tetromino piece) {
		
		while(fall(field, piece));
		return false;
	}
	
	/**
	 * Try to remove a line where a Piece is
	 * 
	 * @param piece
	 * @param field
	 * @return
	 *
	public int removeLine(Field field, Tetromino piece) {
		
		ArrayList<Integer> ys = new ArrayList<Integer>();
		
		for(Square cube : piece.squares) {
			
			if(!ys.contains(cube.getY())) {
				
				ys.add(cube.getY());
			}
		}
		
		int s = 0;
		for(Integer y : ys) {
			
			int n = 0;
			for(int i = 1; i <= Field.WIDTH_FIELD; i++) {
				
				if(field.getCube(i, y).isEmpty() == false) {
					
					n++;
				}
			}
			
			if(n == Field.WIDTH_FIELD) {
				
				for(int i = 1; i <= Field.WIDTH_FIELD; i++) {
					
					field.setCube(new Square(i, y, null, false, true), i, y);
				}
				
				field.fallLine(y);
				s++;
			}
		}
		
		return s;
	}

	public boolean turn(int turn, Field field, Tetromino piece) {
		
		piece.turn(turn);
		if(!verify(field, piece)) {
			
			piece.turn(Tetromino.TURN_LEFT);
			return false;
		}
		
		for(Square cube : piece.squares) {
			
			if(cube.getX() >= Field.WIDTH_TOTAL-1 || cube.getX() < 1 || cube.getY() > Field.HEIGHT_TOTAL) {

				piece.turn(Tetromino.TURN_LEFT);
				return false;
			}
		}
		
		return true;
	}*/
}
