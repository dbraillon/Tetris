package com.dbraillon.dtetris.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;

import com.dbraillon.dbgraphics.Depth;
import com.dbraillon.dbgraphics.Point;
import com.dbraillon.dbgraphics.Renderable;

import com.dbraillon.dtetris.GameConfigs;
import com.dbraillon.dtetris.advancedsystem.BoundingSquare;

public class Tetromino extends Renderable {

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
	
	public Tetromino(char type, Point position) {
		super(position, Depth.Front, new Color(255, 255, 255), 0, 0);
		
		this.type = type;
		
		squares = new Square[4];
		
		switch(this.type) {
		case 'O':
			// O
			squares[0] = new Square(5,0, GameConfigs.getInstance().yellowSquare, position);// new Color(255, 255, 0));
			squares[1] = new Square(5,1, GameConfigs.getInstance().yellowSquare, position);
			squares[2] = new Square(6,0, GameConfigs.getInstance().yellowSquare, position);
			squares[3] = new Square(6,1, GameConfigs.getInstance().yellowSquare, position);
			break;
		case 'T':
			// T
			squares[0] = new Square(4,1, GameConfigs.getInstance().purpleSquare, position);// new Color(0, 255, 255));
			squares[1] = new Square(5,1, GameConfigs.getInstance().purpleSquare, position);
			squares[2] = new Square(6,1, GameConfigs.getInstance().purpleSquare, position);
			squares[3] = new Square(5,0, GameConfigs.getInstance().purpleSquare, position);
			break;
		case 'I':
			// I
			squares[0] = new Square(4,0, GameConfigs.getInstance().cyanSquare, position);// new Color(255, 0, 255));
			squares[1] = new Square(5,0, GameConfigs.getInstance().cyanSquare, position);
			squares[2] = new Square(6,0, GameConfigs.getInstance().cyanSquare, position);
			squares[3] = new Square(7,0, GameConfigs.getInstance().cyanSquare, position);
			break;
		case 'L':
			// L
			squares[0] = new Square(4,1, GameConfigs.getInstance().orangeSquare, position);// new Color(255, 127, 0));
			squares[1] = new Square(5,1, GameConfigs.getInstance().orangeSquare, position);
			squares[2] = new Square(6,1, GameConfigs.getInstance().orangeSquare, position);
			squares[3] = new Square(6,0, GameConfigs.getInstance().orangeSquare, position);
			break;
		case 'J':
			// J
			squares[0] = new Square(4,1, GameConfigs.getInstance().blueSquare, position);// new Color(0, 0, 255));
			squares[1] = new Square(5,1, GameConfigs.getInstance().blueSquare, position);
			squares[2] = new Square(6,1, GameConfigs.getInstance().blueSquare, position);
			squares[3] = new Square(4,0, GameConfigs.getInstance().blueSquare, position);
			break;
		case 'Z':
			// Z
			squares[0] = new Square(4,0, GameConfigs.getInstance().redSquare, position);// new Color(255, 0, 0));
			squares[1] = new Square(5,0, GameConfigs.getInstance().redSquare, position);
			squares[2] = new Square(5,1, GameConfigs.getInstance().redSquare, position);
			squares[3] = new Square(6,1, GameConfigs.getInstance().redSquare, position);
			break;
		case 'S':
			// S
			squares[0] = new Square(4,1, GameConfigs.getInstance().greenSquare, position);// new Color(0, 255, 0));
			squares[1] = new Square(5,1, GameConfigs.getInstance().greenSquare, position);
			squares[2] = new Square(5,0, GameConfigs.getInstance().greenSquare, position);
			squares[3] = new Square(6,0, GameConfigs.getInstance().greenSquare, position);
			break;
		}
		
		boundingSquare = new BoundingSquare(this);
	}
	
	@Override
	protected void render(GameContainer gameContainer) {
		
		for(Square square : squares) {
			
			square.render(gameContainer);
		}
	}
	
	public void fall() {
		
		for(int i = 0; i < squares.length; i++) {
			
			squares[i].fall(1);
		}
	}

	public void up() {
		
		for(int i = 0; i < squares.length; i++) {
			
			squares[i].fall(-1);
		}
	}
	
	public void move(int move) {
		
		for(int i = 0; i < squares.length; i++) {
			
			squares[i].move(move);
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
			
			Square playfieldSquare = playfield.getSquare(square.getX(), square.getY());
			
			if(playfieldSquare != null) {
				
				if(!playfieldSquare.isEmpty()) {
				
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isOverlapsTopBar() {
		
		for(Square square : squares) {
			
			if(square.getY() == 1 && square.getX() >= 4 && square.getX() <= 7) {
				
				return true;
			}
		}
		
		return false;
	}
	
	public boolean isVisible() {
		
		for(Square square : squares) {
			
			if(square.getY() <= 1) {
				
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public String toString()
	{
		return type + "";
	}
}
