package com.dbraillon.dtetris;

import java.util.ArrayList;
import java.util.Random;

import com.dbraillon.dbgraphics.Point;


public class RandomGenerator {

	private ArrayList<Character> bag;
	private Point playfieldPosition;
	
	public RandomGenerator(Point playfieldPosition) {
		
		bag = new ArrayList<Character>();
		this.playfieldPosition = playfieldPosition;
		fillBag();
	}
	
	private void fillBag() {
		
		if(bag.size() == 0) {
		
			bag.add(new Character('I'));
			bag.add(new Character('J'));
			bag.add(new Character('L'));
			bag.add(new Character('O'));
			bag.add(new Character('S'));
			bag.add(new Character('T'));
			bag.add(new Character('Z'));
		}
	}
	
	public Tetromino nextPiece() {
		
		if(bag.size() == 0) {
			
			fillBag();
		}
		
		Random r = new Random();
		int index = r.nextInt(bag.size());
		char piece = bag.get(index);
		Tetromino rPiece = new Tetromino(piece, playfieldPosition);
		
		bag.remove(index);
		
		return rPiece;
	}
}
