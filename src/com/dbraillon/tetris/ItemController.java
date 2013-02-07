package com.dbraillon.tetris;

import java.util.ArrayList;

public class ItemController {

	public int xMap, yMap;
	
	public ItemController(int xMap, int yMap) {
		
		this.xMap = xMap;
		this.yMap = yMap;
	}
	
	public Piece create() {
		
		return new Piece();
	}
	
	/**
	 * Verify if a Piece is not overriding another Piece in Field
	 * 
	 * @param field
	 * @param piece
	 * @return
	 */
	public boolean verify(Field field, Piece piece) {
		
		for(Cube cube : piece.cubes) {
			
			if(field.getCube(cube.x, cube.y) != null) {
				
				if(!field.getCube(cube.x, cube.y).isWall) {
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Make a Piece fall
	 * 
	 * @param field
	 * @param piece
	 * @return
	 */
	public boolean fall(Field field, Piece piece) {
		
		for(Cube cube : piece.cubes) {
			
			if(cube.y + 1 >= Field.HEIGHT_TOTAL) {
				
				return false;
			}
			else {
				
				if(cube.y + 1 > 2) {
					
					if(field.getCube(cube.x, cube.y + 1) != null) {
						
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
	 */
	public boolean move(int move, Field field, Piece piece) {
		
		for(Cube cube : piece.cubes) {
			
			if(cube.x + move >= Field.WIDTH_TOTAL || cube.x + move < 0) {
				
				return false;
			}
			else {
				
				if(field.getCube(cube.x + move, cube.y) != null) {
				
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
	 */
	public boolean hardFall(Field field, Piece piece) {
		
		while(fall(field, piece));
		return false;
	}
	
	/**
	 * Try to remove a line where a Piece is
	 * 
	 * @param piece
	 * @param field
	 * @return
	 */
	public int removeLine(Field field, Piece piece) {
		
		ArrayList<Integer> ys = new ArrayList<Integer>();
		
		for(Cube cube : piece.cubes) {
			
			if(!ys.contains(cube.y)) {
				
				ys.add(cube.y);
			}
		}
		
		int s = 0;
		for(Integer y : ys) {
			
			int n = 0;
			for(int i = 1; i <= Field.WIDTH_FIELD; i++) {
				
				if(field.getCube(i, y) != null) {
					
					n++;
				}
			}
			
			if(n == Field.WIDTH_FIELD) {
				
				for(int i = 1; i <= Field.WIDTH_FIELD; i++) {
					
					field.setCube(null, i, y);
				}
				
				field.fall(y);
				s++;
			}
		}
		
		return s;
	}

	public boolean turn(boolean turn, Field field, Piece piece) {
		
		piece.turn(turn);
		if(!verify(field, piece)) {
			
			piece.turn(!turn);
			return false;
		}
		
		for(Cube cube : piece.cubes) {
			
			if(cube.x >= Field.WIDTH_TOTAL-1 || cube.x < 1 || cube.y > Field.HEIGHT_TOTAL) {

				piece.turn(!turn);
				return false;
			}
		}
		
		return true;
	}
}
