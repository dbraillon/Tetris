package com.dbraillon.dtetris.views;

import java.util.concurrent.Callable;

import org.newdawn.slick.Color;

import com.dbraillon.dbgraphics.Depth;
import com.dbraillon.dbgraphics.Point;
import com.dbraillon.dbgraphics.Screen;
import com.dbraillon.dbgraphics.componants.ButtonRenderItem;
import com.dbraillon.dbgraphics.componants.StringRenderItem;
import com.dbraillon.dtetris.GameConfigs;

public class MenuScreen extends Screen {

	public MenuScreen() {
		super();
		
		addItem(new StringRenderItem(new Point(100, 100), GameConfigs.gameTitle));
		addItem(new ButtonRenderItem(new Point(100, 700), Depth.Middle, new Color(255, 255, 255), "Play", new Callable<Void>() {
			@Override
			public Void call() throws Exception {
				
				getNavigator().pushScreen(new PlayScreen());
				return null;
			}
		}));
		addItem(new ButtonRenderItem(new Point(100, 720), Depth.Middle, new Color(255, 255, 255), "Quit", new Callable<Void>() {
			@Override
			public Void call() throws Exception {

				getNavigator().popScreen(MenuScreen.this);
				return null;
			}
		}));
	}
}
