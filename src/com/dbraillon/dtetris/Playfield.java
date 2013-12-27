package com.dbraillon.dtetris;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import com.dbraillon.dbgraphics.Depth;
import com.dbraillon.dbgraphics.Point;
import com.dbraillon.dbgraphics.Renderable;

public class Playfield extends Renderable {

	private final Color DARK_GREY_COLOR = new Color(120, 120, 120);
	private final Color BLACK_COLOR = new Color(10, 10, 10);
	
	private Square[][] squares;
	
	
	public Playfield(int height, int width) {
		super(new Point(175, 50), Depth.Middle, new Color(10, 10, 10), height, width);
		
		this.squares = new Square[width][height];
		for(int y = 0; y < getHeight(); y++) {
			
			for(int x = 0; x < getWidth(); x++) {
				
				if(y == getHeight()-1 || x == 0 || x == getWidth()-1) {
					
					squares[x][y] = new Square(x, y, DARK_GREY_COLOR, false);
				}
				else if(y == 2) {
					
					squares[x][y] = new Square(x, y, DARK_GREY_COLOR, true);
				}
				else {
					
					squares[x][y] = new Square(x, y, BLACK_COLOR, true);
				}
			}
		}
	}
	
	@Override
	protected void render(GameContainer gameContainer) {
		
		for(int y = 0; y < getHeight(); y++) {
			
			for(int x = 0; x < getWidth(); x++) {
				
				squares[x][y].draw(gameContainer.getGraphics(), getPosition());
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
				
				if(square.isEmpty()) {
					
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
					
					squares[x][y] = new Square(x, y, BLACK_COLOR, true);
				}
				else {
					
					Square cube = squares[x][y-1];
					cube.fall(1);
					
					squares[x][y] = cube;
					squares[x][y-1] = new Square(x, y-1, BLACK_COLOR, true);
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
	
	public ArrayList<Square> getLines(int y) {
		
		ArrayList<Square> line = new ArrayList<Square>();
		for(int x = 1; x < getWidth()-1; x++) {
			
			line.add(squares[x][y]);
		}
		
		return line;
	}
	
	public void removeLine(ArrayList<Square> line) {
		
		for(Square square : line) {
			
			setSquare(new Square(square.getX(), square.getY(), BLACK_COLOR, true));
		}
	}
}
