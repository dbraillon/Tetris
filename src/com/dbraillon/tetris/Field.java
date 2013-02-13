package com.dbraillon.tetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Field {

	public static final int HEIGHT_FIELD = 20;
	public static final int WIDTH_FIELD = 10;
	
	public static final int HEIGHT_TOTAL = HEIGHT_FIELD + 4;
	public static final int WIDTH_TOTAL = WIDTH_FIELD + 2;
	
	private final Color DARK_GREY_COLOR = new Color(120, 120, 120);
	
	private Cube[][] _cubes;
	
	public Field() {
		
		_cubes = new Cube[WIDTH_TOTAL][HEIGHT_TOTAL];
		
		for(int x = 0; x < WIDTH_TOTAL; x++) {
			
			for(int y = 0; y < HEIGHT_TOTAL; y++) {
				
				if(x == 0 || x == WIDTH_TOTAL-1 || y == 2 || y == HEIGHT_TOTAL-1) {
					
					_cubes[x][y] = new Cube(x, y, DARK_GREY_COLOR, true, false);
				}
				else {
					
					_cubes[x][y] = new Cube(x, y, null, false, true);
				}
			}
		}
	}
	
	public Cube getCube(int x, int y) {
		
		if(x >= WIDTH_TOTAL) return null;
		
		return _cubes[x][y];
	}
	
	public void setCube(Cube cube, int x, int y) {
		
		_cubes[x][y] = cube;
	}
	
	public void drawField(Graphics graphics) {
		
		for(int x = 0; x < WIDTH_TOTAL; x++) {
			
			for(int y = 0; y < HEIGHT_TOTAL; y++) {
				
				_cubes[x][y].drawCube(graphics);
			}
		}
	}

	public void removePiece(Piece piece) {
		
		for(Cube cube : piece.cubes) {
			
			_cubes[cube.getX()][cube.getY()].isEmpty(true);
		}
	}

	public void addPiece(Piece piece) {
		
		for(Cube cube : piece.cubes) {
			
			_cubes[cube.getX()][cube.getY()] = cube;
		}
	}

	public void fallLine(int start) {
		
		for(int y = start; y > 2; y--) {
			
			for(int x = 1; x < WIDTH_TOTAL-1; x++) {
				
				if(y-1 == 2) {
					
					_cubes[x][y] = new Cube(x, y, null, false, true);
				}
				else {
					
					Cube cube = _cubes[x][y-1];
					cube.fall();
					
					_cubes[x][y] = cube;
					_cubes[x][y-1] = new Cube(x, y-1, null, false, true);
				}
			}
		}
	}
}
