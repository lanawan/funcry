package com.amerticum.funcry.screens;

import com.amerticum.funcry.Constants;
import com.amerticum.funcry.model.Cat;
import com.amerticum.funcry.model.PussyCats;
import com.amerticum.funcry.model.TouchInfo;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Java-программист on 24.10.2016.
 */

public class CatPlayScreen implements Screen, InputProcessor {
    private Stage stage;
    private Cat cat;
    private TextureAtlas catAtlas;
    private Music rrr;
    private int defaultHeight;
    private int counter=0;
    private Game game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Vector3 touchPos;
    private Skin skin;

    public CatPlayScreen(Game game) {
        this.game = game;
        stage = new Stage(new ScreenViewport());
        catAtlas = new TextureAtlas("cats/fun/cat.pack");
        rrr = Gdx.audio.newMusic(Gdx.files.internal("cats/fun/rrr.mp3"));
        cat = new Cat(catAtlas,rrr,0,0,false);

        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch = new SpriteBatch();

        skin = new Skin();
        skin.addRegions(catAtlas);

        Gdx.input.setInputProcessor(stage);
    }
    @Override
    public void show(){

    }
    @Override
    public void resize(int width, int height) {
        // TODO Auto-generated method stub

    }

    @Override
    public void render(float delta) {
    }
    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
    @Override
    public void dispose() {
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
