package com.dbraillon.tetris;

import java.util.Random;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Piece {

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
			cubes[0] = new Cube(5,2, new Color(255, 255, 0), false);
			cubes[1] = new Cube(5,3, new Color(255, 255, 0), false);
			cubes[2] = new Cube(6,2, new Color(255, 255, 0), false);
			cubes[3] = new Cube(6,3, new Color(255, 255, 0), false);
			break;
		case 1:
			// T
			cubes[0] = new Cube(5,1, new Color(0, 255, 255), false);
			cubes[1] = new Cube(5,2, new Color(0, 255, 255), false);
			cubes[2] = new Cube(5,3, new Color(0, 255, 255), false);
			cubes[3] = new Cube(6,2, new Color(0, 255, 255), false);
			break;
		case 2:
			// I
			cubes[0] = new Cube(6,0, new Color(255, 0, 255), false);
			cubes[1] = new Cube(6,1, new Color(255, 0, 255), false);
			cubes[2] = new Cube(6,2, new Color(255, 0, 255), false);
			cubes[3] = new Cube(6,3, new Color(255, 0, 255), false);
			break;
		case 3:
			// L
			cubes[0] = new Cube(5,1, new Color(255, 127, 0), false);
			cubes[1] = new Cube(5,2, new Color(255, 127, 0), false);
			cubes[2] = new Cube(5,3, new Color(255, 127, 0), false);
			cubes[3] = new Cube(6,3, new Color(255, 127, 0), false);
			break;
		case 4:
			// J
			cubes[0] = new Cube(6,1, new Color(0, 0, 255), false);
			cubes[1] = new Cube(6,2, new Color(0, 0, 255), false);
			cubes[2] = new Cube(6,3, new Color(0, 0, 255), false);
			cubes[3] = new Cube(5,3, new Color(0, 0, 255), false);
			break;
		case 5:
			// Z
			cubes[0] = new Cube(6,1, new Color(255, 0, 0), false);
			cubes[1] = new Cube(6,2, new Color(255, 0, 0), false);
			cubes[2] = new Cube(5,2, new Color(255, 0, 0), false);
			cubes[3] = new Cube(5,3, new Color(255, 0, 0), false);
			break;
		case 6:
			// S
			cubes[0] = new Cube(5,1, new Color(0, 255, 0), false);
			cubes[1] = new Cube(5,2, new Color(0, 255, 0), false);
			cubes[2] = new Cube(6,2, new Color(0, 255, 0), false);
			cubes[3] = new Cube(6,3, new Color(0, 255, 0), false);
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
	
	public void turn(boolean turn) {
		
		int t = (turn) ? 1 : -1;
		int tt = 0;
		
		switch(piece) {
		case 0:
			// O
			break;
		case 1:
			// T
			
			if(position == 0) tt = 1;
			else if(position == 1) tt = 1;
			else if(position == 2) tt = -1;
			else if(position == 3) tt = -1;
			
			if(position == 0 || position == 2) {
				
				cubes[0].x++; cubes[0].y++;
				cubes[2].x--; cubes[2].y--;
				cubes[3].x -= tt; cubes[3].y += tt * t;
			}
			else if(position == 1 || position == 3) {
				
				cubes[0].x--; cubes[0].y--;
				cubes[2].x++; cubes[2].y++;
				cubes[3].x -= tt * t; cubes[3].y -= tt;
			}
			
			break;
			
		case 2:
			// I
			if(position == 0 || position == 2) {
				
				cubes[0].x--; cubes[0].y++;
				cubes[2].x++; cubes[2].y--;
				cubes[3].x += 2; cubes[3].y -= 2;
			}
			else if(position == 1 || position == 3) {
				
				cubes[0].x++; cubes[0].y--;
				cubes[2].x--; cubes[2].y++;
				cubes[3].x -= 2; cubes[3].y += 2;
			}
			
			break;
			
		case 3:
			// L
			
			if(position == 0) {
				
				cubes[0].x++; cubes[0].y++;
				cubes[2].x--; cubes[2].y--;
				
				if(turn) cubes[3].x -= 2;
				else  	 cubes[3].y -= 2;
			}
			else if(position == 1) {
				
				cubes[0].x--; cubes[0].y--;
				cubes[2].x++; cubes[2].y++;
				
				if(turn) cubes[3].y -= 2;
				else	 cubes[3].x += 2;
			}
			else if(position == 2) {
				
				cubes[0].x++; cubes[0].y++;
				cubes[2].x--; cubes[2].y--;
				
				if(turn) cubes[3].x += 2;
				else	 cubes[3].y += 2;
			}
			else if(position == 3) {
				
				cubes[0].x--; cubes[0].y--;
				cubes[2].x++; cubes[2].y++;
				
				if(turn) cubes[3].y += 2;
				else	 cubes[3].x -= 2;
			}
			
			break;
			
		case 4:
			// J
			
			if(position == 0) {
				
				cubes[0].x++; cubes[0].y++;
				cubes[2].x--; cubes[2].y--;
				
				if(turn) cubes[3].y -= 2;
				else	 cubes[3].x += 2;
			}
			else if(position == 1) {
				
				cubes[0].x--; cubes[0].y--;
				cubes[2].x++; cubes[2].y++;
				
				if(turn) cubes[3].x += 2;
				else	 cubes[3].y += 2;
			}
			else if(position == 2) {
				
				cubes[0].x++; cubes[0].y++;
				cubes[2].x--; cubes[2].y--;
				
				if(turn) cubes[3].y += 2;
				else	 cubes[3].x -= 2;
			}
			else if(position == 3) {
				
				cubes[0].x--; cubes[0].y--;
				cubes[2].x++; cubes[2].y++;
				
				if(turn) cubes[3].x -= 2;
				else	 cubes[3].y -= 2;
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
				cubes[0].x--; cubes[0].y++;
				cubes[2].x++; cubes[2].y++;
				cubes[3].x += 2;
				break;
			case 1:
			case 3:
				//         0
				// 01  -> 21
				//  23	  3
				cubes[0].x++; cubes[0].y--;
				cubes[2].x--; cubes[2].y--;
				cubes[3].x -= 2;
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
				cubes[0].x++; cubes[0].y++;
				cubes[2].x--; cubes[2].y++;
				cubes[3].x -= 2;
				break;
			case 1:
			case 3:
				//         0
				//  10  -> 12
				// 32	  	3
				cubes[0].x--; cubes[0].y--;
				cubes[2].x++; cubes[2].y--;
				cubes[3].x += 2;
				break;
			}
			break;
		}
		
		position = (turn) ? ((position == 3) ? 0 : position + 1) : ((position == 0) ? 3 : position - 1);
	}

	public void drawPiece(Graphics graphics) {
		
		for(Cube cube : cubes) {
			
			cube.drawCube(graphics);
		}
	}
}
