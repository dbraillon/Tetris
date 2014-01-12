package com.dbraillon.dtetris.entities;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;

import com.dbraillon.dbgraphics.Depth;
import com.dbraillon.dbgraphics.Point;
import com.dbraillon.dbgraphics.Renderable;
import com.dbraillon.dtetris.GameConfigs;

public class Playfield extends Renderable {

	private Square[][] squares;
	
	public Playfield(int height, int width) {
		super(new Point(175, -16), Depth.Middle);
		
		// Set playfield size
		setHeight(height + 3);
		setWidth(width + 2);
		
		// Table that contains all Tetrominos 
		squares = new Square[(int) getWidth()][(int) getHeight()];
		
		// Loop in the table
		for(int y = 1; y < getHeight(); y++) {
			
			for(int x = 0; x < getWidth(); x++) {
				
				// Put walls
				if(y == 1) {
					
					squares[x][y] = new Square(x, y, GameConfigs.getInstance().graySquare, getPosition(), true);
				}
				else if(y == getHeight() - 1 || x == 0 || x == getWidth() - 1) {
					
					squares[x][y] = new Square(x, y, GameConfigs.getInstance().graySquare, getPosition());
				}
			}
		}
	}
	
	@Override
	protected void render(GameContainer gameContainer) {
		
		for(int y = 0; y < getHeight(); y++) {
			
			for(int x = 0; x < getWidth(); x++) {
				
				if(squares[x][y] != null)
					squares[x][y].render(gameContainer);
			}
		}
	}
	
	public void setTetromino(Tetromino tetromino) {
		
		for(Square square : tetromino.squares) {
			
			setSquare(square);
		}
	}
	
	public int checkLines(Tetromino tetromino) {
		
		int lineClear = 0;
		
		ArrayList<Integer> lines = new ArrayList<Integer>();
		for(Square square : tetromino.squares) {
			
			if(!lines.contains(square.getY())) {
				
				lines.add(square.getY());
			}
		}
		
		
		for(int y = 0; y < lines.size(); y++) {
			
			int yyy = 0;
			for(int yy = 0; yy < y; yy++) {
				
				if(lines.get(y) < lines.get(yy)) {
					
					yyy++;
				}
			}
			
			boolean isComplete = true;
			ArrayList<Square> line = getLines(lines.get(y)+yyy);
			for(Square square : line) {
				
				if(square == null) {
					
					isComplete = false;
				}
			}
			
			if(isComplete) {
				
				lineClear++;
				removeLine(line);
				fallLine(lines.get(y)+yyy);
			}
		}
		
		return lineClear;
	}
	
	public void fallLine(int start) {
		
		for(int y = start; y > 2; y--) {
			
			for(int x = 1; x < getWidth()-1; x++) {
				
				if(y-1 == 2) {
					
					squares[x][y] = null;//new Square(x, y, BLACK_COLOR);
				}
				else {
					
					Square cube = squares[x][y-1];
					if(cube != null)
						cube.fall(1);
					
					squares[x][y] = cube;
					squares[x][y-1] = null;//new Square(x, y-1, BLACK_COLOR);
				}
			}
		}
	}
	
	public Square getSquare(int x, int y) {
		
		return squares[x][y];
	}
	
	public void setSquare(Square square) {
		
		squares[square.getX()][square.getY()] = square;
	}
	
	public void nullSquare(int x, int y) {
		
		squares[x][y] = null;
	}
	
	public ArrayList<Square> getLines(int y) {
		
		ArrayList<Square> line = new ArrayList<Square>();
		for(int x = 1; x < getWidth()-1; x++) {
			
			line.add(squares[x][y]);
		}
		
		return line;
	}
	
	public void removeLine(ArrayList<Square> line) {
		
		for(Square square : line) {
			
			nullSquare(square.getX(), square.getY());
		}
	}
}
