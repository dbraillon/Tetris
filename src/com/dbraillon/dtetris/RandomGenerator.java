package com.dbraillon.dtetris;

import java.util.ArrayList;
import java.util.Random;


public class RandomGenerator {

	private ArrayList<Character> bag;
	
	public RandomGenerator() {
		
		bag = new ArrayList<Character>();
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
		Tetromino rPiece = new Tetromino(piece);
		
		bag.remove(index);
		
		return rPiece;
	}
}
