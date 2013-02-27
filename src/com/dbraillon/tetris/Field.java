package com.dbraillon.tetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.dbraillon.dtetris.Tetromino;

public class Field {

	public static final int HEIGHT_FIELD = 20;
	public static final int WIDTH_FIELD = 10;
	
	public static final int HEIGHT_TOTAL = HEIGHT_FIELD + 4;
	public static final int WIDTH_TOTAL = WIDTH_FIELD + 2;
	
	private final Color DARK_GREY_COLOR = new Color(120, 120, 120);
	
	private Square[][] _squares;
	
	public Field() {
		
		_squares = new Square[WIDTH_TOTAL][HEIGHT_TOTAL];
		
		for(int x = 0; x < WIDTH_TOTAL; x++) {
			
			for(int y = 0; y < HEIGHT_TOTAL; y++) {
				
				if(x == 0 || x == WIDTH_TOTAL-1 || y == 2 || y == HEIGHT_TOTAL-1) {
					
					_squares[x][y] = new Square(x, y, DARK_GREY_COLOR, true, false);
				}
				else {
					
					_squares[x][y] = new Square(x, y, null, false, true);
				}
			}
		}
	}
	
	public Square getSquare(int x, int y) {
		
		if(x >= WIDTH_TOTAL) return null;
		
		return _squares[x][y];
	}
	
	public void setSquare(Square square, int x, int y) {
		
		_squares[x][y] = square;
	}
	
	public void drawField(Graphics graphics) {
		
		for(int x = 0; x < WIDTH_TOTAL; x++) {
			
			for(int y = 0; y < HEIGHT_TOTAL; y++) {
				
				_squares[x][y].draw(graphics);
			}
		}
	}

	public void removePiece(Tetromino piece) {
		
		//for(Square cube : piece.squares) {
			
			//_squares[cube.getX()][cube.getY()].isEmpty(true);
		//}
	}

	public void addPiece(Tetromino piece) {
		
		//for(Square cube : piece.squares) {
			
			//_squares[cube.getX()][cube.getY()] = cube;
		//}
	}

	public void fallLine(int start) {
		
		for(int y = start; y > 2; y--) {
			
			for(int x = 1; x < WIDTH_TOTAL-1; x++) {
				
				if(y-1 == 2) {
					
					_squares[x][y] = new Square(x, y, null, false, true);
				}
				else {
					
					Square cube = _squares[x][y-1];
					cube.fall();
					
					_squares[x][y] = cube;
					_squares[x][y-1] = new Square(x, y-1, null, false, true);
				}
			}
		}
	}
}
