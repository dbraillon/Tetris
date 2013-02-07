package com.dbraillon.tetris;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Piece {

	public static final int TURN_RIGHT =  1;
	public static final int TURN_LEFT  = -1;
	
	public Cube[] cubes;
	public int piece;
	public int position;
	
	public Piece() {
		
		cubes = new Cube[4];
		
		Random r = new Random();
		int p = r.nextInt(7);
		
		piece = p;
		position = 0;
		switch(piece) {
		case 0:
			// O
			cubes[0] = new Cube(5,0, new Color(255, 255, 0), false, false);
			cubes[1] = new Cube(5,1, new Color(255, 255, 0), false, false);
			cubes[2] = new Cube(6,0, new Color(255, 255, 0), false, false);
			cubes[3] = new Cube(6,1, new Color(255, 255, 0), false, false);
			break;
		case 1:
			// T
			cubes[0] = new Cube(4,1, new Color(0, 255, 255), false, false);
			cubes[1] = new Cube(5,1, new Color(0, 255, 255), false, false);
			cubes[2] = new Cube(6,1, new Color(0, 255, 255), false, false);
			cubes[3] = new Cube(5,0, new Color(0, 255, 255), false, false);
			break;
		case 2:
			// I
			cubes[0] = new Cube(4,0, new Color(255, 0, 255), false, false);
			cubes[1] = new Cube(5,0, new Color(255, 0, 255), false, false);
			cubes[2] = new Cube(6,0, new Color(255, 0, 255), false, false);
			cubes[3] = new Cube(7,0, new Color(255, 0, 255), false, false);
			break;
		case 3:
			// L
			cubes[0] = new Cube(4,1, new Color(255, 127, 0), false, false);
			cubes[1] = new Cube(5,1, new Color(255, 127, 0), false, false);
			cubes[2] = new Cube(6,1, new Color(255, 127, 0), false, false);
			cubes[3] = new Cube(4,0, new Color(255, 127, 0), false, false);
			break;
		case 4:
			// J
			cubes[0] = new Cube(4,1, new Color(0, 0, 255), false, false);
			cubes[1] = new Cube(5,1, new Color(0, 0, 255), false, false);
			cubes[2] = new Cube(6,1, new Color(0, 0, 255), false, false);
			cubes[3] = new Cube(6,0, new Color(0, 0, 255), false, false);
			break;
		case 5:
			// Z
			cubes[0] = new Cube(4,0, new Color(255, 0, 0), false, false);
			cubes[1] = new Cube(5,0, new Color(255, 0, 0), false, false);
			cubes[2] = new Cube(5,1, new Color(255, 0, 0), false, false);
			cubes[3] = new Cube(6,1, new Color(255, 0, 0), false, false);
			break;
		case 6:
			// S
			cubes[0] = new Cube(4,1, new Color(0, 255, 0), false, false);
			cubes[1] = new Cube(5,1, new Color(0, 255, 0), false, false);
			cubes[2] = new Cube(5,0, new Color(0, 255, 0), false, false);
			cubes[3] = new Cube(6,0, new Color(0, 255, 0), false, false);
			break;
		}
	}
	
	public void fall() {
		
		for(int i = 0; i < cubes.length; i++) {
			
			cubes[i].fall();
		}
	}
	
	public void move(int move) {
		
		for(int i = 0; i < cubes.length; i++) {
			
			cubes[i].move(move);
		}
	}
	
	public void turn(int rotation) {
		
		/*
		 *  1: sens aiguille d'une montre
		 * -1: sens inverse
		 */
		
		System.out.println("Piece want to turn RIGHT !");
		System.out.println("Actual position : " + position);
		boolean turn = true;
		switch(piece) {
		case 0:
			
			// O
			break;
		
		case 1:
			
			// T
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
					
					cubes[0].addX( 1); cubes[0].addY(-1);
					cubes[2].addX(-1); cubes[2].addY( 1);
					cubes[3].addX( 1); cubes[3].addY( 1);
					
					position = 1;
				}
				else if(rotation == TURN_LEFT) {
					
					cubes[0].addX( 1); cubes[0].addY( 1);
					cubes[2].addX(-1); cubes[2].addY(-1);
					cubes[3].addX(-1); cubes[3].addY( 1);
					
					position = 3;
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
					cubes[0].addX( 1); cubes[0].addY( 1);
					cubes[2].addX(-1); cubes[2].addY(-1);
					cubes[3].addX(-1); cubes[3].addY( 1);
					
					position = 2;
				}
				else if(rotation == TURN_LEFT) {
					
					cubes[0].addX(-1); cubes[0].addY( 1);
					cubes[2].addX( 1); cubes[2].addY(-1);
					cubes[3].addX(-1); cubes[3].addY(-1);
					
					position = 0;
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
					cubes[0].addX(-1); cubes[0].addY( 1);
					cubes[2].addX( 1); cubes[2].addY(-1);
					cubes[3].addX(-1); cubes[3].addY(-1);
					
					position = 3;
				}
				else if(rotation == TURN_LEFT) {
					
					cubes[0].addX(-1); cubes[0].addY(-1);
					cubes[2].addX( 1); cubes[2].addY( 1);
					cubes[3].addX( 1); cubes[3].addY(-1);
					
					position = 1;
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
					cubes[0].addX(-1); cubes[0].addY(-1);
					cubes[2].addX( 1); cubes[2].addY( 1);
					cubes[3].addX( 1); cubes[3].addY(-1);
					
					position = 0;
				}
				else if(rotation == TURN_LEFT) {
					
					cubes[0].addX( 1); cubes[0].addY(-1);
					cubes[2].addX(-1); cubes[2].addY( 1);
					cubes[3].addX( 1); cubes[3].addY( 1);
					
					position = 2;
				}
			}
			
			break;
			
		case 2:
			
			// I
			if(position == 0) {
				
				if(rotation == TURN_RIGHT) {
				
				}
				else if(rotation == TURN_LEFT) {
					
				}
			}
			else if(position == 1) {
				
				if(rotation == TURN_RIGHT) {
					
				}
				else if(rotation == TURN_LEFT) {
					
				}
			}
			else if(position == 2) {
				
				if(rotation == TURN_RIGHT) {
					
				}
				else if(rotation == TURN_LEFT) {
					
				}
			}
			else if(position == 3) {
				
				if(rotation == TURN_RIGHT) {
					
				}
				else if(rotation == TURN_LEFT) {
					
				}
			}
			
			break;
			
		case 3:
			// L
			
			if(position == 0) {
				
				cubes[0].addX(1); cubes[0].addY(1);
				cubes[2].addX(-1); cubes[2].addY(-1);
				
				if(turn) cubes[3].addX(-2);
				else  	 cubes[3].addY(-2);
			}
			else if(position == 1) {
				
				cubes[0].addX(-1); cubes[0].addY(-1);
				cubes[2].addX(1); cubes[2].addY(1);
				
				if(turn) cubes[3].addY(-2);
				else	 cubes[3].addX(2);
			}
			else if(position == 2) {
				
				cubes[0].addX(1); cubes[0].addY(1);
				cubes[2].addX(-1); cubes[2].addY(-1);
				
				if(turn) cubes[3].addX(2);
				else	 cubes[3].addY(2);
			}
			else if(position == 3) {
				
				cubes[0].addX(-1); cubes[0].addY(-1);
				cubes[2].addX(1); cubes[2].addY(1);
				
				if(turn) cubes[3].addY(2);
				else	 cubes[3].addX(-2);
			}
			
			break;
			
		case 4:
			// J
			
			if(position == 0) {
				
				cubes[0].addX(1); cubes[0].addY(1);
				cubes[2].addX(-1); cubes[2].addY(-1);
				
				if(turn) cubes[3].addY(-2);
				else	 cubes[3].addX(2);
			}
			else if(position == 1) {
				
				cubes[0].addX(-1); cubes[0].addY(-1);
				cubes[2].addX(1); cubes[2].addY(1);
				
				if(turn) cubes[3].addX(2);
				else	 cubes[3].addY(2);
			}
			else if(position == 2) {
				
				cubes[0].addX(1); cubes[0].addY(1);
				cubes[2].addX(-1); cubes[2].addY(-1);
				
				if(turn) cubes[3].addY(2);
				else	 cubes[3].addX(-2);
			}
			else if(position == 3) {
				
				cubes[0].addX(-1); cubes[0].addY(-1);
				cubes[2].addX(1); cubes[2].addY(1);
				
				if(turn) cubes[3].addX(-2);
				else	 cubes[3].addY(-2);
			}
			
			break;
			
		case 5:
			// Z
			switch(position) {
			case 0:
			case 2:
				//  0    
				// 21 -> 01
				// 3      23
				cubes[0].addX(-1); cubes[0].addY(1);
				cubes[2].addX(1); cubes[2].addY(1);
				cubes[3].addX(2);
				break;
			case 1:
			case 3:
				//         0
				// 01  -> 21
				//  23	  3
				cubes[0].addX(1); cubes[0].addY(-1);
				cubes[2].addX(-1); cubes[2].addY(-1);
				cubes[3].addX(-2);
				break;
			}
			break;
		case 6:
			// S
			switch(position) {
			case 0:
			case 2:
				// 0      
				// 12 ->  10
				//  3    32
				cubes[0].addX(1); cubes[0].addY(1);
				cubes[2].addX(-1); cubes[2].addY(1);
				cubes[3].addX(-2);
				break;
			case 1:
			case 3:
				//         0
				//  10  -> 12
				// 32	  	3
				cubes[0].addX(-1); cubes[0].addY(-1);
				cubes[2].addX(1); cubes[2].addY(-1);
				cubes[3].addX(2);
				break;
			}
			break;
		}
	}

	public void drawPiece(Graphics graphics) {
		
		for(Cube cube : cubes) {
			
			cube.drawCube(graphics);
		}
	}
}
