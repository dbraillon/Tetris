package com.dbraillon.dtetris.views;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;


public interface View {

	public void init(GameContainer gc);
	public int 	update(GameContainer gc);
	public void render(Graphics g);
}
