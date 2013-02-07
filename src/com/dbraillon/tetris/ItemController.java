package com.dbraillon.tetris;

import java.util.ArrayList;
import java.util.Vector;

public class ItemController {

	public int xMap, yMap;
	
	public ItemController(int xMap, int yMap) {
		
		this.xMap = xMap;
		this.yMap = yMap;
	}
	
	public Piece create(ArrayList<Cube> items) {
		
		return new Piece();
	}
	
	public boolean verify(ArrayList<Cube> items, Piece piece) {
		
		for(Cube cItem : items) {
			
			for(Cube oItem : piece.pieces) {
				
				if(cItem.x == oItem.x && cItem.y == oItem.y) {
					
					return false;
				}
			}
		}
		
		return true;
	}
	
	public boolean fall(Cube cItem, Vector<Cube> items) {
		
		boolean canFall = true;
		
		for(Cube oItem : items) {
			
			if(cItem.y + 1 == oItem.y && cItem.x == oItem.x) {
				
				canFall = false;
			}
		}
		
		if(cItem.y + 1 >= yMap) {
			
			canFall = false;
		}
		
		if(canFall) {
			
			cItem.fall();
			return true;
		}
		
		return false;
	}
	
	public boolean fall(Piece cPiece, ArrayList<Cube> items) {
		
		boolean canFall = true;
		
		for(Cube oItem : items) {
			
			for(Cube cItem : cPiece.pieces) {
				
				if(cItem.y + 1 == oItem.y && cItem.x == oItem.x) {
					
					canFall = false;
				}
			}
		}
		
		for(Cube cItem : cPiece.pieces) {
			
			if(cItem.y + 1 >= yMap) {
				
				canFall = false;
			}
		}
		
		if(canFall) {
			
			cPiece.fall();
			return true;
		}
		
		return false;
	}
	
	public boolean move(int move, Cube cItem, Vector<Cube> items) {
		
		boolean canMove = true;
		
		for(Cube oItem : items) {
			
			if(cItem.x + move == oItem.x && cItem.y == oItem.y) {
				
				canMove = false;
			}
		}
		
		if(cItem.x + move >= xMap || cItem.x + move < 0) {
			
			canMove = false;
		}
		
		if(canMove) {
			
			cItem.move(move);
			return true;
		}
		
		return false;
	}
	
	public boolean move(int move, Piece cPiece, ArrayList<Cube> items) {
		
		boolean canMove = true;
		
		for(Cube oItem : items) {
			
			for(Cube cItem : cPiece.pieces) {
				
				if(cItem.x + move == oItem.x && cItem.y == oItem.y) {
					
					canMove = false;
				}
			}
		}
		
		for(Cube cItem : cPiece.pieces) {
			
			if(cItem.x + move >= xMap || cItem.x + move < 0) {
				
				canMove = false;
			}
		}
		
		if(canMove) {
			
			cPiece.move(move);
			return true;
		}
		
		return false;
	}
	
	public boolean hardFall(Cube cItem, Vector<Cube> items) {
		
		int yFree = 0;
		
		for(int y = cItem.y; y < yMap; y++) {
			
			for(Cube oItem : items) {
				
				if(oItem.y == y && oItem.x == cItem.x) {
					
					yFree = y - 1;
					break;
				}
			}
			
			if(yFree != 0) {
				
				break;
			}
		}

		if(yFree == 0) yFree = yMap - 1;
		cItem.y = yFree;
		
		return false;
	}
	
	public boolean hardFall(Piece cPiece, ArrayList<Cube> items) {
		
		while(fall(cPiece, items));
		return false;
	}
	
	public boolean removeLine(Cube cItem, ArrayList<Cube> items) {
		
		int n = 0;
		for(Cube oItem : items) {
			
			if(cItem.y == oItem.y) {
				
				n++;
			}
		}
		
		if(n == xMap) {
			
			ArrayList<Cube> indexes = new ArrayList<Cube>();
			
			for(Cube oItem : items) {
				
				if(cItem.y == oItem.y) {
					
					indexes.add(oItem);
				}
				else if(cItem.y > oItem.y) {
					
					oItem.y++;
				}
			}
			
			for(Cube i : indexes) {
				
				items.remove(i);
			}
			
			items.remove(cItem);
			return true;
		}
		
		return false;
	}
	
	public int removeLine(Piece cPiece, ArrayList<Cube> items) {
		
		ArrayList<Integer> ys = new ArrayList<Integer>();
		
		for(Cube cItem : cPiece.pieces) {
			
			if(!ys.contains(cItem.y)) {
				
				ys.add(cItem.y);
			}
		}
		
		int s = 0;
		for(Integer y : ys) {
			
			int n = 0;
			for(Cube cItem : items) {
				
				if(cItem.y == y) {
					
					n++;
				}
			}
			
			if(n == xMap) {
				
				ArrayList<Cube> indexes = new ArrayList<Cube>();
				
				for(Cube oItem : items) {
					
					if(y == oItem.y) {
						
						indexes.add(oItem);
					}
					else if(y > oItem.y) {
						
						oItem.y++;
					}
				}
				
				for(Cube i : indexes) {
					
					items.remove(i);
				}
				
				s++;
			}
		}
		
		return s;
	}

	public boolean turn(boolean turn, Piece cPiece, ArrayList<Cube> items) {
		
		boolean canTurn = true;
		
		cPiece.turn(turn);
		for(Cube oItem : items) {
			
			for(Cube cItem : cPiece.pieces) {
				
				if(cItem.x == oItem.x && cItem.y == oItem.y) {
					
					canTurn = false;
				}
			}
		}
		
		for(Cube cItem : cPiece.pieces) {
			
			if(cItem.x >= xMap || cItem.x < 0 || cItem.y >= yMap) {
				
				canTurn = false;
			}
		}
		
		if(!canTurn) {
			
			cPiece.turn(!turn);
			return false;
		}
		
		return true;
	}
}
