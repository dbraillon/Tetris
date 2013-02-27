import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.dbraillon.dtetris.views.MenuView;
import com.dbraillon.dtetris.views.PlayView;

public class Game extends BasicGame {

	// TODO: http://tetris.wikia.com/wiki/Tetris_Guideline
	public final int MENU_VIEW = 0;
	public final int PLAY_VIEW = 1;
	public final int PREFERENCES_VIEW = 2;
	
	private int  _selectedView;
	private MenuView _menuView;
	private PlayView _playView;

	
	public Game(String title) {
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
		
		_selectedView = 0;
		_menuView = new MenuView(gc.getWidth(), gc.getHeight());
		_playView = new PlayView(gc.getWidth(), gc.getHeight());
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		
		switch(_selectedView) {
		case MENU_VIEW:
			_menuView.render(g);
			break;
		case PLAY_VIEW:
			_playView.render(g);
			break;
		}
	}
	
	@Override
	public void update(GameContainer gc, int arg1) throws SlickException {
		
		switch(_selectedView) {
		case MENU_VIEW:
			_selectedView = _menuView.update(gc);
			if(_selectedView == PLAY_VIEW) _playView = new PlayView(gc.getWidth(), gc.getHeight());
			break;
		case PLAY_VIEW:
			_selectedView = _playView.update(gc);
			break;
		}
	}
	
	public static void main(String[] args) throws SlickException {
		
		AppGameContainer app = new AppGameContainer(new Game("Tétris2d"));
		app.setDisplayMode(600, 800, false);
		app.setTargetFrameRate(30);
		
		app.start();
	}
}
