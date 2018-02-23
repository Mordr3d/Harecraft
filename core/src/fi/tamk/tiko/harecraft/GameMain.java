package fi.tamk.tiko.harecraft;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameMain extends Game {
	public SpriteBatch batch;
	
	@Override
	public void create () {
	    setScreen(new GameScreen());
		batch = new SpriteBatch();
	}
	//this method is teh render methodk
	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
