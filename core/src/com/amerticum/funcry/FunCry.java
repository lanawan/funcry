package com.amerticum.funcry;

import com.amerticum.funcry.screens.MainMenu;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FunCry extends Game {
	private SpriteBatch batch;
	Game game;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		game = this;
		setScreen(new MainMenu(game));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}

}
