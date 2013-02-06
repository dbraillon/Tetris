package com.dbraillon.tetris;

import java.util.Vector;

public class ItemController {

	public int xMap, yMap;
	
	public ItemController(int xMap, int yMap) {
		
		this.xMap = xMap;
		this.yMap = yMap;
	}
	
	public Piece create(Vector<Item> items) {
		
		Piece cPiece = new Piece();
		
		for(Item cItem : items) {
			
			for(Item oItem : cPiece.pieces) {
				
				if(cItem.x == oItem.x && cItem.y == oItem.y) {
					
					return null;
				}
			}
		}
		
		return cPiece;
	}
	
	public boolean fall(Item cItem, Vector<Item> items) {
		
		boolean canFall = true;
		
		for(Item oItem : items) {
			
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
	
	public boolean fall(Piece cPiece, Vector<Item> items) {
		
		boolean canFall = true;
		
		for(Item oItem : items) {
			
			for(Item cItem : cPiece.pieces) {
				
				if(cItem.y + 1 == oItem.y && cItem.x == oItem.x) {
					
					canFall = false;
				}
			}
		}
		
		for(Item cItem : cPiece.pieces) {
			
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
	
	public boolean move(int move, Item cItem, Vector<Item> items) {
		
		boolean canMove = true;
		
		for(Item oItem : items) {
			
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
	
	public boolean move(int move, Piece cPiece, Vector<Item> items) {
		
		boolean canMove = true;
		
		for(Item oItem : items) {
			
			for(Item cItem : cPiece.pieces) {
				
				if(cItem.x + move == oItem.x && cItem.y == oItem.y) {
					
					canMove = false;
				}
			}
		}
		
		for(Item cItem : cPiece.pieces) {
			
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
	
	public boolean hardFall(Item cItem, Vector<Item> items) {
		
		int yFree = 0;
		
		for(int y = cItem.y; y < yMap; y++) {
			
			for(Item oItem : items) {
				
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
	
	public boolean hardFall(Piece cPiece, Vector<Item> items) {
		
		while(fall(cPiece, items));
		return false;
	}
	
	public boolean removeLine(Item cItem, Vector<Item> items) {
		
		int n = 0;
		for(Item oItem : items) {
			
			if(cItem.y == oItem.y) {
				
				n++;
			}
		}
		
		if(n == xMap) {
			
			Vector<Item> indexes = new Vector<Item>();
			
			for(Item oItem : items) {
				
				if(cItem.y == oItem.y) {
					
					indexes.add(oItem);
				}
				else if(cItem.y > oItem.y) {
					
					oItem.y++;
				}
			}
			
			for(Item i : indexes) {
				
				items.removeElement(i);
			}
			
			items.removeElement(cItem);
			return true;
		}
		
		return false;
	}
	
	public int removeLine(Piece cPiece, Vector<Item> items) {
		
		Vector<Integer> ys = new Vector<Integer>();
		
		for(Item cItem : cPiece.pieces) {
			
			if(!ys.contains(cItem.y)) {
				
				ys.add(cItem.y);
			}
		}
		
		int s = 0;
		for(Integer y : ys) {
			
			int n = 0;
			for(Item cItem : items) {
				
				if(cItem.y == y) {
					
					n++;
				}
			}
			
			if(n == xMap) {
				
				Vector<Item> indexes = new Vector<Item>();
				
				for(Item oItem : items) {
					
					if(y == oItem.y) {
						
						indexes.add(oItem);
					}
					else if(y > oItem.y) {
						
						oItem.y++;
					}
				}
				
				for(Item i : indexes) {
					
					items.removeElement(i);
				}
				
				s++;
			}
		}
		
		return s;
	}

	public boolean turn(boolean turn, Piece cPiece, Vector<Item> items) {
		
		boolean canTurn = true;
		
		cPiece.turn(turn);
		for(Item oItem : items) {
			
			for(Item cItem : cPiece.pieces) {
				
				if(cItem.x == oItem.x && cItem.y == oItem.y) {
					
					canTurn = false;
				}
			}
		}
		
		for(Item cItem : cPiece.pieces) {
			
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
