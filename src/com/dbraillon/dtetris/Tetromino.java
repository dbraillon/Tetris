package com.dbraillon.dtetris;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import com.dbraillon.tetris.Field;
import com.dbraillon.dtetris.Square;

public class Tetromino {

	public static final int TURN_RIGHT =  1;
	public static final int TURN_LEFT  = -1;
	public static final int MOVE_RIGHT =  1;
	public static final int MOVE_LEFT  = -1;
	
	public static final int POSITION_TOP 	= 0;
	public static final int POSITION_RIGHT 	= 1;
	public static final int POSITION_BOTTOM = 2;
	public static final int POSITION_LEFT 	= 3;
	
	public Square[] squares;
	public char type;
	public int position;
	
	public BoundingSquare boundingSquare;
	
	public Tetromino(char type) {
		
		this.type = type;
		
		squares = new Square[4];
		
		switch(this.type) {
		case 'O':
			// O
			squares[0] = new Square(5,0, new Color(255, 255, 0), false);
			squares[1] = new Square(5,1, new Color(255, 255, 0), false);
			squares[2] = new Square(6,0, new Color(255, 255, 0), false);
			squares[3] = new Square(6,1, new Color(255, 255, 0), false);
			break;
		case 'T':
			// T
			squares[0] = new Square(4,1, new Color(0, 255, 255), false);
			squares[1] = new Square(5,1, new Color(0, 255, 255), false);
			squares[2] = new Square(6,1, new Color(0, 255, 255), false);
			squares[3] = new Square(5,0, new Color(0, 255, 255), false);
			break;
		case 'I':
			// I
			squares[0] = new Square(4,0, new Color(255, 0, 255), false);
			squares[1] = new Square(5,0, new Color(255, 0, 255), false);
			squares[2] = new Square(6,0, new Color(255, 0, 255), false);
			squares[3] = new Square(7,0, new Color(255, 0, 255), false);
			break;
		case 'L':
			// L
			squares[0] = new Square(4,1, new Color(255, 127, 0), false);
			squares[1] = new Square(5,1, new Color(255, 127, 0), false);
			squares[2] = new Square(6,1, new Color(255, 127, 0), false);
			squares[3] = new Square(6,0, new Color(255, 127, 0), false);
			break;
		case 'J':
			// J
			squares[0] = new Square(4,1, new Color(0, 0, 255), false);
			squares[1] = new Square(5,1, new Color(0, 0, 255), false);
			squares[2] = new Square(6,1, new Color(0, 0, 255), false);
			squares[3] = new Square(4,0, new Color(0, 0, 255), false);
			break;
		case 'Z':
			// Z
			squares[0] = new Square(4,0, new Color(255, 0, 0), false);
			squares[1] = new Square(5,0, new Color(255, 0, 0), false);
			squares[2] = new Square(5,1, new Color(255, 0, 0), false);
			squares[3] = new Square(6,1, new Color(255, 0, 0), false);
			break;
		case 'S':
			// S
			squares[0] = new Square(4,1, new Color(0, 255, 0), false);
			squares[1] = new Square(5,1, new Color(0, 255, 0), false);
			squares[2] = new Square(5,0, new Color(0, 255, 0), false);
			squares[3] = new Square(6,0, new Color(0, 255, 0), false);
			break;
		}
		
		boundingSquare = new BoundingSquare(this);
	}
	
	public void fall() {
		
		for(int i = 0; i < squares.length; i++) {
			
			squares[i].fall(1);
		}
	}
	
	public void move(int move) {
		
		for(int i = 0; i < squares.length; i++) {
			
			squares[i].move(move);
		}
	}
	
	public void draw(Graphics graphics) {
		
		for(Square square : squares) {
			
			square.draw(graphics);
		}
	}

	public void rotate(int rotation) {
		
		System.out.println("-Tetromino: " + toString() + " rotate " + ((rotation == TURN_RIGHT) ? "RIGHT" : "LEFT") + " !");
		
		switch(type) {
		case 'O':
			
			// O
			break;
		
		case 'T':
			
			// T
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move( 1); squares[3].fall( 1);
					
					position = 1;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move(-1); squares[3].fall( 1);
					
					position = 3;
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move(-1); squares[3].fall( 1);
					
					position = 2;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move(-1); squares[3].fall(-1);
					
					position = 0;
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move(-1); squares[3].fall(-1);
					
					position = 3;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 1); squares[3].fall(-1);
					
					position = 1;
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 1); squares[3].fall(-1);
					
					position = 0;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move( 1); squares[3].fall( 1);
					
					position = 2;
				}
			}
			
			break;
			
		case 'I':
			
			// I
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
				
					squares[0].move( 2); squares[0].fall(-1);
					squares[1].move( 1); squares[1].fall( 0);
					squares[2].move( 0); squares[2].fall( 1);
					squares[3].move(-1); squares[3].fall( 2);
					
					position = 1;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 1); squares[0].fall( 2);
					squares[1].move( 0); squares[1].fall( 1);
					squares[2].move(-1); squares[2].fall( 0);
					squares[3].move(-2); squares[3].fall(-1);
					
					position = 3;
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 1); squares[0].fall( 2);
					squares[1].move( 0); squares[1].fall( 1);
					squares[2].move(-1); squares[2].fall( 0);
					squares[3].move(-2); squares[3].fall(-1);
					
					position = 2;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-2); squares[0].fall( 1);
					squares[1].move(-1); squares[1].fall( 0);
					squares[2].move( 0); squares[2].fall(-1);
					squares[3].move( 1); squares[3].fall(-2);
					
					position = 0;
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-2); squares[0].fall( 1);
					squares[1].move(-1); squares[1].fall( 0);
					squares[2].move( 0); squares[2].fall(-1);
					squares[3].move( 1); squares[3].fall(-2);
					
					position = 3;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall(-2);
					squares[1].move( 0); squares[1].fall(-1);
					squares[2].move( 1); squares[2].fall( 0);
					squares[3].move( 2); squares[3].fall( 1);
					
					position = 1;
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall(-2);
					squares[1].move( 0); squares[1].fall(-1);
					squares[2].move( 1); squares[2].fall( 0);
					squares[3].move( 2); squares[3].fall( 1);
					
					position = 0;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 2); squares[0].fall(-1);
					squares[1].move( 1); squares[1].fall( 0);
					squares[2].move( 0); squares[2].fall( 1);
					squares[3].move(-1); squares[3].fall( 2);
					
					position = 2;
				}
			}
			
			break;
			
		case 'L':
			
			// L
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
				
					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move( 0); squares[3].fall( 2);
					
					position = 1;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move(-2); squares[3].fall( 0);
					
					position = 3;
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move(-2); squares[3].fall( 0);
					
					position = 2;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move( 0); squares[3].fall(-2);
					
					position = 0;
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move( 0); squares[3].fall(-2);
					
					position = 3;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 2); squares[3].fall( 0);
					
					position = 1;
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 2); squares[3].fall( 0);
					
					position = 0;
				}
				else if(rotation == TURN_LEFT) {

					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move( 0); squares[3].fall( 2);
					
					position = 2;
				}
			}
			
			break;
			
		case 'J':
			
			// J
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
				
					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move( 2); squares[3].fall( 0);
					
					position = 1;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move( 0); squares[3].fall( 2);
					
					position = 3;
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move( 0); squares[3].fall( 2);
					
					position = 2;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move(-2); squares[3].fall( 0);
					
					position = 0;
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move(-2); squares[3].fall( 0);
					
					position = 3;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 0); squares[3].fall(-2);
					
					position = 1;
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 0); squares[3].fall(-2);
					
					position = 0;
				}
				else if(rotation == TURN_LEFT) {

					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move( 2); squares[3].fall( 0);
					
					position = 2;
				}
			}
			
			break;
			
		case 'Z':
			
			// Z
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
				
					squares[0].move( 2); squares[0].fall( 0);
					squares[1].move( 1); squares[1].fall( 1);
					squares[3].move(-1); squares[3].fall( 1);
					
					position = 1;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 0); squares[0].fall( 2);
					squares[1].move(-1); squares[1].fall( 1);
					squares[3].move(-1); squares[3].fall(-1);
					
					position = 3;
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 0); squares[0].fall( 2);
					squares[1].move(-1); squares[1].fall( 1);
					squares[3].move(-1); squares[3].fall(-1);
					
					position = 2;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-2); squares[0].fall( 0);
					squares[1].move(-1); squares[1].fall(-1);
					squares[3].move( 1); squares[3].fall(-1);
					
					position = 0;
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-2); squares[0].fall( 0);
					squares[1].move(-1); squares[1].fall(-1);
					squares[3].move( 1); squares[3].fall(-1);
					
					position = 3;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 0); squares[0].fall(-2);
					squares[1].move( 1); squares[1].fall(-1);
					squares[3].move( 1); squares[3].fall( 1);
					
					position = 1;
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 0); squares[0].fall(-2);
					squares[1].move( 1); squares[1].fall(-1);
					squares[3].move( 1); squares[3].fall( 1);
					
					position = 0;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 2); squares[0].fall( 0);
					squares[1].move( 1); squares[1].fall( 1);
					squares[3].move(-1); squares[3].fall( 1);
					
					position = 2;
				}
			}
			
			break;
			
		case 'S':
			
			// S
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
				
					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 0); squares[3].fall( 2);
					
					position = 1;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move(-2); squares[3].fall( 0);
					
					position = 3;
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move( 1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall( 1);
					squares[3].move(-2); squares[3].fall( 0);
					
					position = 2;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move( 0); squares[3].fall(-2);
					
					position = 0;
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall( 1);
					squares[2].move(-1); squares[2].fall(-1);
					squares[3].move( 0); squares[3].fall(-2);
					
					position = 3;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move( 2); squares[3].fall( 0);
					
					position = 1;
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
					squares[0].move(-1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall(-1);
					squares[3].move( 2); squares[3].fall( 0);
					
					position = 0;
				}
				else if(rotation == TURN_LEFT) {
					
					squares[0].move( 1); squares[0].fall(-1);
					squares[2].move( 1); squares[2].fall( 1);
					squares[3].move( 0); squares[3].fall( 2);
					
					position = 2;
				}
			}

			break;
		}
	}

	public boolean isOverlaps(Playfield playfield) {
		
		for(Square square : squares) {
			
			if(!playfield.getSquare(square.getX(), square.getY()).isEmpty()) {
				
				return true;
			}
		}
		
		return false;
	}

	public void up() {
		
		for(int i = 0; i < squares.length; i++) {
			
			squares[i].fall(-1);
		}
	}
}
