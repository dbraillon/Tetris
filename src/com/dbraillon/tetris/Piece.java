package com.dbraillon.tetris;

import java.util.Random;

import org.newdawn.slick.Color;

public class Piece {

	public Cube[] pieces;
	public int piece;
	public int position;
	
	public Piece() {
		
		pieces = new Cube[4];
		
		Random r = new Random();
		int p = r.nextInt(7);
		
		piece = p;
		position = 0;
		switch(piece) {
		case 0:
			// O
			pieces[0] = new Cube(4,-1, new Color(255, 255, 0));
			pieces[1] = new Cube(4,0, new Color(255, 255, 0));
			pieces[2] = new Cube(5,-1, new Color(255, 255, 0));
			pieces[3] = new Cube(5,0, new Color(255, 255, 0));
			break;
		case 1:
			// T
			pieces[0] = new Cube(4,-2, new Color(0, 255, 255));
			pieces[1] = new Cube(4,-1, new Color(0, 255, 255));
			pieces[2] = new Cube(4,0, new Color(0, 255, 255));
			pieces[3] = new Cube(5,-1, new Color(0, 255, 255));
			break;
		case 2:
			// I
			pieces[0] = new Cube(5,-3, new Color(255, 0, 255));
			pieces[1] = new Cube(5,-2, new Color(255, 0, 255));
			pieces[2] = new Cube(5,-1, new Color(255, 0, 255));
			pieces[3] = new Cube(5,0, new Color(255, 0, 255));
			break;
		case 3:
			// L
			pieces[0] = new Cube(4,-2, new Color(255, 127, 0));
			pieces[1] = new Cube(4,-1, new Color(255, 127, 0));
			pieces[2] = new Cube(4,0, new Color(255, 127, 0));
			pieces[3] = new Cube(5,0, new Color(255, 127, 0));
			break;
		case 4:
			// J
			pieces[0] = new Cube(5,-2, new Color(0, 0, 255));
			pieces[1] = new Cube(5,-1, new Color(0, 0, 255));
			pieces[2] = new Cube(5,0, new Color(0, 0, 255));
			pieces[3] = new Cube(4,0, new Color(0, 0, 255));
			break;
		case 5:
			// Z
			pieces[0] = new Cube(5,-2, new Color(255, 0, 0));
			pieces[1] = new Cube(5,-1, new Color(255, 0, 0));
			pieces[2] = new Cube(4,-1, new Color(255, 0, 0));
			pieces[3] = new Cube(4,0, new Color(255, 0, 0));
			break;
		case 6:
			// S
			pieces[0] = new Cube(4,-2, new Color(0, 255, 0));
			pieces[1] = new Cube(4,-1, new Color(0, 255, 0));
			pieces[2] = new Cube(5,-1, new Color(0, 255, 0));
			pieces[3] = new Cube(5,0, new Color(0, 255, 0));
			break;
		}
	}
	
	public void fall() {
		
		for(int i = 0; i < pieces.length; i++) {
			
			pieces[i].fall();
		}
	}
	
	public void move(int move) {
		
		for(int i = 0; i < pieces.length; i++) {
			
			pieces[i].move(move);
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
				
				pieces[0].x++; pieces[0].y++;
				pieces[2].x--; pieces[2].y--;
				pieces[3].x -= tt; pieces[3].y += tt * t;
			}
			else if(position == 1 || position == 3) {
				
				pieces[0].x--; pieces[0].y--;
				pieces[2].x++; pieces[2].y++;
				pieces[3].x -= tt * t; pieces[3].y -= tt;
			}
			
			break;
			
		case 2:
			// I
			if(position == 0 || position == 2) {
				
				pieces[0].x--; pieces[0].y++;
				pieces[2].x++; pieces[2].y--;
				pieces[3].x += 2; pieces[3].y -= 2;
			}
			else if(position == 1 || position == 3) {
				
				pieces[0].x++; pieces[0].y--;
				pieces[2].x--; pieces[2].y++;
				pieces[3].x -= 2; pieces[3].y += 2;
			}
			
			break;
			
		case 3:
			// L
			
			if(position == 0) {
				
				pieces[0].x++; pieces[0].y++;
				pieces[2].x--; pieces[2].y--;
				
				if(turn) pieces[3].x -= 2;
				else  	 pieces[3].y -= 2;
			}
			else if(position == 1) {
				
				pieces[0].x--; pieces[0].y--;
				pieces[2].x++; pieces[2].y++;
				
				if(turn) pieces[3].y -= 2;
				else	 pieces[3].x += 2;
			}
			else if(position == 2) {
				
				pieces[0].x++; pieces[0].y++;
				pieces[2].x--; pieces[2].y--;
				
				if(turn) pieces[3].x += 2;
				else	 pieces[3].y += 2;
			}
			else if(position == 3) {
				
				pieces[0].x--; pieces[0].y--;
				pieces[2].x++; pieces[2].y++;
				
				if(turn) pieces[3].y += 2;
				else	 pieces[3].x -= 2;
			}
			
			break;
			
		case 4:
			// J
			
			if(position == 0) {
				
				pieces[0].x++; pieces[0].y++;
				pieces[2].x--; pieces[2].y--;
				
				if(turn) pieces[3].y -= 2;
				else	 pieces[3].x += 2;
			}
			else if(position == 1) {
				
				pieces[0].x--; pieces[0].y--;
				pieces[2].x++; pieces[2].y++;
				
				if(turn) pieces[3].x += 2;
				else	 pieces[3].y += 2;
			}
			else if(position == 2) {
				
				pieces[0].x++; pieces[0].y++;
				pieces[2].x--; pieces[2].y--;
				
				if(turn) pieces[3].y += 2;
				else	 pieces[3].x -= 2;
			}
			else if(position == 3) {
				
				pieces[0].x--; pieces[0].y--;
				pieces[2].x++; pieces[2].y++;
				
				if(turn) pieces[3].x -= 2;
				else	 pieces[3].y -= 2;
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
				pieces[0].x--; pieces[0].y++;
				pieces[2].x++; pieces[2].y++;
				pieces[3].x += 2;
				break;
			case 1:
			case 3:
				//         0
				// 01  -> 21
				//  23	  3
				pieces[0].x++; pieces[0].y--;
				pieces[2].x--; pieces[2].y--;
				pieces[3].x -= 2;
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
				pieces[0].x++; pieces[0].y++;
				pieces[2].x--; pieces[2].y++;
				pieces[3].x -= 2;
				break;
			case 1:
			case 3:
				//         0
				//  10  -> 12
				// 32	  	3
				pieces[0].x--; pieces[0].y--;
				pieces[2].x++; pieces[2].y--;
				pieces[3].x += 2;
				break;
			}
			break;
		}
		
		position = (turn) ? ((position == 3) ? 0 : position + 1) : ((position == 0) ? 3 : position - 1);
	}
}
