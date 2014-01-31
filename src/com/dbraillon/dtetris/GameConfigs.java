package com.dbraillon.dtetris;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class GameConfigs {

	// Game settings
	public final static String gameTitle = "dTetris";
	public final static int gameSquareSize = 32;
	
	// Windows settings
	public final static int windowsWidth = 600;
	public final static int windowsHeight = 800;
	public final static String windowsName = gameTitle;
	public final static int windowsFrameRate = 30;
	
	// Playfield settings
	public final static int playfieldHeight = 22;
	public final static int playfieldWidth = 10;
	
	// Image settings
	public final Image blueSquare;
	public final Image cyanSquare;
	public final Image graySquare;
	public final Image greenSquare;
	public final Image orangeSquare;
	public final Image purpleSquare;
	public final Image redSquare;
	public final Image yellowSquare;
	
	
	private static GameConfigs instance = null;
	
	private GameConfigs() throws SlickException {
		
		blueSquare = new Image("assets/blue_square.png");
		cyanSquare = new Image("assets/cyan_square.png");
		graySquare = new Image("assets/gray_square.png");
		greenSquare = new Image("assets/green_square.png");
		orangeSquare = new Image("assets/orange_square.png");
		purpleSquare = new Image("assets/purple_square.png");
		redSquare = new Image("assets/red_square.png");
		yellowSquare = new Image("assets/yellow_square.png");
	}
	
	public static GameConfigs getInstance() {
		
		if(instance == null) {
			
			try {
				instance = new GameConfigs();
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
		
		return instance;
	}
}
