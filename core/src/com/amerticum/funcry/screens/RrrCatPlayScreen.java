package com.amerticum.funcry.screens;

import com.amerticum.funcry.Constants;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;


/**
 * Created by Java-программист on 24.10.2016.
 */

public class RrrCatPlayScreen implements Screen, InputProcessor {
    private Game game;
    private TextureAtlas rrrCatAtlas;
    private Music rrr;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Animation rrrCatAnimation;
    private float elapsedTime = 0;
    private float pleasureLvl = 0.0f;
    private float defaultWidth, defaultHeight;

    public RrrCatPlayScreen(Game game) {
        this.game = game;
        batch = new SpriteBatch();
        rrrCatAtlas = new TextureAtlas("cats/fun/cat.pack");
        rrrCatAnimation = new Animation(Constants.RRR_ANIMATION_SPEED,rrrCatAtlas.getRegions());

        rrr = Gdx.audio.newMusic(Gdx.files.internal("cats/fun/rrr.mp3"));
        rrr.setLooping(true);
        rrr.setVolume(0.1f);
        rrr.play();

        defaultWidth = rrrCatAtlas.findRegion("cat1").getRegionWidth();
        defaultHeight = rrrCatAtlas.findRegion("cat1").getRegionHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, defaultWidth, defaultHeight);

        Gdx.input.setInputProcessor(this);
        Gdx.input.setCatchBackKey(true);
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
        pleasureLvl = pleasureLvl - delta/Constants.RRR__PLEASURE_DOWN_VELOCITY > 0f ? pleasureLvl - delta/Constants.RRR__PLEASURE_DOWN_VELOCITY : 0f;
        rrr.setVolume(pleasureLvl);
        elapsedTime += delta;
        if(elapsedTime>=Constants.MAX_FRAMES){ elapsedTime = 0; }

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(rrrCatAnimation.getKeyFrame(elapsedTime, true), 0, 0);
        batch.end();
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
        batch.dispose();
        rrrCatAtlas.dispose();
        rrr.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Keys.BACK) {
            dispose();
            game.setScreen(new MainMenu(game));
            return true;
        }
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
        if(pleasureLvl > 1f){
            pleasureLvl = 1f;
        }
        if(pleasureLvl < 1f){
            pleasureLvl += 1f/Constants.RRR__MAX_PLEASURE_LVL;
        }
        //System.out.println(pleasureLvl);
        return true;
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
